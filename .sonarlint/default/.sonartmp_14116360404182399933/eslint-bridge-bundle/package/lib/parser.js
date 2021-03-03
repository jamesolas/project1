"use strict";
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (Object.hasOwnProperty.call(mod, k)) result[k] = mod[k];
    result["default"] = mod;
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
/*
 * SonarQube JavaScript Plugin
 * Copyright (C) 2011-2020 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
const espree = __importStar(require("espree"));
const babel = __importStar(require("babel-eslint"));
const eslint_1 = require("eslint");
const VueJS = __importStar(require("vue-eslint-parser"));
const semver = __importStar(require("semver"));
const typescript_1 = require("typescript");
const tsParser = __importStar(require("@typescript-eslint/parser"));
const context_1 = require("./context");
// this value is taken from typescript-estree
// still we might consider extending this range
// if everything which we need is working on older/newer versions
const TYPESCRIPT_MINIMUM_VERSION = '3.3.1';
// next released version is 4.0.0, we need version which is above current 3.9.x and below 4.0.0
const TYPESCRIPT_MAXIMUM_VERSION = '3.10.0';
exports.PARSER_CONFIG_MODULE = {
    tokens: true,
    comment: true,
    loc: true,
    range: true,
    ecmaVersion: 2018,
    sourceType: 'module',
    codeFrame: false,
    ecmaFeatures: {
        jsx: true,
        globalReturn: true,
        legacyDecorators: true,
    },
};
// 'script' source type forces not strict
exports.PARSER_CONFIG_SCRIPT = {
    ...exports.PARSER_CONFIG_MODULE,
    sourceType: 'script',
};
function parseJavaScriptSourceFile(fileContent, filePath, tsConfigs) {
    const context = context_1.getContext();
    const shouldUseTypeScriptParserForJS = context ? context.shouldUseTypeScriptParserForJS : true;
    if (shouldUseTypeScriptParserForJS) {
        const parsed = parseTypeScriptSourceFile(fileContent, filePath, tsConfigs);
        if (parsed instanceof eslint_1.SourceCode) {
            return parsed;
        }
        console.log(`DEBUG Failed to parse ${filePath} with TypeScript compiler: ${parsed.message}`);
    }
    let parseFunctions = [espree.parse, babel.parseForESLint];
    if (fileContent.includes('@flow')) {
        parseFunctions = [babel.parseForESLint];
    }
    let exceptionToReport = null;
    for (const parseFunction of parseFunctions) {
        for (const config of [exports.PARSER_CONFIG_MODULE, exports.PARSER_CONFIG_SCRIPT]) {
            const result = parse(parseFunction, config, fileContent);
            if (result instanceof eslint_1.SourceCode) {
                return result;
            }
            else if (!exceptionToReport) {
                exceptionToReport = result;
            }
        }
    }
    // if we reach this point, we are sure that "exceptionToReport" is defined
    return {
        line: exceptionToReport.lineNumber,
        message: exceptionToReport.message,
        code: ParseExceptionCode.Parsing,
    };
}
exports.parseJavaScriptSourceFile = parseJavaScriptSourceFile;
let typescriptVersionLogged = false;
function parseTypeScriptSourceFile(fileContent, filePath, tsConfigs) {
    try {
        if (!typescriptVersionLogged) {
            console.log(`Version of TypeScript used during analysis: ${typescript_1.version}`);
            typescriptVersionLogged = true;
        }
        checkTypeScriptVersionCompatibility(typescript_1.version);
        const result = tsParser.parseForESLint(fileContent, {
            ...exports.PARSER_CONFIG_MODULE,
            filePath,
            project: tsConfigs,
        });
        return new eslint_1.SourceCode({
            ...result,
            parserServices: result.services,
            text: fileContent,
        });
    }
    catch (exception) {
        return {
            line: exception.lineNumber,
            message: exception.message,
            code: parseExceptionCodeOf(exception.message),
        };
    }
}
exports.parseTypeScriptSourceFile = parseTypeScriptSourceFile;
let reportedNewerTypeScriptVersion = false;
function resetReportedNewerTypeScriptVersion() {
    reportedNewerTypeScriptVersion = false;
}
exports.resetReportedNewerTypeScriptVersion = resetReportedNewerTypeScriptVersion;
// exported for testing
function checkTypeScriptVersionCompatibility(currentVersion) {
    if (semver.gt(currentVersion, TYPESCRIPT_MAXIMUM_VERSION) && !reportedNewerTypeScriptVersion) {
        reportedNewerTypeScriptVersion = true;
        console.log(`WARN You are using version of TypeScript ${currentVersion} which is not officially supported; ` +
            `supported versions >=${TYPESCRIPT_MINIMUM_VERSION} <${TYPESCRIPT_MAXIMUM_VERSION}`);
    }
    else if (semver.lt(currentVersion, TYPESCRIPT_MINIMUM_VERSION)) {
        throw {
            message: `You are using version of TypeScript ${currentVersion} which is not supported; supported versions >=${TYPESCRIPT_MINIMUM_VERSION}`,
        };
    }
}
exports.checkTypeScriptVersionCompatibility = checkTypeScriptVersionCompatibility;
function unloadTypeScriptEslint() {
    tsParser.clearCaches();
}
exports.unloadTypeScriptEslint = unloadTypeScriptEslint;
function parseVueSourceFile(fileContent) {
    let exceptionToReport = null;
    // setting parser to be able to parse more code (by default `espree` is used by vue parser)
    const vueModuleConfig = { ...exports.PARSER_CONFIG_MODULE, parser: 'babel-eslint' };
    for (const config of [vueModuleConfig, exports.PARSER_CONFIG_SCRIPT]) {
        try {
            const result = VueJS.parseForESLint(fileContent, config);
            return new eslint_1.SourceCode(fileContent, result.ast);
        }
        catch (exception) {
            exceptionToReport = exception;
        }
    }
    // if we reach this point, we are sure that "exceptionToReport" is defined
    return {
        line: exceptionToReport.lineNumber,
        message: exceptionToReport.message,
        code: ParseExceptionCode.Parsing,
    };
}
exports.parseVueSourceFile = parseVueSourceFile;
function parse(parse, config, fileContent) {
    try {
        const result = parse(fileContent, config);
        if (result.ast) {
            return new eslint_1.SourceCode({ text: fileContent, ...result });
        }
        else {
            return new eslint_1.SourceCode(fileContent, result);
        }
    }
    catch (exception) {
        return exception;
    }
}
exports.parse = parse;
var ParseExceptionCode;
(function (ParseExceptionCode) {
    ParseExceptionCode["Parsing"] = "PARSING";
    ParseExceptionCode["MissingTypeScript"] = "MISSING_TYPESCRIPT";
    ParseExceptionCode["UnsupportedTypeScript"] = "UNSUPPORTED_TYPESCRIPT";
    ParseExceptionCode["FailingTypeScript"] = "FAILING_TYPESCRIPT";
    ParseExceptionCode["GeneralError"] = "GENERAL_ERROR";
})(ParseExceptionCode = exports.ParseExceptionCode || (exports.ParseExceptionCode = {}));
// exported for testing
function parseExceptionCodeOf(exceptionMsg) {
    if (exceptionMsg.startsWith("Cannot find module 'typescript'")) {
        return ParseExceptionCode.MissingTypeScript;
    }
    else if (exceptionMsg.startsWith('You are using version of TypeScript')) {
        return ParseExceptionCode.UnsupportedTypeScript;
    }
    else if (exceptionMsg.startsWith('Debug Failure')) {
        return ParseExceptionCode.FailingTypeScript;
    }
    else {
        return ParseExceptionCode.Parsing;
    }
}
exports.parseExceptionCodeOf = parseExceptionCodeOf;
//# sourceMappingURL=parser.js.map
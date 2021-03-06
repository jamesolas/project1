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
const path = __importStar(require("path"));
const parser_1 = require("./parser");
const ts = __importStar(require("typescript"));
function getFilesForTsConfig(tsConfig, parseConfigHost = {
    useCaseSensitiveFileNames: true,
    readDirectory: ts.sys.readDirectory,
    fileExists: ts.sys.fileExists,
    readFile: ts.sys.readFile,
}) {
    const config = ts.readConfigFile(tsConfig, parseConfigHost.readFile);
    if (config.error !== undefined) {
        console.error(`Failed to parse tsconfig: ${tsConfig} (${config.error.messageText})`);
        return { error: diagnosticToString(config.error) };
    }
    const parsed = ts.parseJsonConfigFileContent(config.config, parseConfigHost, path.resolve(path.dirname(tsConfig)), {
        noEmit: true,
    });
    if (parsed.errors.length > 0) {
        let error = '';
        parsed.errors.forEach(d => {
            error += diagnosticToString(d);
        });
        return { error, errorCode: parser_1.ParseExceptionCode.GeneralError };
    }
    const projectReferences = parsed.projectReferences
        ? parsed.projectReferences.map(p => p.path)
        : [];
    return { files: parsed.fileNames, projectReferences };
}
exports.getFilesForTsConfig = getFilesForTsConfig;
function diagnosticToString(diagnostic) {
    if (typeof diagnostic.messageText === 'string') {
        return diagnostic.messageText;
    }
    else {
        return diagnostic.messageText.messageText;
    }
}
//# sourceMappingURL=tsconfig.js.map
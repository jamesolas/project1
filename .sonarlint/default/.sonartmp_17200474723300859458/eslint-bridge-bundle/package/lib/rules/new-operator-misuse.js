"use strict";
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
// https://jira.sonarsource.com/browse/RSPEC-2999
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (Object.hasOwnProperty.call(mod, k)) result[k] = mod[k];
    result["default"] = mod;
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
const isRequiredParserServices_1 = require("../utils/isRequiredParserServices");
const ts = __importStar(require("typescript"));
const utils_1 = require("./utils");
exports.rule = {
    meta: {
        schema: [
            { type: 'object' },
            {
                // internal parameter for rules having secondary locations
                enum: ['sonar-runtime'],
            },
        ],
    },
    create(context) {
        const { considerJSDoc } = context.options[0];
        const services = context.parserServices;
        if (!isRequiredParserServices_1.isRequiredParserServices(services)) {
            return {};
        }
        return {
            'NewExpression[callee.type!="ThisExpression"]': (node) => {
                const { callee } = node;
                const type = utils_1.getTypeFromTreeNode(callee, services);
                const signature = utils_1.getSignatureFromCallee(node, services);
                if (!isInstantiable(type, signature, considerJSDoc) && !isAny(type)) {
                    const functionToken = context
                        .getSourceCode()
                        .getFirstToken(node, token => token.type === 'Keyword' && token.value === 'function');
                    const newToken = context
                        .getSourceCode()
                        .getFirstToken(node, token => token.type === 'Keyword' && token.value === 'new');
                    const text = isFunction(type) ? 'this function' : context.getSourceCode().getText(callee);
                    const loc = callee.type === 'FunctionExpression' ? functionToken.loc : callee.loc;
                    context.report({
                        message: utils_1.toEncodedMessage(`Replace ${text} with a constructor function.`, [newToken]),
                        loc,
                    });
                }
            },
        };
    },
};
function isInstantiable(type, signature, considerJSDoc) {
    return (isClass(type) ||
        isModule(type) ||
        isConstructor(type, signature, considerJSDoc) ||
        (type.isUnionOrIntersection() &&
            type.types.some(tp => isInstantiable(tp, signature, considerJSDoc))));
}
function isClass(type) {
    return (type.symbol &&
        ((type.symbol.flags & ts.SymbolFlags.Class) !== 0 ||
            (type.symbol.flags & ts.SymbolFlags.Type) !== 0));
}
function isModule(type) {
    return type.symbol && (type.symbol.flags & ts.SymbolFlags.Module) !== 0;
}
function isFunction(type) {
    return type.symbol && (type.symbol.flags & ts.SymbolFlags.Function) !== 0;
}
function isConstructor(type, signature, considerJSDoc) {
    return isFunction(type) && (!considerJSDoc || hasJSDocAnnotation(signature));
}
function hasJSDocAnnotation(signature) {
    return (signature !== undefined &&
        signature.getJsDocTags().some(tag => ['constructor', 'class'].includes(tag.name)));
}
function isAny(type) {
    return type.flags === ts.TypeFlags.Any;
}
//# sourceMappingURL=new-operator-misuse.js.map
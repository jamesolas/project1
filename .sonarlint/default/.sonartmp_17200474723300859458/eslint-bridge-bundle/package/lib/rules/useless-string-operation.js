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
// https://jira.sonarsource.com/browse/RSPEC-1154
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
    create(context) {
        const services = context.parserServices;
        if (!isRequiredParserServices_1.isRequiredParserServices(services)) {
            return {};
        }
        function isString(node) {
            const type = utils_1.getTypeFromTreeNode(node, services);
            return (type.flags & ts.TypeFlags.StringLike) !== 0;
        }
        function getVariable(node) {
            let variable = context.getSourceCode().getText(node);
            if (variable.length > 30) {
                variable = 'String';
            }
            return variable;
        }
        return {
            'ExpressionStatement > CallExpression[callee.type="MemberExpression"]': (node) => {
                const { object, property } = node
                    .callee;
                if (isString(object) && property.type === 'Identifier') {
                    context.report({
                        message: `${getVariable(object)} is an immutable object; you must either store or return the result of the operation.`,
                        node: property,
                    });
                }
            },
        };
    },
};
//# sourceMappingURL=useless-string-operation.js.map
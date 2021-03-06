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
// https://jira.sonarsource.com/browse/RSPEC-3403
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (Object.hasOwnProperty.call(mod, k)) result[k] = mod[k];
    result["default"] = mod;
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
const ts = __importStar(require("typescript"));
const isRequiredParserServices_1 = require("../utils/isRequiredParserServices");
const utils_1 = require("./utils");
exports.rule = {
    meta: {
        schema: [
            {
                // internal parameter for rules having secondary locations
                enum: ['sonar-runtime'],
            },
        ],
    },
    create(context) {
        const services = context.parserServices;
        if (!isRequiredParserServices_1.isRequiredParserServices(services)) {
            return {};
        }
        function isSameSymbol(s, t) {
            return s.symbol && t.symbol && s.symbol.name === t.symbol.name;
        }
        function isSubType(s, t) {
            return ((s.flags & t.flags) !== 0 ||
                (t.isUnionOrIntersection() && t.types.some(tp => isSubType(s, tp))));
        }
        function isAny(type) {
            return type.flags === ts.TypeFlags.Any;
        }
        function isUndefinedOrNull(type) {
            return type.flags === ts.TypeFlags.Null || type.flags === ts.TypeFlags.Undefined;
        }
        function isThis(node) {
            return node.type === 'ThisExpression';
        }
        function haveDissimilarTypes(lhs, rhs) {
            const { getBaseTypeOfLiteralType } = services.program.getTypeChecker();
            const lhsType = getBaseTypeOfLiteralType(utils_1.getTypeFromTreeNode(lhs, services));
            const rhsType = getBaseTypeOfLiteralType(utils_1.getTypeFromTreeNode(rhs, services));
            return (!isSameSymbol(lhsType, rhsType) &&
                !isSubType(lhsType, rhsType) &&
                !isSubType(rhsType, lhsType) &&
                !isAny(lhsType) &&
                !isAny(rhsType) &&
                !isUndefinedOrNull(lhsType) &&
                !isUndefinedOrNull(rhsType) &&
                !isThis(lhs) &&
                !isThis(rhs));
        }
        return {
            BinaryExpression: (node) => {
                const { left, operator, right } = node;
                if (['===', '!=='].includes(operator) && haveDissimilarTypes(left, right)) {
                    const [actual, expected, outcome] = operator === '===' ? ['===', '==', 'false'] : ['!==', '!=', 'true'];
                    context.report({
                        message: utils_1.toEncodedMessage(`Remove this "${actual}" check; it will always be ${outcome}. Did you mean to use "${expected}"?`, [left, right]),
                        loc: context
                            .getSourceCode()
                            .getTokensBetween(left, right)
                            .find(token => token.type === 'Punctuator' && token.value === operator).loc,
                    });
                }
            },
        };
    },
};
//# sourceMappingURL=different-types-comparison.js.map
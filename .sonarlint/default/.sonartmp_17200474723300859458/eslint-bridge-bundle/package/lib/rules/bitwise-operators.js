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
// https://jira.sonarsource.com/browse/RSPEC-1529
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (Object.hasOwnProperty.call(mod, k)) result[k] = mod[k];
    result["default"] = mod;
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
const ts = __importStar(require("typescript"));
const utils_1 = require("./utils");
const BITWISE_AND_OR = ['&', '|'];
const BITWISE_OPERATORS = [
    '&',
    '|',
    '^',
    '~',
    '<<',
    '>>',
    '>>>',
    '&=',
    '|=',
    '^=',
    '<<=',
    '>>=',
    '>>>=',
];
exports.rule = {
    create(context) {
        const isNumeric = getNumericTypeChecker(context);
        let lonelyBitwiseAndOr = null;
        let lonelyBitwiseAndOrAncestors = [];
        let fileContainsSeveralBitwiseOperations = false;
        return {
            BinaryExpression(node) {
                const expression = node;
                if (!lonelyBitwiseAndOr &&
                    BITWISE_AND_OR.includes(expression.operator) &&
                    !isNumeric(expression.left) &&
                    !isNumeric(expression.right)) {
                    lonelyBitwiseAndOr = expression;
                    lonelyBitwiseAndOrAncestors = [...context.getAncestors()];
                }
                else if (BITWISE_OPERATORS.includes(expression.operator)) {
                    fileContainsSeveralBitwiseOperations = true;
                }
            },
            'Program:exit': function () {
                if (!fileContainsSeveralBitwiseOperations &&
                    lonelyBitwiseAndOr &&
                    insideCondition(lonelyBitwiseAndOr, lonelyBitwiseAndOrAncestors)) {
                    const op = lonelyBitwiseAndOr.operator;
                    const operatorToken = context.getSourceCode().getTokenAfter(lonelyBitwiseAndOr.left);
                    if (operatorToken) {
                        context.report({
                            loc: operatorToken.loc,
                            message: `Review this use of bitwise "${op}" operator; conditional "${op}${op}" might have been intended.`,
                        });
                    }
                }
            },
        };
    },
};
function insideCondition(node, ancestors) {
    let child = node;
    for (let i = ancestors.length - 1; i >= 0; i--) {
        const parent = ancestors[i];
        if (parent.type === 'IfStatement' ||
            parent.type === 'ForStatement' ||
            parent.type === 'WhileStatement' ||
            parent.type === 'DoWhileStatement' ||
            parent.type === 'ConditionalExpression') {
            return parent.test === child;
        }
        child = parent;
    }
    return false;
}
function getNumericTypeChecker(context) {
    const services = context.parserServices;
    if (!!services && !!services.program && !!services.esTreeNodeToTSNodeMap) {
        return (node) => isNumericType(utils_1.getTypeFromTreeNode(node, services));
    }
    else {
        const numericTypes = ['number', 'bigint'];
        return (node) => node.type === 'Literal' ? numericTypes.includes(typeof node.value) : false;
    }
    function isNumericType(type) {
        return ((type.getFlags() & (ts.TypeFlags.NumberLike | ts.TypeFlags.BigIntLike)) !== 0 ||
            (type.isUnionOrIntersection() && !!type.types.find(isNumericType)));
    }
}
//# sourceMappingURL=bitwise-operators.js.map
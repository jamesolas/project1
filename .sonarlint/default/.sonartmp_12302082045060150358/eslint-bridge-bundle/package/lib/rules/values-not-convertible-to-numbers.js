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
// https://jira.sonarsource.com/browse/RSPEC-3758
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (Object.hasOwnProperty.call(mod, k)) result[k] = mod[k];
    result["default"] = mod;
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
const isRequiredParserServices_1 = require("../utils/isRequiredParserServices");
const tsTypes = __importStar(require("typescript"));
const utils_1 = require("./utils");
const message = (typeName) => `Re-evaluate the data flow; this operand of a numeric comparison could be of type ${typeName}.`;
const comparisonOperators = new Set(['>', '<', '>=', '<=']);
exports.rule = {
    create(context) {
        const services = context.parserServices;
        if (!isRequiredParserServices_1.isRequiredParserServices(services)) {
            return {};
        }
        return {
            BinaryExpression(node) {
                const { left, operator, right } = node;
                if (!comparisonOperators.has(operator)) {
                    return;
                }
                const checker = services.program.getTypeChecker();
                const leftType = utils_1.getTypeFromTreeNode(left, services);
                const rightType = utils_1.getTypeFromTreeNode(right, services);
                if (utils_1.isStringType(leftType) || utils_1.isStringType(rightType)) {
                    return;
                }
                const isLeftConvertibleToNumber = isConvertibleToNumber(leftType, checker);
                const isRightConvertibleToNumber = isConvertibleToNumber(rightType, checker);
                if (!isLeftConvertibleToNumber) {
                    context.report({
                        message: message(checker.typeToString(leftType)),
                        node: left,
                    });
                }
                if (!isRightConvertibleToNumber) {
                    context.report({
                        message: message(checker.typeToString(rightType)),
                        node: right,
                    });
                }
            },
        };
    },
};
function isConvertibleToNumber(typ, checker) {
    const flags = typ.getFlags();
    if ((flags & tsTypes.TypeFlags.BooleanLike) !== 0) {
        return true;
    }
    if ((flags & tsTypes.TypeFlags.Undefined) !== 0) {
        return false;
    }
    const valueOfSignatures = getValueOfSignatures(typ, checker);
    return (valueOfSignatures.length === 0 ||
        valueOfSignatures.some(signature => isNumberLike(signature.getReturnType())));
}
function getValueOfSignatures(typ, checker) {
    const valueOfSymbol = typ.getProperty('valueOf');
    if (!valueOfSymbol) {
        return [];
    }
    const declarations = valueOfSymbol.getDeclarations() || [];
    return declarations
        .map(declaration => checker.getTypeAtLocation(declaration).getCallSignatures())
        .reduce((result, decl) => result.concat(decl), []);
}
function isNumberLike(typ) {
    return (typ.getFlags() & tsTypes.TypeFlags.NumberLike) !== 0;
}
//# sourceMappingURL=values-not-convertible-to-numbers.js.map
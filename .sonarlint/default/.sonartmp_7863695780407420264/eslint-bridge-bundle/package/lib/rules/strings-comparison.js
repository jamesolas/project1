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
// https://jira.sonarsource.com/browse/RSPEC-3003
Object.defineProperty(exports, "__esModule", { value: true });
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
        return {
            BinaryExpression: (node) => {
                const { operator, left, right } = node;
                if (['<', '<=', '>', '>='].includes(operator) &&
                    utils_1.isString(left, services) &&
                    utils_1.isString(right, services) &&
                    !isLiteralException(left) &&
                    !isLiteralException(right)) {
                    context.report({
                        message: utils_1.toEncodedMessage(`Convert operands of this use of "${operator}" to number type.`, [left, right]),
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
function isLiteralException(node) {
    return node.type === 'Literal' && node.raw.length === 3;
}
//# sourceMappingURL=strings-comparison.js.map
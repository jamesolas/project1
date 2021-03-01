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
// https://jira.sonarsource.com/browse/RSPEC-5659
Object.defineProperty(exports, "__esModule", { value: true });
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
        const SIGN_MESSAGE = 'Use only strong cipher algorithms when signing this JWT.';
        const VERIFY_MESSAGE = 'Use only strong cipher algorithms when verifying the signature of this JWT.';
        function checkCallToSign(callExpression, thirdArgumentValue, secondaryLocations) {
            const unsafeAlgorithmProperty = utils_1.getPropertyWithValue(context, thirdArgumentValue, 'algorithm', 'none');
            if (unsafeAlgorithmProperty) {
                const unsafeAlgorithmValue = utils_1.getValueOfExpression(context, unsafeAlgorithmProperty.value, 'Literal');
                if (unsafeAlgorithmValue && unsafeAlgorithmValue !== unsafeAlgorithmProperty.value) {
                    secondaryLocations.push(unsafeAlgorithmValue);
                }
                context.report({
                    node: callExpression.callee,
                    message: utils_1.toEncodedMessage(SIGN_MESSAGE, secondaryLocations),
                });
            }
        }
        function checkCallToVerify(callExpression, thirdArgumentValue, secondaryLocations) {
            const algorithmsProperty = utils_1.getObjectExpressionProperty(thirdArgumentValue, 'algorithms');
            if (!algorithmsProperty) {
                context.report({
                    node: callExpression.callee,
                    message: utils_1.toEncodedMessage(VERIFY_MESSAGE, secondaryLocations),
                });
                return;
            }
            const algorithmsValue = utils_1.getValueOfExpression(context, algorithmsProperty.value, 'ArrayExpression');
            if (!algorithmsValue) {
                return;
            }
            const algorithmsContainNone = algorithmsValue.elements.some(e => {
                const value = utils_1.getValueOfExpression(context, e, 'Literal');
                return (value === null || value === void 0 ? void 0 : value.value) === 'none';
            });
            if (algorithmsContainNone) {
                if (algorithmsProperty.value !== algorithmsValue) {
                    secondaryLocations.push(algorithmsValue);
                }
                context.report({
                    node: callExpression.callee,
                    message: utils_1.toEncodedMessage(VERIFY_MESSAGE, secondaryLocations),
                });
            }
        }
        return {
            CallExpression: (node) => {
                const callExpression = node;
                const isCallToSign = utils_1.isCallToFQN(context, callExpression, 'jsonwebtoken', 'sign');
                const isCallToVerify = utils_1.isCallToFQN(context, callExpression, 'jsonwebtoken', 'verify');
                if (!isCallToSign && !isCallToVerify) {
                    return;
                }
                if (callExpression.arguments.length < 3) {
                    // algorithm(s) property is contained in third argument of "sign" and "verify" calls
                    return;
                }
                const thirdArgument = callExpression.arguments[2];
                const thirdArgumentValue = utils_1.getValueOfExpression(context, thirdArgument, 'ObjectExpression');
                if (!thirdArgumentValue) {
                    return;
                }
                const secondaryLocations = [thirdArgumentValue];
                if (thirdArgumentValue !== thirdArgument) {
                    secondaryLocations.push(thirdArgument);
                }
                if (isCallToSign) {
                    checkCallToSign(callExpression, thirdArgumentValue, secondaryLocations);
                }
                if (isCallToVerify) {
                    checkCallToVerify(callExpression, thirdArgumentValue, secondaryLocations);
                }
            },
        };
    },
};
//# sourceMappingURL=insecure-jwt-token.js.map
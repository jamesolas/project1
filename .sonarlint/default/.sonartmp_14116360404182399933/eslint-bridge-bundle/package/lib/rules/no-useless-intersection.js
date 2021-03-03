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
// https://jira.sonarsource.com/browse/RSPEC-4335
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
exports.rule = {
    create(context) {
        const services = context.parserServices;
        if (isRequiredParserServices_1.isRequiredParserServices(services)) {
            return {
                TSIntersectionType: (node) => {
                    const intersection = node;
                    const anyOrNever = intersection.types.find(typeNode => ['TSAnyKeyword', 'TSNeverKeyword'].includes(typeNode.type));
                    if (anyOrNever) {
                        context.report({
                            message: `Simplify this intersection as it always has type "${anyOrNever.type === 'TSAnyKeyword' ? 'any' : 'never'}".`,
                            node,
                        });
                    }
                    else {
                        intersection.types.forEach(typeNode => {
                            const tp = services.program
                                .getTypeChecker()
                                .getTypeAtLocation(services.esTreeNodeToTSNodeMap.get(typeNode));
                            if (isTypeWithoutMembers(tp)) {
                                context.report({
                                    message: 'Remove this type without members or change this type intersection.',
                                    node: typeNode,
                                });
                            }
                        });
                    }
                },
            };
        }
        return {};
    },
};
function isTypeWithoutMembers(tp) {
    return isNullLike(tp) || (isEmptyInterface(tp) && isStandaloneInterface(tp.symbol));
}
function isNullLike(tp) {
    return (Boolean(tp.flags & ts.TypeFlags.Null) ||
        Boolean(tp.flags & ts.TypeFlags.Undefined) ||
        Boolean(tp.flags & ts.TypeFlags.Void));
}
function isEmptyInterface(tp) {
    return Boolean(tp.symbol && tp.symbol.members && tp.symbol.members.size === 0);
}
function isStandaloneInterface({ declarations }) {
    // there is no declarations for `{}`
    // otherwise check that none of declarations has a heritage clause (`extends X` or `implements X`)
    return (!declarations ||
        declarations.every(declaration => {
            return (isInterfaceDeclaration(declaration) && (declaration.heritageClauses || []).length === 0);
        }));
}
function isInterfaceDeclaration(declaration) {
    return declaration.kind === ts.SyntaxKind.InterfaceDeclaration;
}
//# sourceMappingURL=no-useless-intersection.js.map
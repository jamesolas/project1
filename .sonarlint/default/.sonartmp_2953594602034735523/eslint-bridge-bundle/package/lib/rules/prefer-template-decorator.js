"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const decorators_1 = require("../utils/decorators");
function decoratePreferTemplate(rule) {
    return decorators_1.interceptReport(rule, reportExempting(isTwoOperands));
}
exports.decoratePreferTemplate = decoratePreferTemplate;
function reportExempting(exemptionCondition) {
    return (context, reportDescriptor) => {
        if ('node' in reportDescriptor) {
            const expr = reportDescriptor['node'];
            if (!exemptionCondition(expr)) {
                context.report(reportDescriptor);
            }
        }
    };
}
function isTwoOperands(node) {
    return node.right.type !== 'BinaryExpression' && node.left.type !== 'BinaryExpression';
}
//# sourceMappingURL=prefer-template-decorator.js.map
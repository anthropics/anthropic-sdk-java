package com.anthropic.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import io.gitlab.arturbosch.detekt.api.internal.RequiresTypeResolution
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression
import org.jetbrains.kotlin.resolve.BindingContext

/**
 * `Optional.orElse(null)` returns a platform type; `kotlin.jvm.optionals.getOrNull()` returns a
 * proper `T?`. `orElse` with a typed default (`orElse(0)`) is fine, so a blanket
 * `ForbiddenMethodCall` on `Optional.orElse` over-reaches; flag only the literal `null` argument.
 */
@RequiresTypeResolution
class OptionalOrElseNull(config: Config) : Rule(config) {

    override val issue =
        Issue(
            javaClass.simpleName,
            Severity.Defect,
            "`Optional.orElse(null)` returns a platform type; use `getOrNull()`.",
            Debt.FIVE_MINS,
        )

    override fun visitDotQualifiedExpression(expression: KtDotQualifiedExpression) {
        super.visitDotQualifiedExpression(expression)
        if (bindingContext == BindingContext.EMPTY) return
        val call = expression.selectorExpression as? KtCallExpression ?: return
        if (call.calleeExpression?.text != "orElse") return
        val arg = call.valueArguments.singleOrNull()?.getArgumentExpression() ?: return
        if (arg.text != "null") return
        if (
            expression.receiverExpression.resolvedTypeFqName(bindingContext) != "java.util.Optional"
        )
            return
        report(
            CodeSmell(
                issue,
                Entity.from(call),
                "`.orElse(null)` returns a platform type; use `.getOrNull()`.",
            )
        )
    }
}

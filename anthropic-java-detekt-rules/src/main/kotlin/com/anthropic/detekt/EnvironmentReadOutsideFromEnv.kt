package com.anthropic.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.psiUtil.getStrictParentOfType

/**
 * The caller chooses whether the environment is read, by calling `fromEnv()`. A blanket
 * `ForbiddenMethodCall` on `System.getenv` would also reject the reads inside `fromEnv()`; flag
 * only the ones outside it.
 */
class EnvironmentReadOutsideFromEnv(config: Config) : Rule(config) {

    override val issue =
        Issue(
            javaClass.simpleName,
            Severity.Defect,
            "`System.getenv` is called only inside `fromEnv()`.",
            Debt.TWENTY_MINS,
        )

    override fun visitDotQualifiedExpression(expression: KtDotQualifiedExpression) {
        super.visitDotQualifiedExpression(expression)
        if (expression.receiverExpression.text != "System") return
        val call = expression.selectorExpression as? KtCallExpression ?: return
        if (call.calleeExpression?.text != "getenv") return
        if (expression.getStrictParentOfType<KtNamedFunction>()?.name == "fromEnv") return
        report(
            CodeSmell(
                issue,
                Entity.from(expression),
                "`${expression.text}` reads the environment outside `fromEnv()`; move the read into a `fromEnv()` the caller opts into.",
            )
        )
    }
}

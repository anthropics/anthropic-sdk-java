package com.anthropic.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtNamedFunction

/**
 * Without `@JvmStatic`, a public companion function surfaces to Java as `Type.Companion.foo()`. The
 * SDK convention is `Type.foo()`.
 */
class CompanionFunctionMissingJvmStatic(config: Config) : Rule(config) {

    override val issue =
        Issue(
            javaClass.simpleName,
            Severity.Defect,
            "Public companion functions must carry `@JvmStatic`.",
            Debt.FIVE_MINS,
        )

    override fun visitNamedFunction(function: KtNamedFunction) {
        super.visitNamedFunction(function)
        if (!function.isInCompanionObject()) return
        if (!function.isPublishedJavaApi()) return
        if (function.hasAnnotation("JvmStatic")) return
        report(
            CodeSmell(
                issue,
                Entity.atName(function),
                "Companion function `${function.name}` surfaces to Java as `Companion.${function.name}()`; add `@JvmStatic`.",
            )
        )
    }
}

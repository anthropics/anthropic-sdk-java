package com.anthropic.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtNamedFunction

/**
 * Coroutines are not part of the published API: Java callers can't invoke `suspend` functions, and
 * the SDK's async surface is `CompletableFuture`.
 */
class PublicSuspendFunction(config: Config) : Rule(config) {

    override val issue =
        Issue(
            javaClass.simpleName,
            Severity.Defect,
            "Public `suspend` functions are uncallable from Java; expose `CompletableFuture` instead.",
            Debt.TWENTY_MINS,
        )

    override fun visitNamedFunction(function: KtNamedFunction) {
        super.visitNamedFunction(function)
        if (!function.hasModifier(KtTokens.SUSPEND_KEYWORD)) return
        if (!function.isPublishedJavaApi()) return
        report(
            CodeSmell(
                issue,
                Entity.atName(function),
                "Public `suspend fun ${function.name}` is uncallable from Java; expose a `CompletableFuture`-returning variant instead.",
            )
        )
    }
}

package com.anthropic.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import io.gitlab.arturbosch.detekt.api.config
import io.gitlab.arturbosch.detekt.api.internal.RequiresTypeResolution
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.resolve.BindingContext

/**
 * `AnthropicException` is the single root of the SDK's exception hierarchy and the only class
 * permitted to extend `RuntimeException` directly. New exceptions extend an existing SDK type;
 * per-feature exception types are not minted.
 */
@RequiresTypeResolution
class RuntimeExceptionSubclass(config: Config) : Rule(config) {

    override val issue =
        Issue(
            javaClass.simpleName,
            Severity.Defect,
            "Only the SDK's root exception may extend `RuntimeException` directly.",
            Debt.TEN_MINS,
        )

    private val allowedClasses: List<String> by config(listOf("AnthropicException"))

    private val forbidden =
        setOf(
            "java.lang.RuntimeException",
            "java.lang.Exception",
            "java.lang.Throwable",
            // `kotlin.Throwable` is a mapped type (not a typealias), so `: Throwable()` resolves
            // here rather than to `java.lang.Throwable`.
            "kotlin.Throwable",
        )

    override fun visitClass(klass: KtClass) {
        super.visitClass(klass)
        if (bindingContext == BindingContext.EMPTY) return
        if (klass.name in allowedClasses) return
        val superFqName = klass.directSuperClassFqName(bindingContext) ?: return
        if (superFqName !in forbidden) return
        report(
            CodeSmell(
                issue,
                Entity.atName(klass),
                "`${klass.name}` extends `$superFqName` directly; extend `AnthropicException` (or a subclass) instead.",
            )
        )
    }
}

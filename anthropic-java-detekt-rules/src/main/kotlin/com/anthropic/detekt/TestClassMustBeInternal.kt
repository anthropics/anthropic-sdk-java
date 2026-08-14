package com.anthropic.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtNamedFunction

/** Test classes are `internal class` so they don't leak into the published Kotlin API surface. */
class TestClassMustBeInternal(config: Config) : Rule(config) {

    override val issue =
        Issue(
            javaClass.simpleName,
            Severity.Style,
            "Test classes must be declared `internal`.",
            Debt.FIVE_MINS,
        )

    private val testAnnotations =
        setOf("Test", "TestFactory", "ParameterizedTest", "RepeatedTest", "TestTemplate")

    override fun visitClass(klass: KtClass) {
        super.visitClass(klass)
        if (klass.hasModifier(KtTokens.INTERNAL_KEYWORD)) return
        if (klass.hasModifier(KtTokens.PRIVATE_KEYWORD)) return
        val hasTestMethod =
            klass.body?.declarations.orEmpty().filterIsInstance<KtNamedFunction>().any { fn ->
                fn.annotationEntries.any { it.shortName?.asString() in testAnnotations }
            }
        if (!hasTestMethod) return
        report(
            CodeSmell(
                issue,
                Entity.atName(klass),
                "Test class `${klass.name}` must be declared `internal`.",
            )
        )
    }
}

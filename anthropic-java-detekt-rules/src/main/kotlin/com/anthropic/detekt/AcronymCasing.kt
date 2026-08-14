package com.anthropic.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtNamedDeclaration
import org.jetbrains.kotlin.psi.KtNamedFunction

/**
 * Java-style acronym casing: `SseMessage` / `getId`, not `SSEMessage` / `getID`.
 *
 * In camelCase, the last uppercase of a mid-identifier run is the next word's initial, so an
 * N-uppercase run there is an (N-1)-letter acronym; at the end it's an N-letter acronym. Acronyms
 * of 3+ letters must be PascalCased, so a violation is 4+ uppercase anywhere or 3+ at the end.
 * Two-letter acronyms (`IO`, `Id`) are left alone.
 */
class AcronymCasing(config: Config) : Rule(config) {

    override val issue =
        Issue(
            javaClass.simpleName,
            Severity.Style,
            "Acronyms in identifiers use Pascal/camel case (e.g. `Sse`, not `SSE`).",
            Debt.FIVE_MINS,
        )

    private val pattern = Regex("[A-Z]{4,}|[A-Z]{3,}$")

    override fun visitClassOrObject(classOrObject: KtClassOrObject) {
        super.visitClassOrObject(classOrObject)
        check(classOrObject)
    }

    override fun visitNamedFunction(function: KtNamedFunction) {
        super.visitNamedFunction(function)
        check(function)
    }

    private fun check(declaration: KtNamedDeclaration) {
        val name = declaration.name ?: return
        // SCREAMING_SNAKE constants are handled elsewhere; skip all-upper names.
        if (name == name.uppercase()) return
        val match = pattern.find(name) ?: return
        val next = name.getOrNull(match.range.last + 1)
        // The final uppercase belongs to the next word only when that word starts with a letter
        // (e.g. `SSEM` in `SSEMessage`). Before a digit or at end, the run is the full acronym
        // (`HTTP` in `parseHTTP2`).
        val acronym = if (next?.isLetter() == true) match.value.dropLast(1) else match.value
        val suggestion = acronym.first() + acronym.drop(1).lowercase()
        report(
            CodeSmell(
                issue,
                Entity.atName(declaration),
                "`$name` contains the all-caps acronym `$acronym`; use `$suggestion`.",
            )
        )
    }
}

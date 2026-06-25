package com.anthropic.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassBody
import org.jetbrains.kotlin.psi.KtNamedFunction

/**
 * A public class with a nested `Builder` must expose the canonical entry points: a companion `fun
 * builder()` and an instance `fun toBuilder()`. (`@JvmStatic` on `builder()` is enforced by
 * [CompanionFunctionMissingJvmStatic].)
 *
 * Triggering on a nested `Builder` scopes this to classes opted into the pattern (vs. types using
 * `of(...)` factories).
 */
class BuilderEntryPoints(config: Config) : Rule(config) {

    override val issue =
        Issue(
            javaClass.simpleName,
            Severity.Defect,
            "Builder-backed classes must expose `companion.builder()` and `toBuilder()`.",
            Debt.TEN_MINS,
        )

    override fun visitClass(klass: KtClass) {
        super.visitClass(klass)
        if (!klass.isEffectivelyPublic()) return
        if (!klass.hasNestedBuilder()) return

        if (klass.companionObjects.firstOrNull()?.body.hasFunction("builder") != true) {
            report(klass, "`${klass.name}` has a `Builder` but no companion `fun builder()`.")
        }
        if (!klass.body.hasFunction("toBuilder")) {
            report(klass, "`${klass.name}` has a `Builder` but no `fun toBuilder()`.")
        }
    }

    private fun KtClass.hasNestedBuilder(): Boolean =
        body?.declarations.orEmpty().filterIsInstance<KtClass>().any { it.name == "Builder" }

    private fun KtClassBody?.hasFunction(name: String): Boolean =
        this?.declarations.orEmpty().filterIsInstance<KtNamedFunction>().any { it.name == name }

    private fun report(klass: KtClass, message: String) {
        report(CodeSmell(issue, Entity.atName(klass), message))
    }
}

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
 *
 * `toBuilder()` is only required when the `Builder` builds the enclosing class. A `Builder` whose
 * `build()` declares some other return type is a factory for that type: the enclosing class is
 * never instantiated, so there is no instance for `toBuilder()` to copy.
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
        val builder = klass.nestedBuilder() ?: return

        if (klass.companionObjects.firstOrNull()?.body.function("builder") == null) {
            report(klass, "`${klass.name}` has a `Builder` but no companion `fun builder()`.")
        }
        if (builder.buildsEnclosing(klass) && klass.body.function("toBuilder") == null) {
            report(klass, "`${klass.name}` has a `Builder` but no `fun toBuilder()`.")
        }
    }

    private fun KtClass.nestedBuilder(): KtClass? =
        body?.declarations.orEmpty().filterIsInstance<KtClass>().firstOrNull {
            it.name == "Builder"
        }

    /** An absent `build()` or an inferred return type is assumed to build [klass]. */
    private fun KtClass.buildsEnclosing(klass: KtClass): Boolean {
        val returnType = body.function("build")?.typeReference?.text ?: return true
        return returnType.substringBefore('<').substringAfterLast('.') == klass.name
    }

    private fun KtClassBody?.function(name: String): KtNamedFunction? =
        this?.declarations.orEmpty().filterIsInstance<KtNamedFunction>().firstOrNull {
            it.name == name
        }

    private fun report(klass: KtClass, message: String) {
        report(CodeSmell(issue, Entity.atName(klass), message))
    }
}

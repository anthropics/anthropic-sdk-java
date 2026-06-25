package com.anthropic.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import io.gitlab.arturbosch.detekt.api.internal.RequiresTypeResolution
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtEnumEntry
import org.jetbrains.kotlin.psi.allConstructors
import org.jetbrains.kotlin.resolve.BindingContext

/**
 * Public API types use `private constructor` + companion `builder()`, never public constructors.
 *
 * Exempt: abstract/sealed/enum/annotation classes (not directly instantiated) and Throwable
 * subclasses (public constructors by design).
 */
@RequiresTypeResolution
class PublicConstructor(config: Config) : Rule(config) {

    override val issue =
        Issue(
            javaClass.simpleName,
            Severity.Defect,
            "Public classes use a private constructor and a companion `builder()`.",
            Debt.TWENTY_MINS,
        )

    override fun visitClass(klass: KtClass) {
        super.visitClass(klass)
        if (bindingContext == BindingContext.EMPTY) return
        if (!klass.isEffectivelyPublic()) return
        if (klass is KtEnumEntry) return
        if (klass.isInterface() || klass.isEnum() || klass.isAnnotation()) return
        if (klass.hasModifier(KtTokens.ABSTRACT_KEYWORD)) return
        if (klass.hasModifier(KtTokens.SEALED_KEYWORD)) return
        if (klass.isThrowableSubclass(bindingContext)) return

        val publicCtor =
            klass.allConstructors.firstOrNull {
                !it.hasModifier(KtTokens.PRIVATE_KEYWORD) &&
                    !it.hasModifier(KtTokens.INTERNAL_KEYWORD) &&
                    !it.hasModifier(KtTokens.PROTECTED_KEYWORD)
            }
        // No explicit constructors means an implicit public no-arg one.
        if (publicCtor == null && klass.allConstructors.isNotEmpty()) return

        report(
            CodeSmell(
                issue,
                publicCtor?.let { Entity.from(it) } ?: Entity.atName(klass),
                "`${klass.name}` is publicly constructable; use `private constructor` with a companion `builder()` (or `of(...)`).",
            )
        )
    }
}

package com.anthropic.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtProperty

/**
 * Without `@JvmField`, a public SCREAMING_CASE companion constant surfaces to Java as
 * `Type.Companion.getFOO()`; the convention is the static field `Type.FOO`. `const val` already
 * compiles to a static field and is exempt.
 */
class CompanionConstantMissingJvmField(config: Config) : Rule(config) {

    override val issue =
        Issue(
            javaClass.simpleName,
            Severity.Defect,
            "Companion-object constants must carry `@JvmField` (or `const`).",
            Debt.FIVE_MINS,
        )

    private val constantName = Regex("[A-Z][A-Z0-9_]*")

    override fun visitProperty(property: KtProperty) {
        super.visitProperty(property)
        if (!property.isInCompanionObject()) return
        if (!property.isEffectivelyPublic()) return
        if (property.isVar) return
        val name = property.name ?: return
        if (!constantName.matches(name)) return
        if (property.hasModifier(KtTokens.CONST_KEYWORD)) return
        if (property.hasAnnotation("JvmField")) return
        if (property.hasAnnotation("JvmStatic")) return
        if (property.hasAnnotation("JvmSynthetic")) return
        report(
            CodeSmell(
                issue,
                Entity.atName(property),
                "Companion constant `$name` surfaces to Java as `Companion.get$name()`; add `@JvmField`.",
            )
        )
    }
}

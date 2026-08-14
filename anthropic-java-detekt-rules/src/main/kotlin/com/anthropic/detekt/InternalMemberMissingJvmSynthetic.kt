package com.anthropic.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtPropertyAccessor
import org.jetbrains.kotlin.psi.psiUtil.containingClassOrObject

/**
 * Kotlin `internal` is module-scoped to Kotlin callers but compiles to a public JVM member, leaking
 * into the Java API. `@JvmSynthetic` marks the bytecode `ACC_SYNTHETIC`, hiding it from javac.
 *
 * Covers every `internal` declaration `@JvmSynthetic` can target: functions and property accessors
 * (`@get:`/`@set:`). Constructors and classes are out of scope: `@JvmSynthetic`'s targets are
 * `FILE, FUNCTION, PROPERTY_GETTER, PROPERTY_SETTER, FIELD`, so `internal constructor` has no
 * annotation-based fix and the SDK accepts that leak (e.g. `Builder internal constructor()`).
 */
class InternalMemberMissingJvmSynthetic(config: Config) : Rule(config) {

    override val issue =
        Issue(
            javaClass.simpleName,
            Severity.Defect,
            "`internal` members are visible to Java callers; add `@JvmSynthetic`.",
            Debt.FIVE_MINS,
        )

    override fun visitNamedFunction(function: KtNamedFunction) {
        super.visitNamedFunction(function)
        if (!function.isReachableInternal()) return
        if (function.hasAnnotation("JvmSynthetic")) return
        report(function, "internal fun ${function.name}", "@JvmSynthetic")
    }

    override fun visitProperty(property: KtProperty) {
        super.visitProperty(property)
        if (property.isLocal) return
        if (!property.isReachableInternal()) return
        // Any JvmSynthetic placement (bare, @get:, @set:, @field:) means the author addressed Java
        // visibility; the message below recommends the accessor sites.
        if (property.hasAnyJvmSynthetic()) return
        if (property.hasAnnotation("JvmField")) return
        val advice =
            if (property.isVar) "@get:JvmSynthetic @set:JvmSynthetic" else "@get:JvmSynthetic"
        report(
            property,
            "internal ${if (property.isVar) "var" else "val"} ${property.name}",
            advice,
        )
    }

    override fun visitPropertyAccessor(accessor: KtPropertyAccessor) {
        super.visitPropertyAccessor(accessor)
        // An `internal` accessor on a non-internal property (e.g. `var x: T internal set`).
        if (!accessor.hasModifier(KtTokens.INTERNAL_KEYWORD)) return
        val property = accessor.property
        if (property.hasModifier(KtTokens.INTERNAL_KEYWORD)) return
        if (property.isEnclosedInNonPublic()) return
        if (accessor.hasAnnotation("JvmSynthetic")) return
        val site = if (accessor.isGetter) "get" else "set"
        if (property.hasUseSiteAnnotation(site, "JvmSynthetic")) return
        report(accessor, "internal $site on ${property.name}", "@$site:JvmSynthetic")
    }

    private fun KtDeclaration.isReachableInternal(): Boolean =
        hasModifier(KtTokens.INTERNAL_KEYWORD) && !isEnclosedInNonPublic()

    private fun report(declaration: KtDeclaration, what: String, advice: String) {
        report(
            CodeSmell(
                issue,
                Entity.from(declaration),
                "`$what` is visible to Java callers; add `$advice`.",
            )
        )
    }
}

/**
 * True when any enclosing class/object is `private` or `internal`. A `private` container makes
 * members unreachable; an `internal` container is the convention's boundary ("internal classes use
 * the `internal` keyword alone"), so per-member `@JvmSynthetic` is not required inside one.
 */
private fun KtDeclaration.isEnclosedInNonPublic(): Boolean {
    var outer: KtClassOrObject? = containingClassOrObject
    while (outer != null) {
        if (outer.hasModifier(KtTokens.PRIVATE_KEYWORD)) return true
        if (outer.hasModifier(KtTokens.INTERNAL_KEYWORD)) return true
        outer = outer.containingClassOrObject
    }
    return false
}

private fun KtProperty.hasAnyJvmSynthetic(): Boolean =
    hasAnnotation("JvmSynthetic") ||
        hasUseSiteAnnotation("get", "JvmSynthetic") ||
        hasUseSiteAnnotation("set", "JvmSynthetic") ||
        hasUseSiteAnnotation("field", "JvmSynthetic")

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
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtObjectDeclaration
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.psiUtil.containingClassOrObject
import org.jetbrains.kotlin.psi.psiUtil.isPrivate

/**
 * Public Kotlin `val` properties surface to Java as `getFoo()`. The SDK convention is `foo()`,
 * which requires `@get:JvmName("foo")`.
 *
 * Flags instance properties on public classes only; companion-object constants and top-level vals
 * follow the static-field/`@JvmField` pattern and are exempt.
 */
class PublicPropertyMissingJvmName(config: Config) : Rule(config) {

    override val issue =
        Issue(
            javaClass.simpleName,
            Severity.Style,
            "Public properties surface to Java as `getX()`; add `@get:JvmName(\"x\")`.",
            Debt.FIVE_MINS,
        )

    override fun visitProperty(property: KtProperty) {
        super.visitProperty(property)
        if (property.isLocal) return
        if (!property.isEffectivelyPublic()) return
        if (property.hasModifier(KtTokens.CONST_KEYWORD)) return
        if (property.hasModifier(KtTokens.OVERRIDE_KEYWORD)) return
        if (property.hasAnnotation("JvmField")) return
        if (property.hasUseSiteAnnotation("get", "JvmName")) return
        if (property.getter?.hasAnnotation("JvmName") == true) return
        // `@JvmSynthetic` already hides the accessor from Java.
        if (property.hasAnnotation("JvmSynthetic")) return
        if (property.hasUseSiteAnnotation("get", "JvmSynthetic")) return

        val container = property.containingClassOrObject
        // Top-level and (companion-)object properties are statics with their own idiom.
        if (container == null || container is KtObjectDeclaration) return
        if (container.isPrivate()) return

        // kotlinc rejects `@get:JvmName` on non-final declarations (`INAPPLICABLE_JVM_NAME`), so
        // for abstract/open/interface members the only fix is a function.
        val canTakeJvmName = property.isFinalIn(container)
        val advice =
            if (canTakeJvmName) "add `@get:JvmName(\"${property.name}\")` or make it a function"
            else
                "declare `fun ${property.name}()` instead (`@get:JvmName` is rejected on non-final members)"
        report(
            CodeSmell(
                issue,
                Entity.atName(property),
                "Public property `${property.name}` surfaces to Java as " +
                    "`get${property.name?.replaceFirstChar { it.uppercase() }}()`; $advice.",
            )
        )
    }

    private fun KtProperty.isFinalIn(container: KtClassOrObject): Boolean {
        if (hasModifier(KtTokens.ABSTRACT_KEYWORD)) return false
        if (hasModifier(KtTokens.OPEN_KEYWORD)) return false
        // Interface members without a body are abstract; with a body they're open.
        if ((container as? KtClass)?.isInterface() == true) return false
        return true
    }
}

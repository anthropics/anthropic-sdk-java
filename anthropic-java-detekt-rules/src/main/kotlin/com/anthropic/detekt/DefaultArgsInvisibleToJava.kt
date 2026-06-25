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
import org.jetbrains.kotlin.psi.psiUtil.containingClass
import org.jetbrains.kotlin.psi.psiUtil.containingClassOrObject

/**
 * Kotlin default arguments don't reach Java callers. Satisfy the convention with `@JvmOverloads`
 * (class methods only; unavailable on interface/abstract methods) or an explicit sibling overload
 * whose parameter list equals the non-defaulted prefix.
 */
class DefaultArgsInvisibleToJava(config: Config) : Rule(config) {

    override val issue =
        Issue(
            javaClass.simpleName,
            Severity.Defect,
            "Default arguments are invisible to Java; add `@JvmOverloads` or an explicit overload.",
            Debt.TEN_MINS,
        )

    override fun visitNamedFunction(function: KtNamedFunction) {
        super.visitNamedFunction(function)
        if (!function.isPublishedJavaApi()) return
        if (function.isLocal) return
        val defaulted = function.valueParameters.firstOrNull { it.hasDefaultValue() } ?: return
        if (function.hasAnnotation("JvmOverloads")) return
        if (function.hasSiblingOverloadCoveringRequiredParams()) return

        report(
            CodeSmell(
                issue,
                Entity.from(defaulted),
                "Default argument on `${function.name}` is invisible to Java; ${adviceFor(function)}.",
            )
        )
    }

    /**
     * True when a same-name sibling exists whose parameter types equal the non-defaulted prefix.
     * Compared by type text (not name), since parameter names aren't part of the Java signature.
     */
    private fun KtNamedFunction.hasSiblingOverloadCoveringRequiredParams(): Boolean {
        val requiredTypes =
            valueParameters.filterNot { it.hasDefaultValue() }.map { it.typeReference?.text }
        val container = containingClassOrObject?.body?.declarations ?: containingKtFile.declarations
        return container.filterIsInstance<KtNamedFunction>().any { sibling ->
            sibling !== this &&
                sibling.name == name &&
                sibling.valueParameters.map { it.typeReference?.text } == requiredTypes
        }
    }

    private fun adviceFor(function: KtNamedFunction): String {
        val onInterface = function.containingClass()?.isInterface() == true
        return if (onInterface || function.hasModifier(KtTokens.ABSTRACT_KEYWORD))
            "declare an explicit overload"
        else "add `@JvmOverloads` or declare an explicit overload"
    }
}

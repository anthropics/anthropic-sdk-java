package com.anthropic.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import io.gitlab.arturbosch.detekt.api.internal.RequiresTypeResolution
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.resolve.BindingContext

/**
 * Public API never exposes Kotlin nullable types. The published surface uses a private nullable
 * field with an `Optional<T>` getter so Java callers see a real type.
 *
 * Covers both explicit (`fun foo(): String?`) and inferred (`fun foo() = nullableThing`) returns by
 * resolving the callable's return type.
 */
@RequiresTypeResolution
class NullablePublicReturn(config: Config) : Rule(config) {

    override val issue =
        Issue(
            javaClass.simpleName,
            Severity.Defect,
            "Public API must not expose Kotlin nullable types; return `Optional<T>` instead.",
            Debt.TEN_MINS,
        )

    override fun visitNamedFunction(function: KtNamedFunction) {
        super.visitNamedFunction(function)
        check(function)
    }

    override fun visitProperty(property: KtProperty) {
        super.visitProperty(property)
        if (property.isLocal) return
        check(property)
    }

    private fun check(declaration: KtCallableDeclaration) {
        if (bindingContext == BindingContext.EMPTY) return
        if (!declaration.isPublishedJavaApi()) return
        val returnType = declaration.resolvedReturnType(bindingContext) ?: return
        if (!returnType.isMarkedNullable) return
        report(
            CodeSmell(
                issue,
                Entity.atName(declaration),
                "Public `${declaration.name}` returns a nullable type; expose " +
                    "`Optional<${returnType.nonNullRendering()}>` instead.",
            )
        )
    }
}

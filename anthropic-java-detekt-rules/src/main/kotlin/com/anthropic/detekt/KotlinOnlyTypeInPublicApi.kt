package com.anthropic.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import io.gitlab.arturbosch.detekt.api.config
import io.gitlab.arturbosch.detekt.api.internal.RequiresTypeResolution
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtFunctionType
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtNullableType
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.KtUserType
import org.jetbrains.kotlin.resolve.BindingContext

/**
 * Public signatures must not expose Kotlin-only types Java can't consume idiomatically: `Sequence`
 * (use `java.util.stream.Stream`), `Flow` (use `CompletableFuture`/`Publisher`), `Pair`/`Triple`
 * (use a named value class), `kotlin.Result`, and `(A) -> B` lambda types (use
 * `java.util.function.*`).
 */
@RequiresTypeResolution
class KotlinOnlyTypeInPublicApi(config: Config) : Rule(config) {

    override val issue =
        Issue(
            javaClass.simpleName,
            Severity.Defect,
            "Public API must not expose Kotlin-only types Java can't consume idiomatically.",
            Debt.TWENTY_MINS,
        )

    private val forbiddenTypes: List<String> by
        config(
            listOf(
                "kotlin.sequences.Sequence",
                "kotlin.Pair",
                "kotlin.Triple",
                "kotlin.Result",
                "kotlin.Unit",
                "kotlinx.coroutines.flow.Flow",
            )
        )

    override fun visitNamedFunction(function: KtNamedFunction) {
        super.visitNamedFunction(function)
        check(function, function.typeReference)
        function.valueParameters.forEach { check(function, it.typeReference) }
    }

    override fun visitProperty(property: KtProperty) {
        super.visitProperty(property)
        if (property.isLocal) return
        check(property, property.typeReference)
    }

    private fun check(declaration: KtCallableDeclaration, typeRef: KtTypeReference?) {
        typeRef ?: return
        if (bindingContext == BindingContext.EMPTY) return
        if (!declaration.isPublishedJavaApi()) return
        val offending = findOffending(typeRef) ?: return
        report(
            CodeSmell(
                issue,
                Entity.from(typeRef),
                "Public `${declaration.name}` exposes Kotlin-only type `$offending`; use the Java-idiomatic equivalent.",
            )
        )
    }

    private fun findOffending(typeRef: KtTypeReference): String? {
        var element = typeRef.typeElement
        if (element is KtNullableType) element = element.innerType
        return when (element) {
            is KtFunctionType -> "(${"..."}) -> ${"..."}"
            is KtUserType -> {
                val fqName = typeRef.resolvedFqName(bindingContext)
                if (fqName in forbiddenTypes) fqName
                else
                    element.typeArgumentsAsTypes.filterNotNull().firstNotNullOfOrNull {
                        findOffending(it)
                    }
            }
            else -> null
        }
    }
}

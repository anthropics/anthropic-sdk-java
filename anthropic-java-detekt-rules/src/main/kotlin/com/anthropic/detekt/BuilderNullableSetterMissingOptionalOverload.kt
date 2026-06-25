package com.anthropic.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import io.gitlab.arturbosch.detekt.api.internal.RequiresTypeResolution
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.resolve.BindingContext

/**
 * Builder setters for optional fields come in pairs: `fun foo(foo: T?) = apply { ... }` plus `fun
 * foo(foo: Optional<T>) = foo(foo.getOrNull())`. The alias lets Java callers pass an `Optional`
 * they already hold without unwrapping.
 */
@RequiresTypeResolution
class BuilderNullableSetterMissingOptionalOverload(config: Config) : Rule(config) {

    override val issue =
        Issue(
            javaClass.simpleName,
            Severity.Defect,
            "Builder setters taking `T?` must have an `Optional<T>` overload.",
            Debt.FIVE_MINS,
        )

    override fun visitClass(klass: KtClass) {
        super.visitClass(klass)
        if (bindingContext == BindingContext.EMPTY) return
        val functions = klass.publicBuilderFunctions() ?: return

        for (fn in functions) {
            val param = fn.valueParameters.singleOrNull() ?: continue
            // A defaulted nullable parameter (`fromEnv(x: T? = null)`) uses `@JvmOverloads`, not
            // the Optional-overload idiom; Java already gets a no-arg variant.
            if (param.hasDefaultValue()) continue
            val inner = param.typeReference.nullableInnerTypeText() ?: continue

            val hasOptionalSibling =
                functions.hasSingleParamSiblingOf(fn) {
                    it.resolvedFqName(bindingContext) == "java.util.Optional"
                }
            if (hasOptionalSibling) continue

            report(
                CodeSmell(
                    issue,
                    Entity.atName(fn),
                    "Builder setter `${fn.name}($inner?)` has no `Optional<$inner>` overload; " +
                        "add `fun ${fn.name}(${param.name}: Optional<$inner>) = ${fn.name}(${param.name}.getOrNull())`.",
                )
            )
        }
    }
}

package com.anthropic.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtClass

/**
 * A `Builder` setter taking `Long?`/`Double?`/`Float?` must also offer the unboxed primitive
 * overload. Java won't widen-then-box in one step, so with only `void foo(Long x)` a caller can't
 * write `foo(42)` (an `int` literal); `void foo(long x)` accepts it via primitive widening. `Int?`,
 * `Boolean?`, `Char?` autobox from their literals and need no overload; `Short?`/`Byte?` have no
 * literal form so an overload wouldn't help.
 */
class BuilderNullablePrimitiveSetterMissingUnboxedOverload(config: Config) : Rule(config) {

    override val issue =
        Issue(
            javaClass.simpleName,
            Severity.Defect,
            "Builder setters taking `Long?`/`Double?`/`Float?` need an unboxed overload so Java callers can pass `int` literals.",
            Debt.FIVE_MINS,
        )

    private val targets = setOf("Long", "Double", "Float")

    override fun visitClass(klass: KtClass) {
        super.visitClass(klass)
        val functions = klass.publicBuilderFunctions() ?: return

        for (fn in functions) {
            val param = fn.valueParameters.singleOrNull() ?: continue
            val inner = param.typeReference.nullableInnerTypeText() ?: continue
            if (inner !in targets) continue

            val hasUnboxedSibling = functions.hasSingleParamSiblingOf(fn) { it.text == inner }
            if (hasUnboxedSibling) continue

            report(
                CodeSmell(
                    issue,
                    Entity.atName(fn),
                    "Builder setter `${fn.name}($inner?)` has no unboxed `$inner` overload; Java callers must write a suffixed literal. " +
                        "Add `fun ${fn.name}(${param.name}: $inner) = ${fn.name}(${param.name} as $inner?)`.",
                )
            )
        }
    }
}

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

/** Exceptions end in `Exception`, never `Error` (a JVM `Error` is a different thing). */
@RequiresTypeResolution
class ThrowableSubclassNaming(config: Config) : Rule(config) {

    override val issue =
        Issue(
            javaClass.simpleName,
            Severity.Style,
            "Exception classes must be named `*Exception`.",
            Debt.FIVE_MINS,
        )

    override fun visitClass(klass: KtClass) {
        super.visitClass(klass)
        if (bindingContext == BindingContext.EMPTY) return
        val name = klass.name ?: return
        if (name.endsWith("Exception")) return
        if (!klass.isThrowableSubclass(bindingContext)) return
        report(
            CodeSmell(
                issue,
                Entity.atName(klass),
                "`$name` extends `Throwable` but is not named `*Exception`.",
            )
        )
    }
}

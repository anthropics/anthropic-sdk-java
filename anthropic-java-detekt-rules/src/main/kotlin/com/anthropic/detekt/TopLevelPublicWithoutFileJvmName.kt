package com.anthropic.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtProperty

/**
 * A file with public top-level functions or properties compiles to a `FooKt` facade class. The SDK
 * names the facade with `@file:JvmName(...)` so Java sees a stable, intentional class name.
 * Top-level classes/interfaces compile to their own classes, not the facade, so a file containing
 * only those is exempt.
 */
class TopLevelPublicWithoutFileJvmName(config: Config) : Rule(config) {

    override val issue =
        Issue(
            javaClass.simpleName,
            Severity.Style,
            "Files with public top-level callables must declare `@file:JvmName(...)`.",
            Debt.FIVE_MINS,
        )

    override fun visitKtFile(file: KtFile) {
        super.visitKtFile(file)
        if (file.fileAnnotationNamed("JvmName") != null) return
        val publicTopLevel =
            file.declarations.firstOrNull {
                (it is KtNamedFunction || it is KtProperty) &&
                    it.isEffectivelyPublic() &&
                    !it.hasAnnotation("JvmSynthetic")
            } ?: return
        val basename = file.name.substringAfterLast('/')
        report(
            CodeSmell(
                issue,
                Entity.from(publicTopLevel),
                "`$basename` has public top-level declarations but no `@file:JvmName`; Java sees `${basename.removeSuffix(".kt")}Kt`.",
            )
        )
    }
}

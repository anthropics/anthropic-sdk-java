package com.anthropic.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtFile

/**
 * `@file:JvmSynthetic` hides the file-facade class from Java, so every top-level declaration
 * becomes invisible at once and any public top-level added later is silently swallowed. Annotate
 * each `internal` member individually instead.
 */
class FileJvmSynthetic(config: Config) : Rule(config) {

    override val issue =
        Issue(
            javaClass.simpleName,
            Severity.Defect,
            "`@file:JvmSynthetic` hides the whole file from Java; annotate members individually.",
            Debt.FIVE_MINS,
        )

    override fun visitKtFile(file: KtFile) {
        super.visitKtFile(file)
        val entry = file.fileAnnotationNamed("JvmSynthetic") ?: return
        report(
            CodeSmell(
                issue,
                Entity.from(entry),
                "`@file:JvmSynthetic` hides every top-level in this file from Java; " +
                    "annotate each `internal` member with `@JvmSynthetic` instead.",
            )
        )
    }
}

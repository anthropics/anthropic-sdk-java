package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaExternalStorageInWorkspacePolicyErrorDetailsTest {

    @Test
    fun create() {
        val betaExternalStorageInWorkspacePolicyErrorDetails =
            BetaExternalStorageInWorkspacePolicyErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaExternalStorageInWorkspacePolicyErrorDetails =
            BetaExternalStorageInWorkspacePolicyErrorDetails.builder().build()

        val roundtrippedBetaExternalStorageInWorkspacePolicyErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaExternalStorageInWorkspacePolicyErrorDetails),
                jacksonTypeRef<BetaExternalStorageInWorkspacePolicyErrorDetails>(),
            )

        assertThat(roundtrippedBetaExternalStorageInWorkspacePolicyErrorDetails)
            .isEqualTo(betaExternalStorageInWorkspacePolicyErrorDetails)
    }
}

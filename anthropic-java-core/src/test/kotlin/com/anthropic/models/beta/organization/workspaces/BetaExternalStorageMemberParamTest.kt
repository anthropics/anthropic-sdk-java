package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaExternalStorageMemberParamTest {

    @Test
    fun create() {
        val betaExternalStorageMemberParam =
            BetaExternalStorageMemberParam.of("external_storage_id")

        assertThat(betaExternalStorageMemberParam.externalStorageId())
            .isEqualTo("external_storage_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaExternalStorageMemberParam =
            BetaExternalStorageMemberParam.of("external_storage_id")

        val roundtrippedBetaExternalStorageMemberParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaExternalStorageMemberParam),
                jacksonTypeRef<BetaExternalStorageMemberParam>(),
            )

        assertThat(roundtrippedBetaExternalStorageMemberParam)
            .isEqualTo(betaExternalStorageMemberParam)
    }
}

package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaInternalStorageParamTest {

    @Test
    fun create() {
        val betaInternalStorageParam = BetaInternalStorageParam.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaInternalStorageParam = BetaInternalStorageParam.builder().build()

        val roundtrippedBetaInternalStorageParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaInternalStorageParam),
                jacksonTypeRef<BetaInternalStorageParam>(),
            )

        assertThat(roundtrippedBetaInternalStorageParam).isEqualTo(betaInternalStorageParam)
    }
}

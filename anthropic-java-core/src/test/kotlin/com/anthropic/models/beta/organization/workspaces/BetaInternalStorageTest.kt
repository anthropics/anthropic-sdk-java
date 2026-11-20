package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaInternalStorageTest {

    @Test
    fun create() {
        val betaInternalStorage = BetaInternalStorage.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaInternalStorage = BetaInternalStorage.builder().build()

        val roundtrippedBetaInternalStorage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaInternalStorage),
                jacksonTypeRef<BetaInternalStorage>(),
            )

        assertThat(roundtrippedBetaInternalStorage).isEqualTo(betaInternalStorage)
    }
}

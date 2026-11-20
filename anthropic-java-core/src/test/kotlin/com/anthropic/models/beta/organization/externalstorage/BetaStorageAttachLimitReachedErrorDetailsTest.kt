package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaStorageAttachLimitReachedErrorDetailsTest {

    @Test
    fun create() {
        val betaStorageAttachLimitReachedErrorDetails =
            BetaStorageAttachLimitReachedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaStorageAttachLimitReachedErrorDetails =
            BetaStorageAttachLimitReachedErrorDetails.builder().build()

        val roundtrippedBetaStorageAttachLimitReachedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaStorageAttachLimitReachedErrorDetails),
                jacksonTypeRef<BetaStorageAttachLimitReachedErrorDetails>(),
            )

        assertThat(roundtrippedBetaStorageAttachLimitReachedErrorDetails)
            .isEqualTo(betaStorageAttachLimitReachedErrorDetails)
    }
}

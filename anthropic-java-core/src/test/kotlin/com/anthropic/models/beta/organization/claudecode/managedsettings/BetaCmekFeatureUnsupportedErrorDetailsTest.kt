package com.anthropic.models.beta.organization.claudecode.managedsettings

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCmekFeatureUnsupportedErrorDetailsTest {

    @Test
    fun create() {
        val betaCmekFeatureUnsupportedErrorDetails =
            BetaCmekFeatureUnsupportedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCmekFeatureUnsupportedErrorDetails =
            BetaCmekFeatureUnsupportedErrorDetails.builder().build()

        val roundtrippedBetaCmekFeatureUnsupportedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCmekFeatureUnsupportedErrorDetails),
                jacksonTypeRef<BetaCmekFeatureUnsupportedErrorDetails>(),
            )

        assertThat(roundtrippedBetaCmekFeatureUnsupportedErrorDetails)
            .isEqualTo(betaCmekFeatureUnsupportedErrorDetails)
    }
}

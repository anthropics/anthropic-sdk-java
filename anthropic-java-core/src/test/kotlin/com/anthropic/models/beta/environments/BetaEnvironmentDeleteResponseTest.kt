package com.anthropic.models.beta.environments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaEnvironmentDeleteResponseTest {

    @Test
    fun create() {
        val betaEnvironmentDeleteResponse =
            BetaEnvironmentDeleteResponse.of("env_011CZkZ9X2dpNyB7HsEFoRfW")

        assertThat(betaEnvironmentDeleteResponse.id()).isEqualTo("env_011CZkZ9X2dpNyB7HsEFoRfW")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaEnvironmentDeleteResponse =
            BetaEnvironmentDeleteResponse.of("env_011CZkZ9X2dpNyB7HsEFoRfW")

        val roundtrippedBetaEnvironmentDeleteResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaEnvironmentDeleteResponse),
                jacksonTypeRef<BetaEnvironmentDeleteResponse>(),
            )

        assertThat(roundtrippedBetaEnvironmentDeleteResponse)
            .isEqualTo(betaEnvironmentDeleteResponse)
    }
}

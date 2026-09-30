package com.anthropic.models.beta.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSpendLimitScopedApiKeyActorTest {

    @Test
    fun create() {
        val betaSpendLimitScopedApiKeyActor =
            BetaSpendLimitScopedApiKeyActor.of("scoped_api_key_id")

        assertThat(betaSpendLimitScopedApiKeyActor.scopedApiKeyId()).isEqualTo("scoped_api_key_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSpendLimitScopedApiKeyActor =
            BetaSpendLimitScopedApiKeyActor.of("scoped_api_key_id")

        val roundtrippedBetaSpendLimitScopedApiKeyActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSpendLimitScopedApiKeyActor),
                jacksonTypeRef<BetaSpendLimitScopedApiKeyActor>(),
            )

        assertThat(roundtrippedBetaSpendLimitScopedApiKeyActor)
            .isEqualTo(betaSpendLimitScopedApiKeyActor)
    }
}

package com.anthropic.models.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SpendLimitScopedApiKeyActorTest {

    @Test
    fun create() {
        val spendLimitScopedApiKeyActor = SpendLimitScopedApiKeyActor.of("scoped_api_key_id")

        assertThat(spendLimitScopedApiKeyActor.scopedApiKeyId()).isEqualTo("scoped_api_key_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val spendLimitScopedApiKeyActor = SpendLimitScopedApiKeyActor.of("scoped_api_key_id")

        val roundtrippedSpendLimitScopedApiKeyActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(spendLimitScopedApiKeyActor),
                jacksonTypeRef<SpendLimitScopedApiKeyActor>(),
            )

        assertThat(roundtrippedSpendLimitScopedApiKeyActor).isEqualTo(spendLimitScopedApiKeyActor)
    }
}

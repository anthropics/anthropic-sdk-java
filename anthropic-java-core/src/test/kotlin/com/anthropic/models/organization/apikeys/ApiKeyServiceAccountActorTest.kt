package com.anthropic.models.organization.apikeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ApiKeyServiceAccountActorTest {

    @Test
    fun create() {
        val apiKeyServiceAccountActor =
            ApiKeyServiceAccountActor.of("svac_01Hk3R9TWxq7CfQak00OiVw4")

        assertThat(apiKeyServiceAccountActor.serviceAccountId())
            .isEqualTo("svac_01Hk3R9TWxq7CfQak00OiVw4")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val apiKeyServiceAccountActor =
            ApiKeyServiceAccountActor.of("svac_01Hk3R9TWxq7CfQak00OiVw4")

        val roundtrippedApiKeyServiceAccountActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(apiKeyServiceAccountActor),
                jacksonTypeRef<ApiKeyServiceAccountActor>(),
            )

        assertThat(roundtrippedApiKeyServiceAccountActor).isEqualTo(apiKeyServiceAccountActor)
    }
}

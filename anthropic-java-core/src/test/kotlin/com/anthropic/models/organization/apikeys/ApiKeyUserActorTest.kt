package com.anthropic.models.organization.apikeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ApiKeyUserActorTest {

    @Test
    fun create() {
        val apiKeyUserActor = ApiKeyUserActor.of("user_01WxYzAbCdEfGhJkMnPqRsTu")

        assertThat(apiKeyUserActor.userId()).isEqualTo("user_01WxYzAbCdEfGhJkMnPqRsTu")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val apiKeyUserActor = ApiKeyUserActor.of("user_01WxYzAbCdEfGhJkMnPqRsTu")

        val roundtrippedApiKeyUserActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(apiKeyUserActor),
                jacksonTypeRef<ApiKeyUserActor>(),
            )

        assertThat(roundtrippedApiKeyUserActor).isEqualTo(apiKeyUserActor)
    }
}

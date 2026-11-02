package com.anthropic.models.beta.organization.apikeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaApiKeyUserActorTest {

    @Test
    fun create() {
        val betaApiKeyUserActor = BetaApiKeyUserActor.of("user_01WxYzAbCdEfGhJkMnPqRsTu")

        assertThat(betaApiKeyUserActor.userId()).isEqualTo("user_01WxYzAbCdEfGhJkMnPqRsTu")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaApiKeyUserActor = BetaApiKeyUserActor.of("user_01WxYzAbCdEfGhJkMnPqRsTu")

        val roundtrippedBetaApiKeyUserActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaApiKeyUserActor),
                jacksonTypeRef<BetaApiKeyUserActor>(),
            )

        assertThat(roundtrippedBetaApiKeyUserActor).isEqualTo(betaApiKeyUserActor)
    }
}

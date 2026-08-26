// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.apikeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaApiKeyServiceAccountActorTest {

    @Test
    fun create() {
        val betaApiKeyServiceAccountActor =
            BetaApiKeyServiceAccountActor.of("svac_01Hk3R9TWxq7CfQak00OiVw4")

        assertThat(betaApiKeyServiceAccountActor.serviceAccountId())
            .isEqualTo("svac_01Hk3R9TWxq7CfQak00OiVw4")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaApiKeyServiceAccountActor =
            BetaApiKeyServiceAccountActor.of("svac_01Hk3R9TWxq7CfQak00OiVw4")

        val roundtrippedBetaApiKeyServiceAccountActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaApiKeyServiceAccountActor),
                jacksonTypeRef<BetaApiKeyServiceAccountActor>(),
            )

        assertThat(roundtrippedBetaApiKeyServiceAccountActor)
            .isEqualTo(betaApiKeyServiceAccountActor)
    }
}

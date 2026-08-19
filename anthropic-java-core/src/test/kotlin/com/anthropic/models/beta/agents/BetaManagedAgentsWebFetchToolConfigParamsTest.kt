// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWebFetchToolConfigParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsWebFetchToolConfigParams =
            BetaManagedAgentsWebFetchToolConfigParams.builder()
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .enabled(true)
                .maxContentTokens(0)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .type(BetaManagedAgentsWebFetchToolConfigParams.Type.WEB_FETCH)
                .build()

        assertThat(betaManagedAgentsWebFetchToolConfigParams.allowedDomains().getOrNull())
            .containsExactly("string")
        assertThat(betaManagedAgentsWebFetchToolConfigParams.blockedDomains().getOrNull())
            .containsExactly("string")
        assertThat(betaManagedAgentsWebFetchToolConfigParams.enabled()).contains(true)
        assertThat(betaManagedAgentsWebFetchToolConfigParams.maxContentTokens()).contains(0)
        assertThat(betaManagedAgentsWebFetchToolConfigParams.permissionPolicy())
            .contains(
                BetaManagedAgentsWebFetchToolConfigParams.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                )
            )
        assertThat(betaManagedAgentsWebFetchToolConfigParams.type())
            .contains(BetaManagedAgentsWebFetchToolConfigParams.Type.WEB_FETCH)
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaManagedAgentsWebFetchToolConfigParams =
            BetaManagedAgentsWebFetchToolConfigParams.builder().build()

        val betaManagedAgentsWebFetchToolConfigParams =
            baseBetaManagedAgentsWebFetchToolConfigParams
                .toBuilder()
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .build()

        assertThat(betaManagedAgentsWebFetchToolConfigParams.allowedDomains().getOrNull())
            .containsExactly("string")
        assertThat(betaManagedAgentsWebFetchToolConfigParams.blockedDomains().getOrNull())
            .containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWebFetchToolConfigParams =
            BetaManagedAgentsWebFetchToolConfigParams.builder()
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .enabled(true)
                .maxContentTokens(0)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .type(BetaManagedAgentsWebFetchToolConfigParams.Type.WEB_FETCH)
                .build()

        val roundtrippedBetaManagedAgentsWebFetchToolConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWebFetchToolConfigParams),
                jacksonTypeRef<BetaManagedAgentsWebFetchToolConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWebFetchToolConfigParams)
            .isEqualTo(betaManagedAgentsWebFetchToolConfigParams)
    }
}

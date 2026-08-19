// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWebFetchToolConfigTest {

    @Test
    fun create() {
        val betaManagedAgentsWebFetchToolConfig =
            BetaManagedAgentsWebFetchToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .maxContentTokens(0)
                .build()

        assertThat(betaManagedAgentsWebFetchToolConfig.enabled()).isEqualTo(true)
        assertThat(betaManagedAgentsWebFetchToolConfig.permissionPolicy())
            .isEqualTo(
                BetaManagedAgentsWebFetchToolConfig.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                )
            )
        assertThat(betaManagedAgentsWebFetchToolConfig.allowedDomains().getOrNull())
            .containsExactly("string")
        assertThat(betaManagedAgentsWebFetchToolConfig.blockedDomains().getOrNull())
            .containsExactly("string")
        assertThat(betaManagedAgentsWebFetchToolConfig.maxContentTokens()).contains(0)
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaManagedAgentsWebFetchToolConfig =
            BetaManagedAgentsWebFetchToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        val betaManagedAgentsWebFetchToolConfig =
            baseBetaManagedAgentsWebFetchToolConfig
                .toBuilder()
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .build()

        assertThat(betaManagedAgentsWebFetchToolConfig.allowedDomains().getOrNull())
            .containsExactly("string")
        assertThat(betaManagedAgentsWebFetchToolConfig.blockedDomains().getOrNull())
            .containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWebFetchToolConfig =
            BetaManagedAgentsWebFetchToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .maxContentTokens(0)
                .build()

        val roundtrippedBetaManagedAgentsWebFetchToolConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWebFetchToolConfig),
                jacksonTypeRef<BetaManagedAgentsWebFetchToolConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWebFetchToolConfig)
            .isEqualTo(betaManagedAgentsWebFetchToolConfig)
    }
}

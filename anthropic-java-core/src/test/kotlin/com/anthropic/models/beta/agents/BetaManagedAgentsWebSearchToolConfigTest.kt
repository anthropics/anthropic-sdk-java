// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWebSearchToolConfigTest {

    @Test
    fun create() {
        val betaManagedAgentsWebSearchToolConfig =
            BetaManagedAgentsWebSearchToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .userLocation(
                    BetaManagedAgentsUserLocation.builder()
                        .city("x")
                        .country("country")
                        .region("x")
                        .timezone("x")
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsWebSearchToolConfig.enabled()).isEqualTo(true)
        assertThat(betaManagedAgentsWebSearchToolConfig.permissionPolicy())
            .isEqualTo(
                BetaManagedAgentsWebSearchToolConfig.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                )
            )
        assertThat(betaManagedAgentsWebSearchToolConfig.allowedDomains().getOrNull())
            .containsExactly("string")
        assertThat(betaManagedAgentsWebSearchToolConfig.blockedDomains().getOrNull())
            .containsExactly("string")
        assertThat(betaManagedAgentsWebSearchToolConfig.userLocation())
            .contains(
                BetaManagedAgentsUserLocation.builder()
                    .city("x")
                    .country("country")
                    .region("x")
                    .timezone("x")
                    .build()
            )
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaManagedAgentsWebSearchToolConfig =
            BetaManagedAgentsWebSearchToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        val betaManagedAgentsWebSearchToolConfig =
            baseBetaManagedAgentsWebSearchToolConfig
                .toBuilder()
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .build()

        assertThat(betaManagedAgentsWebSearchToolConfig.allowedDomains().getOrNull())
            .containsExactly("string")
        assertThat(betaManagedAgentsWebSearchToolConfig.blockedDomains().getOrNull())
            .containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWebSearchToolConfig =
            BetaManagedAgentsWebSearchToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .userLocation(
                    BetaManagedAgentsUserLocation.builder()
                        .city("x")
                        .country("country")
                        .region("x")
                        .timezone("x")
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsWebSearchToolConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWebSearchToolConfig),
                jacksonTypeRef<BetaManagedAgentsWebSearchToolConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWebSearchToolConfig)
            .isEqualTo(betaManagedAgentsWebSearchToolConfig)
    }
}

// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWebSearchToolConfigParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsWebSearchToolConfigParams =
            BetaManagedAgentsWebSearchToolConfigParams.builder()
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .type(BetaManagedAgentsWebSearchToolConfigParams.Type.WEB_SEARCH)
                .userLocation(
                    BetaManagedAgentsUserLocation.builder()
                        .city("x")
                        .country("country")
                        .region("x")
                        .timezone("x")
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsWebSearchToolConfigParams.allowedDomains().getOrNull())
            .containsExactly("string")
        assertThat(betaManagedAgentsWebSearchToolConfigParams.blockedDomains().getOrNull())
            .containsExactly("string")
        assertThat(betaManagedAgentsWebSearchToolConfigParams.enabled()).contains(true)
        assertThat(betaManagedAgentsWebSearchToolConfigParams.permissionPolicy())
            .contains(
                BetaManagedAgentsWebSearchToolConfigParams.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                )
            )
        assertThat(betaManagedAgentsWebSearchToolConfigParams.type())
            .contains(BetaManagedAgentsWebSearchToolConfigParams.Type.WEB_SEARCH)
        assertThat(betaManagedAgentsWebSearchToolConfigParams.userLocation())
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
        val baseBetaManagedAgentsWebSearchToolConfigParams =
            BetaManagedAgentsWebSearchToolConfigParams.builder().build()

        val betaManagedAgentsWebSearchToolConfigParams =
            baseBetaManagedAgentsWebSearchToolConfigParams
                .toBuilder()
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .build()

        assertThat(betaManagedAgentsWebSearchToolConfigParams.allowedDomains().getOrNull())
            .containsExactly("string")
        assertThat(betaManagedAgentsWebSearchToolConfigParams.blockedDomains().getOrNull())
            .containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWebSearchToolConfigParams =
            BetaManagedAgentsWebSearchToolConfigParams.builder()
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .type(BetaManagedAgentsWebSearchToolConfigParams.Type.WEB_SEARCH)
                .userLocation(
                    BetaManagedAgentsUserLocation.builder()
                        .city("x")
                        .country("country")
                        .region("x")
                        .timezone("x")
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsWebSearchToolConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWebSearchToolConfigParams),
                jacksonTypeRef<BetaManagedAgentsWebSearchToolConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWebSearchToolConfigParams)
            .isEqualTo(betaManagedAgentsWebSearchToolConfigParams)
    }
}

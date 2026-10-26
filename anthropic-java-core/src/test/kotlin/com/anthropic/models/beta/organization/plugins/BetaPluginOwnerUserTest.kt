package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPluginOwnerUserTest {

    @Test
    fun create() {
        val betaPluginOwnerUser = BetaPluginOwnerUser.of("user_01WxYzAbCdEfGhJkMnPqRsTu")

        assertThat(betaPluginOwnerUser.userId()).isEqualTo("user_01WxYzAbCdEfGhJkMnPqRsTu")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPluginOwnerUser = BetaPluginOwnerUser.of("user_01WxYzAbCdEfGhJkMnPqRsTu")

        val roundtrippedBetaPluginOwnerUser =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPluginOwnerUser),
                jacksonTypeRef<BetaPluginOwnerUser>(),
            )

        assertThat(roundtrippedBetaPluginOwnerUser).isEqualTo(betaPluginOwnerUser)
    }
}

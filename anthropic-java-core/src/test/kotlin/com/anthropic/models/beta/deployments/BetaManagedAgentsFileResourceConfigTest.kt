// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsFileResourceConfigTest {

    @Test
    fun create() {
        val betaManagedAgentsFileResourceConfig =
            BetaManagedAgentsFileResourceConfig.builder()
                .fileId("file_id")
                .type(BetaManagedAgentsFileResourceConfig.Type.FILE)
                .mountPath("mount_path")
                .build()

        assertThat(betaManagedAgentsFileResourceConfig.fileId()).isEqualTo("file_id")
        assertThat(betaManagedAgentsFileResourceConfig.type())
            .isEqualTo(BetaManagedAgentsFileResourceConfig.Type.FILE)
        assertThat(betaManagedAgentsFileResourceConfig.mountPath()).contains("mount_path")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsFileResourceConfig =
            BetaManagedAgentsFileResourceConfig.builder()
                .fileId("file_id")
                .type(BetaManagedAgentsFileResourceConfig.Type.FILE)
                .mountPath("mount_path")
                .build()

        val roundtrippedBetaManagedAgentsFileResourceConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsFileResourceConfig),
                jacksonTypeRef<BetaManagedAgentsFileResourceConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsFileResourceConfig)
            .isEqualTo(betaManagedAgentsFileResourceConfig)
    }
}

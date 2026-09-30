package com.anthropic.models.organization.externalkeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GcpExternalKeyConfigTest {

    @Test
    fun create() {
        val gcpExternalKeyConfig =
            GcpExternalKeyConfig.of(
                "projects/my-proj/locations/us/keyRings/my-ring/cryptoKeys/my-key"
            )

        assertThat(gcpExternalKeyConfig.keyName())
            .isEqualTo("projects/my-proj/locations/us/keyRings/my-ring/cryptoKeys/my-key")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val gcpExternalKeyConfig =
            GcpExternalKeyConfig.of(
                "projects/my-proj/locations/us/keyRings/my-ring/cryptoKeys/my-key"
            )

        val roundtrippedGcpExternalKeyConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(gcpExternalKeyConfig),
                jacksonTypeRef<GcpExternalKeyConfig>(),
            )

        assertThat(roundtrippedGcpExternalKeyConfig).isEqualTo(gcpExternalKeyConfig)
    }
}

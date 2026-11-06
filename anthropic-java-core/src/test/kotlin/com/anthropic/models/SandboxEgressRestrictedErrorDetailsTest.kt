package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SandboxEgressRestrictedErrorDetailsTest {

    @Test
    fun create() {
        val sandboxEgressRestrictedErrorDetails =
            SandboxEgressRestrictedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val sandboxEgressRestrictedErrorDetails =
            SandboxEgressRestrictedErrorDetails.builder().build()

        val roundtrippedSandboxEgressRestrictedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(sandboxEgressRestrictedErrorDetails),
                jacksonTypeRef<SandboxEgressRestrictedErrorDetails>(),
            )

        assertThat(roundtrippedSandboxEgressRestrictedErrorDetails)
            .isEqualTo(sandboxEgressRestrictedErrorDetails)
    }
}

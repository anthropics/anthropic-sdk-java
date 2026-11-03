package com.anthropic.models.organization.externalkeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExternalKeyInWorkspacePolicyErrorDetailsTest {

    @Test
    fun create() {
        val externalKeyInWorkspacePolicyErrorDetails =
            ExternalKeyInWorkspacePolicyErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val externalKeyInWorkspacePolicyErrorDetails =
            ExternalKeyInWorkspacePolicyErrorDetails.builder().build()

        val roundtrippedExternalKeyInWorkspacePolicyErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(externalKeyInWorkspacePolicyErrorDetails),
                jacksonTypeRef<ExternalKeyInWorkspacePolicyErrorDetails>(),
            )

        assertThat(roundtrippedExternalKeyInWorkspacePolicyErrorDetails)
            .isEqualTo(externalKeyInWorkspacePolicyErrorDetails)
    }
}

// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsFileResourceParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsFileResourceParams =
            BetaManagedAgentsFileResourceParams.builder()
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                .type(BetaManagedAgentsFileResourceParams.Type.FILE)
                .mountPath("/uploads/receipt.pdf")
                .build()

        assertThat(betaManagedAgentsFileResourceParams.fileId())
            .isEqualTo("file_011CNha8iCJcU1wXNR6q4V8w")
        assertThat(betaManagedAgentsFileResourceParams.type())
            .isEqualTo(BetaManagedAgentsFileResourceParams.Type.FILE)
        assertThat(betaManagedAgentsFileResourceParams.mountPath()).contains("/uploads/receipt.pdf")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsFileResourceParams =
            BetaManagedAgentsFileResourceParams.builder()
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                .type(BetaManagedAgentsFileResourceParams.Type.FILE)
                .mountPath("/uploads/receipt.pdf")
                .build()

        val roundtrippedBetaManagedAgentsFileResourceParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsFileResourceParams),
                jacksonTypeRef<BetaManagedAgentsFileResourceParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsFileResourceParams)
            .isEqualTo(betaManagedAgentsFileResourceParams)
    }
}

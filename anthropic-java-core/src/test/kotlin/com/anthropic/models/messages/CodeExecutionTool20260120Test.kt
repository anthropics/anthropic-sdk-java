// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CodeExecutionTool20260120Test {

    @Test
    fun create() {
        val codeExecutionTool20260120 =
            CodeExecutionTool20260120.builder()
                .addAllowedCaller(CodeExecutionTool20260120.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        assertThat(codeExecutionTool20260120.allowedCallers().getOrNull())
            .containsExactly(CodeExecutionTool20260120.AllowedCaller.DIRECT)
        assertThat(codeExecutionTool20260120.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(codeExecutionTool20260120.deferLoading()).contains(true)
        assertThat(codeExecutionTool20260120.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val codeExecutionTool20260120 =
            CodeExecutionTool20260120.builder()
                .addAllowedCaller(CodeExecutionTool20260120.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val roundtrippedCodeExecutionTool20260120 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(codeExecutionTool20260120),
                jacksonTypeRef<CodeExecutionTool20260120>(),
            )

        assertThat(roundtrippedCodeExecutionTool20260120).isEqualTo(codeExecutionTool20260120)
    }
}

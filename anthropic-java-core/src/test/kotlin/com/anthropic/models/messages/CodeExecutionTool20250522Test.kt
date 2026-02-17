// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CodeExecutionTool20250522Test {

    @Test
    fun create() {
        val codeExecutionTool20250522 =
            CodeExecutionTool20250522.builder()
                .addAllowedCaller(CodeExecutionTool20250522.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        assertThat(codeExecutionTool20250522.allowedCallers().getOrNull())
            .containsExactly(CodeExecutionTool20250522.AllowedCaller.DIRECT)
        assertThat(codeExecutionTool20250522.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(codeExecutionTool20250522.deferLoading()).contains(true)
        assertThat(codeExecutionTool20250522.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val codeExecutionTool20250522 =
            CodeExecutionTool20250522.builder()
                .addAllowedCaller(CodeExecutionTool20250522.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val roundtrippedCodeExecutionTool20250522 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(codeExecutionTool20250522),
                jacksonTypeRef<CodeExecutionTool20250522>(),
            )

        assertThat(roundtrippedCodeExecutionTool20250522).isEqualTo(codeExecutionTool20250522)
    }
}

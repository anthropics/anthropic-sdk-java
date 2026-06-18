// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CodeExecutionTool20260521Test {

    @Test
    fun create() {
        val codeExecutionTool20260521 =
            CodeExecutionTool20260521.builder()
                .addAllowedCaller(CodeExecutionTool20260521.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        assertThat(codeExecutionTool20260521.allowedCallers().getOrNull())
            .containsExactly(CodeExecutionTool20260521.AllowedCaller.DIRECT)
        assertThat(codeExecutionTool20260521.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(codeExecutionTool20260521.deferLoading()).contains(true)
        assertThat(codeExecutionTool20260521.strict()).contains(true)
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseCodeExecutionTool20260521 = CodeExecutionTool20260521.builder().build()

        val codeExecutionTool20260521 =
            baseCodeExecutionTool20260521
                .toBuilder()
                .addAllowedCaller(CodeExecutionTool20260521.AllowedCaller.DIRECT)
                .build()

        assertThat(codeExecutionTool20260521.allowedCallers().getOrNull())
            .containsExactly(CodeExecutionTool20260521.AllowedCaller.DIRECT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val codeExecutionTool20260521 =
            CodeExecutionTool20260521.builder()
                .addAllowedCaller(CodeExecutionTool20260521.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val roundtrippedCodeExecutionTool20260521 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(codeExecutionTool20260521),
                jacksonTypeRef<CodeExecutionTool20260521>(),
            )

        assertThat(roundtrippedCodeExecutionTool20260521).isEqualTo(codeExecutionTool20260521)
    }
}

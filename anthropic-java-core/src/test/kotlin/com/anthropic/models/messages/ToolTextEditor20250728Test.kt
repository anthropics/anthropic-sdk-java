// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolTextEditor20250728Test {

    @Test
    fun create() {
        val toolTextEditor20250728 =
            ToolTextEditor20250728.builder()
                .addAllowedCaller(ToolTextEditor20250728.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .addInputExample(
                    ToolTextEditor20250728.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .maxCharacters(1L)
                .strict(true)
                .build()

        assertThat(toolTextEditor20250728.allowedCallers().getOrNull())
            .containsExactly(ToolTextEditor20250728.AllowedCaller.DIRECT)
        assertThat(toolTextEditor20250728.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(toolTextEditor20250728.deferLoading()).contains(true)
        assertThat(toolTextEditor20250728.inputExamples().getOrNull())
            .containsExactly(
                ToolTextEditor20250728.InputExample.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(toolTextEditor20250728.maxCharacters()).contains(1L)
        assertThat(toolTextEditor20250728.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolTextEditor20250728 =
            ToolTextEditor20250728.builder()
                .addAllowedCaller(ToolTextEditor20250728.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .addInputExample(
                    ToolTextEditor20250728.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .maxCharacters(1L)
                .strict(true)
                .build()

        val roundtrippedToolTextEditor20250728 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolTextEditor20250728),
                jacksonTypeRef<ToolTextEditor20250728>(),
            )

        assertThat(roundtrippedToolTextEditor20250728).isEqualTo(toolTextEditor20250728)
    }
}

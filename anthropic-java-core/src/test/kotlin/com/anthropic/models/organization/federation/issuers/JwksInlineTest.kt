package com.anthropic.models.organization.federation.issuers

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class JwksInlineTest {

    @Test
    fun create() {
        val jwksInline =
            JwksInline.builder()
                .addKey(
                    JwksInline.Key.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .build()

        assertThat(jwksInline.keys())
            .containsExactly(
                JwksInline.Key.builder().putAdditionalProperty("foo", JsonValue.from("bar")).build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val jwksInline =
            JwksInline.builder()
                .addKey(
                    JwksInline.Key.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .build()

        val roundtrippedJwksInline =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(jwksInline),
                jacksonTypeRef<JwksInline>(),
            )

        assertThat(roundtrippedJwksInline).isEqualTo(jwksInline)
    }
}

// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ContainerTest {

    @Test
    fun create() {
        val container =
            Container.builder()
                .id("id")
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(container.id()).isEqualTo("id")
        assertThat(container.expiresAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val container =
            Container.builder()
                .id("id")
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedContainer =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(container),
                jacksonTypeRef<Container>(),
            )

        assertThat(roundtrippedContainer).isEqualTo(container)
    }
}

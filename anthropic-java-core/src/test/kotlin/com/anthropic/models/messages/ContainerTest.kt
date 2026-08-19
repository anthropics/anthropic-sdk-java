// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ContainerTest {

    @Test
    fun create() {
        val container =
            Container.builder()
                .id("container_011CpZohnwH4vuy7gazohgSP")
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addSkill(
                    ContainerSkill.builder()
                        .skillId("pdf")
                        .type(ContainerSkill.Type.ANTHROPIC)
                        .version("latest")
                        .build()
                )
                .build()

        assertThat(container.id()).isEqualTo("container_011CpZohnwH4vuy7gazohgSP")
        assertThat(container.expiresAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(container.skills().getOrNull())
            .containsExactly(
                ContainerSkill.builder()
                    .skillId("pdf")
                    .type(ContainerSkill.Type.ANTHROPIC)
                    .version("latest")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val container =
            Container.builder()
                .id("container_011CpZohnwH4vuy7gazohgSP")
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addSkill(
                    ContainerSkill.builder()
                        .skillId("pdf")
                        .type(ContainerSkill.Type.ANTHROPIC)
                        .version("latest")
                        .build()
                )
                .build()

        val roundtrippedContainer =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(container),
                jacksonTypeRef<Container>(),
            )

        assertThat(roundtrippedContainer).isEqualTo(container)
    }
}

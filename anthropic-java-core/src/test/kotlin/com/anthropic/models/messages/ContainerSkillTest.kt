// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ContainerSkillTest {

    @Test
    fun create() {
        val containerSkill =
            ContainerSkill.builder()
                .skillId("pdf")
                .type(ContainerSkill.Type.ANTHROPIC)
                .version("latest")
                .build()

        assertThat(containerSkill.skillId()).isEqualTo("pdf")
        assertThat(containerSkill.type()).isEqualTo(ContainerSkill.Type.ANTHROPIC)
        assertThat(containerSkill.version()).isEqualTo("latest")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val containerSkill =
            ContainerSkill.builder()
                .skillId("pdf")
                .type(ContainerSkill.Type.ANTHROPIC)
                .version("latest")
                .build()

        val roundtrippedContainerSkill =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(containerSkill),
                jacksonTypeRef<ContainerSkill>(),
            )

        assertThat(roundtrippedContainerSkill).isEqualTo(containerSkill)
    }
}

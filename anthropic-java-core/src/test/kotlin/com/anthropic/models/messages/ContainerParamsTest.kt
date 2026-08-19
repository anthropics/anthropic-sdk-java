// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ContainerParamsTest {

    @Test
    fun create() {
        val containerParams =
            ContainerParams.builder()
                .id("id")
                .addSkill(
                    SkillParams.builder()
                        .skillId("pdf")
                        .type(SkillParams.Type.ANTHROPIC)
                        .version("latest")
                        .build()
                )
                .build()

        assertThat(containerParams.id()).contains("id")
        assertThat(containerParams.skills().getOrNull())
            .containsExactly(
                SkillParams.builder()
                    .skillId("pdf")
                    .type(SkillParams.Type.ANTHROPIC)
                    .version("latest")
                    .build()
            )
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseContainerParams = ContainerParams.builder().build()

        val containerParams =
            baseContainerParams
                .toBuilder()
                .addSkill(
                    SkillParams.builder()
                        .skillId("pdf")
                        .type(SkillParams.Type.ANTHROPIC)
                        .version("latest")
                        .build()
                )
                .build()

        assertThat(containerParams.skills().getOrNull())
            .containsExactly(
                SkillParams.builder()
                    .skillId("pdf")
                    .type(SkillParams.Type.ANTHROPIC)
                    .version("latest")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val containerParams =
            ContainerParams.builder()
                .id("id")
                .addSkill(
                    SkillParams.builder()
                        .skillId("pdf")
                        .type(SkillParams.Type.ANTHROPIC)
                        .version("latest")
                        .build()
                )
                .build()

        val roundtrippedContainerParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(containerParams),
                jacksonTypeRef<ContainerParams>(),
            )

        assertThat(roundtrippedContainerParams).isEqualTo(containerParams)
    }
}

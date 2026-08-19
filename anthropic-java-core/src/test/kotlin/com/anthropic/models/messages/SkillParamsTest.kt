// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SkillParamsTest {

    @Test
    fun create() {
        val skillParams =
            SkillParams.builder()
                .skillId("pdf")
                .type(SkillParams.Type.ANTHROPIC)
                .version("latest")
                .build()

        assertThat(skillParams.skillId()).isEqualTo("pdf")
        assertThat(skillParams.type()).isEqualTo(SkillParams.Type.ANTHROPIC)
        assertThat(skillParams.version()).contains("latest")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val skillParams =
            SkillParams.builder()
                .skillId("pdf")
                .type(SkillParams.Type.ANTHROPIC)
                .version("latest")
                .build()

        val roundtrippedSkillParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(skillParams),
                jacksonTypeRef<SkillParams>(),
            )

        assertThat(roundtrippedSkillParams).isEqualTo(skillParams)
    }
}

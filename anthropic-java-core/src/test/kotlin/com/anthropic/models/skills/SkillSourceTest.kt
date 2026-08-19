// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.skills

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SkillSourceTest {

    @Test
    fun create() {
        val skillSource = SkillSource.of(SkillSource.Type.CUSTOM)

        assertThat(skillSource.type()).isEqualTo(SkillSource.Type.CUSTOM)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val skillSource = SkillSource.of(SkillSource.Type.CUSTOM)

        val roundtrippedSkillSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(skillSource),
                jacksonTypeRef<SkillSource>(),
            )

        assertThat(roundtrippedSkillSource).isEqualTo(skillSource)
    }
}

// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.files

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaFileScopeTest {

    @Test
    fun create() {
        val betaFileScope = BetaFileScope.builder().id("id").build()

        assertThat(betaFileScope.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFileScope = BetaFileScope.builder().id("id").build()

        val roundtrippedBetaFileScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFileScope),
                jacksonTypeRef<BetaFileScope>(),
            )

        assertThat(roundtrippedBetaFileScope).isEqualTo(betaFileScope)
    }
}

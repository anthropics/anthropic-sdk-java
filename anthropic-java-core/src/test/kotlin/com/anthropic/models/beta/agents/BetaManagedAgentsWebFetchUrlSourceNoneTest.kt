package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWebFetchUrlSourceNoneTest {

    @Test
    fun create() {
        val betaManagedAgentsWebFetchUrlSourceNone =
            BetaManagedAgentsWebFetchUrlSourceNone.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWebFetchUrlSourceNone =
            BetaManagedAgentsWebFetchUrlSourceNone.builder().build()

        val roundtrippedBetaManagedAgentsWebFetchUrlSourceNone =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWebFetchUrlSourceNone),
                jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceNone>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWebFetchUrlSourceNone)
            .isEqualTo(betaManagedAgentsWebFetchUrlSourceNone)
    }
}

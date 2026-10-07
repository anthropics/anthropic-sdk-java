package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWebFetchUrlSourceAllTest {

    @Test
    fun create() {
        val betaManagedAgentsWebFetchUrlSourceAll =
            BetaManagedAgentsWebFetchUrlSourceAll.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWebFetchUrlSourceAll =
            BetaManagedAgentsWebFetchUrlSourceAll.builder().build()

        val roundtrippedBetaManagedAgentsWebFetchUrlSourceAll =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWebFetchUrlSourceAll),
                jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceAll>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWebFetchUrlSourceAll)
            .isEqualTo(betaManagedAgentsWebFetchUrlSourceAll)
    }
}

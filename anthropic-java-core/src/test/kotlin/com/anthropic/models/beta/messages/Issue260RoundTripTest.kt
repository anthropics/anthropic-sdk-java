package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Issue260RoundTripTest {

    private val jsonMapper = jsonMapper()

    @Test
    fun `empty assistant response is omitted before a subsequent request message`() {
        val message =
            jsonMapper.readValue(
                """{"role":"assistant","content":[]}""",
                jacksonTypeRef<BetaMessage>(),
            )

        val paramsBuilder =
            MessageCreateParams.builder()
                .model(Model.CLAUDE_SONNET_4_20250514)
                .maxTokens(64)
                .addUserMessage("first question")
                .addMessage(message)

        val finalAssistantJson =
            jsonMapper.valueToTree<com.fasterxml.jackson.databind.JsonNode>(
                paramsBuilder.build()._body()
            )

        assertThat(finalAssistantJson.at("/messages/1/role").textValue()).isEqualTo("assistant")
        assertThat(finalAssistantJson.at("/messages/1/content")).isEmpty()

        val followUpJson =
            jsonMapper.valueToTree<com.fasterxml.jackson.databind.JsonNode>(
                paramsBuilder.addUserMessage("follow-up question").build()._body()
            )

        assertThat(followUpJson.at("/messages").size()).isEqualTo(2)
        assertThat(followUpJson.at("/messages/0/content").textValue()).isEqualTo("first question")
        assertThat(followUpJson.at("/messages/1/content").textValue())
            .isEqualTo("follow-up question")
    }
}

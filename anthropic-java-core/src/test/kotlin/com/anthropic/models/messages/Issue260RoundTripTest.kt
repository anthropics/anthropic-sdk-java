package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Issue260RoundTripTest {

    private val jsonMapper = jsonMapper()

    @Test
    fun `web search response can be converted back into request params`() {
        val message =
            readMessage(
                """
                {
                  "role": "assistant",
                  "content": [{
                    "type": "web_search_tool_result",
                    "tool_use_id": "srvtoolu_123",
                    "caller": {"type": "direct"},
                    "content": [{
                      "type": "web_search_result",
                      "url": "https://example.com",
                      "title": "Example",
                      "encrypted_content": "encrypted"
                    }]
                  }]
                }
                """
            )

        val json =
            jsonMapper.valueToTree<com.fasterxml.jackson.databind.JsonNode>(message.toParam())

        assertThat(json.at("/content/0/content/0/encrypted_content").textValue())
            .isEqualTo("encrypted")
    }

    @Test
    fun `missing web search content no longer throws while converting to params`() {
        val message =
            readMessage(
                """
                {
                  "role": "assistant",
                  "content": [{
                    "type": "web_search_tool_result",
                    "tool_use_id": "srvtoolu_123",
                    "caller": {"type": "direct"}
                  }]
                }
                """
            )

        val json =
            jsonMapper.valueToTree<com.fasterxml.jackson.databind.JsonNode>(message.toParam())

        assertThat(json.at("/content/0").has("content")).isFalse()
    }

    @Test
    fun `empty assistant response is omitted before a subsequent request message`() {
        val message = readMessage("""{"role":"assistant","content":[]}""")

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

    private fun readMessage(json: String): Message =
        jsonMapper.readValue(json.trimIndent(), jacksonTypeRef<Message>())
}

// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services

import com.anthropic.client.AnthropicClient
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.models.CacheControlEphemeral
import com.anthropic.models.CitationCharLocation
import com.anthropic.models.CitationCharLocationParam
import com.anthropic.models.Message
import com.anthropic.models.MessageCreateParams
import com.anthropic.models.Metadata
import com.anthropic.models.Model
import com.anthropic.models.TextBlock
import com.anthropic.models.TextBlockParam
import com.anthropic.models.Tool
import com.anthropic.models.ToolChoiceAuto
import com.anthropic.models.Usage
import com.fasterxml.jackson.databind.json.JsonMapper
import com.github.tomakehurst.wiremock.client.WireMock.anyUrl
import com.github.tomakehurst.wiremock.client.WireMock.equalTo
import com.github.tomakehurst.wiremock.client.WireMock.matchingJsonPath
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@WireMockTest
class ServiceParamsTest {

    private val JSON_MAPPER: JsonMapper = jsonMapper()

    private lateinit var client: AnthropicClient

    @BeforeEach
    fun beforeEach(wmRuntimeInfo: WireMockRuntimeInfo) {
        client =
            AnthropicOkHttpClient.builder()
                .apiKey("my-anthropic-api-key")
                .authToken("my-auth-token")
                .baseUrl(wmRuntimeInfo.getHttpBaseUrl())
                .build()
    }

    @Test
    fun messagesCreateWithAdditionalParams() {
        val additionalHeaders = mutableMapOf<String, List<String>>()

        additionalHeaders.put("x-test-header", listOf("abc1234"))

        val additionalQueryParams = mutableMapOf<String, List<String>>()

        additionalQueryParams.put("test_query_param", listOf("def567"))

        val additionalBodyProperties = mutableMapOf<String, JsonValue>()

        additionalBodyProperties.put("testBodyProperty", JsonValue.from("ghi890"))

        val params =
            MessageCreateParams.builder()
                .maxTokens(1024L)
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                .metadata(Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
                .addStopSequence("string")
                .systemOfTextBlockParams(
                    listOf(
                        TextBlockParam.builder()
                            .text("Today's date is 2024-06-01.")
                            .type(TextBlockParam.Type.TEXT)
                            .cacheControl(
                                CacheControlEphemeral.builder()
                                    .type(CacheControlEphemeral.Type.EPHEMERAL)
                                    .build()
                            )
                            .addCitation(
                                CitationCharLocationParam.builder()
                                    .citedText("cited_text")
                                    .documentIndex(0L)
                                    .documentTitle("x")
                                    .endCharIndex(0L)
                                    .startCharIndex(0L)
                                    .type(CitationCharLocationParam.Type.CHAR_LOCATION)
                                    .build()
                            )
                            .build()
                    )
                )
                .temperature(1.0)
                .toolChoice(
                    ToolChoiceAuto.builder()
                        .type(ToolChoiceAuto.Type.AUTO)
                        .disableParallelToolUse(true)
                        .build()
                )
                .addTool(
                    Tool.builder()
                        .inputSchema(
                            Tool.InputSchema.builder()
                                .type(Tool.InputSchema.Type.OBJECT)
                                .properties(
                                    JsonValue.from(
                                        mapOf(
                                            "location" to
                                                mapOf(
                                                    "description" to
                                                        "The city and state, e.g. San Francisco, CA",
                                                    "type" to "string"
                                                ),
                                            "unit" to
                                                mapOf(
                                                    "description" to
                                                        "Unit for the output - one of (celsius, fahrenheit)",
                                                    "type" to "string"
                                                )
                                        )
                                    )
                                )
                                .build()
                        )
                        .name("name")
                        .cacheControl(
                            CacheControlEphemeral.builder()
                                .type(CacheControlEphemeral.Type.EPHEMERAL)
                                .build()
                        )
                        .description("Get the current weather in a given location")
                        .build()
                )
                .topK(5L)
                .topP(0.7)
                .additionalHeaders(additionalHeaders)
                .additionalBodyProperties(additionalBodyProperties)
                .additionalQueryParams(additionalQueryParams)
                .build()

        val apiResponse =
            Message.builder()
                .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                .addContent(
                    TextBlock.builder()
                        .addCitation(
                            CitationCharLocation.builder()
                                .citedText("cited_text")
                                .documentIndex(0L)
                                .documentTitle("document_title")
                                .endCharIndex(0L)
                                .startCharIndex(0L)
                                .type(CitationCharLocation.Type.CHAR_LOCATION)
                                .build()
                        )
                        .text("Hi! My name is Claude.")
                        .type(TextBlock.Type.TEXT)
                        .build()
                )
                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                .role(Message.Role.ASSISTANT)
                .stopReason(Message.StopReason.END_TURN)
                .stopSequence(null)
                .type(Message.Type.MESSAGE)
                .usage(
                    Usage.builder()
                        .cacheCreationInputTokens(2051L)
                        .cacheReadInputTokens(2051L)
                        .inputTokens(2095L)
                        .outputTokens(503L)
                        .build()
                )
                .build()

        stubFor(
            post(anyUrl())
                .withHeader("x-test-header", equalTo("abc1234"))
                .withQueryParam("test_query_param", equalTo("def567"))
                .withRequestBody(matchingJsonPath("$.testBodyProperty", equalTo("ghi890")))
                .willReturn(ok(JSON_MAPPER.writeValueAsString(apiResponse)))
        )

        client.messages().create(params)

        verify(postRequestedFor(anyUrl()))
    }
}

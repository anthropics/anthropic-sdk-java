// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services

import com.anthropic.client.AnthropicClient
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonNull
import com.anthropic.core.JsonString
import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.models.*
import com.fasterxml.jackson.databind.json.JsonMapper
import com.github.tomakehurst.wiremock.client.WireMock.anyUrl
import com.github.tomakehurst.wiremock.client.WireMock.equalTo
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.matchingJsonPath
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.put
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

        additionalBodyProperties.put("testBodyProperty", JsonString.of("ghi890"))

        val params =
            MessageCreateParams.builder()
                .maxTokens(1024L)
                .messages(
                    listOf(
                        MessageParam.builder()
                            .content(MessageParam.Content.ofString("string"))
                            .role(MessageParam.Role.USER)
                            .build()
                    )
                )
                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                .metadata(Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
                .stopSequences(listOf("string"))
                .system(MessageCreateParams.System.ofString("string"))
                .temperature(1.0)
                .toolChoice(
                    ToolChoice.ofToolChoiceAuto(
                        ToolChoiceAuto.builder()
                            .type(ToolChoiceAuto.Type.AUTO)
                            .disableParallelToolUse(true)
                            .build()
                    )
                )
                .tools(
                    listOf(
                        Tool.builder()
                            .inputSchema(
                                Tool.InputSchema.builder()
                                    .type(Tool.InputSchema.Type.OBJECT)
                                    .properties(JsonNull.of())
                                    .build()
                            )
                            .name("x")
                            .description("Get the current weather in a given location")
                            .build()
                    )
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
                .content(
                    listOf(
                        ContentBlock.ofTextBlock(
                            TextBlock.builder().text("text").type(TextBlock.Type.TEXT).build()
                        )
                    )
                )
                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                .role(Message.Role.ASSISTANT)
                .stopReason(Message.StopReason.END_TURN)
                .stopSequence("stop_sequence")
                .type(Message.Type.MESSAGE)
                .usage(Usage.builder().inputTokens(2095L).outputTokens(503L).build())
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

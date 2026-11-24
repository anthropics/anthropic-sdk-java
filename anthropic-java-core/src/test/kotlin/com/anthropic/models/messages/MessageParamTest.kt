package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class MessageParamTest {

    @Test
    fun ofUser() {
        val user =
            UserMessageParam.builder()
                .contentOfContentBlockParams(
                    listOf(
                        ContentBlockParam.ofText(
                            TextBlockParam.builder()
                                .text("What is a quaternion?")
                                .cacheControl(
                                    CacheControlEphemeral.builder()
                                        .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                        .build()
                                )
                                .addCitation(
                                    CitationCharLocationParam.builder()
                                        .citedText("The grass is green. The sky is blue.")
                                        .documentIndex(0L)
                                        .documentTitle("x")
                                        .endCharIndex(0L)
                                        .startCharIndex(0L)
                                        .build()
                                )
                                .build()
                        )
                    )
                )
                .build()

        val messageParam = MessageParam.ofUser(user)

        assertThat(presentVariants(messageParam)).isEqualTo(mapOf("user" to Optional.of(user)))
    }

    @Test
    fun ofUserRoundtrip() {
        val jsonMapper = jsonMapper()
        val messageParam =
            MessageParam.ofUser(
                UserMessageParam.builder()
                    .contentOfContentBlockParams(
                        listOf(
                            ContentBlockParam.ofText(
                                TextBlockParam.builder()
                                    .text("What is a quaternion?")
                                    .cacheControl(
                                        CacheControlEphemeral.builder()
                                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                            .build()
                                    )
                                    .addCitation(
                                        CitationCharLocationParam.builder()
                                            .citedText("The grass is green. The sky is blue.")
                                            .documentIndex(0L)
                                            .documentTitle("x")
                                            .endCharIndex(0L)
                                            .startCharIndex(0L)
                                            .build()
                                    )
                                    .build()
                            )
                        )
                    )
                    .build()
            )

        val roundtrippedMessageParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageParam),
                jacksonTypeRef<MessageParam>(),
            )

        assertThat(roundtrippedMessageParam).isEqualTo(messageParam)
    }

    @Test
    fun ofAssistant() {
        val assistant =
            AssistantMessageParam.builder()
                .contentOfContentBlockParams(
                    listOf(
                        ContentBlockParam.ofText(
                            TextBlockParam.builder()
                                .text("What is a quaternion?")
                                .cacheControl(
                                    CacheControlEphemeral.builder()
                                        .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                        .build()
                                )
                                .addCitation(
                                    CitationCharLocationParam.builder()
                                        .citedText("The grass is green. The sky is blue.")
                                        .documentIndex(0L)
                                        .documentTitle("x")
                                        .endCharIndex(0L)
                                        .startCharIndex(0L)
                                        .build()
                                )
                                .build()
                        )
                    )
                )
                .build()

        val messageParam = MessageParam.ofAssistant(assistant)

        assertThat(presentVariants(messageParam))
            .isEqualTo(mapOf("assistant" to Optional.of(assistant)))
    }

    @Test
    fun ofAssistantRoundtrip() {
        val jsonMapper = jsonMapper()
        val messageParam =
            MessageParam.ofAssistant(
                AssistantMessageParam.builder()
                    .contentOfContentBlockParams(
                        listOf(
                            ContentBlockParam.ofText(
                                TextBlockParam.builder()
                                    .text("What is a quaternion?")
                                    .cacheControl(
                                        CacheControlEphemeral.builder()
                                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                            .build()
                                    )
                                    .addCitation(
                                        CitationCharLocationParam.builder()
                                            .citedText("The grass is green. The sky is blue.")
                                            .documentIndex(0L)
                                            .documentTitle("x")
                                            .endCharIndex(0L)
                                            .startCharIndex(0L)
                                            .build()
                                    )
                                    .build()
                            )
                        )
                    )
                    .build()
            )

        val roundtrippedMessageParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageParam),
                jacksonTypeRef<MessageParam>(),
            )

        assertThat(roundtrippedMessageParam).isEqualTo(messageParam)
    }

    @Test
    fun ofSystem() {
        val system = SystemMessageParam.builder().content("string").build()

        val messageParam = MessageParam.ofSystem(system)

        assertThat(presentVariants(messageParam)).isEqualTo(mapOf("system" to Optional.of(system)))
    }

    @Test
    fun ofSystemRoundtrip() {
        val jsonMapper = jsonMapper()
        val messageParam =
            MessageParam.ofSystem(SystemMessageParam.builder().content("string").build())

        val roundtrippedMessageParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageParam),
                jacksonTypeRef<MessageParam>(),
            )

        assertThat(roundtrippedMessageParam).isEqualTo(messageParam)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val messageParam =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "role" to "unknown_variant",
                            "content" to
                                listOf(
                                    mapOf(
                                        "text" to "What is a quaternion?",
                                        "type" to "text",
                                        "cache_control" to
                                            mapOf("type" to "ephemeral", "ttl" to "5m"),
                                        "citations" to
                                            listOf(
                                                mapOf(
                                                    "cited_text" to
                                                        "The grass is green. The sky is blue.",
                                                    "document_index" to 0,
                                                    "document_title" to "x",
                                                    "end_char_index" to 0,
                                                    "start_char_index" to 0,
                                                    "type" to "char_location",
                                                )
                                            ),
                                    )
                                ),
                        )
                    ),
                    jacksonTypeRef<MessageParam>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { messageParam.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(messageParam.content())
            .isEqualTo(
                MessageContentParam.ofContentBlockParams(
                    listOf(
                        ContentBlockParam.ofText(
                            TextBlockParam.builder()
                                .text("What is a quaternion?")
                                .cacheControl(
                                    CacheControlEphemeral.builder()
                                        .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                        .build()
                                )
                                .addCitation(
                                    CitationCharLocationParam.builder()
                                        .citedText("The grass is green. The sky is blue.")
                                        .documentIndex(0L)
                                        .documentTitle("x")
                                        .endCharIndex(0L)
                                        .startCharIndex(0L)
                                        .build()
                                )
                                .build()
                        )
                    )
                )
            )
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val messageParam = jsonMapper().convertValue(testCase.value, jacksonTypeRef<MessageParam>())

        val e = assertThrows<AnthropicInvalidDataException> { messageParam.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThrows<AnthropicInvalidDataException> { messageParam.content() }
    }

    private fun presentVariants(messageParam: MessageParam): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "user" to messageParam.user(),
                "assistant" to messageParam.assistant(),
                "system" to messageParam.system(),
            )
            .filterValues { it.isPresent }
}

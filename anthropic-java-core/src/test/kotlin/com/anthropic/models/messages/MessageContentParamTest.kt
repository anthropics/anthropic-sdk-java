package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class MessageContentParamTest {

    @Test
    fun ofString() {
        val string = "string"

        val messageContentParam = MessageContentParam.ofString(string)

        assertThat(messageContentParam.string()).contains(string)
        assertThat(messageContentParam.contentBlockParams()).isEmpty
    }

    @Test
    fun ofStringRoundtrip() {
        val jsonMapper = jsonMapper()
        val messageContentParam = MessageContentParam.ofString("string")

        val roundtrippedMessageContentParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageContentParam),
                jacksonTypeRef<MessageContentParam>(),
            )

        assertThat(roundtrippedMessageContentParam).isEqualTo(messageContentParam)
    }

    @Test
    fun ofContentBlockParams() {
        val contentBlockParams =
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

        val messageContentParam = MessageContentParam.ofContentBlockParams(contentBlockParams)

        assertThat(messageContentParam.string()).isEmpty
        assertThat(messageContentParam.contentBlockParams()).contains(contentBlockParams)
    }

    @Test
    fun ofContentBlockParamsRoundtrip() {
        val jsonMapper = jsonMapper()
        val messageContentParam =
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

        val roundtrippedMessageContentParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageContentParam),
                jacksonTypeRef<MessageContentParam>(),
            )

        assertThat(roundtrippedMessageContentParam).isEqualTo(messageContentParam)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        OBJECT(JsonValue.from(mapOf("invalid" to "object"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val messageContentParam =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<MessageContentParam>())

        val e = assertThrows<AnthropicInvalidDataException> { messageContentParam.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}

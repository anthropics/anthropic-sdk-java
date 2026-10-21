package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaMessageParamTest {

    @Test
    fun ofUser() {
        val user =
            BetaUserMessageParam.builder()
                .contentOfContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofText(
                            BetaTextBlockParam.builder()
                                .text("What is a quaternion?")
                                .cacheControl(
                                    BetaCacheControlEphemeral.builder()
                                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                        .build()
                                )
                                .addCitation(
                                    BetaCitationCharLocationParam.builder()
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

        val betaMessageParam = BetaMessageParam.ofUser(user)

        assertThat(betaMessageParam.user()).contains(user)
        assertThat(betaMessageParam.assistant()).isEmpty
        assertThat(betaMessageParam.system()).isEmpty
    }

    @Test
    fun ofUserRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaMessageParam =
            BetaMessageParam.ofUser(
                BetaUserMessageParam.builder()
                    .contentOfContentBlockParams(
                        listOf(
                            BetaContentBlockParam.ofText(
                                BetaTextBlockParam.builder()
                                    .text("What is a quaternion?")
                                    .cacheControl(
                                        BetaCacheControlEphemeral.builder()
                                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                            .build()
                                    )
                                    .addCitation(
                                        BetaCitationCharLocationParam.builder()
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

        val roundtrippedBetaMessageParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageParam),
                jacksonTypeRef<BetaMessageParam>(),
            )

        assertThat(roundtrippedBetaMessageParam).isEqualTo(betaMessageParam)
    }

    @Test
    fun ofAssistant() {
        val assistant =
            BetaAssistantMessageParam.builder()
                .contentOfContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofText(
                            BetaTextBlockParam.builder()
                                .text("What is a quaternion?")
                                .cacheControl(
                                    BetaCacheControlEphemeral.builder()
                                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                        .build()
                                )
                                .addCitation(
                                    BetaCitationCharLocationParam.builder()
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

        val betaMessageParam = BetaMessageParam.ofAssistant(assistant)

        assertThat(betaMessageParam.user()).isEmpty
        assertThat(betaMessageParam.assistant()).contains(assistant)
        assertThat(betaMessageParam.system()).isEmpty
    }

    @Test
    fun ofAssistantRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaMessageParam =
            BetaMessageParam.ofAssistant(
                BetaAssistantMessageParam.builder()
                    .contentOfContentBlockParams(
                        listOf(
                            BetaContentBlockParam.ofText(
                                BetaTextBlockParam.builder()
                                    .text("What is a quaternion?")
                                    .cacheControl(
                                        BetaCacheControlEphemeral.builder()
                                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                            .build()
                                    )
                                    .addCitation(
                                        BetaCitationCharLocationParam.builder()
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

        val roundtrippedBetaMessageParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageParam),
                jacksonTypeRef<BetaMessageParam>(),
            )

        assertThat(roundtrippedBetaMessageParam).isEqualTo(betaMessageParam)
    }

    @Test
    fun ofSystem() {
        val system =
            BetaSystemMessageParam.builder()
                .clearAt(BetaSystemMessageParam.ClearAt.NEXT_USER_MESSAGE)
                .content("string")
                .outputConfig(
                    BetaSystemMessageOutputConfig.builder()
                        .effort(BetaSystemMessageOutputConfig.Effort.LOW)
                        .build()
                )
                .build()

        val betaMessageParam = BetaMessageParam.ofSystem(system)

        assertThat(betaMessageParam.user()).isEmpty
        assertThat(betaMessageParam.assistant()).isEmpty
        assertThat(betaMessageParam.system()).contains(system)
    }

    @Test
    fun ofSystemRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaMessageParam =
            BetaMessageParam.ofSystem(
                BetaSystemMessageParam.builder()
                    .clearAt(BetaSystemMessageParam.ClearAt.NEXT_USER_MESSAGE)
                    .content("string")
                    .outputConfig(
                        BetaSystemMessageOutputConfig.builder()
                            .effort(BetaSystemMessageOutputConfig.Effort.LOW)
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaMessageParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageParam),
                jacksonTypeRef<BetaMessageParam>(),
            )

        assertThat(roundtrippedBetaMessageParam).isEqualTo(betaMessageParam)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaMessageParam =
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
                    jacksonTypeRef<BetaMessageParam>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { betaMessageParam.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaMessageParam.content())
            .contains(
                BetaMessageContentParam.ofContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofText(
                            BetaTextBlockParam.builder()
                                .text("What is a quaternion?")
                                .cacheControl(
                                    BetaCacheControlEphemeral.builder()
                                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                        .build()
                                )
                                .addCitation(
                                    BetaCitationCharLocationParam.builder()
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
        val betaMessageParam =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaMessageParam>())

        val e = assertThrows<AnthropicInvalidDataException> { betaMessageParam.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaMessageParam.content()).isEmpty
    }
}

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

internal class BetaMessageContentParamTest {

    @Test
    fun ofString() {
        val string = "string"

        val betaMessageContentParam = BetaMessageContentParam.ofString(string)

        assertThat(betaMessageContentParam.string()).contains(string)
        assertThat(betaMessageContentParam.contentBlockParams()).isEmpty
    }

    @Test
    fun ofStringRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaMessageContentParam = BetaMessageContentParam.ofString("string")

        val roundtrippedBetaMessageContentParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageContentParam),
                jacksonTypeRef<BetaMessageContentParam>(),
            )

        assertThat(roundtrippedBetaMessageContentParam).isEqualTo(betaMessageContentParam)
    }

    @Test
    fun ofContentBlockParams() {
        val contentBlockParams =
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

        val betaMessageContentParam =
            BetaMessageContentParam.ofContentBlockParams(contentBlockParams)

        assertThat(betaMessageContentParam.string()).isEmpty
        assertThat(betaMessageContentParam.contentBlockParams()).contains(contentBlockParams)
    }

    @Test
    fun ofContentBlockParamsRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaMessageContentParam =
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

        val roundtrippedBetaMessageContentParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageContentParam),
                jacksonTypeRef<BetaMessageContentParam>(),
            )

        assertThat(roundtrippedBetaMessageContentParam).isEqualTo(betaMessageContentParam)
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
        val betaMessageContentParam =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaMessageContentParam>())

        val e = assertThrows<AnthropicInvalidDataException> { betaMessageContentParam.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}

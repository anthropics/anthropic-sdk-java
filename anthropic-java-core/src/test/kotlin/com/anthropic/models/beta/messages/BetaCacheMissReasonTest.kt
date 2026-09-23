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

internal class BetaCacheMissReasonTest {

    @Test
    fun ofModelChanged() {
        val modelChanged = BetaCacheMissModelChanged.of(0L)

        val betaCacheMissReason = BetaCacheMissReason.ofModelChanged(modelChanged)

        assertThat(betaCacheMissReason.modelChanged()).contains(modelChanged)
        assertThat(betaCacheMissReason.systemChanged()).isEmpty
        assertThat(betaCacheMissReason.toolsChanged()).isEmpty
        assertThat(betaCacheMissReason.messagesChanged()).isEmpty
        assertThat(betaCacheMissReason.previousMessageNotFound()).isEmpty
        assertThat(betaCacheMissReason.unavailable()).isEmpty
    }

    @Test
    fun ofModelChangedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaCacheMissReason =
            BetaCacheMissReason.ofModelChanged(BetaCacheMissModelChanged.of(0L))

        val roundtrippedBetaCacheMissReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCacheMissReason),
                jacksonTypeRef<BetaCacheMissReason>(),
            )

        assertThat(roundtrippedBetaCacheMissReason).isEqualTo(betaCacheMissReason)
    }

    @Test
    fun ofSystemChanged() {
        val systemChanged = BetaCacheMissSystemChanged.of(0L)

        val betaCacheMissReason = BetaCacheMissReason.ofSystemChanged(systemChanged)

        assertThat(betaCacheMissReason.modelChanged()).isEmpty
        assertThat(betaCacheMissReason.systemChanged()).contains(systemChanged)
        assertThat(betaCacheMissReason.toolsChanged()).isEmpty
        assertThat(betaCacheMissReason.messagesChanged()).isEmpty
        assertThat(betaCacheMissReason.previousMessageNotFound()).isEmpty
        assertThat(betaCacheMissReason.unavailable()).isEmpty
    }

    @Test
    fun ofSystemChangedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaCacheMissReason =
            BetaCacheMissReason.ofSystemChanged(BetaCacheMissSystemChanged.of(0L))

        val roundtrippedBetaCacheMissReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCacheMissReason),
                jacksonTypeRef<BetaCacheMissReason>(),
            )

        assertThat(roundtrippedBetaCacheMissReason).isEqualTo(betaCacheMissReason)
    }

    @Test
    fun ofToolsChanged() {
        val toolsChanged = BetaCacheMissToolsChanged.of(0L)

        val betaCacheMissReason = BetaCacheMissReason.ofToolsChanged(toolsChanged)

        assertThat(betaCacheMissReason.modelChanged()).isEmpty
        assertThat(betaCacheMissReason.systemChanged()).isEmpty
        assertThat(betaCacheMissReason.toolsChanged()).contains(toolsChanged)
        assertThat(betaCacheMissReason.messagesChanged()).isEmpty
        assertThat(betaCacheMissReason.previousMessageNotFound()).isEmpty
        assertThat(betaCacheMissReason.unavailable()).isEmpty
    }

    @Test
    fun ofToolsChangedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaCacheMissReason =
            BetaCacheMissReason.ofToolsChanged(BetaCacheMissToolsChanged.of(0L))

        val roundtrippedBetaCacheMissReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCacheMissReason),
                jacksonTypeRef<BetaCacheMissReason>(),
            )

        assertThat(roundtrippedBetaCacheMissReason).isEqualTo(betaCacheMissReason)
    }

    @Test
    fun ofMessagesChanged() {
        val messagesChanged = BetaCacheMissMessagesChanged.of(0L)

        val betaCacheMissReason = BetaCacheMissReason.ofMessagesChanged(messagesChanged)

        assertThat(betaCacheMissReason.modelChanged()).isEmpty
        assertThat(betaCacheMissReason.systemChanged()).isEmpty
        assertThat(betaCacheMissReason.toolsChanged()).isEmpty
        assertThat(betaCacheMissReason.messagesChanged()).contains(messagesChanged)
        assertThat(betaCacheMissReason.previousMessageNotFound()).isEmpty
        assertThat(betaCacheMissReason.unavailable()).isEmpty
    }

    @Test
    fun ofMessagesChangedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaCacheMissReason =
            BetaCacheMissReason.ofMessagesChanged(BetaCacheMissMessagesChanged.of(0L))

        val roundtrippedBetaCacheMissReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCacheMissReason),
                jacksonTypeRef<BetaCacheMissReason>(),
            )

        assertThat(roundtrippedBetaCacheMissReason).isEqualTo(betaCacheMissReason)
    }

    @Test
    fun ofPreviousMessageNotFound() {
        val previousMessageNotFound = BetaCacheMissPreviousMessageNotFound.builder().build()

        val betaCacheMissReason =
            BetaCacheMissReason.ofPreviousMessageNotFound(previousMessageNotFound)

        assertThat(betaCacheMissReason.modelChanged()).isEmpty
        assertThat(betaCacheMissReason.systemChanged()).isEmpty
        assertThat(betaCacheMissReason.toolsChanged()).isEmpty
        assertThat(betaCacheMissReason.messagesChanged()).isEmpty
        assertThat(betaCacheMissReason.previousMessageNotFound()).contains(previousMessageNotFound)
        assertThat(betaCacheMissReason.unavailable()).isEmpty
    }

    @Test
    fun ofPreviousMessageNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaCacheMissReason =
            BetaCacheMissReason.ofPreviousMessageNotFound(
                BetaCacheMissPreviousMessageNotFound.builder().build()
            )

        val roundtrippedBetaCacheMissReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCacheMissReason),
                jacksonTypeRef<BetaCacheMissReason>(),
            )

        assertThat(roundtrippedBetaCacheMissReason).isEqualTo(betaCacheMissReason)
    }

    @Test
    fun ofUnavailable() {
        val unavailable = BetaCacheMissUnavailable.builder().build()

        val betaCacheMissReason = BetaCacheMissReason.ofUnavailable(unavailable)

        assertThat(betaCacheMissReason.modelChanged()).isEmpty
        assertThat(betaCacheMissReason.systemChanged()).isEmpty
        assertThat(betaCacheMissReason.toolsChanged()).isEmpty
        assertThat(betaCacheMissReason.messagesChanged()).isEmpty
        assertThat(betaCacheMissReason.previousMessageNotFound()).isEmpty
        assertThat(betaCacheMissReason.unavailable()).contains(unavailable)
    }

    @Test
    fun ofUnavailableRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaCacheMissReason =
            BetaCacheMissReason.ofUnavailable(BetaCacheMissUnavailable.builder().build())

        val roundtrippedBetaCacheMissReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCacheMissReason),
                jacksonTypeRef<BetaCacheMissReason>(),
            )

        assertThat(roundtrippedBetaCacheMissReason).isEqualTo(betaCacheMissReason)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaCacheMissReason =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "cache_missed_input_tokens" to 0)
                    ),
                    jacksonTypeRef<BetaCacheMissReason>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { betaCacheMissReason.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaCacheMissReason.cacheMissedInputTokens()).contains(0L)

        val mismatchedBetaCacheMissReason =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "cache_missed_input_tokens" to listOf("invalid"),
                        )
                    ),
                    jacksonTypeRef<BetaCacheMissReason>(),
                )

        assertThat(mismatchedBetaCacheMissReason.cacheMissedInputTokens()).isEmpty
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
        val betaCacheMissReason =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaCacheMissReason>())

        val e = assertThrows<AnthropicInvalidDataException> { betaCacheMissReason.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaCacheMissReason.cacheMissedInputTokens()).isEmpty
    }
}

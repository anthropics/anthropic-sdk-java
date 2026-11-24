package com.anthropic.models.beta.messages

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

internal class BetaCacheMissReasonTest {

    @Test
    fun ofModelChanged() {
        val modelChanged = BetaCacheMissModelChanged.of(0L)

        val betaCacheMissReason = BetaCacheMissReason.ofModelChanged(modelChanged)

        assertThat(presentVariants(betaCacheMissReason))
            .isEqualTo(mapOf("modelChanged" to Optional.of(modelChanged)))
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

        assertThat(presentVariants(betaCacheMissReason))
            .isEqualTo(mapOf("systemChanged" to Optional.of(systemChanged)))
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

        assertThat(presentVariants(betaCacheMissReason))
            .isEqualTo(mapOf("toolsChanged" to Optional.of(toolsChanged)))
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

        assertThat(presentVariants(betaCacheMissReason))
            .isEqualTo(mapOf("messagesChanged" to Optional.of(messagesChanged)))
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

        assertThat(presentVariants(betaCacheMissReason))
            .isEqualTo(mapOf("previousMessageNotFound" to Optional.of(previousMessageNotFound)))
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

        assertThat(presentVariants(betaCacheMissReason))
            .isEqualTo(mapOf("unavailable" to Optional.of(unavailable)))
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

    private fun presentVariants(
        betaCacheMissReason: BetaCacheMissReason
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "modelChanged" to betaCacheMissReason.modelChanged(),
                "systemChanged" to betaCacheMissReason.systemChanged(),
                "toolsChanged" to betaCacheMissReason.toolsChanged(),
                "messagesChanged" to betaCacheMissReason.messagesChanged(),
                "previousMessageNotFound" to betaCacheMissReason.previousMessageNotFound(),
                "unavailable" to betaCacheMissReason.unavailable(),
            )
            .filterValues { it.isPresent }
}

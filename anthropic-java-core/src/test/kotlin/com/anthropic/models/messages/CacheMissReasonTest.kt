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

internal class CacheMissReasonTest {

    @Test
    fun ofModelChanged() {
        val modelChanged = CacheMissModelChanged.of(0L)

        val cacheMissReason = CacheMissReason.ofModelChanged(modelChanged)

        assertThat(presentVariants(cacheMissReason))
            .isEqualTo(mapOf("modelChanged" to Optional.of(modelChanged)))
    }

    @Test
    fun ofModelChangedRoundtrip() {
        val jsonMapper = jsonMapper()
        val cacheMissReason = CacheMissReason.ofModelChanged(CacheMissModelChanged.of(0L))

        val roundtrippedCacheMissReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(cacheMissReason),
                jacksonTypeRef<CacheMissReason>(),
            )

        assertThat(roundtrippedCacheMissReason).isEqualTo(cacheMissReason)
    }

    @Test
    fun ofSystemChanged() {
        val systemChanged = CacheMissSystemChanged.of(0L)

        val cacheMissReason = CacheMissReason.ofSystemChanged(systemChanged)

        assertThat(presentVariants(cacheMissReason))
            .isEqualTo(mapOf("systemChanged" to Optional.of(systemChanged)))
    }

    @Test
    fun ofSystemChangedRoundtrip() {
        val jsonMapper = jsonMapper()
        val cacheMissReason = CacheMissReason.ofSystemChanged(CacheMissSystemChanged.of(0L))

        val roundtrippedCacheMissReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(cacheMissReason),
                jacksonTypeRef<CacheMissReason>(),
            )

        assertThat(roundtrippedCacheMissReason).isEqualTo(cacheMissReason)
    }

    @Test
    fun ofToolsChanged() {
        val toolsChanged = CacheMissToolsChanged.of(0L)

        val cacheMissReason = CacheMissReason.ofToolsChanged(toolsChanged)

        assertThat(presentVariants(cacheMissReason))
            .isEqualTo(mapOf("toolsChanged" to Optional.of(toolsChanged)))
    }

    @Test
    fun ofToolsChangedRoundtrip() {
        val jsonMapper = jsonMapper()
        val cacheMissReason = CacheMissReason.ofToolsChanged(CacheMissToolsChanged.of(0L))

        val roundtrippedCacheMissReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(cacheMissReason),
                jacksonTypeRef<CacheMissReason>(),
            )

        assertThat(roundtrippedCacheMissReason).isEqualTo(cacheMissReason)
    }

    @Test
    fun ofMessagesChanged() {
        val messagesChanged = CacheMissMessagesChanged.of(0L)

        val cacheMissReason = CacheMissReason.ofMessagesChanged(messagesChanged)

        assertThat(presentVariants(cacheMissReason))
            .isEqualTo(mapOf("messagesChanged" to Optional.of(messagesChanged)))
    }

    @Test
    fun ofMessagesChangedRoundtrip() {
        val jsonMapper = jsonMapper()
        val cacheMissReason = CacheMissReason.ofMessagesChanged(CacheMissMessagesChanged.of(0L))

        val roundtrippedCacheMissReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(cacheMissReason),
                jacksonTypeRef<CacheMissReason>(),
            )

        assertThat(roundtrippedCacheMissReason).isEqualTo(cacheMissReason)
    }

    @Test
    fun ofPreviousMessageNotFound() {
        val previousMessageNotFound = CacheMissPreviousMessageNotFound.builder().build()

        val cacheMissReason = CacheMissReason.ofPreviousMessageNotFound(previousMessageNotFound)

        assertThat(presentVariants(cacheMissReason))
            .isEqualTo(mapOf("previousMessageNotFound" to Optional.of(previousMessageNotFound)))
    }

    @Test
    fun ofPreviousMessageNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val cacheMissReason =
            CacheMissReason.ofPreviousMessageNotFound(
                CacheMissPreviousMessageNotFound.builder().build()
            )

        val roundtrippedCacheMissReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(cacheMissReason),
                jacksonTypeRef<CacheMissReason>(),
            )

        assertThat(roundtrippedCacheMissReason).isEqualTo(cacheMissReason)
    }

    @Test
    fun ofUnavailable() {
        val unavailable = CacheMissUnavailable.builder().build()

        val cacheMissReason = CacheMissReason.ofUnavailable(unavailable)

        assertThat(presentVariants(cacheMissReason))
            .isEqualTo(mapOf("unavailable" to Optional.of(unavailable)))
    }

    @Test
    fun ofUnavailableRoundtrip() {
        val jsonMapper = jsonMapper()
        val cacheMissReason = CacheMissReason.ofUnavailable(CacheMissUnavailable.builder().build())

        val roundtrippedCacheMissReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(cacheMissReason),
                jacksonTypeRef<CacheMissReason>(),
            )

        assertThat(roundtrippedCacheMissReason).isEqualTo(cacheMissReason)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val cacheMissReason =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "cache_missed_input_tokens" to 0)
                    ),
                    jacksonTypeRef<CacheMissReason>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { cacheMissReason.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(cacheMissReason.cacheMissedInputTokens()).contains(0L)

        val mismatchedCacheMissReason =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "cache_missed_input_tokens" to listOf("invalid"),
                        )
                    ),
                    jacksonTypeRef<CacheMissReason>(),
                )

        assertThat(mismatchedCacheMissReason.cacheMissedInputTokens()).isEmpty
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
        val cacheMissReason =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<CacheMissReason>())

        val e = assertThrows<AnthropicInvalidDataException> { cacheMissReason.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(cacheMissReason.cacheMissedInputTokens()).isEmpty
    }

    private fun presentVariants(cacheMissReason: CacheMissReason): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "modelChanged" to cacheMissReason.modelChanged(),
                "systemChanged" to cacheMissReason.systemChanged(),
                "toolsChanged" to cacheMissReason.toolsChanged(),
                "messagesChanged" to cacheMissReason.messagesChanged(),
                "previousMessageNotFound" to cacheMissReason.previousMessageNotFound(),
                "unavailable" to cacheMissReason.unavailable(),
            )
            .filterValues { it.isPresent }
}

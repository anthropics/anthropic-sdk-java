// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaDreamInputTest {

    @Test
    fun ofMemoryStore() {
        val memoryStore =
            BetaDreamMemoryStoreInput.builder()
                .memoryStoreId("x")
                .type(BetaDreamMemoryStoreInput.Type.MEMORY_STORE)
                .build()

        val betaDreamInput = BetaDreamInput.ofMemoryStore(memoryStore)

        assertThat(betaDreamInput.memoryStore()).contains(memoryStore)
        assertThat(betaDreamInput.sessions()).isEmpty
    }

    @Test
    fun ofMemoryStoreRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamInput =
            BetaDreamInput.ofMemoryStore(
                BetaDreamMemoryStoreInput.builder()
                    .memoryStoreId("x")
                    .type(BetaDreamMemoryStoreInput.Type.MEMORY_STORE)
                    .build()
            )

        val roundtrippedBetaDreamInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamInput),
                jacksonTypeRef<BetaDreamInput>(),
            )

        assertThat(roundtrippedBetaDreamInput).isEqualTo(betaDreamInput)
    }

    @Test
    fun ofSessions() {
        val sessions =
            BetaDreamSessionsInput.builder()
                .addSessionId("string")
                .type(BetaDreamSessionsInput.Type.SESSIONS)
                .build()

        val betaDreamInput = BetaDreamInput.ofSessions(sessions)

        assertThat(betaDreamInput.memoryStore()).isEmpty
        assertThat(betaDreamInput.sessions()).contains(sessions)
    }

    @Test
    fun ofSessionsRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamInput =
            BetaDreamInput.ofSessions(
                BetaDreamSessionsInput.builder()
                    .addSessionId("string")
                    .type(BetaDreamSessionsInput.Type.SESSIONS)
                    .build()
            )

        val roundtrippedBetaDreamInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamInput),
                jacksonTypeRef<BetaDreamInput>(),
            )

        assertThat(roundtrippedBetaDreamInput).isEqualTo(betaDreamInput)
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
        val betaDreamInput =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaDreamInput>())

        val e = assertThrows<AnthropicInvalidDataException> { betaDreamInput.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}

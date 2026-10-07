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

internal class ToolUseCallerTest {

    @Test
    fun ofDirect() {
        val direct = DirectCaller.builder().build()

        val toolUseCaller = ToolUseCaller.ofDirect(direct)

        assertThat(toolUseCaller.direct()).contains(direct)
        assertThat(toolUseCaller.codeExecution20250825()).isEmpty
        assertThat(toolUseCaller.codeExecution20260120()).isEmpty
    }

    @Test
    fun ofDirectRoundtrip() {
        val jsonMapper = jsonMapper()
        val toolUseCaller = ToolUseCaller.ofDirect(DirectCaller.builder().build())

        val roundtrippedToolUseCaller =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolUseCaller),
                jacksonTypeRef<ToolUseCaller>(),
            )

        assertThat(roundtrippedToolUseCaller).isEqualTo(toolUseCaller)
    }

    @Test
    fun ofCodeExecution20250825() {
        val codeExecution20250825 = ServerToolCaller.of("srvtoolu_SQfNkl1n_JR_")

        val toolUseCaller = ToolUseCaller.ofCodeExecution20250825(codeExecution20250825)

        assertThat(toolUseCaller.direct()).isEmpty
        assertThat(toolUseCaller.codeExecution20250825()).contains(codeExecution20250825)
        assertThat(toolUseCaller.codeExecution20260120()).isEmpty
    }

    @Test
    fun ofCodeExecution20250825Roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUseCaller =
            ToolUseCaller.ofCodeExecution20250825(ServerToolCaller.of("srvtoolu_SQfNkl1n_JR_"))

        val roundtrippedToolUseCaller =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolUseCaller),
                jacksonTypeRef<ToolUseCaller>(),
            )

        assertThat(roundtrippedToolUseCaller).isEqualTo(toolUseCaller)
    }

    @Test
    fun ofCodeExecution20260120() {
        val codeExecution20260120 = ServerToolCaller20260120.of("srvtoolu_SQfNkl1n_JR_")

        val toolUseCaller = ToolUseCaller.ofCodeExecution20260120(codeExecution20260120)

        assertThat(toolUseCaller.direct()).isEmpty
        assertThat(toolUseCaller.codeExecution20250825()).isEmpty
        assertThat(toolUseCaller.codeExecution20260120()).contains(codeExecution20260120)
    }

    @Test
    fun ofCodeExecution20260120Roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUseCaller =
            ToolUseCaller.ofCodeExecution20260120(
                ServerToolCaller20260120.of("srvtoolu_SQfNkl1n_JR_")
            )

        val roundtrippedToolUseCaller =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolUseCaller),
                jacksonTypeRef<ToolUseCaller>(),
            )

        assertThat(roundtrippedToolUseCaller).isEqualTo(toolUseCaller)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val toolUseCaller =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "tool_id" to "srvtoolu_SQfNkl1n_JR_")
                    ),
                    jacksonTypeRef<ToolUseCaller>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { toolUseCaller.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(toolUseCaller.toolId()).contains("srvtoolu_SQfNkl1n_JR_")

        val mismatchedToolUseCaller =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "tool_id" to listOf("invalid"))
                    ),
                    jacksonTypeRef<ToolUseCaller>(),
                )

        assertThat(mismatchedToolUseCaller.toolId()).isEmpty
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
        val toolUseCaller =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<ToolUseCaller>())

        val e = assertThrows<AnthropicInvalidDataException> { toolUseCaller.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(toolUseCaller.toolId()).isEmpty
    }
}

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsTriggerContextTest {

    @Test
    fun ofSchedule() {
        val schedule =
            BetaManagedAgentsScheduleTriggerContext.of(
                OffsetDateTime.parse("2019-12-27T18:11:19.117Z")
            )

        val betaManagedAgentsTriggerContext = BetaManagedAgentsTriggerContext.ofSchedule(schedule)

        assertThat(betaManagedAgentsTriggerContext.schedule()).contains(schedule)
        assertThat(betaManagedAgentsTriggerContext.manual()).isEmpty
    }

    @Test
    fun ofScheduleRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsTriggerContext =
            BetaManagedAgentsTriggerContext.ofSchedule(
                BetaManagedAgentsScheduleTriggerContext.of(
                    OffsetDateTime.parse("2019-12-27T18:11:19.117Z")
                )
            )

        val roundtrippedBetaManagedAgentsTriggerContext =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsTriggerContext),
                jacksonTypeRef<BetaManagedAgentsTriggerContext>(),
            )

        assertThat(roundtrippedBetaManagedAgentsTriggerContext)
            .isEqualTo(betaManagedAgentsTriggerContext)
    }

    @Test
    fun ofManual() {
        val manual = BetaManagedAgentsManualTriggerContext.builder().build()

        val betaManagedAgentsTriggerContext = BetaManagedAgentsTriggerContext.ofManual(manual)

        assertThat(betaManagedAgentsTriggerContext.schedule()).isEmpty
        assertThat(betaManagedAgentsTriggerContext.manual()).contains(manual)
    }

    @Test
    fun ofManualRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsTriggerContext =
            BetaManagedAgentsTriggerContext.ofManual(
                BetaManagedAgentsManualTriggerContext.builder().build()
            )

        val roundtrippedBetaManagedAgentsTriggerContext =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsTriggerContext),
                jacksonTypeRef<BetaManagedAgentsTriggerContext>(),
            )

        assertThat(roundtrippedBetaManagedAgentsTriggerContext)
            .isEqualTo(betaManagedAgentsTriggerContext)
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
        val betaManagedAgentsTriggerContext =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaManagedAgentsTriggerContext>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsTriggerContext.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}

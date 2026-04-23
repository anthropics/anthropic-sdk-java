// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memoryversions

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsActorTest {

    @Test
    fun ofSession() {
        val session =
            BetaManagedAgentsSessionActor.builder()
                .sessionId("x")
                .type(BetaManagedAgentsSessionActor.Type.SESSION_ACTOR)
                .build()

        val betaManagedAgentsActor = BetaManagedAgentsActor.ofSession(session)

        assertThat(betaManagedAgentsActor.session()).contains(session)
        assertThat(betaManagedAgentsActor.api()).isEmpty
        assertThat(betaManagedAgentsActor.user()).isEmpty
    }

    @Test
    fun ofSessionRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsActor =
            BetaManagedAgentsActor.ofSession(
                BetaManagedAgentsSessionActor.builder()
                    .sessionId("x")
                    .type(BetaManagedAgentsSessionActor.Type.SESSION_ACTOR)
                    .build()
            )

        val roundtrippedBetaManagedAgentsActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsActor),
                jacksonTypeRef<BetaManagedAgentsActor>(),
            )

        assertThat(roundtrippedBetaManagedAgentsActor).isEqualTo(betaManagedAgentsActor)
    }

    @Test
    fun ofApi() {
        val api =
            BetaManagedAgentsApiActor.builder()
                .apiKeyId("x")
                .type(BetaManagedAgentsApiActor.Type.API_ACTOR)
                .build()

        val betaManagedAgentsActor = BetaManagedAgentsActor.ofApi(api)

        assertThat(betaManagedAgentsActor.session()).isEmpty
        assertThat(betaManagedAgentsActor.api()).contains(api)
        assertThat(betaManagedAgentsActor.user()).isEmpty
    }

    @Test
    fun ofApiRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsActor =
            BetaManagedAgentsActor.ofApi(
                BetaManagedAgentsApiActor.builder()
                    .apiKeyId("x")
                    .type(BetaManagedAgentsApiActor.Type.API_ACTOR)
                    .build()
            )

        val roundtrippedBetaManagedAgentsActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsActor),
                jacksonTypeRef<BetaManagedAgentsActor>(),
            )

        assertThat(roundtrippedBetaManagedAgentsActor).isEqualTo(betaManagedAgentsActor)
    }

    @Test
    fun ofUser() {
        val user =
            BetaManagedAgentsUserActor.builder()
                .type(BetaManagedAgentsUserActor.Type.USER_ACTOR)
                .userId("x")
                .build()

        val betaManagedAgentsActor = BetaManagedAgentsActor.ofUser(user)

        assertThat(betaManagedAgentsActor.session()).isEmpty
        assertThat(betaManagedAgentsActor.api()).isEmpty
        assertThat(betaManagedAgentsActor.user()).contains(user)
    }

    @Test
    fun ofUserRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsActor =
            BetaManagedAgentsActor.ofUser(
                BetaManagedAgentsUserActor.builder()
                    .type(BetaManagedAgentsUserActor.Type.USER_ACTOR)
                    .userId("x")
                    .build()
            )

        val roundtrippedBetaManagedAgentsActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsActor),
                jacksonTypeRef<BetaManagedAgentsActor>(),
            )

        assertThat(roundtrippedBetaManagedAgentsActor).isEqualTo(betaManagedAgentsActor)
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
        val betaManagedAgentsActor =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaManagedAgentsActor>())

        val e = assertThrows<AnthropicInvalidDataException> { betaManagedAgentsActor.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}

package com.anthropic.models.beta.memorystores.memoryversions

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

internal class BetaManagedAgentsActorTest {

    @Test
    fun ofSession() {
        val session = BetaManagedAgentsSessionActor.of("x")

        val betaManagedAgentsActor = BetaManagedAgentsActor.ofSession(session)

        assertThat(presentVariants(betaManagedAgentsActor))
            .isEqualTo(mapOf("session" to Optional.of(session)))
    }

    @Test
    fun ofSessionRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsActor =
            BetaManagedAgentsActor.ofSession(BetaManagedAgentsSessionActor.of("x"))

        val roundtrippedBetaManagedAgentsActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsActor),
                jacksonTypeRef<BetaManagedAgentsActor>(),
            )

        assertThat(roundtrippedBetaManagedAgentsActor).isEqualTo(betaManagedAgentsActor)
    }

    @Test
    fun ofApi() {
        val api = BetaManagedAgentsApiActor.of("x")

        val betaManagedAgentsActor = BetaManagedAgentsActor.ofApi(api)

        assertThat(presentVariants(betaManagedAgentsActor))
            .isEqualTo(mapOf("api" to Optional.of(api)))
    }

    @Test
    fun ofApiRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsActor = BetaManagedAgentsActor.ofApi(BetaManagedAgentsApiActor.of("x"))

        val roundtrippedBetaManagedAgentsActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsActor),
                jacksonTypeRef<BetaManagedAgentsActor>(),
            )

        assertThat(roundtrippedBetaManagedAgentsActor).isEqualTo(betaManagedAgentsActor)
    }

    @Test
    fun ofUser() {
        val user = BetaManagedAgentsUserActor.of("x")

        val betaManagedAgentsActor = BetaManagedAgentsActor.ofUser(user)

        assertThat(presentVariants(betaManagedAgentsActor))
            .isEqualTo(mapOf("user" to Optional.of(user)))
    }

    @Test
    fun ofUserRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsActor =
            BetaManagedAgentsActor.ofUser(BetaManagedAgentsUserActor.of("x"))

        val roundtrippedBetaManagedAgentsActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsActor),
                jacksonTypeRef<BetaManagedAgentsActor>(),
            )

        assertThat(roundtrippedBetaManagedAgentsActor).isEqualTo(betaManagedAgentsActor)
    }

    @Test
    fun ofServiceAccount() {
        val serviceAccount = BetaManagedAgentsServiceAccountActor.of("x")

        val betaManagedAgentsActor = BetaManagedAgentsActor.ofServiceAccount(serviceAccount)

        assertThat(presentVariants(betaManagedAgentsActor))
            .isEqualTo(mapOf("serviceAccount" to Optional.of(serviceAccount)))
    }

    @Test
    fun ofServiceAccountRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsActor =
            BetaManagedAgentsActor.ofServiceAccount(BetaManagedAgentsServiceAccountActor.of("x"))

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

    private fun presentVariants(
        betaManagedAgentsActor: BetaManagedAgentsActor
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "session" to betaManagedAgentsActor.session(),
                "api" to betaManagedAgentsActor.api(),
                "user" to betaManagedAgentsActor.user(),
                "serviceAccount" to betaManagedAgentsActor.serviceAccount(),
            )
            .filterValues { it.isPresent }
}

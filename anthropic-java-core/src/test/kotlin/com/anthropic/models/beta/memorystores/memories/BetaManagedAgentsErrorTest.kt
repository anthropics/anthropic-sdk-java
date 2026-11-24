package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.BetaApiError
import com.anthropic.models.beta.BetaAuthenticationError
import com.anthropic.models.beta.BetaBillingError
import com.anthropic.models.beta.BetaGatewayTimeoutError
import com.anthropic.models.beta.BetaInvalidRequestError
import com.anthropic.models.beta.BetaNotFoundError
import com.anthropic.models.beta.BetaOverloadedError
import com.anthropic.models.beta.BetaPermissionError
import com.anthropic.models.beta.BetaRateLimitError
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsErrorTest {

    @Test
    fun ofInvalidRequest() {
        val invalidRequest = BetaInvalidRequestError.of("message")

        val betaManagedAgentsError = BetaManagedAgentsError.ofInvalidRequest(invalidRequest)

        assertThat(presentVariants(betaManagedAgentsError))
            .isEqualTo(mapOf("invalidRequest" to Optional.of(invalidRequest)))
    }

    @Test
    fun ofInvalidRequestRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofInvalidRequest(BetaInvalidRequestError.of("message"))

        val roundtrippedBetaManagedAgentsError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsError),
                jacksonTypeRef<BetaManagedAgentsError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsError).isEqualTo(betaManagedAgentsError)
    }

    @Test
    fun ofAuthentication() {
        val authentication = BetaAuthenticationError.of("message")

        val betaManagedAgentsError = BetaManagedAgentsError.ofAuthentication(authentication)

        assertThat(presentVariants(betaManagedAgentsError))
            .isEqualTo(mapOf("authentication" to Optional.of(authentication)))
    }

    @Test
    fun ofAuthenticationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofAuthentication(BetaAuthenticationError.of("message"))

        val roundtrippedBetaManagedAgentsError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsError),
                jacksonTypeRef<BetaManagedAgentsError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsError).isEqualTo(betaManagedAgentsError)
    }

    @Test
    fun ofBilling() {
        val billing = BetaBillingError.of("message")

        val betaManagedAgentsError = BetaManagedAgentsError.ofBilling(billing)

        assertThat(presentVariants(betaManagedAgentsError))
            .isEqualTo(mapOf("billing" to Optional.of(billing)))
    }

    @Test
    fun ofBillingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofBilling(BetaBillingError.of("message"))

        val roundtrippedBetaManagedAgentsError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsError),
                jacksonTypeRef<BetaManagedAgentsError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsError).isEqualTo(betaManagedAgentsError)
    }

    @Test
    fun ofPermission() {
        val permission = BetaPermissionError.of("message")

        val betaManagedAgentsError = BetaManagedAgentsError.ofPermission(permission)

        assertThat(presentVariants(betaManagedAgentsError))
            .isEqualTo(mapOf("permission" to Optional.of(permission)))
    }

    @Test
    fun ofPermissionRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofPermission(BetaPermissionError.of("message"))

        val roundtrippedBetaManagedAgentsError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsError),
                jacksonTypeRef<BetaManagedAgentsError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsError).isEqualTo(betaManagedAgentsError)
    }

    @Test
    fun ofNotFound() {
        val notFound = BetaNotFoundError.of("message")

        val betaManagedAgentsError = BetaManagedAgentsError.ofNotFound(notFound)

        assertThat(presentVariants(betaManagedAgentsError))
            .isEqualTo(mapOf("notFound" to Optional.of(notFound)))
    }

    @Test
    fun ofNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofNotFound(BetaNotFoundError.of("message"))

        val roundtrippedBetaManagedAgentsError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsError),
                jacksonTypeRef<BetaManagedAgentsError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsError).isEqualTo(betaManagedAgentsError)
    }

    @Test
    fun ofRateLimit() {
        val rateLimit = BetaRateLimitError.of("message")

        val betaManagedAgentsError = BetaManagedAgentsError.ofRateLimit(rateLimit)

        assertThat(presentVariants(betaManagedAgentsError))
            .isEqualTo(mapOf("rateLimit" to Optional.of(rateLimit)))
    }

    @Test
    fun ofRateLimitRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofRateLimit(BetaRateLimitError.of("message"))

        val roundtrippedBetaManagedAgentsError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsError),
                jacksonTypeRef<BetaManagedAgentsError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsError).isEqualTo(betaManagedAgentsError)
    }

    @Test
    fun ofTimeout() {
        val timeout = BetaGatewayTimeoutError.of("message")

        val betaManagedAgentsError = BetaManagedAgentsError.ofTimeout(timeout)

        assertThat(presentVariants(betaManagedAgentsError))
            .isEqualTo(mapOf("timeout" to Optional.of(timeout)))
    }

    @Test
    fun ofTimeoutRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofTimeout(BetaGatewayTimeoutError.of("message"))

        val roundtrippedBetaManagedAgentsError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsError),
                jacksonTypeRef<BetaManagedAgentsError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsError).isEqualTo(betaManagedAgentsError)
    }

    @Test
    fun ofApi() {
        val api = BetaApiError.of("message")

        val betaManagedAgentsError = BetaManagedAgentsError.ofApi(api)

        assertThat(presentVariants(betaManagedAgentsError))
            .isEqualTo(mapOf("api" to Optional.of(api)))
    }

    @Test
    fun ofApiRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError = BetaManagedAgentsError.ofApi(BetaApiError.of("message"))

        val roundtrippedBetaManagedAgentsError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsError),
                jacksonTypeRef<BetaManagedAgentsError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsError).isEqualTo(betaManagedAgentsError)
    }

    @Test
    fun ofOverloaded() {
        val overloaded = BetaOverloadedError.of("message")

        val betaManagedAgentsError = BetaManagedAgentsError.ofOverloaded(overloaded)

        assertThat(presentVariants(betaManagedAgentsError))
            .isEqualTo(mapOf("overloaded" to Optional.of(overloaded)))
    }

    @Test
    fun ofOverloadedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofOverloaded(BetaOverloadedError.of("message"))

        val roundtrippedBetaManagedAgentsError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsError),
                jacksonTypeRef<BetaManagedAgentsError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsError).isEqualTo(betaManagedAgentsError)
    }

    @Test
    fun ofMemoryPreconditionFailed() {
        val memoryPreconditionFailed =
            BetaManagedAgentsMemoryPreconditionFailedError.builder().message("message").build()

        val betaManagedAgentsError =
            BetaManagedAgentsError.ofMemoryPreconditionFailed(memoryPreconditionFailed)

        assertThat(presentVariants(betaManagedAgentsError))
            .isEqualTo(mapOf("memoryPreconditionFailed" to Optional.of(memoryPreconditionFailed)))
    }

    @Test
    fun ofMemoryPreconditionFailedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofMemoryPreconditionFailed(
                BetaManagedAgentsMemoryPreconditionFailedError.builder().message("message").build()
            )

        val roundtrippedBetaManagedAgentsError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsError),
                jacksonTypeRef<BetaManagedAgentsError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsError).isEqualTo(betaManagedAgentsError)
    }

    @Test
    fun ofMemoryPathConflict() {
        val memoryPathConflict =
            BetaManagedAgentsMemoryPathConflictError.builder()
                .conflictingMemoryId("conflicting_memory_id")
                .conflictingPath("conflicting_path")
                .message("message")
                .build()

        val betaManagedAgentsError = BetaManagedAgentsError.ofMemoryPathConflict(memoryPathConflict)

        assertThat(presentVariants(betaManagedAgentsError))
            .isEqualTo(mapOf("memoryPathConflict" to Optional.of(memoryPathConflict)))
    }

    @Test
    fun ofMemoryPathConflictRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofMemoryPathConflict(
                BetaManagedAgentsMemoryPathConflictError.builder()
                    .conflictingMemoryId("conflicting_memory_id")
                    .conflictingPath("conflicting_path")
                    .message("message")
                    .build()
            )

        val roundtrippedBetaManagedAgentsError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsError),
                jacksonTypeRef<BetaManagedAgentsError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsError).isEqualTo(betaManagedAgentsError)
    }

    @Test
    fun ofConflict() {
        val conflict = BetaManagedAgentsConflictError.builder().message("message").build()

        val betaManagedAgentsError = BetaManagedAgentsError.ofConflict(conflict)

        assertThat(presentVariants(betaManagedAgentsError))
            .isEqualTo(mapOf("conflict" to Optional.of(conflict)))
    }

    @Test
    fun ofConflictRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofConflict(
                BetaManagedAgentsConflictError.builder().message("message").build()
            )

        val roundtrippedBetaManagedAgentsError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsError),
                jacksonTypeRef<BetaManagedAgentsError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsError).isEqualTo(betaManagedAgentsError)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaManagedAgentsError =
            jsonMapper()
                .convertValue(
                    JsonValue.from(mapOf("type" to "unknown_variant", "message" to "message")),
                    jacksonTypeRef<BetaManagedAgentsError>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { betaManagedAgentsError.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaManagedAgentsError.message()).contains("message")

        val mismatchedBetaManagedAgentsError =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "message" to listOf("invalid"))
                    ),
                    jacksonTypeRef<BetaManagedAgentsError>(),
                )

        assertThat(mismatchedBetaManagedAgentsError.message()).isEmpty
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
        val betaManagedAgentsError =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaManagedAgentsError>())

        val e = assertThrows<AnthropicInvalidDataException> { betaManagedAgentsError.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaManagedAgentsError.message()).isEmpty
    }

    private fun presentVariants(
        betaManagedAgentsError: BetaManagedAgentsError
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "invalidRequest" to betaManagedAgentsError.invalidRequest(),
                "authentication" to betaManagedAgentsError.authentication(),
                "billing" to betaManagedAgentsError.billing(),
                "permission" to betaManagedAgentsError.permission(),
                "notFound" to betaManagedAgentsError.notFound(),
                "rateLimit" to betaManagedAgentsError.rateLimit(),
                "timeout" to betaManagedAgentsError.timeout(),
                "api" to betaManagedAgentsError.api(),
                "overloaded" to betaManagedAgentsError.overloaded(),
                "memoryPreconditionFailed" to betaManagedAgentsError.memoryPreconditionFailed(),
                "memoryPathConflict" to betaManagedAgentsError.memoryPathConflict(),
                "conflict" to betaManagedAgentsError.conflict(),
            )
            .filterValues { it.isPresent }
}

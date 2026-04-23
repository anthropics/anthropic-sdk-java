// File generated from our OpenAPI spec by Stainless.

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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsErrorTest {

    @Test
    fun ofInvalidRequest() {
        val invalidRequest = BetaInvalidRequestError.builder().message("message").build()

        val betaManagedAgentsError = BetaManagedAgentsError.ofInvalidRequest(invalidRequest)

        assertThat(betaManagedAgentsError.invalidRequest()).contains(invalidRequest)
        assertThat(betaManagedAgentsError.authentication()).isEmpty
        assertThat(betaManagedAgentsError.billing()).isEmpty
        assertThat(betaManagedAgentsError.permission()).isEmpty
        assertThat(betaManagedAgentsError.notFound()).isEmpty
        assertThat(betaManagedAgentsError.rateLimit()).isEmpty
        assertThat(betaManagedAgentsError.timeout()).isEmpty
        assertThat(betaManagedAgentsError.api()).isEmpty
        assertThat(betaManagedAgentsError.overloaded()).isEmpty
        assertThat(betaManagedAgentsError.memoryPreconditionFailed()).isEmpty
        assertThat(betaManagedAgentsError.memoryPathConflict()).isEmpty
        assertThat(betaManagedAgentsError.conflict()).isEmpty
    }

    @Test
    fun ofInvalidRequestRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofInvalidRequest(
                BetaInvalidRequestError.builder().message("message").build()
            )

        val roundtrippedBetaManagedAgentsError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsError),
                jacksonTypeRef<BetaManagedAgentsError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsError).isEqualTo(betaManagedAgentsError)
    }

    @Test
    fun ofAuthentication() {
        val authentication = BetaAuthenticationError.builder().message("message").build()

        val betaManagedAgentsError = BetaManagedAgentsError.ofAuthentication(authentication)

        assertThat(betaManagedAgentsError.invalidRequest()).isEmpty
        assertThat(betaManagedAgentsError.authentication()).contains(authentication)
        assertThat(betaManagedAgentsError.billing()).isEmpty
        assertThat(betaManagedAgentsError.permission()).isEmpty
        assertThat(betaManagedAgentsError.notFound()).isEmpty
        assertThat(betaManagedAgentsError.rateLimit()).isEmpty
        assertThat(betaManagedAgentsError.timeout()).isEmpty
        assertThat(betaManagedAgentsError.api()).isEmpty
        assertThat(betaManagedAgentsError.overloaded()).isEmpty
        assertThat(betaManagedAgentsError.memoryPreconditionFailed()).isEmpty
        assertThat(betaManagedAgentsError.memoryPathConflict()).isEmpty
        assertThat(betaManagedAgentsError.conflict()).isEmpty
    }

    @Test
    fun ofAuthenticationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofAuthentication(
                BetaAuthenticationError.builder().message("message").build()
            )

        val roundtrippedBetaManagedAgentsError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsError),
                jacksonTypeRef<BetaManagedAgentsError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsError).isEqualTo(betaManagedAgentsError)
    }

    @Test
    fun ofBilling() {
        val billing = BetaBillingError.builder().message("message").build()

        val betaManagedAgentsError = BetaManagedAgentsError.ofBilling(billing)

        assertThat(betaManagedAgentsError.invalidRequest()).isEmpty
        assertThat(betaManagedAgentsError.authentication()).isEmpty
        assertThat(betaManagedAgentsError.billing()).contains(billing)
        assertThat(betaManagedAgentsError.permission()).isEmpty
        assertThat(betaManagedAgentsError.notFound()).isEmpty
        assertThat(betaManagedAgentsError.rateLimit()).isEmpty
        assertThat(betaManagedAgentsError.timeout()).isEmpty
        assertThat(betaManagedAgentsError.api()).isEmpty
        assertThat(betaManagedAgentsError.overloaded()).isEmpty
        assertThat(betaManagedAgentsError.memoryPreconditionFailed()).isEmpty
        assertThat(betaManagedAgentsError.memoryPathConflict()).isEmpty
        assertThat(betaManagedAgentsError.conflict()).isEmpty
    }

    @Test
    fun ofBillingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofBilling(BetaBillingError.builder().message("message").build())

        val roundtrippedBetaManagedAgentsError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsError),
                jacksonTypeRef<BetaManagedAgentsError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsError).isEqualTo(betaManagedAgentsError)
    }

    @Test
    fun ofPermission() {
        val permission = BetaPermissionError.builder().message("message").build()

        val betaManagedAgentsError = BetaManagedAgentsError.ofPermission(permission)

        assertThat(betaManagedAgentsError.invalidRequest()).isEmpty
        assertThat(betaManagedAgentsError.authentication()).isEmpty
        assertThat(betaManagedAgentsError.billing()).isEmpty
        assertThat(betaManagedAgentsError.permission()).contains(permission)
        assertThat(betaManagedAgentsError.notFound()).isEmpty
        assertThat(betaManagedAgentsError.rateLimit()).isEmpty
        assertThat(betaManagedAgentsError.timeout()).isEmpty
        assertThat(betaManagedAgentsError.api()).isEmpty
        assertThat(betaManagedAgentsError.overloaded()).isEmpty
        assertThat(betaManagedAgentsError.memoryPreconditionFailed()).isEmpty
        assertThat(betaManagedAgentsError.memoryPathConflict()).isEmpty
        assertThat(betaManagedAgentsError.conflict()).isEmpty
    }

    @Test
    fun ofPermissionRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofPermission(
                BetaPermissionError.builder().message("message").build()
            )

        val roundtrippedBetaManagedAgentsError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsError),
                jacksonTypeRef<BetaManagedAgentsError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsError).isEqualTo(betaManagedAgentsError)
    }

    @Test
    fun ofNotFound() {
        val notFound = BetaNotFoundError.builder().message("message").build()

        val betaManagedAgentsError = BetaManagedAgentsError.ofNotFound(notFound)

        assertThat(betaManagedAgentsError.invalidRequest()).isEmpty
        assertThat(betaManagedAgentsError.authentication()).isEmpty
        assertThat(betaManagedAgentsError.billing()).isEmpty
        assertThat(betaManagedAgentsError.permission()).isEmpty
        assertThat(betaManagedAgentsError.notFound()).contains(notFound)
        assertThat(betaManagedAgentsError.rateLimit()).isEmpty
        assertThat(betaManagedAgentsError.timeout()).isEmpty
        assertThat(betaManagedAgentsError.api()).isEmpty
        assertThat(betaManagedAgentsError.overloaded()).isEmpty
        assertThat(betaManagedAgentsError.memoryPreconditionFailed()).isEmpty
        assertThat(betaManagedAgentsError.memoryPathConflict()).isEmpty
        assertThat(betaManagedAgentsError.conflict()).isEmpty
    }

    @Test
    fun ofNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofNotFound(
                BetaNotFoundError.builder().message("message").build()
            )

        val roundtrippedBetaManagedAgentsError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsError),
                jacksonTypeRef<BetaManagedAgentsError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsError).isEqualTo(betaManagedAgentsError)
    }

    @Test
    fun ofRateLimit() {
        val rateLimit = BetaRateLimitError.builder().message("message").build()

        val betaManagedAgentsError = BetaManagedAgentsError.ofRateLimit(rateLimit)

        assertThat(betaManagedAgentsError.invalidRequest()).isEmpty
        assertThat(betaManagedAgentsError.authentication()).isEmpty
        assertThat(betaManagedAgentsError.billing()).isEmpty
        assertThat(betaManagedAgentsError.permission()).isEmpty
        assertThat(betaManagedAgentsError.notFound()).isEmpty
        assertThat(betaManagedAgentsError.rateLimit()).contains(rateLimit)
        assertThat(betaManagedAgentsError.timeout()).isEmpty
        assertThat(betaManagedAgentsError.api()).isEmpty
        assertThat(betaManagedAgentsError.overloaded()).isEmpty
        assertThat(betaManagedAgentsError.memoryPreconditionFailed()).isEmpty
        assertThat(betaManagedAgentsError.memoryPathConflict()).isEmpty
        assertThat(betaManagedAgentsError.conflict()).isEmpty
    }

    @Test
    fun ofRateLimitRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofRateLimit(
                BetaRateLimitError.builder().message("message").build()
            )

        val roundtrippedBetaManagedAgentsError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsError),
                jacksonTypeRef<BetaManagedAgentsError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsError).isEqualTo(betaManagedAgentsError)
    }

    @Test
    fun ofTimeout() {
        val timeout = BetaGatewayTimeoutError.builder().message("message").build()

        val betaManagedAgentsError = BetaManagedAgentsError.ofTimeout(timeout)

        assertThat(betaManagedAgentsError.invalidRequest()).isEmpty
        assertThat(betaManagedAgentsError.authentication()).isEmpty
        assertThat(betaManagedAgentsError.billing()).isEmpty
        assertThat(betaManagedAgentsError.permission()).isEmpty
        assertThat(betaManagedAgentsError.notFound()).isEmpty
        assertThat(betaManagedAgentsError.rateLimit()).isEmpty
        assertThat(betaManagedAgentsError.timeout()).contains(timeout)
        assertThat(betaManagedAgentsError.api()).isEmpty
        assertThat(betaManagedAgentsError.overloaded()).isEmpty
        assertThat(betaManagedAgentsError.memoryPreconditionFailed()).isEmpty
        assertThat(betaManagedAgentsError.memoryPathConflict()).isEmpty
        assertThat(betaManagedAgentsError.conflict()).isEmpty
    }

    @Test
    fun ofTimeoutRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofTimeout(
                BetaGatewayTimeoutError.builder().message("message").build()
            )

        val roundtrippedBetaManagedAgentsError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsError),
                jacksonTypeRef<BetaManagedAgentsError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsError).isEqualTo(betaManagedAgentsError)
    }

    @Test
    fun ofApi() {
        val api = BetaApiError.builder().message("message").build()

        val betaManagedAgentsError = BetaManagedAgentsError.ofApi(api)

        assertThat(betaManagedAgentsError.invalidRequest()).isEmpty
        assertThat(betaManagedAgentsError.authentication()).isEmpty
        assertThat(betaManagedAgentsError.billing()).isEmpty
        assertThat(betaManagedAgentsError.permission()).isEmpty
        assertThat(betaManagedAgentsError.notFound()).isEmpty
        assertThat(betaManagedAgentsError.rateLimit()).isEmpty
        assertThat(betaManagedAgentsError.timeout()).isEmpty
        assertThat(betaManagedAgentsError.api()).contains(api)
        assertThat(betaManagedAgentsError.overloaded()).isEmpty
        assertThat(betaManagedAgentsError.memoryPreconditionFailed()).isEmpty
        assertThat(betaManagedAgentsError.memoryPathConflict()).isEmpty
        assertThat(betaManagedAgentsError.conflict()).isEmpty
    }

    @Test
    fun ofApiRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofApi(BetaApiError.builder().message("message").build())

        val roundtrippedBetaManagedAgentsError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsError),
                jacksonTypeRef<BetaManagedAgentsError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsError).isEqualTo(betaManagedAgentsError)
    }

    @Test
    fun ofOverloaded() {
        val overloaded = BetaOverloadedError.builder().message("message").build()

        val betaManagedAgentsError = BetaManagedAgentsError.ofOverloaded(overloaded)

        assertThat(betaManagedAgentsError.invalidRequest()).isEmpty
        assertThat(betaManagedAgentsError.authentication()).isEmpty
        assertThat(betaManagedAgentsError.billing()).isEmpty
        assertThat(betaManagedAgentsError.permission()).isEmpty
        assertThat(betaManagedAgentsError.notFound()).isEmpty
        assertThat(betaManagedAgentsError.rateLimit()).isEmpty
        assertThat(betaManagedAgentsError.timeout()).isEmpty
        assertThat(betaManagedAgentsError.api()).isEmpty
        assertThat(betaManagedAgentsError.overloaded()).contains(overloaded)
        assertThat(betaManagedAgentsError.memoryPreconditionFailed()).isEmpty
        assertThat(betaManagedAgentsError.memoryPathConflict()).isEmpty
        assertThat(betaManagedAgentsError.conflict()).isEmpty
    }

    @Test
    fun ofOverloadedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofOverloaded(
                BetaOverloadedError.builder().message("message").build()
            )

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
            BetaManagedAgentsMemoryPreconditionFailedError.builder()
                .type(
                    BetaManagedAgentsMemoryPreconditionFailedError.Type
                        .MEMORY_PRECONDITION_FAILED_ERROR
                )
                .message("message")
                .build()

        val betaManagedAgentsError =
            BetaManagedAgentsError.ofMemoryPreconditionFailed(memoryPreconditionFailed)

        assertThat(betaManagedAgentsError.invalidRequest()).isEmpty
        assertThat(betaManagedAgentsError.authentication()).isEmpty
        assertThat(betaManagedAgentsError.billing()).isEmpty
        assertThat(betaManagedAgentsError.permission()).isEmpty
        assertThat(betaManagedAgentsError.notFound()).isEmpty
        assertThat(betaManagedAgentsError.rateLimit()).isEmpty
        assertThat(betaManagedAgentsError.timeout()).isEmpty
        assertThat(betaManagedAgentsError.api()).isEmpty
        assertThat(betaManagedAgentsError.overloaded()).isEmpty
        assertThat(betaManagedAgentsError.memoryPreconditionFailed())
            .contains(memoryPreconditionFailed)
        assertThat(betaManagedAgentsError.memoryPathConflict()).isEmpty
        assertThat(betaManagedAgentsError.conflict()).isEmpty
    }

    @Test
    fun ofMemoryPreconditionFailedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofMemoryPreconditionFailed(
                BetaManagedAgentsMemoryPreconditionFailedError.builder()
                    .type(
                        BetaManagedAgentsMemoryPreconditionFailedError.Type
                            .MEMORY_PRECONDITION_FAILED_ERROR
                    )
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
    fun ofMemoryPathConflict() {
        val memoryPathConflict =
            BetaManagedAgentsMemoryPathConflictError.builder()
                .type(BetaManagedAgentsMemoryPathConflictError.Type.MEMORY_PATH_CONFLICT_ERROR)
                .conflictingMemoryId("conflicting_memory_id")
                .conflictingPath("conflicting_path")
                .message("message")
                .build()

        val betaManagedAgentsError = BetaManagedAgentsError.ofMemoryPathConflict(memoryPathConflict)

        assertThat(betaManagedAgentsError.invalidRequest()).isEmpty
        assertThat(betaManagedAgentsError.authentication()).isEmpty
        assertThat(betaManagedAgentsError.billing()).isEmpty
        assertThat(betaManagedAgentsError.permission()).isEmpty
        assertThat(betaManagedAgentsError.notFound()).isEmpty
        assertThat(betaManagedAgentsError.rateLimit()).isEmpty
        assertThat(betaManagedAgentsError.timeout()).isEmpty
        assertThat(betaManagedAgentsError.api()).isEmpty
        assertThat(betaManagedAgentsError.overloaded()).isEmpty
        assertThat(betaManagedAgentsError.memoryPreconditionFailed()).isEmpty
        assertThat(betaManagedAgentsError.memoryPathConflict()).contains(memoryPathConflict)
        assertThat(betaManagedAgentsError.conflict()).isEmpty
    }

    @Test
    fun ofMemoryPathConflictRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofMemoryPathConflict(
                BetaManagedAgentsMemoryPathConflictError.builder()
                    .type(BetaManagedAgentsMemoryPathConflictError.Type.MEMORY_PATH_CONFLICT_ERROR)
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
        val conflict =
            BetaManagedAgentsConflictError.builder()
                .type(BetaManagedAgentsConflictError.Type.CONFLICT_ERROR)
                .message("message")
                .build()

        val betaManagedAgentsError = BetaManagedAgentsError.ofConflict(conflict)

        assertThat(betaManagedAgentsError.invalidRequest()).isEmpty
        assertThat(betaManagedAgentsError.authentication()).isEmpty
        assertThat(betaManagedAgentsError.billing()).isEmpty
        assertThat(betaManagedAgentsError.permission()).isEmpty
        assertThat(betaManagedAgentsError.notFound()).isEmpty
        assertThat(betaManagedAgentsError.rateLimit()).isEmpty
        assertThat(betaManagedAgentsError.timeout()).isEmpty
        assertThat(betaManagedAgentsError.api()).isEmpty
        assertThat(betaManagedAgentsError.overloaded()).isEmpty
        assertThat(betaManagedAgentsError.memoryPreconditionFailed()).isEmpty
        assertThat(betaManagedAgentsError.memoryPathConflict()).isEmpty
        assertThat(betaManagedAgentsError.conflict()).contains(conflict)
    }

    @Test
    fun ofConflictRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsError =
            BetaManagedAgentsError.ofConflict(
                BetaManagedAgentsConflictError.builder()
                    .type(BetaManagedAgentsConflictError.Type.CONFLICT_ERROR)
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
    }
}

// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

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

internal class BetaDreamingErrorTest {

    @Test
    fun ofInvalidRequest() {
        val invalidRequest = BetaInvalidRequestError.of("message")

        val betaDreamingError = BetaDreamingError.ofInvalidRequest(invalidRequest)

        assertThat(betaDreamingError.invalidRequest()).contains(invalidRequest)
        assertThat(betaDreamingError.authentication()).isEmpty
        assertThat(betaDreamingError.billing()).isEmpty
        assertThat(betaDreamingError.permission()).isEmpty
        assertThat(betaDreamingError.notFound()).isEmpty
        assertThat(betaDreamingError.rateLimit()).isEmpty
        assertThat(betaDreamingError.timeout()).isEmpty
        assertThat(betaDreamingError.api()).isEmpty
        assertThat(betaDreamingError.overloaded()).isEmpty
        assertThat(betaDreamingError.conflict()).isEmpty
    }

    @Test
    fun ofInvalidRequestRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamingError =
            BetaDreamingError.ofInvalidRequest(BetaInvalidRequestError.of("message"))

        val roundtrippedBetaDreamingError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamingError),
                jacksonTypeRef<BetaDreamingError>(),
            )

        assertThat(roundtrippedBetaDreamingError).isEqualTo(betaDreamingError)
    }

    @Test
    fun ofAuthentication() {
        val authentication = BetaAuthenticationError.of("message")

        val betaDreamingError = BetaDreamingError.ofAuthentication(authentication)

        assertThat(betaDreamingError.invalidRequest()).isEmpty
        assertThat(betaDreamingError.authentication()).contains(authentication)
        assertThat(betaDreamingError.billing()).isEmpty
        assertThat(betaDreamingError.permission()).isEmpty
        assertThat(betaDreamingError.notFound()).isEmpty
        assertThat(betaDreamingError.rateLimit()).isEmpty
        assertThat(betaDreamingError.timeout()).isEmpty
        assertThat(betaDreamingError.api()).isEmpty
        assertThat(betaDreamingError.overloaded()).isEmpty
        assertThat(betaDreamingError.conflict()).isEmpty
    }

    @Test
    fun ofAuthenticationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamingError =
            BetaDreamingError.ofAuthentication(BetaAuthenticationError.of("message"))

        val roundtrippedBetaDreamingError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamingError),
                jacksonTypeRef<BetaDreamingError>(),
            )

        assertThat(roundtrippedBetaDreamingError).isEqualTo(betaDreamingError)
    }

    @Test
    fun ofBilling() {
        val billing = BetaBillingError.of("message")

        val betaDreamingError = BetaDreamingError.ofBilling(billing)

        assertThat(betaDreamingError.invalidRequest()).isEmpty
        assertThat(betaDreamingError.authentication()).isEmpty
        assertThat(betaDreamingError.billing()).contains(billing)
        assertThat(betaDreamingError.permission()).isEmpty
        assertThat(betaDreamingError.notFound()).isEmpty
        assertThat(betaDreamingError.rateLimit()).isEmpty
        assertThat(betaDreamingError.timeout()).isEmpty
        assertThat(betaDreamingError.api()).isEmpty
        assertThat(betaDreamingError.overloaded()).isEmpty
        assertThat(betaDreamingError.conflict()).isEmpty
    }

    @Test
    fun ofBillingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamingError = BetaDreamingError.ofBilling(BetaBillingError.of("message"))

        val roundtrippedBetaDreamingError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamingError),
                jacksonTypeRef<BetaDreamingError>(),
            )

        assertThat(roundtrippedBetaDreamingError).isEqualTo(betaDreamingError)
    }

    @Test
    fun ofPermission() {
        val permission = BetaPermissionError.of("message")

        val betaDreamingError = BetaDreamingError.ofPermission(permission)

        assertThat(betaDreamingError.invalidRequest()).isEmpty
        assertThat(betaDreamingError.authentication()).isEmpty
        assertThat(betaDreamingError.billing()).isEmpty
        assertThat(betaDreamingError.permission()).contains(permission)
        assertThat(betaDreamingError.notFound()).isEmpty
        assertThat(betaDreamingError.rateLimit()).isEmpty
        assertThat(betaDreamingError.timeout()).isEmpty
        assertThat(betaDreamingError.api()).isEmpty
        assertThat(betaDreamingError.overloaded()).isEmpty
        assertThat(betaDreamingError.conflict()).isEmpty
    }

    @Test
    fun ofPermissionRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamingError = BetaDreamingError.ofPermission(BetaPermissionError.of("message"))

        val roundtrippedBetaDreamingError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamingError),
                jacksonTypeRef<BetaDreamingError>(),
            )

        assertThat(roundtrippedBetaDreamingError).isEqualTo(betaDreamingError)
    }

    @Test
    fun ofNotFound() {
        val notFound = BetaNotFoundError.of("message")

        val betaDreamingError = BetaDreamingError.ofNotFound(notFound)

        assertThat(betaDreamingError.invalidRequest()).isEmpty
        assertThat(betaDreamingError.authentication()).isEmpty
        assertThat(betaDreamingError.billing()).isEmpty
        assertThat(betaDreamingError.permission()).isEmpty
        assertThat(betaDreamingError.notFound()).contains(notFound)
        assertThat(betaDreamingError.rateLimit()).isEmpty
        assertThat(betaDreamingError.timeout()).isEmpty
        assertThat(betaDreamingError.api()).isEmpty
        assertThat(betaDreamingError.overloaded()).isEmpty
        assertThat(betaDreamingError.conflict()).isEmpty
    }

    @Test
    fun ofNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamingError = BetaDreamingError.ofNotFound(BetaNotFoundError.of("message"))

        val roundtrippedBetaDreamingError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamingError),
                jacksonTypeRef<BetaDreamingError>(),
            )

        assertThat(roundtrippedBetaDreamingError).isEqualTo(betaDreamingError)
    }

    @Test
    fun ofRateLimit() {
        val rateLimit = BetaRateLimitError.of("message")

        val betaDreamingError = BetaDreamingError.ofRateLimit(rateLimit)

        assertThat(betaDreamingError.invalidRequest()).isEmpty
        assertThat(betaDreamingError.authentication()).isEmpty
        assertThat(betaDreamingError.billing()).isEmpty
        assertThat(betaDreamingError.permission()).isEmpty
        assertThat(betaDreamingError.notFound()).isEmpty
        assertThat(betaDreamingError.rateLimit()).contains(rateLimit)
        assertThat(betaDreamingError.timeout()).isEmpty
        assertThat(betaDreamingError.api()).isEmpty
        assertThat(betaDreamingError.overloaded()).isEmpty
        assertThat(betaDreamingError.conflict()).isEmpty
    }

    @Test
    fun ofRateLimitRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamingError = BetaDreamingError.ofRateLimit(BetaRateLimitError.of("message"))

        val roundtrippedBetaDreamingError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamingError),
                jacksonTypeRef<BetaDreamingError>(),
            )

        assertThat(roundtrippedBetaDreamingError).isEqualTo(betaDreamingError)
    }

    @Test
    fun ofTimeout() {
        val timeout = BetaGatewayTimeoutError.of("message")

        val betaDreamingError = BetaDreamingError.ofTimeout(timeout)

        assertThat(betaDreamingError.invalidRequest()).isEmpty
        assertThat(betaDreamingError.authentication()).isEmpty
        assertThat(betaDreamingError.billing()).isEmpty
        assertThat(betaDreamingError.permission()).isEmpty
        assertThat(betaDreamingError.notFound()).isEmpty
        assertThat(betaDreamingError.rateLimit()).isEmpty
        assertThat(betaDreamingError.timeout()).contains(timeout)
        assertThat(betaDreamingError.api()).isEmpty
        assertThat(betaDreamingError.overloaded()).isEmpty
        assertThat(betaDreamingError.conflict()).isEmpty
    }

    @Test
    fun ofTimeoutRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamingError = BetaDreamingError.ofTimeout(BetaGatewayTimeoutError.of("message"))

        val roundtrippedBetaDreamingError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamingError),
                jacksonTypeRef<BetaDreamingError>(),
            )

        assertThat(roundtrippedBetaDreamingError).isEqualTo(betaDreamingError)
    }

    @Test
    fun ofApi() {
        val api = BetaApiError.of("message")

        val betaDreamingError = BetaDreamingError.ofApi(api)

        assertThat(betaDreamingError.invalidRequest()).isEmpty
        assertThat(betaDreamingError.authentication()).isEmpty
        assertThat(betaDreamingError.billing()).isEmpty
        assertThat(betaDreamingError.permission()).isEmpty
        assertThat(betaDreamingError.notFound()).isEmpty
        assertThat(betaDreamingError.rateLimit()).isEmpty
        assertThat(betaDreamingError.timeout()).isEmpty
        assertThat(betaDreamingError.api()).contains(api)
        assertThat(betaDreamingError.overloaded()).isEmpty
        assertThat(betaDreamingError.conflict()).isEmpty
    }

    @Test
    fun ofApiRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamingError = BetaDreamingError.ofApi(BetaApiError.of("message"))

        val roundtrippedBetaDreamingError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamingError),
                jacksonTypeRef<BetaDreamingError>(),
            )

        assertThat(roundtrippedBetaDreamingError).isEqualTo(betaDreamingError)
    }

    @Test
    fun ofOverloaded() {
        val overloaded = BetaOverloadedError.of("message")

        val betaDreamingError = BetaDreamingError.ofOverloaded(overloaded)

        assertThat(betaDreamingError.invalidRequest()).isEmpty
        assertThat(betaDreamingError.authentication()).isEmpty
        assertThat(betaDreamingError.billing()).isEmpty
        assertThat(betaDreamingError.permission()).isEmpty
        assertThat(betaDreamingError.notFound()).isEmpty
        assertThat(betaDreamingError.rateLimit()).isEmpty
        assertThat(betaDreamingError.timeout()).isEmpty
        assertThat(betaDreamingError.api()).isEmpty
        assertThat(betaDreamingError.overloaded()).contains(overloaded)
        assertThat(betaDreamingError.conflict()).isEmpty
    }

    @Test
    fun ofOverloadedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamingError = BetaDreamingError.ofOverloaded(BetaOverloadedError.of("message"))

        val roundtrippedBetaDreamingError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamingError),
                jacksonTypeRef<BetaDreamingError>(),
            )

        assertThat(roundtrippedBetaDreamingError).isEqualTo(betaDreamingError)
    }

    @Test
    fun ofConflict() {
        val conflict = BetaTargetStoreHeldError.builder().message("message").build()

        val betaDreamingError = BetaDreamingError.ofConflict(conflict)

        assertThat(betaDreamingError.invalidRequest()).isEmpty
        assertThat(betaDreamingError.authentication()).isEmpty
        assertThat(betaDreamingError.billing()).isEmpty
        assertThat(betaDreamingError.permission()).isEmpty
        assertThat(betaDreamingError.notFound()).isEmpty
        assertThat(betaDreamingError.rateLimit()).isEmpty
        assertThat(betaDreamingError.timeout()).isEmpty
        assertThat(betaDreamingError.api()).isEmpty
        assertThat(betaDreamingError.overloaded()).isEmpty
        assertThat(betaDreamingError.conflict()).contains(conflict)
    }

    @Test
    fun ofConflictRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamingError =
            BetaDreamingError.ofConflict(
                BetaTargetStoreHeldError.builder().message("message").build()
            )

        val roundtrippedBetaDreamingError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamingError),
                jacksonTypeRef<BetaDreamingError>(),
            )

        assertThat(roundtrippedBetaDreamingError).isEqualTo(betaDreamingError)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaDreamingError =
            jsonMapper()
                .convertValue(
                    JsonValue.from(mapOf("type" to "unknown_variant", "message" to "message")),
                    jacksonTypeRef<BetaDreamingError>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { betaDreamingError.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaDreamingError.message()).contains("message")

        val mismatchedBetaDreamingError =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "message" to listOf("invalid"))
                    ),
                    jacksonTypeRef<BetaDreamingError>(),
                )

        assertThat(mismatchedBetaDreamingError.message()).isEmpty
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
        val betaDreamingError =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaDreamingError>())

        val e = assertThrows<AnthropicInvalidDataException> { betaDreamingError.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaDreamingError.message()).isEmpty
    }
}

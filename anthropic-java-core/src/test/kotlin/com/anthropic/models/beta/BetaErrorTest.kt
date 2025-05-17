// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaErrorTest {

    @Test
    fun ofInvalidRequest() {
        val invalidRequest = BetaInvalidRequestError.builder().message("message").build()

        val betaError = BetaError.ofInvalidRequest(invalidRequest)

        assertThat(betaError.invalidRequest()).contains(invalidRequest)
        assertThat(betaError.authentication()).isEmpty
        assertThat(betaError.billing()).isEmpty
        assertThat(betaError.permission()).isEmpty
        assertThat(betaError.notFound()).isEmpty
        assertThat(betaError.rateLimit()).isEmpty
        assertThat(betaError.timeout()).isEmpty
        assertThat(betaError.api()).isEmpty
        assertThat(betaError.overloaded()).isEmpty
    }

    @Test
    fun ofInvalidRequestRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaError =
            BetaError.ofInvalidRequest(BetaInvalidRequestError.builder().message("message").build())

        val roundtrippedBetaError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaError),
                jacksonTypeRef<BetaError>(),
            )

        assertThat(roundtrippedBetaError).isEqualTo(betaError)
    }

    @Test
    fun ofAuthentication() {
        val authentication = BetaAuthenticationError.builder().message("message").build()

        val betaError = BetaError.ofAuthentication(authentication)

        assertThat(betaError.invalidRequest()).isEmpty
        assertThat(betaError.authentication()).contains(authentication)
        assertThat(betaError.billing()).isEmpty
        assertThat(betaError.permission()).isEmpty
        assertThat(betaError.notFound()).isEmpty
        assertThat(betaError.rateLimit()).isEmpty
        assertThat(betaError.timeout()).isEmpty
        assertThat(betaError.api()).isEmpty
        assertThat(betaError.overloaded()).isEmpty
    }

    @Test
    fun ofAuthenticationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaError =
            BetaError.ofAuthentication(BetaAuthenticationError.builder().message("message").build())

        val roundtrippedBetaError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaError),
                jacksonTypeRef<BetaError>(),
            )

        assertThat(roundtrippedBetaError).isEqualTo(betaError)
    }

    @Test
    fun ofBilling() {
        val billing = BetaBillingError.builder().message("message").build()

        val betaError = BetaError.ofBilling(billing)

        assertThat(betaError.invalidRequest()).isEmpty
        assertThat(betaError.authentication()).isEmpty
        assertThat(betaError.billing()).contains(billing)
        assertThat(betaError.permission()).isEmpty
        assertThat(betaError.notFound()).isEmpty
        assertThat(betaError.rateLimit()).isEmpty
        assertThat(betaError.timeout()).isEmpty
        assertThat(betaError.api()).isEmpty
        assertThat(betaError.overloaded()).isEmpty
    }

    @Test
    fun ofBillingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaError = BetaError.ofBilling(BetaBillingError.builder().message("message").build())

        val roundtrippedBetaError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaError),
                jacksonTypeRef<BetaError>(),
            )

        assertThat(roundtrippedBetaError).isEqualTo(betaError)
    }

    @Test
    fun ofPermission() {
        val permission = BetaPermissionError.builder().message("message").build()

        val betaError = BetaError.ofPermission(permission)

        assertThat(betaError.invalidRequest()).isEmpty
        assertThat(betaError.authentication()).isEmpty
        assertThat(betaError.billing()).isEmpty
        assertThat(betaError.permission()).contains(permission)
        assertThat(betaError.notFound()).isEmpty
        assertThat(betaError.rateLimit()).isEmpty
        assertThat(betaError.timeout()).isEmpty
        assertThat(betaError.api()).isEmpty
        assertThat(betaError.overloaded()).isEmpty
    }

    @Test
    fun ofPermissionRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaError =
            BetaError.ofPermission(BetaPermissionError.builder().message("message").build())

        val roundtrippedBetaError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaError),
                jacksonTypeRef<BetaError>(),
            )

        assertThat(roundtrippedBetaError).isEqualTo(betaError)
    }

    @Test
    fun ofNotFound() {
        val notFound = BetaNotFoundError.builder().message("message").build()

        val betaError = BetaError.ofNotFound(notFound)

        assertThat(betaError.invalidRequest()).isEmpty
        assertThat(betaError.authentication()).isEmpty
        assertThat(betaError.billing()).isEmpty
        assertThat(betaError.permission()).isEmpty
        assertThat(betaError.notFound()).contains(notFound)
        assertThat(betaError.rateLimit()).isEmpty
        assertThat(betaError.timeout()).isEmpty
        assertThat(betaError.api()).isEmpty
        assertThat(betaError.overloaded()).isEmpty
    }

    @Test
    fun ofNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaError = BetaError.ofNotFound(BetaNotFoundError.builder().message("message").build())

        val roundtrippedBetaError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaError),
                jacksonTypeRef<BetaError>(),
            )

        assertThat(roundtrippedBetaError).isEqualTo(betaError)
    }

    @Test
    fun ofRateLimit() {
        val rateLimit = BetaRateLimitError.builder().message("message").build()

        val betaError = BetaError.ofRateLimit(rateLimit)

        assertThat(betaError.invalidRequest()).isEmpty
        assertThat(betaError.authentication()).isEmpty
        assertThat(betaError.billing()).isEmpty
        assertThat(betaError.permission()).isEmpty
        assertThat(betaError.notFound()).isEmpty
        assertThat(betaError.rateLimit()).contains(rateLimit)
        assertThat(betaError.timeout()).isEmpty
        assertThat(betaError.api()).isEmpty
        assertThat(betaError.overloaded()).isEmpty
    }

    @Test
    fun ofRateLimitRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaError =
            BetaError.ofRateLimit(BetaRateLimitError.builder().message("message").build())

        val roundtrippedBetaError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaError),
                jacksonTypeRef<BetaError>(),
            )

        assertThat(roundtrippedBetaError).isEqualTo(betaError)
    }

    @Test
    fun ofTimeout() {
        val timeout = BetaGatewayTimeoutError.builder().message("message").build()

        val betaError = BetaError.ofTimeout(timeout)

        assertThat(betaError.invalidRequest()).isEmpty
        assertThat(betaError.authentication()).isEmpty
        assertThat(betaError.billing()).isEmpty
        assertThat(betaError.permission()).isEmpty
        assertThat(betaError.notFound()).isEmpty
        assertThat(betaError.rateLimit()).isEmpty
        assertThat(betaError.timeout()).contains(timeout)
        assertThat(betaError.api()).isEmpty
        assertThat(betaError.overloaded()).isEmpty
    }

    @Test
    fun ofTimeoutRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaError =
            BetaError.ofTimeout(BetaGatewayTimeoutError.builder().message("message").build())

        val roundtrippedBetaError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaError),
                jacksonTypeRef<BetaError>(),
            )

        assertThat(roundtrippedBetaError).isEqualTo(betaError)
    }

    @Test
    fun ofApi() {
        val api = BetaApiError.builder().message("message").build()

        val betaError = BetaError.ofApi(api)

        assertThat(betaError.invalidRequest()).isEmpty
        assertThat(betaError.authentication()).isEmpty
        assertThat(betaError.billing()).isEmpty
        assertThat(betaError.permission()).isEmpty
        assertThat(betaError.notFound()).isEmpty
        assertThat(betaError.rateLimit()).isEmpty
        assertThat(betaError.timeout()).isEmpty
        assertThat(betaError.api()).contains(api)
        assertThat(betaError.overloaded()).isEmpty
    }

    @Test
    fun ofApiRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaError = BetaError.ofApi(BetaApiError.builder().message("message").build())

        val roundtrippedBetaError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaError),
                jacksonTypeRef<BetaError>(),
            )

        assertThat(roundtrippedBetaError).isEqualTo(betaError)
    }

    @Test
    fun ofOverloaded() {
        val overloaded = BetaOverloadedError.builder().message("message").build()

        val betaError = BetaError.ofOverloaded(overloaded)

        assertThat(betaError.invalidRequest()).isEmpty
        assertThat(betaError.authentication()).isEmpty
        assertThat(betaError.billing()).isEmpty
        assertThat(betaError.permission()).isEmpty
        assertThat(betaError.notFound()).isEmpty
        assertThat(betaError.rateLimit()).isEmpty
        assertThat(betaError.timeout()).isEmpty
        assertThat(betaError.api()).isEmpty
        assertThat(betaError.overloaded()).contains(overloaded)
    }

    @Test
    fun ofOverloadedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaError =
            BetaError.ofOverloaded(BetaOverloadedError.builder().message("message").build())

        val roundtrippedBetaError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaError),
                jacksonTypeRef<BetaError>(),
            )

        assertThat(roundtrippedBetaError).isEqualTo(betaError)
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
        val betaError = jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaError>())

        val e = assertThrows<AnthropicInvalidDataException> { betaError.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}

// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class ErrorObjectTest {

    @Test
    fun ofInvalidRequestError() {
        val invalidRequestError = InvalidRequestError.builder().message("message").build()

        val errorObject = ErrorObject.ofInvalidRequestError(invalidRequestError)

        assertThat(errorObject.invalidRequestError()).contains(invalidRequestError)
        assertThat(errorObject.authenticationError()).isEmpty
        assertThat(errorObject.billingError()).isEmpty
        assertThat(errorObject.permissionError()).isEmpty
        assertThat(errorObject.notFoundError()).isEmpty
        assertThat(errorObject.rateLimitError()).isEmpty
        assertThat(errorObject.timeoutError()).isEmpty
        assertThat(errorObject.apiError()).isEmpty
        assertThat(errorObject.overloadedError()).isEmpty
    }

    @Test
    fun ofInvalidRequestErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorObject =
            ErrorObject.ofInvalidRequestError(
                InvalidRequestError.builder().message("message").build()
            )

        val roundtrippedErrorObject =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorObject),
                jacksonTypeRef<ErrorObject>(),
            )

        assertThat(roundtrippedErrorObject).isEqualTo(errorObject)
    }

    @Test
    fun ofAuthenticationError() {
        val authenticationError = AuthenticationError.builder().message("message").build()

        val errorObject = ErrorObject.ofAuthenticationError(authenticationError)

        assertThat(errorObject.invalidRequestError()).isEmpty
        assertThat(errorObject.authenticationError()).contains(authenticationError)
        assertThat(errorObject.billingError()).isEmpty
        assertThat(errorObject.permissionError()).isEmpty
        assertThat(errorObject.notFoundError()).isEmpty
        assertThat(errorObject.rateLimitError()).isEmpty
        assertThat(errorObject.timeoutError()).isEmpty
        assertThat(errorObject.apiError()).isEmpty
        assertThat(errorObject.overloadedError()).isEmpty
    }

    @Test
    fun ofAuthenticationErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorObject =
            ErrorObject.ofAuthenticationError(
                AuthenticationError.builder().message("message").build()
            )

        val roundtrippedErrorObject =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorObject),
                jacksonTypeRef<ErrorObject>(),
            )

        assertThat(roundtrippedErrorObject).isEqualTo(errorObject)
    }

    @Test
    fun ofBillingError() {
        val billingError = BillingError.builder().message("message").build()

        val errorObject = ErrorObject.ofBillingError(billingError)

        assertThat(errorObject.invalidRequestError()).isEmpty
        assertThat(errorObject.authenticationError()).isEmpty
        assertThat(errorObject.billingError()).contains(billingError)
        assertThat(errorObject.permissionError()).isEmpty
        assertThat(errorObject.notFoundError()).isEmpty
        assertThat(errorObject.rateLimitError()).isEmpty
        assertThat(errorObject.timeoutError()).isEmpty
        assertThat(errorObject.apiError()).isEmpty
        assertThat(errorObject.overloadedError()).isEmpty
    }

    @Test
    fun ofBillingErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorObject =
            ErrorObject.ofBillingError(BillingError.builder().message("message").build())

        val roundtrippedErrorObject =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorObject),
                jacksonTypeRef<ErrorObject>(),
            )

        assertThat(roundtrippedErrorObject).isEqualTo(errorObject)
    }

    @Test
    fun ofPermissionError() {
        val permissionError = PermissionError.builder().message("message").build()

        val errorObject = ErrorObject.ofPermissionError(permissionError)

        assertThat(errorObject.invalidRequestError()).isEmpty
        assertThat(errorObject.authenticationError()).isEmpty
        assertThat(errorObject.billingError()).isEmpty
        assertThat(errorObject.permissionError()).contains(permissionError)
        assertThat(errorObject.notFoundError()).isEmpty
        assertThat(errorObject.rateLimitError()).isEmpty
        assertThat(errorObject.timeoutError()).isEmpty
        assertThat(errorObject.apiError()).isEmpty
        assertThat(errorObject.overloadedError()).isEmpty
    }

    @Test
    fun ofPermissionErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorObject =
            ErrorObject.ofPermissionError(PermissionError.builder().message("message").build())

        val roundtrippedErrorObject =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorObject),
                jacksonTypeRef<ErrorObject>(),
            )

        assertThat(roundtrippedErrorObject).isEqualTo(errorObject)
    }

    @Test
    fun ofNotFoundError() {
        val notFoundError = NotFoundError.builder().message("message").build()

        val errorObject = ErrorObject.ofNotFoundError(notFoundError)

        assertThat(errorObject.invalidRequestError()).isEmpty
        assertThat(errorObject.authenticationError()).isEmpty
        assertThat(errorObject.billingError()).isEmpty
        assertThat(errorObject.permissionError()).isEmpty
        assertThat(errorObject.notFoundError()).contains(notFoundError)
        assertThat(errorObject.rateLimitError()).isEmpty
        assertThat(errorObject.timeoutError()).isEmpty
        assertThat(errorObject.apiError()).isEmpty
        assertThat(errorObject.overloadedError()).isEmpty
    }

    @Test
    fun ofNotFoundErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorObject =
            ErrorObject.ofNotFoundError(NotFoundError.builder().message("message").build())

        val roundtrippedErrorObject =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorObject),
                jacksonTypeRef<ErrorObject>(),
            )

        assertThat(roundtrippedErrorObject).isEqualTo(errorObject)
    }

    @Test
    fun ofRateLimitError() {
        val rateLimitError = RateLimitError.builder().message("message").build()

        val errorObject = ErrorObject.ofRateLimitError(rateLimitError)

        assertThat(errorObject.invalidRequestError()).isEmpty
        assertThat(errorObject.authenticationError()).isEmpty
        assertThat(errorObject.billingError()).isEmpty
        assertThat(errorObject.permissionError()).isEmpty
        assertThat(errorObject.notFoundError()).isEmpty
        assertThat(errorObject.rateLimitError()).contains(rateLimitError)
        assertThat(errorObject.timeoutError()).isEmpty
        assertThat(errorObject.apiError()).isEmpty
        assertThat(errorObject.overloadedError()).isEmpty
    }

    @Test
    fun ofRateLimitErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorObject =
            ErrorObject.ofRateLimitError(RateLimitError.builder().message("message").build())

        val roundtrippedErrorObject =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorObject),
                jacksonTypeRef<ErrorObject>(),
            )

        assertThat(roundtrippedErrorObject).isEqualTo(errorObject)
    }

    @Test
    fun ofTimeoutError() {
        val timeoutError = GatewayTimeoutError.builder().message("message").build()

        val errorObject = ErrorObject.ofTimeoutError(timeoutError)

        assertThat(errorObject.invalidRequestError()).isEmpty
        assertThat(errorObject.authenticationError()).isEmpty
        assertThat(errorObject.billingError()).isEmpty
        assertThat(errorObject.permissionError()).isEmpty
        assertThat(errorObject.notFoundError()).isEmpty
        assertThat(errorObject.rateLimitError()).isEmpty
        assertThat(errorObject.timeoutError()).contains(timeoutError)
        assertThat(errorObject.apiError()).isEmpty
        assertThat(errorObject.overloadedError()).isEmpty
    }

    @Test
    fun ofTimeoutErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorObject =
            ErrorObject.ofTimeoutError(GatewayTimeoutError.builder().message("message").build())

        val roundtrippedErrorObject =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorObject),
                jacksonTypeRef<ErrorObject>(),
            )

        assertThat(roundtrippedErrorObject).isEqualTo(errorObject)
    }

    @Test
    fun ofApiError() {
        val apiError = ApiErrorObject.builder().message("message").build()

        val errorObject = ErrorObject.ofApiError(apiError)

        assertThat(errorObject.invalidRequestError()).isEmpty
        assertThat(errorObject.authenticationError()).isEmpty
        assertThat(errorObject.billingError()).isEmpty
        assertThat(errorObject.permissionError()).isEmpty
        assertThat(errorObject.notFoundError()).isEmpty
        assertThat(errorObject.rateLimitError()).isEmpty
        assertThat(errorObject.timeoutError()).isEmpty
        assertThat(errorObject.apiError()).contains(apiError)
        assertThat(errorObject.overloadedError()).isEmpty
    }

    @Test
    fun ofApiErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorObject =
            ErrorObject.ofApiError(ApiErrorObject.builder().message("message").build())

        val roundtrippedErrorObject =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorObject),
                jacksonTypeRef<ErrorObject>(),
            )

        assertThat(roundtrippedErrorObject).isEqualTo(errorObject)
    }

    @Test
    fun ofOverloadedError() {
        val overloadedError = OverloadedError.builder().message("message").build()

        val errorObject = ErrorObject.ofOverloadedError(overloadedError)

        assertThat(errorObject.invalidRequestError()).isEmpty
        assertThat(errorObject.authenticationError()).isEmpty
        assertThat(errorObject.billingError()).isEmpty
        assertThat(errorObject.permissionError()).isEmpty
        assertThat(errorObject.notFoundError()).isEmpty
        assertThat(errorObject.rateLimitError()).isEmpty
        assertThat(errorObject.timeoutError()).isEmpty
        assertThat(errorObject.apiError()).isEmpty
        assertThat(errorObject.overloadedError()).contains(overloadedError)
    }

    @Test
    fun ofOverloadedErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorObject =
            ErrorObject.ofOverloadedError(OverloadedError.builder().message("message").build())

        val roundtrippedErrorObject =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorObject),
                jacksonTypeRef<ErrorObject>(),
            )

        assertThat(roundtrippedErrorObject).isEqualTo(errorObject)
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
        val errorObject = jsonMapper().convertValue(testCase.value, jacksonTypeRef<ErrorObject>())

        val e = assertThrows<AnthropicInvalidDataException> { errorObject.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}

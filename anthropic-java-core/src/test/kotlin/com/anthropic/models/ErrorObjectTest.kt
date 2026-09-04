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
        val invalidRequestError = InvalidRequestError.of("message")

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
        val errorObject = ErrorObject.ofInvalidRequestError(InvalidRequestError.of("message"))

        val roundtrippedErrorObject =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorObject),
                jacksonTypeRef<ErrorObject>(),
            )

        assertThat(roundtrippedErrorObject).isEqualTo(errorObject)
    }

    @Test
    fun ofAuthenticationError() {
        val authenticationError = AuthenticationError.of("message")

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
        val errorObject = ErrorObject.ofAuthenticationError(AuthenticationError.of("message"))

        val roundtrippedErrorObject =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorObject),
                jacksonTypeRef<ErrorObject>(),
            )

        assertThat(roundtrippedErrorObject).isEqualTo(errorObject)
    }

    @Test
    fun ofBillingError() {
        val billingError = BillingError.of("message")

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
        val errorObject = ErrorObject.ofBillingError(BillingError.of("message"))

        val roundtrippedErrorObject =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorObject),
                jacksonTypeRef<ErrorObject>(),
            )

        assertThat(roundtrippedErrorObject).isEqualTo(errorObject)
    }

    @Test
    fun ofPermissionError() {
        val permissionError = PermissionError.of("message")

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
        val errorObject = ErrorObject.ofPermissionError(PermissionError.of("message"))

        val roundtrippedErrorObject =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorObject),
                jacksonTypeRef<ErrorObject>(),
            )

        assertThat(roundtrippedErrorObject).isEqualTo(errorObject)
    }

    @Test
    fun ofNotFoundError() {
        val notFoundError = NotFoundError.of("message")

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
        val errorObject = ErrorObject.ofNotFoundError(NotFoundError.of("message"))

        val roundtrippedErrorObject =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorObject),
                jacksonTypeRef<ErrorObject>(),
            )

        assertThat(roundtrippedErrorObject).isEqualTo(errorObject)
    }

    @Test
    fun ofRateLimitError() {
        val rateLimitError = RateLimitError.of("message")

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
        val errorObject = ErrorObject.ofRateLimitError(RateLimitError.of("message"))

        val roundtrippedErrorObject =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorObject),
                jacksonTypeRef<ErrorObject>(),
            )

        assertThat(roundtrippedErrorObject).isEqualTo(errorObject)
    }

    @Test
    fun ofTimeoutError() {
        val timeoutError = GatewayTimeoutError.of("message")

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
        val errorObject = ErrorObject.ofTimeoutError(GatewayTimeoutError.of("message"))

        val roundtrippedErrorObject =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorObject),
                jacksonTypeRef<ErrorObject>(),
            )

        assertThat(roundtrippedErrorObject).isEqualTo(errorObject)
    }

    @Test
    fun ofApiError() {
        val apiError = ApiErrorObject.of("message")

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
        val errorObject = ErrorObject.ofApiError(ApiErrorObject.of("message"))

        val roundtrippedErrorObject =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorObject),
                jacksonTypeRef<ErrorObject>(),
            )

        assertThat(roundtrippedErrorObject).isEqualTo(errorObject)
    }

    @Test
    fun ofOverloadedError() {
        val overloadedError = OverloadedError.of("message")

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
        val errorObject = ErrorObject.ofOverloadedError(OverloadedError.of("message"))

        val roundtrippedErrorObject =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorObject),
                jacksonTypeRef<ErrorObject>(),
            )

        assertThat(roundtrippedErrorObject).isEqualTo(errorObject)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val errorObject =
            jsonMapper()
                .convertValue(
                    JsonValue.from(mapOf("type" to "unknown_variant", "message" to "message")),
                    jacksonTypeRef<ErrorObject>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { errorObject.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(errorObject.message()).isEqualTo("message")

        val mismatchedErrorObject =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "message" to listOf("invalid"))
                    ),
                    jacksonTypeRef<ErrorObject>(),
                )

        assertThrows<AnthropicInvalidDataException> { mismatchedErrorObject.message() }
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

        assertThrows<AnthropicInvalidDataException> { errorObject.message() }
    }
}

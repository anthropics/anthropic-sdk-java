package com.anthropic.models

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

internal class ErrorObjectTest {

    @Test
    fun ofInvalidRequestError() {
        val invalidRequestError = InvalidRequestError.of("message")

        val errorObject = ErrorObject.ofInvalidRequestError(invalidRequestError)

        assertThat(presentVariants(errorObject))
            .isEqualTo(mapOf("invalidRequestError" to Optional.of(invalidRequestError)))
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

        assertThat(presentVariants(errorObject))
            .isEqualTo(mapOf("authenticationError" to Optional.of(authenticationError)))
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

        assertThat(presentVariants(errorObject))
            .isEqualTo(mapOf("billingError" to Optional.of(billingError)))
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

        assertThat(presentVariants(errorObject))
            .isEqualTo(mapOf("permissionError" to Optional.of(permissionError)))
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

        assertThat(presentVariants(errorObject))
            .isEqualTo(mapOf("notFoundError" to Optional.of(notFoundError)))
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

        assertThat(presentVariants(errorObject))
            .isEqualTo(mapOf("rateLimitError" to Optional.of(rateLimitError)))
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

        assertThat(presentVariants(errorObject))
            .isEqualTo(mapOf("timeoutError" to Optional.of(timeoutError)))
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

        assertThat(presentVariants(errorObject))
            .isEqualTo(mapOf("apiError" to Optional.of(apiError)))
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

        assertThat(presentVariants(errorObject))
            .isEqualTo(mapOf("overloadedError" to Optional.of(overloadedError)))
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

    private fun presentVariants(errorObject: ErrorObject): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "invalidRequestError" to errorObject.invalidRequestError(),
                "authenticationError" to errorObject.authenticationError(),
                "billingError" to errorObject.billingError(),
                "permissionError" to errorObject.permissionError(),
                "notFoundError" to errorObject.notFoundError(),
                "rateLimitError" to errorObject.rateLimitError(),
                "timeoutError" to errorObject.timeoutError(),
                "apiError" to errorObject.apiError(),
                "overloadedError" to errorObject.overloadedError(),
            )
            .filterValues { it.isPresent }
}

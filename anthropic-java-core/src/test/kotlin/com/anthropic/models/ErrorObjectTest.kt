// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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
        assertThat(errorObject.gatewayTimeoutError()).isEmpty
        assertThat(errorObject.api()).isEmpty
        assertThat(errorObject.overloadedError()).isEmpty
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
        assertThat(errorObject.gatewayTimeoutError()).isEmpty
        assertThat(errorObject.api()).isEmpty
        assertThat(errorObject.overloadedError()).isEmpty
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
        assertThat(errorObject.gatewayTimeoutError()).isEmpty
        assertThat(errorObject.api()).isEmpty
        assertThat(errorObject.overloadedError()).isEmpty
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
        assertThat(errorObject.gatewayTimeoutError()).isEmpty
        assertThat(errorObject.api()).isEmpty
        assertThat(errorObject.overloadedError()).isEmpty
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
        assertThat(errorObject.gatewayTimeoutError()).isEmpty
        assertThat(errorObject.api()).isEmpty
        assertThat(errorObject.overloadedError()).isEmpty
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
        assertThat(errorObject.gatewayTimeoutError()).isEmpty
        assertThat(errorObject.api()).isEmpty
        assertThat(errorObject.overloadedError()).isEmpty
    }

    @Test
    fun ofGatewayTimeoutError() {
        val gatewayTimeoutError = GatewayTimeoutError.builder().message("message").build()

        val errorObject = ErrorObject.ofGatewayTimeoutError(gatewayTimeoutError)

        assertThat(errorObject.invalidRequestError()).isEmpty
        assertThat(errorObject.authenticationError()).isEmpty
        assertThat(errorObject.billingError()).isEmpty
        assertThat(errorObject.permissionError()).isEmpty
        assertThat(errorObject.notFoundError()).isEmpty
        assertThat(errorObject.rateLimitError()).isEmpty
        assertThat(errorObject.gatewayTimeoutError()).contains(gatewayTimeoutError)
        assertThat(errorObject.api()).isEmpty
        assertThat(errorObject.overloadedError()).isEmpty
    }

    @Test
    fun ofApi() {
        val api = ApiErrorObject.builder().message("message").build()

        val errorObject = ErrorObject.ofApi(api)

        assertThat(errorObject.invalidRequestError()).isEmpty
        assertThat(errorObject.authenticationError()).isEmpty
        assertThat(errorObject.billingError()).isEmpty
        assertThat(errorObject.permissionError()).isEmpty
        assertThat(errorObject.notFoundError()).isEmpty
        assertThat(errorObject.rateLimitError()).isEmpty
        assertThat(errorObject.gatewayTimeoutError()).isEmpty
        assertThat(errorObject.api()).contains(api)
        assertThat(errorObject.overloadedError()).isEmpty
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
        assertThat(errorObject.gatewayTimeoutError()).isEmpty
        assertThat(errorObject.api()).isEmpty
        assertThat(errorObject.overloadedError()).contains(overloadedError)
    }
}

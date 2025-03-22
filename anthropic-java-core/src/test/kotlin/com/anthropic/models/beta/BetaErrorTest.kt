// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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
        assertThat(betaError.gatewayTimeout()).isEmpty
        assertThat(betaError.api()).isEmpty
        assertThat(betaError.overloaded()).isEmpty
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
        assertThat(betaError.gatewayTimeout()).isEmpty
        assertThat(betaError.api()).isEmpty
        assertThat(betaError.overloaded()).isEmpty
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
        assertThat(betaError.gatewayTimeout()).isEmpty
        assertThat(betaError.api()).isEmpty
        assertThat(betaError.overloaded()).isEmpty
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
        assertThat(betaError.gatewayTimeout()).isEmpty
        assertThat(betaError.api()).isEmpty
        assertThat(betaError.overloaded()).isEmpty
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
        assertThat(betaError.gatewayTimeout()).isEmpty
        assertThat(betaError.api()).isEmpty
        assertThat(betaError.overloaded()).isEmpty
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
        assertThat(betaError.gatewayTimeout()).isEmpty
        assertThat(betaError.api()).isEmpty
        assertThat(betaError.overloaded()).isEmpty
    }

    @Test
    fun ofGatewayTimeout() {
        val gatewayTimeout = BetaGatewayTimeoutError.builder().message("message").build()

        val betaError = BetaError.ofGatewayTimeout(gatewayTimeout)

        assertThat(betaError.invalidRequest()).isEmpty
        assertThat(betaError.authentication()).isEmpty
        assertThat(betaError.billing()).isEmpty
        assertThat(betaError.permission()).isEmpty
        assertThat(betaError.notFound()).isEmpty
        assertThat(betaError.rateLimit()).isEmpty
        assertThat(betaError.gatewayTimeout()).contains(gatewayTimeout)
        assertThat(betaError.api()).isEmpty
        assertThat(betaError.overloaded()).isEmpty
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
        assertThat(betaError.gatewayTimeout()).isEmpty
        assertThat(betaError.api()).contains(api)
        assertThat(betaError.overloaded()).isEmpty
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
        assertThat(betaError.gatewayTimeout()).isEmpty
        assertThat(betaError.api()).isEmpty
        assertThat(betaError.overloaded()).contains(overloaded)
    }
}

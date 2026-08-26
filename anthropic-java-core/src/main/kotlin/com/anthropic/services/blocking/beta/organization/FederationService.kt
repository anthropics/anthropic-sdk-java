// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.organization.federation.IssuerService
import com.anthropic.services.blocking.beta.organization.federation.RuleService
import java.util.function.Consumer

interface FederationService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): FederationService

    fun issuers(): IssuerService

    fun rules(): RuleService

    /** A view of [FederationService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): FederationService.WithRawResponse

        fun issuers(): IssuerService.WithRawResponse

        fun rules(): RuleService.WithRawResponse
    }
}

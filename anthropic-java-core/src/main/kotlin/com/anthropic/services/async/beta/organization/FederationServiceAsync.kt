// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.organization.federation.IssuerServiceAsync
import com.anthropic.services.async.beta.organization.federation.RuleServiceAsync
import java.util.function.Consumer

interface FederationServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): FederationServiceAsync

    fun issuers(): IssuerServiceAsync

    fun rules(): RuleServiceAsync

    /**
     * A view of [FederationServiceAsync] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): FederationServiceAsync.WithRawResponse

        fun issuers(): IssuerServiceAsync.WithRawResponse

        fun rules(): RuleServiceAsync.WithRawResponse
    }
}

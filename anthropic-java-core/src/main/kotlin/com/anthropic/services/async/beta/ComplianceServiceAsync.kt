package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.compliance.ReviewServiceAsync
import java.util.function.Consumer

interface ComplianceServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ComplianceServiceAsync

    fun review(): ReviewServiceAsync

    /**
     * A view of [ComplianceServiceAsync] that provides access to raw HTTP responses for each
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
        ): ComplianceServiceAsync.WithRawResponse

        fun review(): ReviewServiceAsync.WithRawResponse
    }
}

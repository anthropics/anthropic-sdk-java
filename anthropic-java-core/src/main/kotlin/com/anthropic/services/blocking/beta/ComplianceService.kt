package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.compliance.ReviewService
import java.util.function.Consumer

interface ComplianceService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ComplianceService

    fun review(): ReviewService

    /** A view of [ComplianceService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ComplianceService.WithRawResponse

        fun review(): ReviewService.WithRawResponse
    }
}

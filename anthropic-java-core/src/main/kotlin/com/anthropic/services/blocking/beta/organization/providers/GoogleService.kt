package com.anthropic.services.blocking.beta.organization.providers

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.organization.providers.google.ProjectService
import java.util.function.Consumer

interface GoogleService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): GoogleService

    fun projects(): ProjectService

    /** A view of [GoogleService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): GoogleService.WithRawResponse

        fun projects(): ProjectService.WithRawResponse
    }
}

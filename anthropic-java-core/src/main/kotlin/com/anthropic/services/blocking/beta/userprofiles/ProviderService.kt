package com.anthropic.services.blocking.beta.userprofiles

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.userprofiles.providers.GoogleService
import java.util.function.Consumer

interface ProviderService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ProviderService

    fun google(): GoogleService

    /** A view of [ProviderService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): ProviderService.WithRawResponse

        fun google(): GoogleService.WithRawResponse
    }
}

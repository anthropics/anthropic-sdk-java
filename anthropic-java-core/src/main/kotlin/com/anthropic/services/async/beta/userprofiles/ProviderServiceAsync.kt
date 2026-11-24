package com.anthropic.services.async.beta.userprofiles

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.userprofiles.providers.GoogleServiceAsync
import java.util.function.Consumer

interface ProviderServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ProviderServiceAsync

    fun google(): GoogleServiceAsync

    /**
     * A view of [ProviderServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ProviderServiceAsync.WithRawResponse

        fun google(): GoogleServiceAsync.WithRawResponse
    }
}

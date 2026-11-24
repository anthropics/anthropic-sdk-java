package com.anthropic.services.async.beta.organization.providers

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.organization.providers.google.ProjectServiceAsync
import java.util.function.Consumer

interface GoogleServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): GoogleServiceAsync

    fun projects(): ProjectServiceAsync

    /**
     * A view of [GoogleServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): GoogleServiceAsync.WithRawResponse

        fun projects(): ProjectServiceAsync.WithRawResponse
    }
}

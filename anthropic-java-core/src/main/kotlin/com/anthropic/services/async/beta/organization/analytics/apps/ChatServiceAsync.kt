package com.anthropic.services.async.beta.organization.analytics.apps

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.organization.analytics.apps.chat.ProjectServiceAsync
import java.util.function.Consumer

interface ChatServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ChatServiceAsync

    fun projects(): ProjectServiceAsync

    /** A view of [ChatServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): ChatServiceAsync.WithRawResponse

        fun projects(): ProjectServiceAsync.WithRawResponse
    }
}

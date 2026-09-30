package com.anthropic.services.blocking.beta.organization.analytics

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.organization.analytics.apps.ChatService
import java.util.function.Consumer

interface AppService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): AppService

    fun chat(): ChatService

    /** A view of [AppService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): AppService.WithRawResponse

        fun chat(): ChatService.WithRawResponse
    }
}

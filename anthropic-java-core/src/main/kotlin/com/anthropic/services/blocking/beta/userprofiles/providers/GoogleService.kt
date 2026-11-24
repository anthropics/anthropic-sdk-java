package com.anthropic.services.blocking.beta.userprofiles.providers

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.userprofiles.providers.google.DataControlService
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

    fun dataControls(): DataControlService

    /** A view of [GoogleService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): GoogleService.WithRawResponse

        fun dataControls(): DataControlService.WithRawResponse
    }
}

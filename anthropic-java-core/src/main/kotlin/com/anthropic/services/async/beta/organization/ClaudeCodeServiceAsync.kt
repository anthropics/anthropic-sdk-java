package com.anthropic.services.async.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.organization.claudecode.ManagedSettingServiceAsync
import java.util.function.Consumer

interface ClaudeCodeServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ClaudeCodeServiceAsync

    fun managedSettings(): ManagedSettingServiceAsync

    /**
     * A view of [ClaudeCodeServiceAsync] that provides access to raw HTTP responses for each
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
        ): ClaudeCodeServiceAsync.WithRawResponse

        fun managedSettings(): ManagedSettingServiceAsync.WithRawResponse
    }
}

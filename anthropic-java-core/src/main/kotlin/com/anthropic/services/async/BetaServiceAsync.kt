// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.AgentServiceAsync
import com.anthropic.services.async.beta.EnvironmentServiceAsync
import com.anthropic.services.async.beta.FileServiceAsync
import com.anthropic.services.async.beta.MemoryStoreServiceAsync
import com.anthropic.services.async.beta.MessageServiceAsync
import com.anthropic.services.async.beta.ModelServiceAsync
import com.anthropic.services.async.beta.SessionServiceAsync
import com.anthropic.services.async.beta.SkillServiceAsync
import com.anthropic.services.async.beta.UserProfileServiceAsync
import com.anthropic.services.async.beta.VaultServiceAsync
import com.anthropic.services.async.beta.WebhookServiceAsync
import java.util.function.Consumer

interface BetaServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): BetaServiceAsync

    fun models(): ModelServiceAsync

    fun messages(): MessageServiceAsync

    fun agents(): AgentServiceAsync

    fun environments(): EnvironmentServiceAsync

    fun sessions(): SessionServiceAsync

    fun vaults(): VaultServiceAsync

    fun memoryStores(): MemoryStoreServiceAsync

    fun files(): FileServiceAsync

    fun skills(): SkillServiceAsync

    fun webhooks(): WebhookServiceAsync

    fun userProfiles(): UserProfileServiceAsync

    /** A view of [BetaServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): BetaServiceAsync.WithRawResponse

        fun models(): ModelServiceAsync.WithRawResponse

        fun messages(): MessageServiceAsync.WithRawResponse

        fun agents(): AgentServiceAsync.WithRawResponse

        fun environments(): EnvironmentServiceAsync.WithRawResponse

        fun sessions(): SessionServiceAsync.WithRawResponse

        fun vaults(): VaultServiceAsync.WithRawResponse

        fun memoryStores(): MemoryStoreServiceAsync.WithRawResponse

        fun files(): FileServiceAsync.WithRawResponse

        fun skills(): SkillServiceAsync.WithRawResponse

        fun webhooks(): WebhookServiceAsync.WithRawResponse

        fun userProfiles(): UserProfileServiceAsync.WithRawResponse
    }
}

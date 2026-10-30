package com.anthropic.services.async.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.organization.analytics.AppServiceAsync
import com.anthropic.services.async.organization.analytics.ArtifactServiceAsync
import com.anthropic.services.async.organization.analytics.ConnectorServiceAsync
import com.anthropic.services.async.organization.analytics.PluginServiceAsync
import com.anthropic.services.async.organization.analytics.SkillServiceAsync
import com.anthropic.services.async.organization.analytics.SummaryServiceAsync
import com.anthropic.services.async.organization.analytics.UserServiceAsync
import java.util.function.Consumer

interface AnalyticsServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): AnalyticsServiceAsync

    fun summaries(): SummaryServiceAsync

    fun users(): UserServiceAsync

    fun apps(): AppServiceAsync

    fun connectors(): ConnectorServiceAsync

    fun plugins(): PluginServiceAsync

    fun skills(): SkillServiceAsync

    fun artifacts(): ArtifactServiceAsync

    /**
     * A view of [AnalyticsServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): AnalyticsServiceAsync.WithRawResponse

        fun summaries(): SummaryServiceAsync.WithRawResponse

        fun users(): UserServiceAsync.WithRawResponse

        fun apps(): AppServiceAsync.WithRawResponse

        fun connectors(): ConnectorServiceAsync.WithRawResponse

        fun plugins(): PluginServiceAsync.WithRawResponse

        fun skills(): SkillServiceAsync.WithRawResponse

        fun artifacts(): ArtifactServiceAsync.WithRawResponse
    }
}

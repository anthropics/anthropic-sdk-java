package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.organization.analytics.AppService
import com.anthropic.services.blocking.beta.organization.analytics.ArtifactService
import com.anthropic.services.blocking.beta.organization.analytics.ConnectorService
import com.anthropic.services.blocking.beta.organization.analytics.CostReportService
import com.anthropic.services.blocking.beta.organization.analytics.PluginService
import com.anthropic.services.blocking.beta.organization.analytics.SkillService
import com.anthropic.services.blocking.beta.organization.analytics.SummaryService
import com.anthropic.services.blocking.beta.organization.analytics.UsageReportService
import com.anthropic.services.blocking.beta.organization.analytics.UserService
import java.util.function.Consumer

interface AnalyticsService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): AnalyticsService

    fun summaries(): SummaryService

    fun users(): UserService

    fun apps(): AppService

    fun connectors(): ConnectorService

    fun plugins(): PluginService

    fun skills(): SkillService

    fun artifacts(): ArtifactService

    fun usageReport(): UsageReportService

    fun costReport(): CostReportService

    /** A view of [AnalyticsService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): AnalyticsService.WithRawResponse

        fun summaries(): SummaryService.WithRawResponse

        fun users(): UserService.WithRawResponse

        fun apps(): AppService.WithRawResponse

        fun connectors(): ConnectorService.WithRawResponse

        fun plugins(): PluginService.WithRawResponse

        fun skills(): SkillService.WithRawResponse

        fun artifacts(): ArtifactService.WithRawResponse

        fun usageReport(): UsageReportService.WithRawResponse

        fun costReport(): CostReportService.WithRawResponse
    }
}

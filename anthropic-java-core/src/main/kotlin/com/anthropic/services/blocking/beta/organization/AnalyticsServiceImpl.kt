package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.organization.analytics.AppService
import com.anthropic.services.blocking.beta.organization.analytics.AppServiceImpl
import com.anthropic.services.blocking.beta.organization.analytics.ArtifactService
import com.anthropic.services.blocking.beta.organization.analytics.ArtifactServiceImpl
import com.anthropic.services.blocking.beta.organization.analytics.ConnectorService
import com.anthropic.services.blocking.beta.organization.analytics.ConnectorServiceImpl
import com.anthropic.services.blocking.beta.organization.analytics.CostReportService
import com.anthropic.services.blocking.beta.organization.analytics.CostReportServiceImpl
import com.anthropic.services.blocking.beta.organization.analytics.PluginService
import com.anthropic.services.blocking.beta.organization.analytics.PluginServiceImpl
import com.anthropic.services.blocking.beta.organization.analytics.SkillService
import com.anthropic.services.blocking.beta.organization.analytics.SkillServiceImpl
import com.anthropic.services.blocking.beta.organization.analytics.SummaryService
import com.anthropic.services.blocking.beta.organization.analytics.SummaryServiceImpl
import com.anthropic.services.blocking.beta.organization.analytics.UsageReportService
import com.anthropic.services.blocking.beta.organization.analytics.UsageReportServiceImpl
import com.anthropic.services.blocking.beta.organization.analytics.UserService
import com.anthropic.services.blocking.beta.organization.analytics.UserServiceImpl
import java.util.function.Consumer

class AnalyticsServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    AnalyticsService {

    private val withRawResponse: AnalyticsService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val summaries: SummaryService by lazy { SummaryServiceImpl(clientOptions) }

    private val users: UserService by lazy { UserServiceImpl(clientOptions) }

    private val apps: AppService by lazy { AppServiceImpl(clientOptions) }

    private val connectors: ConnectorService by lazy { ConnectorServiceImpl(clientOptions) }

    private val plugins: PluginService by lazy { PluginServiceImpl(clientOptions) }

    private val skills: SkillService by lazy { SkillServiceImpl(clientOptions) }

    private val artifacts: ArtifactService by lazy { ArtifactServiceImpl(clientOptions) }

    private val usageReport: UsageReportService by lazy { UsageReportServiceImpl(clientOptions) }

    private val costReport: CostReportService by lazy { CostReportServiceImpl(clientOptions) }

    override fun withRawResponse(): AnalyticsService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): AnalyticsService =
        AnalyticsServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun summaries(): SummaryService = summaries

    override fun users(): UserService = users

    override fun apps(): AppService = apps

    override fun connectors(): ConnectorService = connectors

    override fun plugins(): PluginService = plugins

    override fun skills(): SkillService = skills

    override fun artifacts(): ArtifactService = artifacts

    override fun usageReport(): UsageReportService = usageReport

    override fun costReport(): CostReportService = costReport

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        AnalyticsService.WithRawResponse {

        private val summaries: SummaryService.WithRawResponse by lazy {
            SummaryServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val users: UserService.WithRawResponse by lazy {
            UserServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val apps: AppService.WithRawResponse by lazy {
            AppServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val connectors: ConnectorService.WithRawResponse by lazy {
            ConnectorServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val plugins: PluginService.WithRawResponse by lazy {
            PluginServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val skills: SkillService.WithRawResponse by lazy {
            SkillServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val artifacts: ArtifactService.WithRawResponse by lazy {
            ArtifactServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val usageReport: UsageReportService.WithRawResponse by lazy {
            UsageReportServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val costReport: CostReportService.WithRawResponse by lazy {
            CostReportServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): AnalyticsService.WithRawResponse =
            AnalyticsServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun summaries(): SummaryService.WithRawResponse = summaries

        override fun users(): UserService.WithRawResponse = users

        override fun apps(): AppService.WithRawResponse = apps

        override fun connectors(): ConnectorService.WithRawResponse = connectors

        override fun plugins(): PluginService.WithRawResponse = plugins

        override fun skills(): SkillService.WithRawResponse = skills

        override fun artifacts(): ArtifactService.WithRawResponse = artifacts

        override fun usageReport(): UsageReportService.WithRawResponse = usageReport

        override fun costReport(): CostReportService.WithRawResponse = costReport
    }
}

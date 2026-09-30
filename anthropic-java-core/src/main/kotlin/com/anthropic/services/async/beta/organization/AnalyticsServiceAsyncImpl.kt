package com.anthropic.services.async.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.organization.analytics.AppServiceAsync
import com.anthropic.services.async.beta.organization.analytics.AppServiceAsyncImpl
import com.anthropic.services.async.beta.organization.analytics.ArtifactServiceAsync
import com.anthropic.services.async.beta.organization.analytics.ArtifactServiceAsyncImpl
import com.anthropic.services.async.beta.organization.analytics.ConnectorServiceAsync
import com.anthropic.services.async.beta.organization.analytics.ConnectorServiceAsyncImpl
import com.anthropic.services.async.beta.organization.analytics.CostReportServiceAsync
import com.anthropic.services.async.beta.organization.analytics.CostReportServiceAsyncImpl
import com.anthropic.services.async.beta.organization.analytics.PluginServiceAsync
import com.anthropic.services.async.beta.organization.analytics.PluginServiceAsyncImpl
import com.anthropic.services.async.beta.organization.analytics.SkillServiceAsync
import com.anthropic.services.async.beta.organization.analytics.SkillServiceAsyncImpl
import com.anthropic.services.async.beta.organization.analytics.SummaryServiceAsync
import com.anthropic.services.async.beta.organization.analytics.SummaryServiceAsyncImpl
import com.anthropic.services.async.beta.organization.analytics.UsageReportServiceAsync
import com.anthropic.services.async.beta.organization.analytics.UsageReportServiceAsyncImpl
import com.anthropic.services.async.beta.organization.analytics.UserCostReportServiceAsync
import com.anthropic.services.async.beta.organization.analytics.UserCostReportServiceAsyncImpl
import com.anthropic.services.async.beta.organization.analytics.UserServiceAsync
import com.anthropic.services.async.beta.organization.analytics.UserServiceAsyncImpl
import com.anthropic.services.async.beta.organization.analytics.UserUsageReportServiceAsync
import com.anthropic.services.async.beta.organization.analytics.UserUsageReportServiceAsyncImpl
import java.util.function.Consumer

class AnalyticsServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    AnalyticsServiceAsync {

    private val withRawResponse: AnalyticsServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val summaries: SummaryServiceAsync by lazy { SummaryServiceAsyncImpl(clientOptions) }

    private val users: UserServiceAsync by lazy { UserServiceAsyncImpl(clientOptions) }

    private val apps: AppServiceAsync by lazy { AppServiceAsyncImpl(clientOptions) }

    private val connectors: ConnectorServiceAsync by lazy {
        ConnectorServiceAsyncImpl(clientOptions)
    }

    private val plugins: PluginServiceAsync by lazy { PluginServiceAsyncImpl(clientOptions) }

    private val skills: SkillServiceAsync by lazy { SkillServiceAsyncImpl(clientOptions) }

    private val artifacts: ArtifactServiceAsync by lazy { ArtifactServiceAsyncImpl(clientOptions) }

    private val usageReport: UsageReportServiceAsync by lazy {
        UsageReportServiceAsyncImpl(clientOptions)
    }

    private val userUsageReport: UserUsageReportServiceAsync by lazy {
        UserUsageReportServiceAsyncImpl(clientOptions)
    }

    private val costReport: CostReportServiceAsync by lazy {
        CostReportServiceAsyncImpl(clientOptions)
    }

    private val userCostReport: UserCostReportServiceAsync by lazy {
        UserCostReportServiceAsyncImpl(clientOptions)
    }

    override fun withRawResponse(): AnalyticsServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): AnalyticsServiceAsync =
        AnalyticsServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun summaries(): SummaryServiceAsync = summaries

    override fun users(): UserServiceAsync = users

    override fun apps(): AppServiceAsync = apps

    override fun connectors(): ConnectorServiceAsync = connectors

    override fun plugins(): PluginServiceAsync = plugins

    override fun skills(): SkillServiceAsync = skills

    override fun artifacts(): ArtifactServiceAsync = artifacts

    override fun usageReport(): UsageReportServiceAsync = usageReport

    override fun userUsageReport(): UserUsageReportServiceAsync = userUsageReport

    override fun costReport(): CostReportServiceAsync = costReport

    override fun userCostReport(): UserCostReportServiceAsync = userCostReport

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        AnalyticsServiceAsync.WithRawResponse {

        private val summaries: SummaryServiceAsync.WithRawResponse by lazy {
            SummaryServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val users: UserServiceAsync.WithRawResponse by lazy {
            UserServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val apps: AppServiceAsync.WithRawResponse by lazy {
            AppServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val connectors: ConnectorServiceAsync.WithRawResponse by lazy {
            ConnectorServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val plugins: PluginServiceAsync.WithRawResponse by lazy {
            PluginServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val skills: SkillServiceAsync.WithRawResponse by lazy {
            SkillServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val artifacts: ArtifactServiceAsync.WithRawResponse by lazy {
            ArtifactServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val usageReport: UsageReportServiceAsync.WithRawResponse by lazy {
            UsageReportServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val userUsageReport: UserUsageReportServiceAsync.WithRawResponse by lazy {
            UserUsageReportServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val costReport: CostReportServiceAsync.WithRawResponse by lazy {
            CostReportServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val userCostReport: UserCostReportServiceAsync.WithRawResponse by lazy {
            UserCostReportServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): AnalyticsServiceAsync.WithRawResponse =
            AnalyticsServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun summaries(): SummaryServiceAsync.WithRawResponse = summaries

        override fun users(): UserServiceAsync.WithRawResponse = users

        override fun apps(): AppServiceAsync.WithRawResponse = apps

        override fun connectors(): ConnectorServiceAsync.WithRawResponse = connectors

        override fun plugins(): PluginServiceAsync.WithRawResponse = plugins

        override fun skills(): SkillServiceAsync.WithRawResponse = skills

        override fun artifacts(): ArtifactServiceAsync.WithRawResponse = artifacts

        override fun usageReport(): UsageReportServiceAsync.WithRawResponse = usageReport

        override fun userUsageReport(): UserUsageReportServiceAsync.WithRawResponse =
            userUsageReport

        override fun costReport(): CostReportServiceAsync.WithRawResponse = costReport

        override fun userCostReport(): UserCostReportServiceAsync.WithRawResponse = userCostReport
    }
}

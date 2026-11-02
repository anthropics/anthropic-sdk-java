package com.anthropic.services.async.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.organization.analytics.AppServiceAsync
import com.anthropic.services.async.organization.analytics.AppServiceAsyncImpl
import com.anthropic.services.async.organization.analytics.ArtifactServiceAsync
import com.anthropic.services.async.organization.analytics.ArtifactServiceAsyncImpl
import com.anthropic.services.async.organization.analytics.ConnectorServiceAsync
import com.anthropic.services.async.organization.analytics.ConnectorServiceAsyncImpl
import com.anthropic.services.async.organization.analytics.PluginServiceAsync
import com.anthropic.services.async.organization.analytics.PluginServiceAsyncImpl
import com.anthropic.services.async.organization.analytics.SkillServiceAsync
import com.anthropic.services.async.organization.analytics.SkillServiceAsyncImpl
import com.anthropic.services.async.organization.analytics.SummaryServiceAsync
import com.anthropic.services.async.organization.analytics.SummaryServiceAsyncImpl
import com.anthropic.services.async.organization.analytics.UserServiceAsync
import com.anthropic.services.async.organization.analytics.UserServiceAsyncImpl
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
    }
}

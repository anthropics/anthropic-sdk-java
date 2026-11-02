package com.anthropic.services.blocking.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.organization.analytics.AppService
import com.anthropic.services.blocking.organization.analytics.AppServiceImpl
import com.anthropic.services.blocking.organization.analytics.ArtifactService
import com.anthropic.services.blocking.organization.analytics.ArtifactServiceImpl
import com.anthropic.services.blocking.organization.analytics.ConnectorService
import com.anthropic.services.blocking.organization.analytics.ConnectorServiceImpl
import com.anthropic.services.blocking.organization.analytics.PluginService
import com.anthropic.services.blocking.organization.analytics.PluginServiceImpl
import com.anthropic.services.blocking.organization.analytics.SkillService
import com.anthropic.services.blocking.organization.analytics.SkillServiceImpl
import com.anthropic.services.blocking.organization.analytics.SummaryService
import com.anthropic.services.blocking.organization.analytics.SummaryServiceImpl
import com.anthropic.services.blocking.organization.analytics.UserService
import com.anthropic.services.blocking.organization.analytics.UserServiceImpl
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
    }
}

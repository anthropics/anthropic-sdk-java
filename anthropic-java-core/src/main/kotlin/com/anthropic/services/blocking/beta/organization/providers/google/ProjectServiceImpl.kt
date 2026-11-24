package com.anthropic.services.blocking.beta.organization.providers.google

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.organization.providers.google.projects.DataControlService
import com.anthropic.services.blocking.beta.organization.providers.google.projects.DataControlServiceImpl
import java.util.function.Consumer

class ProjectServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    ProjectService {

    private val withRawResponse: ProjectService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val dataControls: DataControlService by lazy { DataControlServiceImpl(clientOptions) }

    override fun withRawResponse(): ProjectService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ProjectService =
        ProjectServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun dataControls(): DataControlService = dataControls

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ProjectService.WithRawResponse {

        private val dataControls: DataControlService.WithRawResponse by lazy {
            DataControlServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ProjectService.WithRawResponse =
            ProjectServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun dataControls(): DataControlService.WithRawResponse = dataControls
    }
}

package com.anthropic.services.blocking.beta.organization.providers

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.organization.providers.google.ProjectService
import com.anthropic.services.blocking.beta.organization.providers.google.ProjectServiceImpl
import java.util.function.Consumer

class GoogleServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    GoogleService {

    private val withRawResponse: GoogleService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val projects: ProjectService by lazy { ProjectServiceImpl(clientOptions) }

    override fun withRawResponse(): GoogleService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): GoogleService =
        GoogleServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun projects(): ProjectService = projects

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        GoogleService.WithRawResponse {

        private val projects: ProjectService.WithRawResponse by lazy {
            ProjectServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): GoogleService.WithRawResponse =
            GoogleServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun projects(): ProjectService.WithRawResponse = projects
    }
}

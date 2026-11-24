package com.anthropic.services.async.beta.organization.providers

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.organization.providers.google.ProjectServiceAsync
import com.anthropic.services.async.beta.organization.providers.google.ProjectServiceAsyncImpl
import java.util.function.Consumer

class GoogleServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    GoogleServiceAsync {

    private val withRawResponse: GoogleServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val projects: ProjectServiceAsync by lazy { ProjectServiceAsyncImpl(clientOptions) }

    override fun withRawResponse(): GoogleServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): GoogleServiceAsync =
        GoogleServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun projects(): ProjectServiceAsync = projects

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        GoogleServiceAsync.WithRawResponse {

        private val projects: ProjectServiceAsync.WithRawResponse by lazy {
            ProjectServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): GoogleServiceAsync.WithRawResponse =
            GoogleServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun projects(): ProjectServiceAsync.WithRawResponse = projects
    }
}

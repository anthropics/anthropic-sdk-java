package com.anthropic.services.async.beta.organization.providers.google

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.organization.providers.google.projects.DataControlServiceAsync
import com.anthropic.services.async.beta.organization.providers.google.projects.DataControlServiceAsyncImpl
import java.util.function.Consumer

class ProjectServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    ProjectServiceAsync {

    private val withRawResponse: ProjectServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val dataControls: DataControlServiceAsync by lazy {
        DataControlServiceAsyncImpl(clientOptions)
    }

    override fun withRawResponse(): ProjectServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ProjectServiceAsync =
        ProjectServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun dataControls(): DataControlServiceAsync = dataControls

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ProjectServiceAsync.WithRawResponse {

        private val dataControls: DataControlServiceAsync.WithRawResponse by lazy {
            DataControlServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ProjectServiceAsync.WithRawResponse =
            ProjectServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun dataControls(): DataControlServiceAsync.WithRawResponse = dataControls
    }
}

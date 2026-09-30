package com.anthropic.services.async.beta.organization.analytics.apps

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.organization.analytics.apps.chat.ProjectServiceAsync
import com.anthropic.services.async.beta.organization.analytics.apps.chat.ProjectServiceAsyncImpl
import java.util.function.Consumer

class ChatServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    ChatServiceAsync {

    private val withRawResponse: ChatServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val projects: ProjectServiceAsync by lazy { ProjectServiceAsyncImpl(clientOptions) }

    override fun withRawResponse(): ChatServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ChatServiceAsync =
        ChatServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun projects(): ProjectServiceAsync = projects

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ChatServiceAsync.WithRawResponse {

        private val projects: ProjectServiceAsync.WithRawResponse by lazy {
            ProjectServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ChatServiceAsync.WithRawResponse =
            ChatServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun projects(): ProjectServiceAsync.WithRawResponse = projects
    }
}

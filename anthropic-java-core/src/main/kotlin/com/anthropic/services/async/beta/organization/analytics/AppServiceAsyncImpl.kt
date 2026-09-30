package com.anthropic.services.async.beta.organization.analytics

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.organization.analytics.apps.ChatServiceAsync
import com.anthropic.services.async.beta.organization.analytics.apps.ChatServiceAsyncImpl
import java.util.function.Consumer

class AppServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    AppServiceAsync {

    private val withRawResponse: AppServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val chat: ChatServiceAsync by lazy { ChatServiceAsyncImpl(clientOptions) }

    override fun withRawResponse(): AppServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): AppServiceAsync =
        AppServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun chat(): ChatServiceAsync = chat

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        AppServiceAsync.WithRawResponse {

        private val chat: ChatServiceAsync.WithRawResponse by lazy {
            ChatServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): AppServiceAsync.WithRawResponse =
            AppServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun chat(): ChatServiceAsync.WithRawResponse = chat
    }
}

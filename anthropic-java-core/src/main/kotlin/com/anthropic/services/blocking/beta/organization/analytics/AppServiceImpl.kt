package com.anthropic.services.blocking.beta.organization.analytics

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.organization.analytics.apps.ChatService
import com.anthropic.services.blocking.beta.organization.analytics.apps.ChatServiceImpl
import java.util.function.Consumer

class AppServiceImpl internal constructor(private val clientOptions: ClientOptions) : AppService {

    private val withRawResponse: AppService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val chat: ChatService by lazy { ChatServiceImpl(clientOptions) }

    override fun withRawResponse(): AppService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): AppService =
        AppServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun chat(): ChatService = chat

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        AppService.WithRawResponse {

        private val chat: ChatService.WithRawResponse by lazy {
            ChatServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): AppService.WithRawResponse =
            AppServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun chat(): ChatService.WithRawResponse = chat
    }
}

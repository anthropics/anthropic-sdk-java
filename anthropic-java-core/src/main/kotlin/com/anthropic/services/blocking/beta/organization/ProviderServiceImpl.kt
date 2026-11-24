package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.organization.providers.GoogleService
import com.anthropic.services.blocking.beta.organization.providers.GoogleServiceImpl
import java.util.function.Consumer

class ProviderServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    ProviderService {

    private val withRawResponse: ProviderService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val google: GoogleService by lazy { GoogleServiceImpl(clientOptions) }

    override fun withRawResponse(): ProviderService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ProviderService =
        ProviderServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun google(): GoogleService = google

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ProviderService.WithRawResponse {

        private val google: GoogleService.WithRawResponse by lazy {
            GoogleServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ProviderService.WithRawResponse =
            ProviderServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun google(): GoogleService.WithRawResponse = google
    }
}

package com.anthropic.services.async.beta.userprofiles

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.userprofiles.providers.GoogleServiceAsync
import com.anthropic.services.async.beta.userprofiles.providers.GoogleServiceAsyncImpl
import java.util.function.Consumer

class ProviderServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    ProviderServiceAsync {

    private val withRawResponse: ProviderServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val google: GoogleServiceAsync by lazy { GoogleServiceAsyncImpl(clientOptions) }

    override fun withRawResponse(): ProviderServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ProviderServiceAsync =
        ProviderServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun google(): GoogleServiceAsync = google

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ProviderServiceAsync.WithRawResponse {

        private val google: GoogleServiceAsync.WithRawResponse by lazy {
            GoogleServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ProviderServiceAsync.WithRawResponse =
            ProviderServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun google(): GoogleServiceAsync.WithRawResponse = google
    }
}

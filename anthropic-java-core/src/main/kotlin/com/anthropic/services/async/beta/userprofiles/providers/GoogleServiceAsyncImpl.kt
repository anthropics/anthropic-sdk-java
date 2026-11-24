package com.anthropic.services.async.beta.userprofiles.providers

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.userprofiles.providers.google.DataControlServiceAsync
import com.anthropic.services.async.beta.userprofiles.providers.google.DataControlServiceAsyncImpl
import java.util.function.Consumer

class GoogleServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    GoogleServiceAsync {

    private val withRawResponse: GoogleServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val dataControls: DataControlServiceAsync by lazy {
        DataControlServiceAsyncImpl(clientOptions)
    }

    override fun withRawResponse(): GoogleServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): GoogleServiceAsync =
        GoogleServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun dataControls(): DataControlServiceAsync = dataControls

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        GoogleServiceAsync.WithRawResponse {

        private val dataControls: DataControlServiceAsync.WithRawResponse by lazy {
            DataControlServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): GoogleServiceAsync.WithRawResponse =
            GoogleServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun dataControls(): DataControlServiceAsync.WithRawResponse = dataControls
    }
}

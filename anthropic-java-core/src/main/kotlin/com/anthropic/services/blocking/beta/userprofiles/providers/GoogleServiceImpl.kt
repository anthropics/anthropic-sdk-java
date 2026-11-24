package com.anthropic.services.blocking.beta.userprofiles.providers

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.userprofiles.providers.google.DataControlService
import com.anthropic.services.blocking.beta.userprofiles.providers.google.DataControlServiceImpl
import java.util.function.Consumer

class GoogleServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    GoogleService {

    private val withRawResponse: GoogleService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val dataControls: DataControlService by lazy { DataControlServiceImpl(clientOptions) }

    override fun withRawResponse(): GoogleService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): GoogleService =
        GoogleServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun dataControls(): DataControlService = dataControls

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        GoogleService.WithRawResponse {

        private val dataControls: DataControlService.WithRawResponse by lazy {
            DataControlServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): GoogleService.WithRawResponse =
            GoogleServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun dataControls(): DataControlService.WithRawResponse = dataControls
    }
}

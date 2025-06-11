// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.FileService
import com.anthropic.services.blocking.beta.FileServiceImpl
import com.anthropic.services.blocking.beta.MessageService
import com.anthropic.services.blocking.beta.MessageServiceImpl
import com.anthropic.services.blocking.beta.ModelService
import com.anthropic.services.blocking.beta.ModelServiceImpl

class BetaServiceImpl internal constructor(private val clientOptions: ClientOptions) : BetaService {

    private val withRawResponse: BetaService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val files: FileService by lazy { FileServiceImpl(clientOptions) }

    private val messages: MessageService by lazy { MessageServiceImpl(clientOptions) }

    private val models: ModelService by lazy { ModelServiceImpl(clientOptions) }

    override fun withRawResponse(): BetaService.WithRawResponse = withRawResponse

    override fun files(): FileService = files

    override fun messages(): MessageService = messages

    override fun models(): ModelService = models

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        BetaService.WithRawResponse {

        private val files: FileService.WithRawResponse by lazy {
            FileServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val messages: MessageService.WithRawResponse by lazy {
            MessageServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val models: ModelService.WithRawResponse by lazy {
            ModelServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun files(): FileService.WithRawResponse = files

        override fun messages(): MessageService.WithRawResponse = messages

        override fun models(): ModelService.WithRawResponse = models
    }
}

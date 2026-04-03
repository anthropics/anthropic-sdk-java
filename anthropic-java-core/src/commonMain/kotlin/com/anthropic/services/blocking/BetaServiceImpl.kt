// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.FileService
import com.anthropic.services.blocking.beta.FileServiceImpl
import com.anthropic.services.blocking.beta.MessageService
import com.anthropic.services.blocking.beta.MessageServiceImpl
import com.anthropic.services.blocking.beta.ModelService
import com.anthropic.services.blocking.beta.ModelServiceImpl
import com.anthropic.services.blocking.beta.SkillService
import com.anthropic.services.blocking.beta.SkillServiceImpl
import java.util.function.Consumer

class BetaServiceImpl internal constructor(private val clientOptions: ClientOptions) : BetaService {

    private val withRawResponse: BetaService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val models: ModelService by lazy { ModelServiceImpl(clientOptions) }

    private val messages: MessageService by lazy { MessageServiceImpl(clientOptions) }

    private val files: FileService by lazy { FileServiceImpl(clientOptions) }

    private val skills: SkillService by lazy { SkillServiceImpl(clientOptions) }

    override fun withRawResponse(): BetaService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): BetaService =
        BetaServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun models(): ModelService = models

    override fun messages(): MessageService = messages

    override fun files(): FileService = files

    override fun skills(): SkillService = skills

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        BetaService.WithRawResponse {

        private val models: ModelService.WithRawResponse by lazy {
            ModelServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val messages: MessageService.WithRawResponse by lazy {
            MessageServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val files: FileService.WithRawResponse by lazy {
            FileServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val skills: SkillService.WithRawResponse by lazy {
            SkillServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): BetaService.WithRawResponse =
            BetaServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun models(): ModelService.WithRawResponse = models

        override fun messages(): MessageService.WithRawResponse = messages

        override fun files(): FileService.WithRawResponse = files

        override fun skills(): SkillService.WithRawResponse = skills
    }
}

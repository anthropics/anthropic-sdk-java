// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.AgentService
import com.anthropic.services.blocking.beta.AgentServiceImpl
import com.anthropic.services.blocking.beta.EnvironmentService
import com.anthropic.services.blocking.beta.EnvironmentServiceImpl
import com.anthropic.services.blocking.beta.FileService
import com.anthropic.services.blocking.beta.FileServiceImpl
import com.anthropic.services.blocking.beta.MessageService
import com.anthropic.services.blocking.beta.MessageServiceImpl
import com.anthropic.services.blocking.beta.ModelService
import com.anthropic.services.blocking.beta.ModelServiceImpl
import com.anthropic.services.blocking.beta.SessionService
import com.anthropic.services.blocking.beta.SessionServiceImpl
import com.anthropic.services.blocking.beta.SkillService
import com.anthropic.services.blocking.beta.SkillServiceImpl
import com.anthropic.services.blocking.beta.VaultService
import com.anthropic.services.blocking.beta.VaultServiceImpl
import java.util.function.Consumer

class BetaServiceImpl internal constructor(private val clientOptions: ClientOptions) : BetaService {

    private val withRawResponse: BetaService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val models: ModelService by lazy { ModelServiceImpl(clientOptions) }

    private val messages: MessageService by lazy { MessageServiceImpl(clientOptions) }

    private val agents: AgentService by lazy { AgentServiceImpl(clientOptions) }

    private val environments: EnvironmentService by lazy { EnvironmentServiceImpl(clientOptions) }

    private val sessions: SessionService by lazy { SessionServiceImpl(clientOptions) }

    private val vaults: VaultService by lazy { VaultServiceImpl(clientOptions) }

    private val files: FileService by lazy { FileServiceImpl(clientOptions) }

    private val skills: SkillService by lazy { SkillServiceImpl(clientOptions) }

    override fun withRawResponse(): BetaService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): BetaService =
        BetaServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun models(): ModelService = models

    override fun messages(): MessageService = messages

    override fun agents(): AgentService = agents

    override fun environments(): EnvironmentService = environments

    override fun sessions(): SessionService = sessions

    override fun vaults(): VaultService = vaults

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

        private val agents: AgentService.WithRawResponse by lazy {
            AgentServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val environments: EnvironmentService.WithRawResponse by lazy {
            EnvironmentServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val sessions: SessionService.WithRawResponse by lazy {
            SessionServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val vaults: VaultService.WithRawResponse by lazy {
            VaultServiceImpl.WithRawResponseImpl(clientOptions)
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

        override fun agents(): AgentService.WithRawResponse = agents

        override fun environments(): EnvironmentService.WithRawResponse = environments

        override fun sessions(): SessionService.WithRawResponse = sessions

        override fun vaults(): VaultService.WithRawResponse = vaults

        override fun files(): FileService.WithRawResponse = files

        override fun skills(): SkillService.WithRawResponse = skills
    }
}

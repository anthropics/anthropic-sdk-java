// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.AgentServiceAsync
import com.anthropic.services.async.beta.AgentServiceAsyncImpl
import com.anthropic.services.async.beta.EnvironmentServiceAsync
import com.anthropic.services.async.beta.EnvironmentServiceAsyncImpl
import com.anthropic.services.async.beta.FileServiceAsync
import com.anthropic.services.async.beta.FileServiceAsyncImpl
import com.anthropic.services.async.beta.MemoryStoreServiceAsync
import com.anthropic.services.async.beta.MemoryStoreServiceAsyncImpl
import com.anthropic.services.async.beta.MessageServiceAsync
import com.anthropic.services.async.beta.MessageServiceAsyncImpl
import com.anthropic.services.async.beta.ModelServiceAsync
import com.anthropic.services.async.beta.ModelServiceAsyncImpl
import com.anthropic.services.async.beta.SessionServiceAsync
import com.anthropic.services.async.beta.SessionServiceAsyncImpl
import com.anthropic.services.async.beta.SkillServiceAsync
import com.anthropic.services.async.beta.SkillServiceAsyncImpl
import com.anthropic.services.async.beta.UserProfileServiceAsync
import com.anthropic.services.async.beta.UserProfileServiceAsyncImpl
import com.anthropic.services.async.beta.VaultServiceAsync
import com.anthropic.services.async.beta.VaultServiceAsyncImpl
import java.util.function.Consumer

class BetaServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    BetaServiceAsync {

    private val withRawResponse: BetaServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val models: ModelServiceAsync by lazy { ModelServiceAsyncImpl(clientOptions) }

    private val messages: MessageServiceAsync by lazy { MessageServiceAsyncImpl(clientOptions) }

    private val agents: AgentServiceAsync by lazy { AgentServiceAsyncImpl(clientOptions) }

    private val environments: EnvironmentServiceAsync by lazy {
        EnvironmentServiceAsyncImpl(clientOptions)
    }

    private val sessions: SessionServiceAsync by lazy { SessionServiceAsyncImpl(clientOptions) }

    private val vaults: VaultServiceAsync by lazy { VaultServiceAsyncImpl(clientOptions) }

    private val memoryStores: MemoryStoreServiceAsync by lazy {
        MemoryStoreServiceAsyncImpl(clientOptions)
    }

    private val files: FileServiceAsync by lazy { FileServiceAsyncImpl(clientOptions) }

    private val skills: SkillServiceAsync by lazy { SkillServiceAsyncImpl(clientOptions) }

    private val userProfiles: UserProfileServiceAsync by lazy {
        UserProfileServiceAsyncImpl(clientOptions)
    }

    override fun withRawResponse(): BetaServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): BetaServiceAsync =
        BetaServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun models(): ModelServiceAsync = models

    override fun messages(): MessageServiceAsync = messages

    override fun agents(): AgentServiceAsync = agents

    override fun environments(): EnvironmentServiceAsync = environments

    override fun sessions(): SessionServiceAsync = sessions

    override fun vaults(): VaultServiceAsync = vaults

    override fun memoryStores(): MemoryStoreServiceAsync = memoryStores

    override fun files(): FileServiceAsync = files

    override fun skills(): SkillServiceAsync = skills

    override fun userProfiles(): UserProfileServiceAsync = userProfiles

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        BetaServiceAsync.WithRawResponse {

        private val models: ModelServiceAsync.WithRawResponse by lazy {
            ModelServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val messages: MessageServiceAsync.WithRawResponse by lazy {
            MessageServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val agents: AgentServiceAsync.WithRawResponse by lazy {
            AgentServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val environments: EnvironmentServiceAsync.WithRawResponse by lazy {
            EnvironmentServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val sessions: SessionServiceAsync.WithRawResponse by lazy {
            SessionServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val vaults: VaultServiceAsync.WithRawResponse by lazy {
            VaultServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val memoryStores: MemoryStoreServiceAsync.WithRawResponse by lazy {
            MemoryStoreServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val files: FileServiceAsync.WithRawResponse by lazy {
            FileServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val skills: SkillServiceAsync.WithRawResponse by lazy {
            SkillServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val userProfiles: UserProfileServiceAsync.WithRawResponse by lazy {
            UserProfileServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): BetaServiceAsync.WithRawResponse =
            BetaServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun models(): ModelServiceAsync.WithRawResponse = models

        override fun messages(): MessageServiceAsync.WithRawResponse = messages

        override fun agents(): AgentServiceAsync.WithRawResponse = agents

        override fun environments(): EnvironmentServiceAsync.WithRawResponse = environments

        override fun sessions(): SessionServiceAsync.WithRawResponse = sessions

        override fun vaults(): VaultServiceAsync.WithRawResponse = vaults

        override fun memoryStores(): MemoryStoreServiceAsync.WithRawResponse = memoryStores

        override fun files(): FileServiceAsync.WithRawResponse = files

        override fun skills(): SkillServiceAsync.WithRawResponse = skills

        override fun userProfiles(): UserProfileServiceAsync.WithRawResponse = userProfiles
    }
}

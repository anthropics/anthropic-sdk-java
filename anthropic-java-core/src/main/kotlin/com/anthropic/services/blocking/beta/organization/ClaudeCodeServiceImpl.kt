package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.organization.claudecode.ManagedSettingService
import com.anthropic.services.blocking.beta.organization.claudecode.ManagedSettingServiceImpl
import java.util.function.Consumer

class ClaudeCodeServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    ClaudeCodeService {

    private val withRawResponse: ClaudeCodeService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val managedSettings: ManagedSettingService by lazy {
        ManagedSettingServiceImpl(clientOptions)
    }

    override fun withRawResponse(): ClaudeCodeService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ClaudeCodeService =
        ClaudeCodeServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun managedSettings(): ManagedSettingService = managedSettings

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ClaudeCodeService.WithRawResponse {

        private val managedSettings: ManagedSettingService.WithRawResponse by lazy {
            ManagedSettingServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ClaudeCodeService.WithRawResponse =
            ClaudeCodeServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun managedSettings(): ManagedSettingService.WithRawResponse = managedSettings
    }
}

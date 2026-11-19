package com.anthropic.services.async.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.organization.claudecode.ManagedSettingServiceAsync
import com.anthropic.services.async.beta.organization.claudecode.ManagedSettingServiceAsyncImpl
import java.util.function.Consumer

class ClaudeCodeServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    ClaudeCodeServiceAsync {

    private val withRawResponse: ClaudeCodeServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val managedSettings: ManagedSettingServiceAsync by lazy {
        ManagedSettingServiceAsyncImpl(clientOptions)
    }

    override fun withRawResponse(): ClaudeCodeServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ClaudeCodeServiceAsync =
        ClaudeCodeServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun managedSettings(): ManagedSettingServiceAsync = managedSettings

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ClaudeCodeServiceAsync.WithRawResponse {

        private val managedSettings: ManagedSettingServiceAsync.WithRawResponse by lazy {
            ManagedSettingServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ClaudeCodeServiceAsync.WithRawResponse =
            ClaudeCodeServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun managedSettings(): ManagedSettingServiceAsync.WithRawResponse = managedSettings
    }
}

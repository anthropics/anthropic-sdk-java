package com.anthropic.services.async.beta.organization.claudecode

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.organization.claudecode.managedsettings.ConfigurationServiceAsync
import com.anthropic.services.async.beta.organization.claudecode.managedsettings.ConfigurationServiceAsyncImpl
import com.anthropic.services.async.beta.organization.claudecode.managedsettings.RbacGroupDefaultServiceAsync
import com.anthropic.services.async.beta.organization.claudecode.managedsettings.RbacGroupDefaultServiceAsyncImpl
import com.anthropic.services.async.beta.organization.claudecode.managedsettings.RbacGroupServiceAsync
import com.anthropic.services.async.beta.organization.claudecode.managedsettings.RbacGroupServiceAsyncImpl
import java.util.function.Consumer

class ManagedSettingServiceAsyncImpl
internal constructor(private val clientOptions: ClientOptions) : ManagedSettingServiceAsync {

    private val withRawResponse: ManagedSettingServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val configuration: ConfigurationServiceAsync by lazy {
        ConfigurationServiceAsyncImpl(clientOptions)
    }

    private val rbacGroups: RbacGroupServiceAsync by lazy {
        RbacGroupServiceAsyncImpl(clientOptions)
    }

    private val rbacGroupDefault: RbacGroupDefaultServiceAsync by lazy {
        RbacGroupDefaultServiceAsyncImpl(clientOptions)
    }

    override fun withRawResponse(): ManagedSettingServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(
        modifier: Consumer<ClientOptions.Builder>
    ): ManagedSettingServiceAsync =
        ManagedSettingServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun configuration(): ConfigurationServiceAsync = configuration

    override fun rbacGroups(): RbacGroupServiceAsync = rbacGroups

    override fun rbacGroupDefault(): RbacGroupDefaultServiceAsync = rbacGroupDefault

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ManagedSettingServiceAsync.WithRawResponse {

        private val configuration: ConfigurationServiceAsync.WithRawResponse by lazy {
            ConfigurationServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val rbacGroups: RbacGroupServiceAsync.WithRawResponse by lazy {
            RbacGroupServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val rbacGroupDefault: RbacGroupDefaultServiceAsync.WithRawResponse by lazy {
            RbacGroupDefaultServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ManagedSettingServiceAsync.WithRawResponse =
            ManagedSettingServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun configuration(): ConfigurationServiceAsync.WithRawResponse = configuration

        override fun rbacGroups(): RbacGroupServiceAsync.WithRawResponse = rbacGroups

        override fun rbacGroupDefault(): RbacGroupDefaultServiceAsync.WithRawResponse =
            rbacGroupDefault
    }
}

package com.anthropic.services.blocking.beta.organization.claudecode

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.organization.claudecode.managedsettings.ConfigurationService
import com.anthropic.services.blocking.beta.organization.claudecode.managedsettings.ConfigurationServiceImpl
import com.anthropic.services.blocking.beta.organization.claudecode.managedsettings.RbacGroupDefaultService
import com.anthropic.services.blocking.beta.organization.claudecode.managedsettings.RbacGroupDefaultServiceImpl
import com.anthropic.services.blocking.beta.organization.claudecode.managedsettings.RbacGroupService
import com.anthropic.services.blocking.beta.organization.claudecode.managedsettings.RbacGroupServiceImpl
import java.util.function.Consumer

class ManagedSettingServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    ManagedSettingService {

    private val withRawResponse: ManagedSettingService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val configuration: ConfigurationService by lazy {
        ConfigurationServiceImpl(clientOptions)
    }

    private val rbacGroups: RbacGroupService by lazy { RbacGroupServiceImpl(clientOptions) }

    private val rbacGroupDefault: RbacGroupDefaultService by lazy {
        RbacGroupDefaultServiceImpl(clientOptions)
    }

    override fun withRawResponse(): ManagedSettingService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ManagedSettingService =
        ManagedSettingServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun configuration(): ConfigurationService = configuration

    override fun rbacGroups(): RbacGroupService = rbacGroups

    override fun rbacGroupDefault(): RbacGroupDefaultService = rbacGroupDefault

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ManagedSettingService.WithRawResponse {

        private val configuration: ConfigurationService.WithRawResponse by lazy {
            ConfigurationServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val rbacGroups: RbacGroupService.WithRawResponse by lazy {
            RbacGroupServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val rbacGroupDefault: RbacGroupDefaultService.WithRawResponse by lazy {
            RbacGroupDefaultServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ManagedSettingService.WithRawResponse =
            ManagedSettingServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun configuration(): ConfigurationService.WithRawResponse = configuration

        override fun rbacGroups(): RbacGroupService.WithRawResponse = rbacGroups

        override fun rbacGroupDefault(): RbacGroupDefaultService.WithRawResponse = rbacGroupDefault
    }
}

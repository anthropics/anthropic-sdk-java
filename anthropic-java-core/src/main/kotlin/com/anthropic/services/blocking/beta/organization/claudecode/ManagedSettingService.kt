package com.anthropic.services.blocking.beta.organization.claudecode

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.organization.claudecode.managedsettings.ConfigurationService
import com.anthropic.services.blocking.beta.organization.claudecode.managedsettings.RbacGroupDefaultService
import com.anthropic.services.blocking.beta.organization.claudecode.managedsettings.RbacGroupService
import java.util.function.Consumer

interface ManagedSettingService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ManagedSettingService

    fun configuration(): ConfigurationService

    fun rbacGroups(): RbacGroupService

    fun rbacGroupDefault(): RbacGroupDefaultService

    /**
     * A view of [ManagedSettingService] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ManagedSettingService.WithRawResponse

        fun configuration(): ConfigurationService.WithRawResponse

        fun rbacGroups(): RbacGroupService.WithRawResponse

        fun rbacGroupDefault(): RbacGroupDefaultService.WithRawResponse
    }
}

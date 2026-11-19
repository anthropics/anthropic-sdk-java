package com.anthropic.services.async.beta.organization.claudecode

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.organization.claudecode.managedsettings.ConfigurationServiceAsync
import com.anthropic.services.async.beta.organization.claudecode.managedsettings.RbacGroupDefaultServiceAsync
import com.anthropic.services.async.beta.organization.claudecode.managedsettings.RbacGroupServiceAsync
import java.util.function.Consumer

interface ManagedSettingServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ManagedSettingServiceAsync

    fun configuration(): ConfigurationServiceAsync

    fun rbacGroups(): RbacGroupServiceAsync

    fun rbacGroupDefault(): RbacGroupDefaultServiceAsync

    /**
     * A view of [ManagedSettingServiceAsync] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ManagedSettingServiceAsync.WithRawResponse

        fun configuration(): ConfigurationServiceAsync.WithRawResponse

        fun rbacGroups(): RbacGroupServiceAsync.WithRawResponse

        fun rbacGroupDefault(): RbacGroupDefaultServiceAsync.WithRawResponse
    }
}

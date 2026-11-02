package com.anthropic.services.async.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.organization.spendlimits.EffectiveServiceAsync
import com.anthropic.services.async.organization.spendlimits.IncreaseRequestServiceAsync
import java.util.function.Consumer

interface SpendLimitServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): SpendLimitServiceAsync

    fun effective(): EffectiveServiceAsync

    fun increaseRequests(): IncreaseRequestServiceAsync

    /**
     * A view of [SpendLimitServiceAsync] that provides access to raw HTTP responses for each
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
        ): SpendLimitServiceAsync.WithRawResponse

        fun effective(): EffectiveServiceAsync.WithRawResponse

        fun increaseRequests(): IncreaseRequestServiceAsync.WithRawResponse
    }
}

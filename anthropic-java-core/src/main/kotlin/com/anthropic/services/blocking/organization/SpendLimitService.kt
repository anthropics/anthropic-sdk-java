package com.anthropic.services.blocking.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.organization.spendlimits.EffectiveService
import com.anthropic.services.blocking.organization.spendlimits.IncreaseRequestService
import java.util.function.Consumer

interface SpendLimitService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): SpendLimitService

    fun effective(): EffectiveService

    fun increaseRequests(): IncreaseRequestService

    /** A view of [SpendLimitService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SpendLimitService.WithRawResponse

        fun effective(): EffectiveService.WithRawResponse

        fun increaseRequests(): IncreaseRequestService.WithRawResponse
    }
}

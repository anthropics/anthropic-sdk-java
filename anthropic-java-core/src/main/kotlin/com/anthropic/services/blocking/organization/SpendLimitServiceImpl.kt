package com.anthropic.services.blocking.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.organization.spendlimits.EffectiveService
import com.anthropic.services.blocking.organization.spendlimits.EffectiveServiceImpl
import com.anthropic.services.blocking.organization.spendlimits.IncreaseRequestService
import com.anthropic.services.blocking.organization.spendlimits.IncreaseRequestServiceImpl
import java.util.function.Consumer

class SpendLimitServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    SpendLimitService {

    private val withRawResponse: SpendLimitService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val effective: EffectiveService by lazy { EffectiveServiceImpl(clientOptions) }

    private val increaseRequests: IncreaseRequestService by lazy {
        IncreaseRequestServiceImpl(clientOptions)
    }

    override fun withRawResponse(): SpendLimitService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): SpendLimitService =
        SpendLimitServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun effective(): EffectiveService = effective

    override fun increaseRequests(): IncreaseRequestService = increaseRequests

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        SpendLimitService.WithRawResponse {

        private val effective: EffectiveService.WithRawResponse by lazy {
            EffectiveServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val increaseRequests: IncreaseRequestService.WithRawResponse by lazy {
            IncreaseRequestServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SpendLimitService.WithRawResponse =
            SpendLimitServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun effective(): EffectiveService.WithRawResponse = effective

        override fun increaseRequests(): IncreaseRequestService.WithRawResponse = increaseRequests
    }
}

package com.anthropic.services.async.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.organization.spendlimits.EffectiveServiceAsync
import com.anthropic.services.async.organization.spendlimits.EffectiveServiceAsyncImpl
import com.anthropic.services.async.organization.spendlimits.IncreaseRequestServiceAsync
import com.anthropic.services.async.organization.spendlimits.IncreaseRequestServiceAsyncImpl
import java.util.function.Consumer

class SpendLimitServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    SpendLimitServiceAsync {

    private val withRawResponse: SpendLimitServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val effective: EffectiveServiceAsync by lazy {
        EffectiveServiceAsyncImpl(clientOptions)
    }

    private val increaseRequests: IncreaseRequestServiceAsync by lazy {
        IncreaseRequestServiceAsyncImpl(clientOptions)
    }

    override fun withRawResponse(): SpendLimitServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): SpendLimitServiceAsync =
        SpendLimitServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun effective(): EffectiveServiceAsync = effective

    override fun increaseRequests(): IncreaseRequestServiceAsync = increaseRequests

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        SpendLimitServiceAsync.WithRawResponse {

        private val effective: EffectiveServiceAsync.WithRawResponse by lazy {
            EffectiveServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val increaseRequests: IncreaseRequestServiceAsync.WithRawResponse by lazy {
            IncreaseRequestServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SpendLimitServiceAsync.WithRawResponse =
            SpendLimitServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun effective(): EffectiveServiceAsync.WithRawResponse = effective

        override fun increaseRequests(): IncreaseRequestServiceAsync.WithRawResponse =
            increaseRequests
    }
}

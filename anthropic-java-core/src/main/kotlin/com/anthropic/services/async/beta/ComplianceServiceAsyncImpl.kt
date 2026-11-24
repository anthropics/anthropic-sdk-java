package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.compliance.ReviewServiceAsync
import com.anthropic.services.async.beta.compliance.ReviewServiceAsyncImpl
import java.util.function.Consumer

class ComplianceServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    ComplianceServiceAsync {

    private val withRawResponse: ComplianceServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val review: ReviewServiceAsync by lazy { ReviewServiceAsyncImpl(clientOptions) }

    override fun withRawResponse(): ComplianceServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ComplianceServiceAsync =
        ComplianceServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun review(): ReviewServiceAsync = review

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ComplianceServiceAsync.WithRawResponse {

        private val review: ReviewServiceAsync.WithRawResponse by lazy {
            ReviewServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ComplianceServiceAsync.WithRawResponse =
            ComplianceServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun review(): ReviewServiceAsync.WithRawResponse = review
    }
}

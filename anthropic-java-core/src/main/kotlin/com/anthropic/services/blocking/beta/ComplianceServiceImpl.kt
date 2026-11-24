package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.compliance.ReviewService
import com.anthropic.services.blocking.beta.compliance.ReviewServiceImpl
import java.util.function.Consumer

class ComplianceServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    ComplianceService {

    private val withRawResponse: ComplianceService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val review: ReviewService by lazy { ReviewServiceImpl(clientOptions) }

    override fun withRawResponse(): ComplianceService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ComplianceService =
        ComplianceServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun review(): ReviewService = review

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ComplianceService.WithRawResponse {

        private val review: ReviewService.WithRawResponse by lazy {
            ReviewServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ComplianceService.WithRawResponse =
            ComplianceServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun review(): ReviewService.WithRawResponse = review
    }
}

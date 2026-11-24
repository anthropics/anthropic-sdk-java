package com.anthropic.services.blocking.beta.compliance

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.compliance.review.CaseService
import com.anthropic.services.blocking.beta.compliance.review.CaseServiceImpl
import com.anthropic.services.blocking.beta.compliance.review.ItemService
import com.anthropic.services.blocking.beta.compliance.review.ItemServiceImpl
import com.anthropic.services.blocking.beta.compliance.review.NoticeService
import com.anthropic.services.blocking.beta.compliance.review.NoticeServiceImpl
import java.util.function.Consumer

class ReviewServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    ReviewService {

    private val withRawResponse: ReviewService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val cases: CaseService by lazy { CaseServiceImpl(clientOptions) }

    private val notices: NoticeService by lazy { NoticeServiceImpl(clientOptions) }

    private val items: ItemService by lazy { ItemServiceImpl(clientOptions) }

    override fun withRawResponse(): ReviewService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ReviewService =
        ReviewServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun cases(): CaseService = cases

    override fun notices(): NoticeService = notices

    override fun items(): ItemService = items

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ReviewService.WithRawResponse {

        private val cases: CaseService.WithRawResponse by lazy {
            CaseServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val notices: NoticeService.WithRawResponse by lazy {
            NoticeServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val items: ItemService.WithRawResponse by lazy {
            ItemServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ReviewService.WithRawResponse =
            ReviewServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun cases(): CaseService.WithRawResponse = cases

        override fun notices(): NoticeService.WithRawResponse = notices

        override fun items(): ItemService.WithRawResponse = items
    }
}

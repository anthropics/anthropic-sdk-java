package com.anthropic.services.async.beta.compliance

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.compliance.review.CaseServiceAsync
import com.anthropic.services.async.beta.compliance.review.CaseServiceAsyncImpl
import com.anthropic.services.async.beta.compliance.review.ItemServiceAsync
import com.anthropic.services.async.beta.compliance.review.ItemServiceAsyncImpl
import com.anthropic.services.async.beta.compliance.review.NoticeServiceAsync
import com.anthropic.services.async.beta.compliance.review.NoticeServiceAsyncImpl
import java.util.function.Consumer

class ReviewServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    ReviewServiceAsync {

    private val withRawResponse: ReviewServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val cases: CaseServiceAsync by lazy { CaseServiceAsyncImpl(clientOptions) }

    private val notices: NoticeServiceAsync by lazy { NoticeServiceAsyncImpl(clientOptions) }

    private val items: ItemServiceAsync by lazy { ItemServiceAsyncImpl(clientOptions) }

    override fun withRawResponse(): ReviewServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ReviewServiceAsync =
        ReviewServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun cases(): CaseServiceAsync = cases

    override fun notices(): NoticeServiceAsync = notices

    override fun items(): ItemServiceAsync = items

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ReviewServiceAsync.WithRawResponse {

        private val cases: CaseServiceAsync.WithRawResponse by lazy {
            CaseServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val notices: NoticeServiceAsync.WithRawResponse by lazy {
            NoticeServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val items: ItemServiceAsync.WithRawResponse by lazy {
            ItemServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ReviewServiceAsync.WithRawResponse =
            ReviewServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun cases(): CaseServiceAsync.WithRawResponse = cases

        override fun notices(): NoticeServiceAsync.WithRawResponse = notices

        override fun items(): ItemServiceAsync.WithRawResponse = items
    }
}

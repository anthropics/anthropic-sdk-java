package com.anthropic.services.async.beta.compliance

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.compliance.review.CaseServiceAsync
import com.anthropic.services.async.beta.compliance.review.ItemServiceAsync
import com.anthropic.services.async.beta.compliance.review.NoticeServiceAsync
import java.util.function.Consumer

interface ReviewServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ReviewServiceAsync

    fun cases(): CaseServiceAsync

    fun notices(): NoticeServiceAsync

    fun items(): ItemServiceAsync

    /**
     * A view of [ReviewServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ReviewServiceAsync.WithRawResponse

        fun cases(): CaseServiceAsync.WithRawResponse

        fun notices(): NoticeServiceAsync.WithRawResponse

        fun items(): ItemServiceAsync.WithRawResponse
    }
}

package com.anthropic.services.blocking.beta.compliance

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.compliance.review.CaseService
import com.anthropic.services.blocking.beta.compliance.review.ItemService
import com.anthropic.services.blocking.beta.compliance.review.NoticeService
import java.util.function.Consumer

interface ReviewService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ReviewService

    fun cases(): CaseService

    fun notices(): NoticeService

    fun items(): ItemService

    /** A view of [ReviewService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): ReviewService.WithRawResponse

        fun cases(): CaseService.WithRawResponse

        fun notices(): NoticeService.WithRawResponse

        fun items(): ItemService.WithRawResponse
    }
}

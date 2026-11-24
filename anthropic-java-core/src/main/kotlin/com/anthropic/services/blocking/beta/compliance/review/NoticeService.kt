package com.anthropic.services.blocking.beta.compliance.review

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.compliance.review.notices.NoticeListPage
import com.anthropic.models.beta.compliance.review.notices.NoticeListParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface NoticeService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): NoticeService

    /**
     * List Anthropic's notices to the organizations that the API key can reach, newest first by
     * default. A notice is returned only if the API key can read every case in its `case_ids`.
     *
     * To poll, store the largest `created_at` you have read, and send it as the at-or-after filter
     * on `created_at`. Read every page, match results by `id`, and store the new largest value
     * after the last page.
     *
     * An Admin API key with the `read:compliance_review_cases` scope can call this endpoint.
     */
    fun list(): NoticeListPage = list(NoticeListParams.none())

    /** @see list */
    fun list(
        params: NoticeListParams = NoticeListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): NoticeListPage

    /** @see list */
    fun list(params: NoticeListParams = NoticeListParams.none()): NoticeListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): NoticeListPage =
        list(NoticeListParams.none(), requestOptions)

    /** A view of [NoticeService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): NoticeService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/compliance/review/notices`, but is otherwise the
         * same as [NoticeService.list].
         */
        @MustBeClosed fun list(): HttpResponseFor<NoticeListPage> = list(NoticeListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: NoticeListParams = NoticeListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<NoticeListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: NoticeListParams = NoticeListParams.none()
        ): HttpResponseFor<NoticeListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<NoticeListPage> =
            list(NoticeListParams.none(), requestOptions)
    }
}

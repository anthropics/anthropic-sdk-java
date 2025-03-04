// File generated from our OpenAPI spec by Stainless.

@file:Suppress("OVERLOADS_INTERFACE") // See https://youtrack.jetbrains.com/issue/KT-36102

package com.anthropic.services.blocking

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.models.Completion
import com.anthropic.models.CompletionCreateParams
import com.google.errorprone.annotations.MustBeClosed

interface CompletionService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * [Legacy] Create a Text Completion.
     *
     * The Text Completions API is a legacy API. We recommend using the
     * [Messages API](https://docs.anthropic.com/en/api/messages) going forward.
     *
     * Future models and features will not be compatible with Text Completions. See our
     * [migration guide](https://docs.anthropic.com/en/api/migrating-from-text-completions-to-messages)
     * for guidance in migrating from Text Completions to Messages.
     */
    @JvmOverloads
    fun create(
        params: CompletionCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Completion

    /**
     * [Legacy] Create a Text Completion.
     *
     * The Text Completions API is a legacy API. We recommend using the
     * [Messages API](https://docs.anthropic.com/en/api/messages) going forward.
     *
     * Future models and features will not be compatible with Text Completions. See our
     * [migration guide](https://docs.anthropic.com/en/api/migrating-from-text-completions-to-messages)
     * for guidance in migrating from Text Completions to Messages.
     */
    @JvmOverloads
    @MustBeClosed
    fun createStreaming(
        params: CompletionCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StreamResponse<Completion>

    /** A view of [CompletionService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a raw HTTP response for `post /v1/complete`, but is otherwise the same as
         * [CompletionService.create].
         */
        @JvmOverloads
        @MustBeClosed
        fun create(
            params: CompletionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Completion>

        /**
         * Returns a raw HTTP response for `post /v1/complete`, but is otherwise the same as
         * [CompletionService.createStreaming].
         */
        @JvmOverloads
        @MustBeClosed
        fun createStreaming(
            params: CompletionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StreamResponse<Completion>>
    }
}

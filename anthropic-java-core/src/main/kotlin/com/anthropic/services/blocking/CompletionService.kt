// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.models.completions.Completion
import com.anthropic.models.completions.CompletionCreateParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface CompletionService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): CompletionService

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
    fun create(params: CompletionCreateParams): Completion = create(params, RequestOptions.none())

    /** @see create */
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
    @MustBeClosed
    fun createStreaming(params: CompletionCreateParams): StreamResponse<Completion> =
        createStreaming(params, RequestOptions.none())

    /** @see createStreaming */
    @MustBeClosed
    fun createStreaming(
        params: CompletionCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StreamResponse<Completion>

    /** A view of [CompletionService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CompletionService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/complete`, but is otherwise the same as
         * [CompletionService.create].
         */
        @MustBeClosed
        fun create(params: CompletionCreateParams): HttpResponseFor<Completion> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: CompletionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Completion>

        /**
         * Returns a raw HTTP response for `post /v1/complete`, but is otherwise the same as
         * [CompletionService.createStreaming].
         */
        @MustBeClosed
        fun createStreaming(
            params: CompletionCreateParams
        ): HttpResponseFor<StreamResponse<Completion>> =
            createStreaming(params, RequestOptions.none())

        /** @see createStreaming */
        @MustBeClosed
        fun createStreaming(
            params: CompletionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StreamResponse<Completion>>
    }
}

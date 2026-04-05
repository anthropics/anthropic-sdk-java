// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import kotlinx.kmp.util.core.ClientOptions
import kotlinx.kmp.util.core.RequestOptions
import kotlinx.kmp.util.core.http.AsyncStreamResponse
import kotlinx.kmp.util.core.http.HttpResponseFor
import kotlinx.kmp.util.core.http.StreamResponse
import com.anthropic.models.completions.Completion
import com.anthropic.models.completions.CompletionCreateParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface CompletionServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): CompletionServiceAsync

    /**
     * [Legacy] Create a Text Completion.
     *
     * The Text Completions API is a legacy API. We recommend using the
     * [Messages API](https://docs.claude.com/en/api/messages) going forward.
     *
     * Future models and features will not be compatible with Text Completions. See our
     * [migration guide](https://docs.claude.com/en/api/migrating-from-text-completions-to-messages)
     * for guidance in migrating from Text Completions to Messages.
     */
    suspend fun create(params: CompletionCreateParams): Completion =
        create(params, RequestOptions.none())

    /** @see create */
    suspend fun create(
        params: CompletionCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Completion

    /**
     * [Legacy] Create a Text Completion.
     *
     * The Text Completions API is a legacy API. We recommend using the
     * [Messages API](https://docs.claude.com/en/api/messages) going forward.
     *
     * Future models and features will not be compatible with Text Completions. See our
     * [migration guide](https://docs.claude.com/en/api/migrating-from-text-completions-to-messages)
     * for guidance in migrating from Text Completions to Messages.
     */
    suspend fun createStreaming(params: CompletionCreateParams): AsyncStreamResponse<Completion> =
        createStreaming(params, RequestOptions.none())

    /** @see createStreaming */
    suspend fun createStreaming(
        params: CompletionCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AsyncStreamResponse<Completion>

    /**
     * A view of [CompletionServiceAsync] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CompletionServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/complete`, but is otherwise the same as
         * [CompletionServiceAsync.create].
         */
        suspend fun create(params: CompletionCreateParams): HttpResponseFor<Completion> =
            create(params, RequestOptions.none())

        /** @see create */
        suspend fun create(
            params: CompletionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Completion>

        /**
         * Returns a raw HTTP response for `post /v1/complete`, but is otherwise the same as
         * [CompletionServiceAsync.createStreaming].
         */
        @MustBeClosed
        suspend fun createStreaming(
            params: CompletionCreateParams
        ): HttpResponseFor<StreamResponse<Completion>> =
            createStreaming(params, RequestOptions.none())

        /** @see createStreaming */
        @MustBeClosed
        suspend fun createStreaming(
            params: CompletionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StreamResponse<Completion>>
    }
}

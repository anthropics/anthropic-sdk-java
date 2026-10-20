package com.anthropic.services.async.userprofiles

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.userprofiles.actionurls.ActionUrlCreateParams
import com.anthropic.models.userprofiles.actionurls.UserProfileActionUrl
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface ActionUrlServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ActionUrlServiceAsync

    /** Create Action URL */
    fun create(
        userProfileId: String,
        params: ActionUrlCreateParams,
    ): CompletableFuture<UserProfileActionUrl> =
        create(userProfileId, params, RequestOptions.none())

    /** @see create */
    fun create(
        userProfileId: String,
        params: ActionUrlCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<UserProfileActionUrl> =
        create(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see create */
    fun create(params: ActionUrlCreateParams): CompletableFuture<UserProfileActionUrl> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: ActionUrlCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<UserProfileActionUrl>

    /**
     * A view of [ActionUrlServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ActionUrlServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/user_profiles/{user_profile_id}/action_urls`,
         * but is otherwise the same as [ActionUrlServiceAsync.create].
         */
        fun create(
            userProfileId: String,
            params: ActionUrlCreateParams,
        ): CompletableFuture<HttpResponseFor<UserProfileActionUrl>> =
            create(userProfileId, params, RequestOptions.none())

        /** @see create */
        fun create(
            userProfileId: String,
            params: ActionUrlCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<UserProfileActionUrl>> =
            create(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see create */
        fun create(
            params: ActionUrlCreateParams
        ): CompletableFuture<HttpResponseFor<UserProfileActionUrl>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: ActionUrlCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<UserProfileActionUrl>>
    }
}

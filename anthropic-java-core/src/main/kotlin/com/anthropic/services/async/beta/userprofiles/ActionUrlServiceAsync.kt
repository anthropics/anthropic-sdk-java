package com.anthropic.services.async.beta.userprofiles

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.userprofiles.actionurls.ActionUrlCreateParams
import com.anthropic.models.beta.userprofiles.actionurls.BetaUserProfileActionUrl
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

    /**
     * Create a link that sends the profile's end customer to the Verification Portal.
     *
     * See
     * [Send an end customer a link to the Verification Portal](https://platform.claude.com/docs/en/manage-claude/user-profiles-verification-portal)
     * for when to create the link and what the end customer sees.
     */
    fun create(
        userProfileId: String,
        params: ActionUrlCreateParams,
    ): CompletableFuture<BetaUserProfileActionUrl> =
        create(userProfileId, params, RequestOptions.none())

    /** @see create */
    fun create(
        userProfileId: String,
        params: ActionUrlCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaUserProfileActionUrl> =
        create(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see create */
    fun create(params: ActionUrlCreateParams): CompletableFuture<BetaUserProfileActionUrl> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: ActionUrlCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaUserProfileActionUrl>

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
        ): CompletableFuture<HttpResponseFor<BetaUserProfileActionUrl>> =
            create(userProfileId, params, RequestOptions.none())

        /** @see create */
        fun create(
            userProfileId: String,
            params: ActionUrlCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfileActionUrl>> =
            create(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see create */
        fun create(
            params: ActionUrlCreateParams
        ): CompletableFuture<HttpResponseFor<BetaUserProfileActionUrl>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: ActionUrlCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfileActionUrl>>
    }
}

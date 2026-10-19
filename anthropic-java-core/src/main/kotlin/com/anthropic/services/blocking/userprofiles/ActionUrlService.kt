package com.anthropic.services.blocking.userprofiles

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.userprofiles.actionurls.ActionUrlCreateParams
import com.anthropic.models.userprofiles.actionurls.UserProfileActionUrl
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface ActionUrlService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ActionUrlService

    /** Create Action URL */
    fun create(userProfileId: String, params: ActionUrlCreateParams): UserProfileActionUrl =
        create(userProfileId, params, RequestOptions.none())

    /** @see create */
    fun create(
        userProfileId: String,
        params: ActionUrlCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserProfileActionUrl =
        create(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see create */
    fun create(params: ActionUrlCreateParams): UserProfileActionUrl =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: ActionUrlCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserProfileActionUrl

    /** A view of [ActionUrlService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): ActionUrlService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/user_profiles/{user_profile_id}/action_urls`,
         * but is otherwise the same as [ActionUrlService.create].
         */
        @MustBeClosed
        fun create(
            userProfileId: String,
            params: ActionUrlCreateParams,
        ): HttpResponseFor<UserProfileActionUrl> =
            create(userProfileId, params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            userProfileId: String,
            params: ActionUrlCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserProfileActionUrl> =
            create(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see create */
        @MustBeClosed
        fun create(params: ActionUrlCreateParams): HttpResponseFor<UserProfileActionUrl> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: ActionUrlCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserProfileActionUrl>
    }
}

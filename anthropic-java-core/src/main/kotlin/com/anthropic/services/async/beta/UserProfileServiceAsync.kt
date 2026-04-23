// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.userprofiles.BetaUserProfile
import com.anthropic.models.beta.userprofiles.BetaUserProfileEnrollmentUrl
import com.anthropic.models.beta.userprofiles.UserProfileCreateEnrollmentUrlParams
import com.anthropic.models.beta.userprofiles.UserProfileCreateParams
import com.anthropic.models.beta.userprofiles.UserProfileListPageAsync
import com.anthropic.models.beta.userprofiles.UserProfileListParams
import com.anthropic.models.beta.userprofiles.UserProfileRetrieveParams
import com.anthropic.models.beta.userprofiles.UserProfileUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface UserProfileServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): UserProfileServiceAsync

    /** Create User Profile */
    fun create(): CompletableFuture<BetaUserProfile> = create(UserProfileCreateParams.none())

    /** @see create */
    fun create(
        params: UserProfileCreateParams = UserProfileCreateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaUserProfile>

    /** @see create */
    fun create(
        params: UserProfileCreateParams = UserProfileCreateParams.none()
    ): CompletableFuture<BetaUserProfile> = create(params, RequestOptions.none())

    /** @see create */
    fun create(requestOptions: RequestOptions): CompletableFuture<BetaUserProfile> =
        create(UserProfileCreateParams.none(), requestOptions)

    /** Get User Profile */
    fun retrieve(userProfileId: String): CompletableFuture<BetaUserProfile> =
        retrieve(userProfileId, UserProfileRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        userProfileId: String,
        params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaUserProfile> =
        retrieve(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        userProfileId: String,
        params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
    ): CompletableFuture<BetaUserProfile> = retrieve(userProfileId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: UserProfileRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaUserProfile>

    /** @see retrieve */
    fun retrieve(params: UserProfileRetrieveParams): CompletableFuture<BetaUserProfile> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        userProfileId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaUserProfile> =
        retrieve(userProfileId, UserProfileRetrieveParams.none(), requestOptions)

    /** Update User Profile */
    fun update(userProfileId: String): CompletableFuture<BetaUserProfile> =
        update(userProfileId, UserProfileUpdateParams.none())

    /** @see update */
    fun update(
        userProfileId: String,
        params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaUserProfile> =
        update(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see update */
    fun update(
        userProfileId: String,
        params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
    ): CompletableFuture<BetaUserProfile> = update(userProfileId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: UserProfileUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaUserProfile>

    /** @see update */
    fun update(params: UserProfileUpdateParams): CompletableFuture<BetaUserProfile> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        userProfileId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaUserProfile> =
        update(userProfileId, UserProfileUpdateParams.none(), requestOptions)

    /** List User Profiles */
    fun list(): CompletableFuture<UserProfileListPageAsync> = list(UserProfileListParams.none())

    /** @see list */
    fun list(
        params: UserProfileListParams = UserProfileListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<UserProfileListPageAsync>

    /** @see list */
    fun list(
        params: UserProfileListParams = UserProfileListParams.none()
    ): CompletableFuture<UserProfileListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<UserProfileListPageAsync> =
        list(UserProfileListParams.none(), requestOptions)

    /** Create Enrollment URL */
    fun createEnrollmentUrl(
        userProfileId: String
    ): CompletableFuture<BetaUserProfileEnrollmentUrl> =
        createEnrollmentUrl(userProfileId, UserProfileCreateEnrollmentUrlParams.none())

    /** @see createEnrollmentUrl */
    fun createEnrollmentUrl(
        userProfileId: String,
        params: UserProfileCreateEnrollmentUrlParams = UserProfileCreateEnrollmentUrlParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaUserProfileEnrollmentUrl> =
        createEnrollmentUrl(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see createEnrollmentUrl */
    fun createEnrollmentUrl(
        userProfileId: String,
        params: UserProfileCreateEnrollmentUrlParams = UserProfileCreateEnrollmentUrlParams.none(),
    ): CompletableFuture<BetaUserProfileEnrollmentUrl> =
        createEnrollmentUrl(userProfileId, params, RequestOptions.none())

    /** @see createEnrollmentUrl */
    fun createEnrollmentUrl(
        params: UserProfileCreateEnrollmentUrlParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaUserProfileEnrollmentUrl>

    /** @see createEnrollmentUrl */
    fun createEnrollmentUrl(
        params: UserProfileCreateEnrollmentUrlParams
    ): CompletableFuture<BetaUserProfileEnrollmentUrl> =
        createEnrollmentUrl(params, RequestOptions.none())

    /** @see createEnrollmentUrl */
    fun createEnrollmentUrl(
        userProfileId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaUserProfileEnrollmentUrl> =
        createEnrollmentUrl(
            userProfileId,
            UserProfileCreateEnrollmentUrlParams.none(),
            requestOptions,
        )

    /**
     * A view of [UserProfileServiceAsync] that provides access to raw HTTP responses for each
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
        ): UserProfileServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/user_profiles?beta=true`, but is otherwise the
         * same as [UserProfileServiceAsync.create].
         */
        fun create(): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            create(UserProfileCreateParams.none())

        /** @see create */
        fun create(
            params: UserProfileCreateParams = UserProfileCreateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>>

        /** @see create */
        fun create(
            params: UserProfileCreateParams = UserProfileCreateParams.none()
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            create(UserProfileCreateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/user_profiles/{user_profile_id}?beta=true`, but
         * is otherwise the same as [UserProfileServiceAsync.retrieve].
         */
        fun retrieve(userProfileId: String): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            retrieve(userProfileId, UserProfileRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            userProfileId: String,
            params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            retrieve(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            userProfileId: String,
            params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            retrieve(userProfileId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: UserProfileRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>>

        /** @see retrieve */
        fun retrieve(
            params: UserProfileRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            retrieve(userProfileId, UserProfileRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/user_profiles/{user_profile_id}?beta=true`, but
         * is otherwise the same as [UserProfileServiceAsync.update].
         */
        fun update(userProfileId: String): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            update(userProfileId, UserProfileUpdateParams.none())

        /** @see update */
        fun update(
            userProfileId: String,
            params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            update(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see update */
        fun update(
            userProfileId: String,
            params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            update(userProfileId, params, RequestOptions.none())

        /** @see update */
        fun update(
            params: UserProfileUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>>

        /** @see update */
        fun update(
            params: UserProfileUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            update(userProfileId, UserProfileUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/user_profiles?beta=true`, but is otherwise the
         * same as [UserProfileServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<UserProfileListPageAsync>> =
            list(UserProfileListParams.none())

        /** @see list */
        fun list(
            params: UserProfileListParams = UserProfileListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<UserProfileListPageAsync>>

        /** @see list */
        fun list(
            params: UserProfileListParams = UserProfileListParams.none()
        ): CompletableFuture<HttpResponseFor<UserProfileListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<UserProfileListPageAsync>> =
            list(UserProfileListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/user_profiles/{user_profile_id}/enrollment_url?beta=true`, but is otherwise the same
         * as [UserProfileServiceAsync.createEnrollmentUrl].
         */
        fun createEnrollmentUrl(
            userProfileId: String
        ): CompletableFuture<HttpResponseFor<BetaUserProfileEnrollmentUrl>> =
            createEnrollmentUrl(userProfileId, UserProfileCreateEnrollmentUrlParams.none())

        /** @see createEnrollmentUrl */
        fun createEnrollmentUrl(
            userProfileId: String,
            params: UserProfileCreateEnrollmentUrlParams =
                UserProfileCreateEnrollmentUrlParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfileEnrollmentUrl>> =
            createEnrollmentUrl(
                params.toBuilder().userProfileId(userProfileId).build(),
                requestOptions,
            )

        /** @see createEnrollmentUrl */
        fun createEnrollmentUrl(
            userProfileId: String,
            params: UserProfileCreateEnrollmentUrlParams =
                UserProfileCreateEnrollmentUrlParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfileEnrollmentUrl>> =
            createEnrollmentUrl(userProfileId, params, RequestOptions.none())

        /** @see createEnrollmentUrl */
        fun createEnrollmentUrl(
            params: UserProfileCreateEnrollmentUrlParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfileEnrollmentUrl>>

        /** @see createEnrollmentUrl */
        fun createEnrollmentUrl(
            params: UserProfileCreateEnrollmentUrlParams
        ): CompletableFuture<HttpResponseFor<BetaUserProfileEnrollmentUrl>> =
            createEnrollmentUrl(params, RequestOptions.none())

        /** @see createEnrollmentUrl */
        fun createEnrollmentUrl(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaUserProfileEnrollmentUrl>> =
            createEnrollmentUrl(
                userProfileId,
                UserProfileCreateEnrollmentUrlParams.none(),
                requestOptions,
            )
    }
}

// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.userprofiles.BetaUserProfile
import com.anthropic.models.beta.userprofiles.BetaUserProfileEnrollmentUrl
import com.anthropic.models.beta.userprofiles.UserProfileCreateEnrollmentUrlParams
import com.anthropic.models.beta.userprofiles.UserProfileCreateParams
import com.anthropic.models.beta.userprofiles.UserProfileListPage
import com.anthropic.models.beta.userprofiles.UserProfileListParams
import com.anthropic.models.beta.userprofiles.UserProfileRetrieveParams
import com.anthropic.models.beta.userprofiles.UserProfileUpdateParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface UserProfileService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): UserProfileService

    /** Create User Profile */
    fun create(): BetaUserProfile = create(UserProfileCreateParams.none())

    /** @see create */
    fun create(
        params: UserProfileCreateParams = UserProfileCreateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaUserProfile

    /** @see create */
    fun create(params: UserProfileCreateParams = UserProfileCreateParams.none()): BetaUserProfile =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(requestOptions: RequestOptions): BetaUserProfile =
        create(UserProfileCreateParams.none(), requestOptions)

    /** Get User Profile */
    fun retrieve(userProfileId: String): BetaUserProfile =
        retrieve(userProfileId, UserProfileRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        userProfileId: String,
        params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaUserProfile =
        retrieve(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        userProfileId: String,
        params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
    ): BetaUserProfile = retrieve(userProfileId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: UserProfileRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaUserProfile

    /** @see retrieve */
    fun retrieve(params: UserProfileRetrieveParams): BetaUserProfile =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(userProfileId: String, requestOptions: RequestOptions): BetaUserProfile =
        retrieve(userProfileId, UserProfileRetrieveParams.none(), requestOptions)

    /** Update User Profile */
    fun update(userProfileId: String): BetaUserProfile =
        update(userProfileId, UserProfileUpdateParams.none())

    /** @see update */
    fun update(
        userProfileId: String,
        params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaUserProfile =
        update(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see update */
    fun update(
        userProfileId: String,
        params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
    ): BetaUserProfile = update(userProfileId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: UserProfileUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaUserProfile

    /** @see update */
    fun update(params: UserProfileUpdateParams): BetaUserProfile =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(userProfileId: String, requestOptions: RequestOptions): BetaUserProfile =
        update(userProfileId, UserProfileUpdateParams.none(), requestOptions)

    /** List User Profiles */
    fun list(): UserProfileListPage = list(UserProfileListParams.none())

    /** @see list */
    fun list(
        params: UserProfileListParams = UserProfileListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserProfileListPage

    /** @see list */
    fun list(params: UserProfileListParams = UserProfileListParams.none()): UserProfileListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): UserProfileListPage =
        list(UserProfileListParams.none(), requestOptions)

    /** Create Enrollment URL */
    fun createEnrollmentUrl(userProfileId: String): BetaUserProfileEnrollmentUrl =
        createEnrollmentUrl(userProfileId, UserProfileCreateEnrollmentUrlParams.none())

    /** @see createEnrollmentUrl */
    fun createEnrollmentUrl(
        userProfileId: String,
        params: UserProfileCreateEnrollmentUrlParams = UserProfileCreateEnrollmentUrlParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaUserProfileEnrollmentUrl =
        createEnrollmentUrl(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see createEnrollmentUrl */
    fun createEnrollmentUrl(
        userProfileId: String,
        params: UserProfileCreateEnrollmentUrlParams = UserProfileCreateEnrollmentUrlParams.none(),
    ): BetaUserProfileEnrollmentUrl =
        createEnrollmentUrl(userProfileId, params, RequestOptions.none())

    /** @see createEnrollmentUrl */
    fun createEnrollmentUrl(
        params: UserProfileCreateEnrollmentUrlParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaUserProfileEnrollmentUrl

    /** @see createEnrollmentUrl */
    fun createEnrollmentUrl(
        params: UserProfileCreateEnrollmentUrlParams
    ): BetaUserProfileEnrollmentUrl = createEnrollmentUrl(params, RequestOptions.none())

    /** @see createEnrollmentUrl */
    fun createEnrollmentUrl(
        userProfileId: String,
        requestOptions: RequestOptions,
    ): BetaUserProfileEnrollmentUrl =
        createEnrollmentUrl(
            userProfileId,
            UserProfileCreateEnrollmentUrlParams.none(),
            requestOptions,
        )

    /**
     * A view of [UserProfileService] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): UserProfileService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/user_profiles?beta=true`, but is otherwise the
         * same as [UserProfileService.create].
         */
        @MustBeClosed
        fun create(): HttpResponseFor<BetaUserProfile> = create(UserProfileCreateParams.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: UserProfileCreateParams = UserProfileCreateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaUserProfile>

        /** @see create */
        @MustBeClosed
        fun create(
            params: UserProfileCreateParams = UserProfileCreateParams.none()
        ): HttpResponseFor<BetaUserProfile> = create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(requestOptions: RequestOptions): HttpResponseFor<BetaUserProfile> =
            create(UserProfileCreateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/user_profiles/{user_profile_id}?beta=true`, but
         * is otherwise the same as [UserProfileService.retrieve].
         */
        @MustBeClosed
        fun retrieve(userProfileId: String): HttpResponseFor<BetaUserProfile> =
            retrieve(userProfileId, UserProfileRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            userProfileId: String,
            params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaUserProfile> =
            retrieve(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            userProfileId: String,
            params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
        ): HttpResponseFor<BetaUserProfile> = retrieve(userProfileId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: UserProfileRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaUserProfile>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: UserProfileRetrieveParams): HttpResponseFor<BetaUserProfile> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaUserProfile> =
            retrieve(userProfileId, UserProfileRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/user_profiles/{user_profile_id}?beta=true`, but
         * is otherwise the same as [UserProfileService.update].
         */
        @MustBeClosed
        fun update(userProfileId: String): HttpResponseFor<BetaUserProfile> =
            update(userProfileId, UserProfileUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            userProfileId: String,
            params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaUserProfile> =
            update(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            userProfileId: String,
            params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
        ): HttpResponseFor<BetaUserProfile> = update(userProfileId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: UserProfileUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaUserProfile>

        /** @see update */
        @MustBeClosed
        fun update(params: UserProfileUpdateParams): HttpResponseFor<BetaUserProfile> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaUserProfile> =
            update(userProfileId, UserProfileUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/user_profiles?beta=true`, but is otherwise the
         * same as [UserProfileService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<UserProfileListPage> = list(UserProfileListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: UserProfileListParams = UserProfileListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserProfileListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: UserProfileListParams = UserProfileListParams.none()
        ): HttpResponseFor<UserProfileListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<UserProfileListPage> =
            list(UserProfileListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/user_profiles/{user_profile_id}/enrollment_url?beta=true`, but is otherwise the same
         * as [UserProfileService.createEnrollmentUrl].
         */
        @MustBeClosed
        fun createEnrollmentUrl(
            userProfileId: String
        ): HttpResponseFor<BetaUserProfileEnrollmentUrl> =
            createEnrollmentUrl(userProfileId, UserProfileCreateEnrollmentUrlParams.none())

        /** @see createEnrollmentUrl */
        @MustBeClosed
        fun createEnrollmentUrl(
            userProfileId: String,
            params: UserProfileCreateEnrollmentUrlParams =
                UserProfileCreateEnrollmentUrlParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaUserProfileEnrollmentUrl> =
            createEnrollmentUrl(
                params.toBuilder().userProfileId(userProfileId).build(),
                requestOptions,
            )

        /** @see createEnrollmentUrl */
        @MustBeClosed
        fun createEnrollmentUrl(
            userProfileId: String,
            params: UserProfileCreateEnrollmentUrlParams =
                UserProfileCreateEnrollmentUrlParams.none(),
        ): HttpResponseFor<BetaUserProfileEnrollmentUrl> =
            createEnrollmentUrl(userProfileId, params, RequestOptions.none())

        /** @see createEnrollmentUrl */
        @MustBeClosed
        fun createEnrollmentUrl(
            params: UserProfileCreateEnrollmentUrlParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaUserProfileEnrollmentUrl>

        /** @see createEnrollmentUrl */
        @MustBeClosed
        fun createEnrollmentUrl(
            params: UserProfileCreateEnrollmentUrlParams
        ): HttpResponseFor<BetaUserProfileEnrollmentUrl> =
            createEnrollmentUrl(params, RequestOptions.none())

        /** @see createEnrollmentUrl */
        @MustBeClosed
        fun createEnrollmentUrl(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaUserProfileEnrollmentUrl> =
            createEnrollmentUrl(
                userProfileId,
                UserProfileCreateEnrollmentUrlParams.none(),
                requestOptions,
            )
    }
}

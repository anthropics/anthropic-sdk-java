package com.anthropic.services.blocking

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.userprofiles.DeletedUserProfile
import com.anthropic.models.userprofiles.UserProfile
import com.anthropic.models.userprofiles.UserProfileCreateParams
import com.anthropic.models.userprofiles.UserProfileDeleteParams
import com.anthropic.models.userprofiles.UserProfileListPage
import com.anthropic.models.userprofiles.UserProfileListParams
import com.anthropic.models.userprofiles.UserProfileRetrieveParams
import com.anthropic.models.userprofiles.UserProfileUpdateParams
import com.anthropic.services.blocking.userprofiles.ActionUrlService
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

    fun actionUrls(): ActionUrlService

    /** Create User Profile */
    fun create(): UserProfile = create(UserProfileCreateParams.none())

    /** @see create */
    fun create(
        params: UserProfileCreateParams = UserProfileCreateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserProfile

    /** @see create */
    fun create(params: UserProfileCreateParams = UserProfileCreateParams.none()): UserProfile =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(requestOptions: RequestOptions): UserProfile =
        create(UserProfileCreateParams.none(), requestOptions)

    /** Get User Profile */
    fun retrieve(userProfileId: String): UserProfile =
        retrieve(userProfileId, UserProfileRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        userProfileId: String,
        params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserProfile =
        retrieve(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        userProfileId: String,
        params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
    ): UserProfile = retrieve(userProfileId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: UserProfileRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserProfile

    /** @see retrieve */
    fun retrieve(params: UserProfileRetrieveParams): UserProfile =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(userProfileId: String, requestOptions: RequestOptions): UserProfile =
        retrieve(userProfileId, UserProfileRetrieveParams.none(), requestOptions)

    /** Update User Profile */
    fun update(userProfileId: String): UserProfile =
        update(userProfileId, UserProfileUpdateParams.none())

    /** @see update */
    fun update(
        userProfileId: String,
        params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserProfile = update(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see update */
    fun update(
        userProfileId: String,
        params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
    ): UserProfile = update(userProfileId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: UserProfileUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserProfile

    /** @see update */
    fun update(params: UserProfileUpdateParams): UserProfile = update(params, RequestOptions.none())

    /** @see update */
    fun update(userProfileId: String, requestOptions: RequestOptions): UserProfile =
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

    /** Delete User Profile */
    fun delete(userProfileId: String): DeletedUserProfile =
        delete(userProfileId, UserProfileDeleteParams.none())

    /** @see delete */
    fun delete(
        userProfileId: String,
        params: UserProfileDeleteParams = UserProfileDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DeletedUserProfile =
        delete(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see delete */
    fun delete(
        userProfileId: String,
        params: UserProfileDeleteParams = UserProfileDeleteParams.none(),
    ): DeletedUserProfile = delete(userProfileId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: UserProfileDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DeletedUserProfile

    /** @see delete */
    fun delete(params: UserProfileDeleteParams): DeletedUserProfile =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(userProfileId: String, requestOptions: RequestOptions): DeletedUserProfile =
        delete(userProfileId, UserProfileDeleteParams.none(), requestOptions)

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

        fun actionUrls(): ActionUrlService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/user_profiles`, but is otherwise the same as
         * [UserProfileService.create].
         */
        @MustBeClosed
        fun create(): HttpResponseFor<UserProfile> = create(UserProfileCreateParams.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: UserProfileCreateParams = UserProfileCreateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserProfile>

        /** @see create */
        @MustBeClosed
        fun create(
            params: UserProfileCreateParams = UserProfileCreateParams.none()
        ): HttpResponseFor<UserProfile> = create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(requestOptions: RequestOptions): HttpResponseFor<UserProfile> =
            create(UserProfileCreateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/user_profiles/{user_profile_id}`, but is
         * otherwise the same as [UserProfileService.retrieve].
         */
        @MustBeClosed
        fun retrieve(userProfileId: String): HttpResponseFor<UserProfile> =
            retrieve(userProfileId, UserProfileRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            userProfileId: String,
            params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserProfile> =
            retrieve(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            userProfileId: String,
            params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
        ): HttpResponseFor<UserProfile> = retrieve(userProfileId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: UserProfileRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserProfile>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: UserProfileRetrieveParams): HttpResponseFor<UserProfile> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserProfile> =
            retrieve(userProfileId, UserProfileRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/user_profiles/{user_profile_id}`, but is
         * otherwise the same as [UserProfileService.update].
         */
        @MustBeClosed
        fun update(userProfileId: String): HttpResponseFor<UserProfile> =
            update(userProfileId, UserProfileUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            userProfileId: String,
            params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserProfile> =
            update(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            userProfileId: String,
            params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
        ): HttpResponseFor<UserProfile> = update(userProfileId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: UserProfileUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserProfile>

        /** @see update */
        @MustBeClosed
        fun update(params: UserProfileUpdateParams): HttpResponseFor<UserProfile> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserProfile> =
            update(userProfileId, UserProfileUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/user_profiles`, but is otherwise the same as
         * [UserProfileService.list].
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
         * Returns a raw HTTP response for `delete /v1/user_profiles/{user_profile_id}`, but is
         * otherwise the same as [UserProfileService.delete].
         */
        @MustBeClosed
        fun delete(userProfileId: String): HttpResponseFor<DeletedUserProfile> =
            delete(userProfileId, UserProfileDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            userProfileId: String,
            params: UserProfileDeleteParams = UserProfileDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DeletedUserProfile> =
            delete(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            userProfileId: String,
            params: UserProfileDeleteParams = UserProfileDeleteParams.none(),
        ): HttpResponseFor<DeletedUserProfile> =
            delete(userProfileId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: UserProfileDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DeletedUserProfile>

        /** @see delete */
        @MustBeClosed
        fun delete(params: UserProfileDeleteParams): HttpResponseFor<DeletedUserProfile> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<DeletedUserProfile> =
            delete(userProfileId, UserProfileDeleteParams.none(), requestOptions)
    }
}

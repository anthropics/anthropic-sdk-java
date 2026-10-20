package com.anthropic.services.async

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.userprofiles.DeletedUserProfile
import com.anthropic.models.userprofiles.UserProfile
import com.anthropic.models.userprofiles.UserProfileCreateParams
import com.anthropic.models.userprofiles.UserProfileDeleteParams
import com.anthropic.models.userprofiles.UserProfileListPageAsync
import com.anthropic.models.userprofiles.UserProfileListParams
import com.anthropic.models.userprofiles.UserProfileRetrieveParams
import com.anthropic.models.userprofiles.UserProfileUpdateParams
import com.anthropic.services.async.userprofiles.ActionUrlServiceAsync
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

    fun actionUrls(): ActionUrlServiceAsync

    /** Create User Profile */
    fun create(): CompletableFuture<UserProfile> = create(UserProfileCreateParams.none())

    /** @see create */
    fun create(
        params: UserProfileCreateParams = UserProfileCreateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<UserProfile>

    /** @see create */
    fun create(
        params: UserProfileCreateParams = UserProfileCreateParams.none()
    ): CompletableFuture<UserProfile> = create(params, RequestOptions.none())

    /** @see create */
    fun create(requestOptions: RequestOptions): CompletableFuture<UserProfile> =
        create(UserProfileCreateParams.none(), requestOptions)

    /** Get User Profile */
    fun retrieve(userProfileId: String): CompletableFuture<UserProfile> =
        retrieve(userProfileId, UserProfileRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        userProfileId: String,
        params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<UserProfile> =
        retrieve(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        userProfileId: String,
        params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
    ): CompletableFuture<UserProfile> = retrieve(userProfileId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: UserProfileRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<UserProfile>

    /** @see retrieve */
    fun retrieve(params: UserProfileRetrieveParams): CompletableFuture<UserProfile> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        userProfileId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<UserProfile> =
        retrieve(userProfileId, UserProfileRetrieveParams.none(), requestOptions)

    /** Update User Profile */
    fun update(userProfileId: String): CompletableFuture<UserProfile> =
        update(userProfileId, UserProfileUpdateParams.none())

    /** @see update */
    fun update(
        userProfileId: String,
        params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<UserProfile> =
        update(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see update */
    fun update(
        userProfileId: String,
        params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
    ): CompletableFuture<UserProfile> = update(userProfileId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: UserProfileUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<UserProfile>

    /** @see update */
    fun update(params: UserProfileUpdateParams): CompletableFuture<UserProfile> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        userProfileId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<UserProfile> =
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

    /** Delete User Profile */
    fun delete(userProfileId: String): CompletableFuture<DeletedUserProfile> =
        delete(userProfileId, UserProfileDeleteParams.none())

    /** @see delete */
    fun delete(
        userProfileId: String,
        params: UserProfileDeleteParams = UserProfileDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<DeletedUserProfile> =
        delete(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see delete */
    fun delete(
        userProfileId: String,
        params: UserProfileDeleteParams = UserProfileDeleteParams.none(),
    ): CompletableFuture<DeletedUserProfile> = delete(userProfileId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: UserProfileDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<DeletedUserProfile>

    /** @see delete */
    fun delete(params: UserProfileDeleteParams): CompletableFuture<DeletedUserProfile> =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        userProfileId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<DeletedUserProfile> =
        delete(userProfileId, UserProfileDeleteParams.none(), requestOptions)

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

        fun actionUrls(): ActionUrlServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/user_profiles`, but is otherwise the same as
         * [UserProfileServiceAsync.create].
         */
        fun create(): CompletableFuture<HttpResponseFor<UserProfile>> =
            create(UserProfileCreateParams.none())

        /** @see create */
        fun create(
            params: UserProfileCreateParams = UserProfileCreateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<UserProfile>>

        /** @see create */
        fun create(
            params: UserProfileCreateParams = UserProfileCreateParams.none()
        ): CompletableFuture<HttpResponseFor<UserProfile>> = create(params, RequestOptions.none())

        /** @see create */
        fun create(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<UserProfile>> =
            create(UserProfileCreateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/user_profiles/{user_profile_id}`, but is
         * otherwise the same as [UserProfileServiceAsync.retrieve].
         */
        fun retrieve(userProfileId: String): CompletableFuture<HttpResponseFor<UserProfile>> =
            retrieve(userProfileId, UserProfileRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            userProfileId: String,
            params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<UserProfile>> =
            retrieve(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            userProfileId: String,
            params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<UserProfile>> =
            retrieve(userProfileId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: UserProfileRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<UserProfile>>

        /** @see retrieve */
        fun retrieve(
            params: UserProfileRetrieveParams
        ): CompletableFuture<HttpResponseFor<UserProfile>> = retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<UserProfile>> =
            retrieve(userProfileId, UserProfileRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/user_profiles/{user_profile_id}`, but is
         * otherwise the same as [UserProfileServiceAsync.update].
         */
        fun update(userProfileId: String): CompletableFuture<HttpResponseFor<UserProfile>> =
            update(userProfileId, UserProfileUpdateParams.none())

        /** @see update */
        fun update(
            userProfileId: String,
            params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<UserProfile>> =
            update(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see update */
        fun update(
            userProfileId: String,
            params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
        ): CompletableFuture<HttpResponseFor<UserProfile>> =
            update(userProfileId, params, RequestOptions.none())

        /** @see update */
        fun update(
            params: UserProfileUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<UserProfile>>

        /** @see update */
        fun update(
            params: UserProfileUpdateParams
        ): CompletableFuture<HttpResponseFor<UserProfile>> = update(params, RequestOptions.none())

        /** @see update */
        fun update(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<UserProfile>> =
            update(userProfileId, UserProfileUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/user_profiles`, but is otherwise the same as
         * [UserProfileServiceAsync.list].
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
         * Returns a raw HTTP response for `delete /v1/user_profiles/{user_profile_id}`, but is
         * otherwise the same as [UserProfileServiceAsync.delete].
         */
        fun delete(userProfileId: String): CompletableFuture<HttpResponseFor<DeletedUserProfile>> =
            delete(userProfileId, UserProfileDeleteParams.none())

        /** @see delete */
        fun delete(
            userProfileId: String,
            params: UserProfileDeleteParams = UserProfileDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<DeletedUserProfile>> =
            delete(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see delete */
        fun delete(
            userProfileId: String,
            params: UserProfileDeleteParams = UserProfileDeleteParams.none(),
        ): CompletableFuture<HttpResponseFor<DeletedUserProfile>> =
            delete(userProfileId, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: UserProfileDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<DeletedUserProfile>>

        /** @see delete */
        fun delete(
            params: UserProfileDeleteParams
        ): CompletableFuture<HttpResponseFor<DeletedUserProfile>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<DeletedUserProfile>> =
            delete(userProfileId, UserProfileDeleteParams.none(), requestOptions)
    }
}

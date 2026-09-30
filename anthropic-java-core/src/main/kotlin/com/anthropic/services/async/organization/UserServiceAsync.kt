package com.anthropic.services.async.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.organization.users.OrganizationUser
import com.anthropic.models.organization.users.UserListPageAsync
import com.anthropic.models.organization.users.UserListParams
import com.anthropic.models.organization.users.UserRemoveParams
import com.anthropic.models.organization.users.UserRemoveResponse
import com.anthropic.models.organization.users.UserRetrieveParams
import com.anthropic.models.organization.users.UserUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface UserServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): UserServiceAsync

    /** Retrieve a member of the organization by user ID. */
    fun retrieve(userId: String): CompletableFuture<OrganizationUser> =
        retrieve(userId, UserRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        userId: String,
        params: UserRetrieveParams = UserRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<OrganizationUser> =
        retrieve(params.toBuilder().userId(userId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        userId: String,
        params: UserRetrieveParams = UserRetrieveParams.none(),
    ): CompletableFuture<OrganizationUser> = retrieve(userId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: UserRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<OrganizationUser>

    /** @see retrieve */
    fun retrieve(params: UserRetrieveParams): CompletableFuture<OrganizationUser> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        userId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<OrganizationUser> =
        retrieve(userId, UserRetrieveParams.none(), requestOptions)

    /** Update a member's organization role. */
    fun update(userId: String, params: UserUpdateParams): CompletableFuture<OrganizationUser> =
        update(userId, params, RequestOptions.none())

    /** @see update */
    fun update(
        userId: String,
        params: UserUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<OrganizationUser> =
        update(params.toBuilder().userId(userId).build(), requestOptions)

    /** @see update */
    fun update(params: UserUpdateParams): CompletableFuture<OrganizationUser> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: UserUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<OrganizationUser>

    /** List the organization's members. */
    fun list(): CompletableFuture<UserListPageAsync> = list(UserListParams.none())

    /** @see list */
    fun list(
        params: UserListParams = UserListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<UserListPageAsync>

    /** @see list */
    fun list(params: UserListParams = UserListParams.none()): CompletableFuture<UserListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<UserListPageAsync> =
        list(UserListParams.none(), requestOptions)

    /** Remove a member from the organization. */
    fun remove(userId: String): CompletableFuture<UserRemoveResponse> =
        remove(userId, UserRemoveParams.none())

    /** @see remove */
    fun remove(
        userId: String,
        params: UserRemoveParams = UserRemoveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<UserRemoveResponse> =
        remove(params.toBuilder().userId(userId).build(), requestOptions)

    /** @see remove */
    fun remove(
        userId: String,
        params: UserRemoveParams = UserRemoveParams.none(),
    ): CompletableFuture<UserRemoveResponse> = remove(userId, params, RequestOptions.none())

    /** @see remove */
    fun remove(
        params: UserRemoveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<UserRemoveResponse>

    /** @see remove */
    fun remove(params: UserRemoveParams): CompletableFuture<UserRemoveResponse> =
        remove(params, RequestOptions.none())

    /** @see remove */
    fun remove(
        userId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<UserRemoveResponse> =
        remove(userId, UserRemoveParams.none(), requestOptions)

    /** A view of [UserServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): UserServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/users/{user_id}`, but is otherwise
         * the same as [UserServiceAsync.retrieve].
         */
        fun retrieve(userId: String): CompletableFuture<HttpResponseFor<OrganizationUser>> =
            retrieve(userId, UserRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            userId: String,
            params: UserRetrieveParams = UserRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<OrganizationUser>> =
            retrieve(params.toBuilder().userId(userId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            userId: String,
            params: UserRetrieveParams = UserRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<OrganizationUser>> =
            retrieve(userId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: UserRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<OrganizationUser>>

        /** @see retrieve */
        fun retrieve(
            params: UserRetrieveParams
        ): CompletableFuture<HttpResponseFor<OrganizationUser>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            userId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<OrganizationUser>> =
            retrieve(userId, UserRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/organizations/users/{user_id}`, but is
         * otherwise the same as [UserServiceAsync.update].
         */
        fun update(
            userId: String,
            params: UserUpdateParams,
        ): CompletableFuture<HttpResponseFor<OrganizationUser>> =
            update(userId, params, RequestOptions.none())

        /** @see update */
        fun update(
            userId: String,
            params: UserUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<OrganizationUser>> =
            update(params.toBuilder().userId(userId).build(), requestOptions)

        /** @see update */
        fun update(params: UserUpdateParams): CompletableFuture<HttpResponseFor<OrganizationUser>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            params: UserUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<OrganizationUser>>

        /**
         * Returns a raw HTTP response for `get /v1/organizations/users`, but is otherwise the same
         * as [UserServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<UserListPageAsync>> =
            list(UserListParams.none())

        /** @see list */
        fun list(
            params: UserListParams = UserListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<UserListPageAsync>>

        /** @see list */
        fun list(
            params: UserListParams = UserListParams.none()
        ): CompletableFuture<HttpResponseFor<UserListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<UserListPageAsync>> =
            list(UserListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/organizations/users/{user_id}`, but is
         * otherwise the same as [UserServiceAsync.remove].
         */
        fun remove(userId: String): CompletableFuture<HttpResponseFor<UserRemoveResponse>> =
            remove(userId, UserRemoveParams.none())

        /** @see remove */
        fun remove(
            userId: String,
            params: UserRemoveParams = UserRemoveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<UserRemoveResponse>> =
            remove(params.toBuilder().userId(userId).build(), requestOptions)

        /** @see remove */
        fun remove(
            userId: String,
            params: UserRemoveParams = UserRemoveParams.none(),
        ): CompletableFuture<HttpResponseFor<UserRemoveResponse>> =
            remove(userId, params, RequestOptions.none())

        /** @see remove */
        fun remove(
            params: UserRemoveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<UserRemoveResponse>>

        /** @see remove */
        fun remove(
            params: UserRemoveParams
        ): CompletableFuture<HttpResponseFor<UserRemoveResponse>> =
            remove(params, RequestOptions.none())

        /** @see remove */
        fun remove(
            userId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<UserRemoveResponse>> =
            remove(userId, UserRemoveParams.none(), requestOptions)
    }
}

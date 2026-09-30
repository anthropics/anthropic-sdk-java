package com.anthropic.services.blocking.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.organization.users.OrganizationUser
import com.anthropic.models.organization.users.UserListPage
import com.anthropic.models.organization.users.UserListParams
import com.anthropic.models.organization.users.UserRemoveParams
import com.anthropic.models.organization.users.UserRemoveResponse
import com.anthropic.models.organization.users.UserRetrieveParams
import com.anthropic.models.organization.users.UserUpdateParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface UserService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): UserService

    /** Retrieve a member of the organization by user ID. */
    fun retrieve(userId: String): OrganizationUser = retrieve(userId, UserRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        userId: String,
        params: UserRetrieveParams = UserRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): OrganizationUser = retrieve(params.toBuilder().userId(userId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        userId: String,
        params: UserRetrieveParams = UserRetrieveParams.none(),
    ): OrganizationUser = retrieve(userId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: UserRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): OrganizationUser

    /** @see retrieve */
    fun retrieve(params: UserRetrieveParams): OrganizationUser =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(userId: String, requestOptions: RequestOptions): OrganizationUser =
        retrieve(userId, UserRetrieveParams.none(), requestOptions)

    /** Update a member's organization role. */
    fun update(userId: String, params: UserUpdateParams): OrganizationUser =
        update(userId, params, RequestOptions.none())

    /** @see update */
    fun update(
        userId: String,
        params: UserUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): OrganizationUser = update(params.toBuilder().userId(userId).build(), requestOptions)

    /** @see update */
    fun update(params: UserUpdateParams): OrganizationUser = update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: UserUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): OrganizationUser

    /** List the organization's members. */
    fun list(): UserListPage = list(UserListParams.none())

    /** @see list */
    fun list(
        params: UserListParams = UserListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserListPage

    /** @see list */
    fun list(params: UserListParams = UserListParams.none()): UserListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): UserListPage =
        list(UserListParams.none(), requestOptions)

    /** Remove a member from the organization. */
    fun remove(userId: String): UserRemoveResponse = remove(userId, UserRemoveParams.none())

    /** @see remove */
    fun remove(
        userId: String,
        params: UserRemoveParams = UserRemoveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRemoveResponse = remove(params.toBuilder().userId(userId).build(), requestOptions)

    /** @see remove */
    fun remove(
        userId: String,
        params: UserRemoveParams = UserRemoveParams.none(),
    ): UserRemoveResponse = remove(userId, params, RequestOptions.none())

    /** @see remove */
    fun remove(
        params: UserRemoveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRemoveResponse

    /** @see remove */
    fun remove(params: UserRemoveParams): UserRemoveResponse = remove(params, RequestOptions.none())

    /** @see remove */
    fun remove(userId: String, requestOptions: RequestOptions): UserRemoveResponse =
        remove(userId, UserRemoveParams.none(), requestOptions)

    /** A view of [UserService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): UserService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/users/{user_id}`, but is otherwise
         * the same as [UserService.retrieve].
         */
        @MustBeClosed
        fun retrieve(userId: String): HttpResponseFor<OrganizationUser> =
            retrieve(userId, UserRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            userId: String,
            params: UserRetrieveParams = UserRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<OrganizationUser> =
            retrieve(params.toBuilder().userId(userId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            userId: String,
            params: UserRetrieveParams = UserRetrieveParams.none(),
        ): HttpResponseFor<OrganizationUser> = retrieve(userId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: UserRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<OrganizationUser>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: UserRetrieveParams): HttpResponseFor<OrganizationUser> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            userId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<OrganizationUser> =
            retrieve(userId, UserRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/organizations/users/{user_id}`, but is
         * otherwise the same as [UserService.update].
         */
        @MustBeClosed
        fun update(userId: String, params: UserUpdateParams): HttpResponseFor<OrganizationUser> =
            update(userId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            userId: String,
            params: UserUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<OrganizationUser> =
            update(params.toBuilder().userId(userId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(params: UserUpdateParams): HttpResponseFor<OrganizationUser> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: UserUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<OrganizationUser>

        /**
         * Returns a raw HTTP response for `get /v1/organizations/users`, but is otherwise the same
         * as [UserService.list].
         */
        @MustBeClosed fun list(): HttpResponseFor<UserListPage> = list(UserListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: UserListParams = UserListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: UserListParams = UserListParams.none()): HttpResponseFor<UserListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<UserListPage> =
            list(UserListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/organizations/users/{user_id}`, but is
         * otherwise the same as [UserService.remove].
         */
        @MustBeClosed
        fun remove(userId: String): HttpResponseFor<UserRemoveResponse> =
            remove(userId, UserRemoveParams.none())

        /** @see remove */
        @MustBeClosed
        fun remove(
            userId: String,
            params: UserRemoveParams = UserRemoveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRemoveResponse> =
            remove(params.toBuilder().userId(userId).build(), requestOptions)

        /** @see remove */
        @MustBeClosed
        fun remove(
            userId: String,
            params: UserRemoveParams = UserRemoveParams.none(),
        ): HttpResponseFor<UserRemoveResponse> = remove(userId, params, RequestOptions.none())

        /** @see remove */
        @MustBeClosed
        fun remove(
            params: UserRemoveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRemoveResponse>

        /** @see remove */
        @MustBeClosed
        fun remove(params: UserRemoveParams): HttpResponseFor<UserRemoveResponse> =
            remove(params, RequestOptions.none())

        /** @see remove */
        @MustBeClosed
        fun remove(
            userId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserRemoveResponse> =
            remove(userId, UserRemoveParams.none(), requestOptions)
    }
}

// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.users.BetaOrganizationUser
import com.anthropic.models.beta.organization.users.UserListPage
import com.anthropic.models.beta.organization.users.UserListParams
import com.anthropic.models.beta.organization.users.UserRemoveParams
import com.anthropic.models.beta.organization.users.UserRemoveResponse
import com.anthropic.models.beta.organization.users.UserRetrieveParams
import com.anthropic.models.beta.organization.users.UserUpdateParams
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
    fun retrieve(userId: String): BetaOrganizationUser = retrieve(userId, UserRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        userId: String,
        params: UserRetrieveParams = UserRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaOrganizationUser = retrieve(params.toBuilder().userId(userId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        userId: String,
        params: UserRetrieveParams = UserRetrieveParams.none(),
    ): BetaOrganizationUser = retrieve(userId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: UserRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaOrganizationUser

    /** @see retrieve */
    fun retrieve(params: UserRetrieveParams): BetaOrganizationUser =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(userId: String, requestOptions: RequestOptions): BetaOrganizationUser =
        retrieve(userId, UserRetrieveParams.none(), requestOptions)

    /** Update a member's organization role. */
    fun update(userId: String, params: UserUpdateParams): BetaOrganizationUser =
        update(userId, params, RequestOptions.none())

    /** @see update */
    fun update(
        userId: String,
        params: UserUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaOrganizationUser = update(params.toBuilder().userId(userId).build(), requestOptions)

    /** @see update */
    fun update(params: UserUpdateParams): BetaOrganizationUser =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: UserUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaOrganizationUser

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
         * Returns a raw HTTP response for `get /v1/organizations/users/{user_id}?beta=true`, but is
         * otherwise the same as [UserService.retrieve].
         */
        @MustBeClosed
        fun retrieve(userId: String): HttpResponseFor<BetaOrganizationUser> =
            retrieve(userId, UserRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            userId: String,
            params: UserRetrieveParams = UserRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaOrganizationUser> =
            retrieve(params.toBuilder().userId(userId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            userId: String,
            params: UserRetrieveParams = UserRetrieveParams.none(),
        ): HttpResponseFor<BetaOrganizationUser> = retrieve(userId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: UserRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaOrganizationUser>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: UserRetrieveParams): HttpResponseFor<BetaOrganizationUser> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            userId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaOrganizationUser> =
            retrieve(userId, UserRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/organizations/users/{user_id}?beta=true`, but
         * is otherwise the same as [UserService.update].
         */
        @MustBeClosed
        fun update(
            userId: String,
            params: UserUpdateParams,
        ): HttpResponseFor<BetaOrganizationUser> = update(userId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            userId: String,
            params: UserUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaOrganizationUser> =
            update(params.toBuilder().userId(userId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(params: UserUpdateParams): HttpResponseFor<BetaOrganizationUser> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: UserUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaOrganizationUser>

        /**
         * Returns a raw HTTP response for `get /v1/organizations/users?beta=true`, but is otherwise
         * the same as [UserService.list].
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
         * Returns a raw HTTP response for `delete /v1/organizations/users/{user_id}?beta=true`, but
         * is otherwise the same as [UserService.remove].
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

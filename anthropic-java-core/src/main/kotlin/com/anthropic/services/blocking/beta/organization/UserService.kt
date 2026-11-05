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
import com.anthropic.models.beta.organization.users.UserSignOutParams
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

    /**
     * Remove a member from the organization.
     *
     * @throws com.anthropic.models.organization.users.CannotRemoveLastMemberException
     * @throws com.anthropic.models.organization.ConcurrentMembershipChangeException
     */
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

    /**
     * Sign a member out of Claude everywhere, without removing them from the organization. This
     * ends their sign-in sessions and removes their trusted devices wherever they use Claude,
     * including other organizations and individual plans. It also ends the OAuth tokens issued to
     * them for this organization, so apps such as Claude Code must sign in again.
     *
     * A token can take up to an hour to stop working, though it almost always stops at once. The
     * member keeps their role and can sign in again right away. To keep them out, remove them from
     * the organization or, if your organization uses single sign-on, block them in your identity
     * provider. Signing out does not end API keys, OAuth tokens that were not issued for this
     * organization, or the member's Claude Code cloud sessions and routines.
     *
     * You cannot sign out the organization's primary owner or the person your API key or token acts
     * for, and a request to sign out another owner, a member with owner permissions, or a
     * membership admin may be refused. A refused request returns a `400` or `403` error that says
     * why. If the request returns `503`, send it again to finish; anything already ended stays
     * ended.
     *
     * Every request must include the beta header `anthropic-beta: admin-user-sign-out-2026-10-08`.
     * A request without it returns `400`. Sign-out is in beta. Anthropic turns it on for each
     * organization and has turned it on for every Claude Enterprise organization; until yours is
     * on, requests also return `400`. In a Claude Enterprise organization, an API key needs the
     * `write:members` scope; a key without it gets `403`.
     *
     * @throws com.anthropic.models.beta.organization.users.BetaCannotSignOutPrimaryOwnerException
     * @throws com.anthropic.models.beta.organization.users.BetaCannotSignOutSelfException
     * @throws com.anthropic.models.beta.BetaOrganizationNotEnrolledInBetaException
     * @throws com.anthropic.models.beta.organization.users.BetaCannotSignOutManagedMemberException
     * @throws
     *   com.anthropic.models.beta.organization.users.BetaCannotSignOutMembershipAdminException
     * @throws com.anthropic.models.beta.organization.users.BetaCannotSignOutOwnerException
     * @throws
     *   com.anthropic.models.beta.organization.users.BetaCannotSignOutPrivilegedMemberException
     */
    fun signOut(userId: String): BetaOrganizationUser = signOut(userId, UserSignOutParams.none())

    /** @see signOut */
    fun signOut(
        userId: String,
        params: UserSignOutParams = UserSignOutParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaOrganizationUser = signOut(params.toBuilder().userId(userId).build(), requestOptions)

    /** @see signOut */
    fun signOut(
        userId: String,
        params: UserSignOutParams = UserSignOutParams.none(),
    ): BetaOrganizationUser = signOut(userId, params, RequestOptions.none())

    /** @see signOut */
    fun signOut(
        params: UserSignOutParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaOrganizationUser

    /** @see signOut */
    fun signOut(params: UserSignOutParams): BetaOrganizationUser =
        signOut(params, RequestOptions.none())

    /** @see signOut */
    fun signOut(userId: String, requestOptions: RequestOptions): BetaOrganizationUser =
        signOut(userId, UserSignOutParams.none(), requestOptions)

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
         * Returns a raw HTTP response for `post /v1/organizations/users/{user_id}`, but is
         * otherwise the same as [UserService.update].
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

        /**
         * Returns a raw HTTP response for `post /v1/organizations/users/{user_id}/sign_out`, but is
         * otherwise the same as [UserService.signOut].
         */
        @MustBeClosed
        fun signOut(userId: String): HttpResponseFor<BetaOrganizationUser> =
            signOut(userId, UserSignOutParams.none())

        /** @see signOut */
        @MustBeClosed
        fun signOut(
            userId: String,
            params: UserSignOutParams = UserSignOutParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaOrganizationUser> =
            signOut(params.toBuilder().userId(userId).build(), requestOptions)

        /** @see signOut */
        @MustBeClosed
        fun signOut(
            userId: String,
            params: UserSignOutParams = UserSignOutParams.none(),
        ): HttpResponseFor<BetaOrganizationUser> = signOut(userId, params, RequestOptions.none())

        /** @see signOut */
        @MustBeClosed
        fun signOut(
            params: UserSignOutParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaOrganizationUser>

        /** @see signOut */
        @MustBeClosed
        fun signOut(params: UserSignOutParams): HttpResponseFor<BetaOrganizationUser> =
            signOut(params, RequestOptions.none())

        /** @see signOut */
        @MustBeClosed
        fun signOut(
            userId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaOrganizationUser> =
            signOut(userId, UserSignOutParams.none(), requestOptions)
    }
}

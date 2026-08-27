// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.users

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.organization.UserServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see UserServiceAsync.list */
class UserListPageAsync
private constructor(
    private val service: UserServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: UserListParams,
    private val response: UserListPageResponse,
) : PageAsync<BetaOrganizationUser> {

    /**
     * Delegates to [UserListPageResponse], but gracefully handles missing data.
     *
     * @see UserListPageResponse.data
     */
    fun data(): List<BetaOrganizationUser> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [UserListPageResponse], but gracefully handles missing data.
     *
     * @see UserListPageResponse.hasMore
     */
    fun hasMore(): Optional<Boolean> = response._hasMore().getOptional("has_more")

    /**
     * Delegates to [UserListPageResponse], but gracefully handles missing data.
     *
     * @see UserListPageResponse.firstId
     */
    fun firstId(): Optional<String> = response._firstId().getOptional("first_id")

    /**
     * Delegates to [UserListPageResponse], but gracefully handles missing data.
     *
     * @see UserListPageResponse.lastId
     */
    fun lastId(): Optional<String> = response._lastId().getOptional("last_id")

    override fun items(): List<BetaOrganizationUser> = data()

    override fun hasNextPage(): Boolean {
        if (items().isEmpty()) {
            return false
        }
        if (hasMore().getOrNull() == false) {
            return false
        }

        return if (params.beforeId().isPresent) firstId().isPresent else lastId().isPresent
    }

    fun nextPageParams(): UserListParams {
        if (params.beforeId().isPresent) {
            val previousCursor =
                firstId().getOrNull()
                    ?: throw IllegalStateException("Cannot construct next page params")
            return params.toBuilder().beforeId(previousCursor).build()
        }
        val nextCursor =
            lastId().getOrNull() ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().afterId(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<UserListPageAsync> = service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaOrganizationUser> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): UserListParams = params

    /** The response that this page was parsed from. */
    fun response(): UserListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [UserListPageAsync].
         *
         * The following fields are required:
         * ```java
         * .service()
         * .streamHandlerExecutor()
         * .params()
         * .response()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [UserListPageAsync]. */
    class Builder internal constructor() {

        private var service: UserServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: UserListParams? = null
        private var response: UserListPageResponse? = null

        @JvmSynthetic
        internal fun from(userListPageAsync: UserListPageAsync) = apply {
            service = userListPageAsync.service
            streamHandlerExecutor = userListPageAsync.streamHandlerExecutor
            params = userListPageAsync.params
            response = userListPageAsync.response
        }

        fun service(service: UserServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: UserListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: UserListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [UserListPageAsync].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .service()
         * .streamHandlerExecutor()
         * .params()
         * .response()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): UserListPageAsync =
            UserListPageAsync(
                checkRequired("service", service),
                checkRequired("streamHandlerExecutor", streamHandlerExecutor),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "UserListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}

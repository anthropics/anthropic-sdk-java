package com.anthropic.models.beta.organization.rbacgroups.members

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.rbacgroups.MemberService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see MemberService.list */
class MemberListPage
private constructor(
    private val service: MemberService,
    private val params: MemberListParams,
    private val response: MemberListPageResponse,
) : Page<BetaRbacGroupMember> {

    /**
     * Delegates to [MemberListPageResponse], but gracefully handles missing data.
     *
     * @see MemberListPageResponse.data
     */
    fun data(): List<BetaRbacGroupMember> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [MemberListPageResponse], but gracefully handles missing data.
     *
     * @see MemberListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaRbacGroupMember> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): MemberListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): MemberListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaRbacGroupMember> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): MemberListParams = params

    /** The response that this page was parsed from. */
    fun response(): MemberListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [MemberListPage].
         *
         * The following fields are required:
         * ```java
         * .service()
         * .params()
         * .response()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [MemberListPage]. */
    class Builder internal constructor() {

        private var service: MemberService? = null
        private var params: MemberListParams? = null
        private var response: MemberListPageResponse? = null

        @JvmSynthetic
        internal fun from(memberListPage: MemberListPage) = apply {
            service = memberListPage.service
            params = memberListPage.params
            response = memberListPage.response
        }

        fun service(service: MemberService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: MemberListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: MemberListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [MemberListPage].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .service()
         * .params()
         * .response()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): MemberListPage =
            MemberListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is MemberListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() = "MemberListPage{service=$service, params=$params, response=$response}"
}

package com.anthropic.models.beta.organization.webhooks

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.WebhookService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see WebhookService.list */
class WebhookListPage
private constructor(
    private val service: WebhookService,
    private val params: WebhookListParams,
    private val response: WebhookListPageResponse,
) : Page<BetaOrganizationWebhookEndpoint> {

    /**
     * Delegates to [WebhookListPageResponse], but gracefully handles missing data.
     *
     * @see WebhookListPageResponse.data
     */
    fun data(): List<BetaOrganizationWebhookEndpoint> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [WebhookListPageResponse], but gracefully handles missing data.
     *
     * @see WebhookListPageResponse.hasMore
     */
    fun hasMore(): Optional<Boolean> = response._hasMore().getOptional("has_more")

    /**
     * Delegates to [WebhookListPageResponse], but gracefully handles missing data.
     *
     * @see WebhookListPageResponse.firstId
     */
    fun firstId(): Optional<String> = response._firstId().getOptional("first_id")

    /**
     * Delegates to [WebhookListPageResponse], but gracefully handles missing data.
     *
     * @see WebhookListPageResponse.lastId
     */
    fun lastId(): Optional<String> = response._lastId().getOptional("last_id")

    override fun items(): List<BetaOrganizationWebhookEndpoint> = data()

    override fun hasNextPage(): Boolean {
        if (items().isEmpty()) {
            return false
        }
        if (hasMore().getOrNull() == false) {
            return false
        }

        return if (params.beforeId().isPresent) firstId().isPresent else lastId().isPresent
    }

    fun nextPageParams(): WebhookListParams {
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

    override fun nextPage(): WebhookListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaOrganizationWebhookEndpoint> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): WebhookListParams = params

    /** The response that this page was parsed from. */
    fun response(): WebhookListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [WebhookListPage].
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

    /** A builder for [WebhookListPage]. */
    class Builder internal constructor() {

        private var service: WebhookService? = null
        private var params: WebhookListParams? = null
        private var response: WebhookListPageResponse? = null

        @JvmSynthetic
        internal fun from(webhookListPage: WebhookListPage) = apply {
            service = webhookListPage.service
            params = webhookListPage.params
            response = webhookListPage.response
        }

        fun service(service: WebhookService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: WebhookListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: WebhookListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [WebhookListPage].
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
        fun build(): WebhookListPage =
            WebhookListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is WebhookListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "WebhookListPage{service=$service, params=$params, response=$response}"
}

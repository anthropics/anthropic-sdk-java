package com.anthropic.models.beta.organization.plugins.installationsettings

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.organization.plugins.InstallationSettingServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see InstallationSettingServiceAsync.list */
class InstallationSettingListPageAsync
private constructor(
    private val service: InstallationSettingServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: InstallationSettingListParams,
    private val response: InstallationSettingListPageResponse,
) : PageAsync<BetaPluginInstallationSetting> {

    /**
     * Delegates to [InstallationSettingListPageResponse], but gracefully handles missing data.
     *
     * @see InstallationSettingListPageResponse.data
     */
    fun data(): List<BetaPluginInstallationSetting> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [InstallationSettingListPageResponse], but gracefully handles missing data.
     *
     * @see InstallationSettingListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaPluginInstallationSetting> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): InstallationSettingListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<InstallationSettingListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaPluginInstallationSetting> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): InstallationSettingListParams = params

    /** The response that this page was parsed from. */
    fun response(): InstallationSettingListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [InstallationSettingListPageAsync].
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

    /** A builder for [InstallationSettingListPageAsync]. */
    class Builder internal constructor() {

        private var service: InstallationSettingServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: InstallationSettingListParams? = null
        private var response: InstallationSettingListPageResponse? = null

        @JvmSynthetic
        internal fun from(installationSettingListPageAsync: InstallationSettingListPageAsync) =
            apply {
                service = installationSettingListPageAsync.service
                streamHandlerExecutor = installationSettingListPageAsync.streamHandlerExecutor
                params = installationSettingListPageAsync.params
                response = installationSettingListPageAsync.response
            }

        fun service(service: InstallationSettingServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: InstallationSettingListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: InstallationSettingListPageResponse) = apply {
            this.response = response
        }

        /**
         * Returns an immutable instance of [InstallationSettingListPageAsync].
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
        fun build(): InstallationSettingListPageAsync =
            InstallationSettingListPageAsync(
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

        return other is InstallationSettingListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "InstallationSettingListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}

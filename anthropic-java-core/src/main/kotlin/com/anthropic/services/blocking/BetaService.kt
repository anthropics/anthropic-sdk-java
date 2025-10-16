// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.FileService
import com.anthropic.services.blocking.beta.MessageService
import com.anthropic.services.blocking.beta.ModelService
import com.anthropic.services.blocking.beta.SkillService
import java.util.function.Consumer

interface BetaService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): BetaService

    fun models(): ModelService

    fun messages(): MessageService

    fun files(): FileService

    fun skills(): SkillService

    /** A view of [BetaService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): BetaService.WithRawResponse

        fun models(): ModelService.WithRawResponse

        fun messages(): MessageService.WithRawResponse

        fun files(): FileService.WithRawResponse

        fun skills(): SkillService.WithRawResponse
    }
}

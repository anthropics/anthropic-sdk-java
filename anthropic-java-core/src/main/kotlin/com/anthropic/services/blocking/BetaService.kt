// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking

import com.anthropic.services.blocking.beta.FileService
import com.anthropic.services.blocking.beta.MessageService
import com.anthropic.services.blocking.beta.ModelService

interface BetaService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    fun models(): ModelService

    fun messages(): MessageService

    fun files(): FileService

    /** A view of [BetaService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        fun models(): ModelService.WithRawResponse

        fun messages(): MessageService.WithRawResponse

        fun files(): FileService.WithRawResponse
    }
}

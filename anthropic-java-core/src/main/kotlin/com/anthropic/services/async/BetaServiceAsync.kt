// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import com.anthropic.services.async.beta.MessageServiceAsync
import com.anthropic.services.async.beta.ModelServiceAsync

interface BetaServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    fun models(): ModelServiceAsync

    fun messages(): MessageServiceAsync

    /** A view of [BetaServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        fun models(): ModelServiceAsync.WithRawResponse

        fun messages(): MessageServiceAsync.WithRawResponse
    }
}

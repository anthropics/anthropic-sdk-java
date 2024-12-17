// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import com.anthropic.services.async.beta.MessageServiceAsync
import com.anthropic.services.async.beta.ModelServiceAsync

interface BetaServiceAsync {

    fun models(): ModelServiceAsync

    fun messages(): MessageServiceAsync
}

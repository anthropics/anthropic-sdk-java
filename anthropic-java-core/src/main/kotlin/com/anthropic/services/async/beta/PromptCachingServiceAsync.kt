// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.services.async.beta.promptCaching.MessageServiceAsync

interface PromptCachingServiceAsync {

    fun messages(): MessageServiceAsync
}

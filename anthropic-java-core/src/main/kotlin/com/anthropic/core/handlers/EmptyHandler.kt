@file:JvmName("EmptyHandler")

package com.anthropic.core.handlers

import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler

@JvmSynthetic internal fun emptyHandler(): Handler<Void?> = EmptyHandlerInternal

private object EmptyHandlerInternal : Handler<Void?> {
    override fun handle(response: HttpResponse): Void? = null
}

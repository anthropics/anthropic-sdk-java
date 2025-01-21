@file:JvmName("JsonlHandler")

package com.anthropic.core.handlers

import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.StreamResponse
import com.anthropic.errors.AnthropicException
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef

@JvmSynthetic
internal inline fun <reified T> jsonlHandler(jsonMapper: JsonMapper): Handler<StreamResponse<T>> =
    streamHandler { lines ->
        for (line in lines) {
            val value =
                try {
                    jsonMapper.readValue(line, jacksonTypeRef<T>())
                } catch (e: Exception) {
                    throw AnthropicException("Error reading response", e)
                }
            yield(value)
        }
    }

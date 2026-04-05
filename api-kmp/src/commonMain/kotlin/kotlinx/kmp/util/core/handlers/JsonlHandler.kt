
package kotlinx.kmp.util.core.handlers

import kotlinx.kmp.util.core.http.HttpResponse.Handler
import kotlinx.kmp.util.core.http.StreamResponse
import kotlinx.kmp.util.core.errors.AnthropicException
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef

inline fun <reified T> jsonlHandler(jsonMapper: JsonMapper): Handler<StreamResponse<T>> =
    streamHandler { _, lines ->
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

package com.anthropic.core.http

import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.databind.JsonNode
import java.io.ByteArrayInputStream
import java.io.InputStream
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class HttpResponseTest {

    @Test
    fun json_parsesBody() {
        val response = FakeHttpResponse("""{"hello":"world"}""")

        val json = response.json(JsonNode::class.java)

        assertThat(json.path("hello").asText()).isEqualTo("world")
    }

    @Test
    fun json_whenBodyIsNotJson_throws() {
        val response = FakeHttpResponse("not json")

        assertThatThrownBy { response.json(JsonNode::class.java) }
            .isInstanceOf(AnthropicInvalidDataException::class.java)
    }

    @Test
    fun buffered_bodyIsReadRepeatedly() {
        val response = FakeHttpResponse("hello")

        val buffered = response.buffered()

        assertThat(buffered.statusCode()).isEqualTo(200)
        assertThat(buffered.body().readBytes().decodeToString()).isEqualTo("hello")
        assertThat(buffered.body().readBytes().decodeToString()).isEqualTo("hello")
    }

    @Test
    fun buffered_readsBodyLazilyAndOnce() {
        val response = FakeHttpResponse("hello")

        val buffered = response.buffered()

        assertThat(response.bodyCalls).isEqualTo(0)
        buffered.body()
        buffered.body()
        assertThat(response.bodyCalls).isEqualTo(1)
    }

    @Test
    fun buffered_ofBufferedResponse_returnsSameResponse() {
        val buffered = FakeHttpResponse("hello").buffered()

        assertThat(buffered.buffered()).isSameAs(buffered)
    }

    @Test
    fun buffered_closeClosesOriginalResponse() {
        val response = FakeHttpResponse("hello")

        response.buffered().close()

        assertThat(response.closed).isTrue()
    }

    private class FakeHttpResponse(private val bodyText: String) : HttpResponse {
        var bodyCalls = 0
        var closed = false

        override fun statusCode(): Int = 200

        override fun headers(): Headers = Headers.builder().build()

        override fun body(): InputStream {
            bodyCalls++
            return ByteArrayInputStream(bodyText.toByteArray())
        }

        override fun close() {
            closed = true
        }
    }
}

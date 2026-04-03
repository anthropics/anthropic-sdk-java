package com.anthropic.core

import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class RequestOptionsTest {

    @Test
    fun `timeoutFromMaxTokensStreaming should scale based on token count`() {
        // Test with minimum tokens
        val minOptions = RequestOptions.builder().timeoutFromMaxTokensStreaming(0).build()
        assertThat(minOptions.timeout!!.request()).isEqualTo(10.minutes)

        // Test with token count that should increase timeout
        val medOptions = RequestOptions.builder().timeoutFromMaxTokensStreaming(128_000).build()
        assertThat(medOptions.timeout!!.request()).isEqualTo(60.minutes)

        // Test with very large token count (should be capped at max)
        val maxOptions = RequestOptions.builder().timeoutFromMaxTokensStreaming(1_000_000).build()
        assertThat(maxOptions.timeout!!.request()).isEqualTo(60.minutes)
    }

    @Test
    fun `timeoutFromMaxTokensNonStreaming should scale based on token count`() {
        // Test with minimum tokens
        val minOptions = RequestOptions.builder().timeoutFromMaxTokensNonStreaming(0).build()
        assertThat(minOptions.timeout!!.request()).isEqualTo(30.seconds)

        // Test with token count that should increase timeout
        val medOptions = RequestOptions.builder().timeoutFromMaxTokensNonStreaming(5000).build()
        assertThat(medOptions.timeout!!.request()).isEqualTo(150.seconds)

        // Test with very large token count (should be capped at max)
        val maxOptions = RequestOptions.builder().timeoutFromMaxTokensNonStreaming(100_000).build()
        assertThat(maxOptions.timeout!!.request()).isEqualTo(10.minutes)
    }

    @Test
    fun `applyDefaultTimeoutFromMaxTokens should use streaming timeouts for streaming requests`() {
        val requestOptions =
            RequestOptions.none().applyDefaultTimeoutFromMaxTokens(5000, isStreaming = true)

        // Should be based on streaming calculation (which would be 10 minutes for this token count)
        assertThat(requestOptions.timeout!!.request()).isEqualTo(10.minutes)
    }

    @Test
    fun `applyDefaultTimeoutFromMaxTokens should use non-streaming timeouts for non-streaming requests`() {
        val requestOptions =
            RequestOptions.none().applyDefaultTimeoutFromMaxTokens(5000, isStreaming = false)

        // Should be based on non-streaming calculation (150 seconds for 5000 tokens)
        assertThat(requestOptions.timeout!!.request()).isEqualTo(150.seconds)
    }

    @Test
    fun `applyDefaultTimeoutFromMaxTokens should not override custom timeouts`() {
        val customTimeout = Timeout.builder().request(42.seconds).build()
        val options = RequestOptions.builder().timeout(customTimeout).build()

        val requestOptions = options.applyDefaultTimeoutFromMaxTokens(5000, isStreaming = false)

        // Should keep the custom timeout
        assertThat(requestOptions.timeout!!.request()).isEqualTo(42.seconds)
    }

    @Test
    fun `applyDefaultTimeoutFromMaxTokens should enforce streaming for models with token limits`() {
        // Use a model with a defined token limit
        val model = "claude-opus-4-20250514"
        val tokenLimit = 8_192L // Hard-coded to avoid reflection

        // Should not throw when under the limit
        assertThatCode {
                RequestOptions.none()
                    .applyDefaultTimeoutFromMaxTokens(
                        tokenLimit - 1L,
                        isStreaming = false,
                        model = model,
                    )
            }
            .doesNotThrowAnyException()

        // Should throw when exceeding the model's token limit
        assertThatThrownBy {
                RequestOptions.none()
                    .applyDefaultTimeoutFromMaxTokens(
                        tokenLimit + 1L,
                        isStreaming = false,
                        model = model,
                    )
            }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Streaming is required")
    }

    @Test
    fun `applyDefaultTimeoutFromMaxTokens should not enforce streaming for unknown models`() {
        // Use a model not in the MODEL_NONSTREAMING_TOKENS map
        val model = "unknown-model"

        // Should not throw for reasonable token counts
        assertThatCode {
                RequestOptions.none()
                    .applyDefaultTimeoutFromMaxTokens(5000, isStreaming = false, model = model)
            }
            .doesNotThrowAnyException()
    }
}

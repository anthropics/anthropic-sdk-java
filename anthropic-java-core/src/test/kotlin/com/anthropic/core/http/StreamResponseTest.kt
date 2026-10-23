package com.anthropic.core.http

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class StreamResponseTest {

    @Test
    fun iterator_yieldsStreamElements() {
        val streamResponse =
            object : StreamResponse<Int> {

                override fun stream(): Stream<Int> = Stream.of(0, 1, 2)

                override fun close() {}
            }

        val elements = streamResponse.toList()

        assertThat(elements).containsExactly(0, 1, 2)
    }
}

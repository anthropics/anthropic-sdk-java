package com.anthropic.core.http

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class HttpRequestTest {
    @Test
    fun replaceAllPathSegmentsNoneWithNone() {
        val request1 = createRequest()

        assertThat(request1.pathSegments.size).isEqualTo(0)

        val request2 = request1.toBuilder().replaceAllPathSegments().build()

        assertThat(request2.pathSegments.size).isEqualTo(0)
    }

    @Test
    fun replaceAllPathSegmentsSomeWithNone() {
        val request1 = createRequest("s1", "s2")

        // The correct construction of the request test fixture is checked once
        // here for sanity. Elsewhere, these checks are not repeated.
        assertThat(request1.pathSegments.size).isEqualTo(2)
        assertThat(request1.pathSegments[0]).isEqualTo("s1")
        assertThat(request1.pathSegments[1]).isEqualTo("s2")

        val request2 = request1.toBuilder().replaceAllPathSegments().build()

        assertThat(request2.pathSegments.size).isEqualTo(0)
    }

    @Test
    fun replaceAllPathSegmentsSomeWithSome() {
        val request =
            createRequest("s1", "s2").toBuilder().replaceAllPathSegments("s3", "s4").build()

        assertThat(request.pathSegments.size).isEqualTo(2)
        assertThat(request.pathSegments[0]).isEqualTo("s3")
        assertThat(request.pathSegments[1]).isEqualTo("s4")
    }

    @Test
    fun replaceAllPathSegmentsWhileBuilding() {
        val request =
            createRequest()
                .toBuilder()
                .addPathSegments("s1", "s2")
                .replaceAllPathSegments("s3", "s4")
                .addPathSegments("s5", "s6")
                .build()

        assertThat(request.pathSegments.size).isEqualTo(4)
        assertThat(request.pathSegments[0]).isEqualTo("s3")
        assertThat(request.pathSegments[1]).isEqualTo("s4")
        assertThat(request.pathSegments[2]).isEqualTo("s5")
        assertThat(request.pathSegments[3]).isEqualTo("s6")
    }

    private fun createRequest(vararg pathSegments: String): HttpRequest {
        return HttpRequest.builder()
            .method(HttpMethod.POST) // A method is required.
            .addPathSegments(*pathSegments)
            .build()
    }
}

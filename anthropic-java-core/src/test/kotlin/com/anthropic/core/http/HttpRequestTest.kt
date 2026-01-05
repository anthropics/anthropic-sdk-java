<<<<<<< HEAD
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
||||||| parent of 29ddc4f8 (feat(client): add `HttpRequest#url()` method)
=======
package com.anthropic.core.http

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class HttpRequestTest {

    enum class UrlTestCase(val request: HttpRequest, val expectedUrl: String) {
        BASE_URL_ONLY(
            HttpRequest.builder().method(HttpMethod.GET).baseUrl("https://api.example.com").build(),
            expectedUrl = "https://api.example.com",
        ),
        BASE_URL_WITH_TRAILING_SLASH(
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .baseUrl("https://api.example.com/")
                .build(),
            expectedUrl = "https://api.example.com/",
        ),
        SINGLE_PATH_SEGMENT(
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .baseUrl("https://api.example.com")
                .addPathSegment("users")
                .build(),
            expectedUrl = "https://api.example.com/users",
        ),
        MULTIPLE_PATH_SEGMENTS(
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .baseUrl("https://api.example.com")
                .addPathSegments("users", "123", "profile")
                .build(),
            expectedUrl = "https://api.example.com/users/123/profile",
        ),
        PATH_SEGMENT_WITH_SPECIAL_CHARS(
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .baseUrl("https://api.example.com")
                .addPathSegment("user name")
                .build(),
            expectedUrl = "https://api.example.com/user+name",
        ),
        SINGLE_QUERY_PARAM(
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .baseUrl("https://api.example.com")
                .addPathSegment("users")
                .putQueryParam("limit", "10")
                .build(),
            expectedUrl = "https://api.example.com/users?limit=10",
        ),
        MULTIPLE_QUERY_PARAMS(
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .baseUrl("https://api.example.com")
                .addPathSegment("users")
                .putQueryParam("limit", "10")
                .putQueryParam("offset", "20")
                .build(),
            expectedUrl = "https://api.example.com/users?limit=10&offset=20",
        ),
        QUERY_PARAM_WITH_SPECIAL_CHARS(
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .baseUrl("https://api.example.com")
                .addPathSegment("search")
                .putQueryParam("q", "hello world")
                .build(),
            expectedUrl = "https://api.example.com/search?q=hello+world",
        ),
        MULTIPLE_VALUES_SAME_PARAM(
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .baseUrl("https://api.example.com")
                .addPathSegment("users")
                .putQueryParams("tags", listOf("admin", "user"))
                .build(),
            expectedUrl = "https://api.example.com/users?tags=admin&tags=user",
        ),
        BASE_URL_WITH_TRAILING_SLASH_AND_PATH(
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .baseUrl("https://api.example.com/")
                .addPathSegment("users")
                .build(),
            expectedUrl = "https://api.example.com/users",
        ),
        COMPLEX_URL(
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://api.example.com")
                .addPathSegments("v1", "users", "123")
                .putQueryParams("include", listOf("profile", "settings"))
                .putQueryParam("format", "json")
                .build(),
            expectedUrl =
                "https://api.example.com/v1/users/123?include=profile&include=settings&format=json",
        ),
    }

    @ParameterizedTest
    @EnumSource
    fun url(testCase: UrlTestCase) {
        val actualUrl = testCase.request.url()

        assertThat(actualUrl).isEqualTo(testCase.expectedUrl)
    }
}
>>>>>>> 29ddc4f8 (feat(client): add `HttpRequest#url()` method)

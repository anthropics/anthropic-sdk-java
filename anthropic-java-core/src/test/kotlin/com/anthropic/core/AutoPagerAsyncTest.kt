package com.anthropic.core

import com.anthropic.core.http.AsyncStreamResponse
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.RejectedExecutionException
import java.util.concurrent.TimeUnit
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.spy
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
internal class AutoPagerAsyncTest {

    companion object {

        private val ERROR = RuntimeException("ERROR!")
    }

    private class PageAsyncImpl(
        private val items: List<String>,
        private val hasNext: Boolean = true,
    ) : PageAsync<String> {

        val nextPageFuture: CompletableFuture<PageAsync<String>> = CompletableFuture()

        override fun hasNextPage(): Boolean = hasNext

        override fun nextPage(): CompletableFuture<PageAsync<String>> = nextPageFuture

        override fun items(): List<String> = items
    }

    private val executor =
        spy<Executor> {
            doAnswer { invocation -> invocation.getArgument<Runnable>(0).run() }
                .whenever(it)
                .execute(any())
        }
    private val handler = mock<AsyncStreamResponse.Handler<String>>()

    @Test
    fun subscribe_whenAlreadySubscribed_throws() {
        val autoPagerAsync = AutoPagerAsync.from(PageAsyncImpl(emptyList()), executor)
        autoPagerAsync.subscribe {}
        clearInvocations(executor)

        val throwable = catchThrowable { autoPagerAsync.subscribe {} }

        assertThat(throwable).isInstanceOf(IllegalStateException::class.java)
        assertThat(throwable).hasMessage("Cannot subscribe more than once")
        verify(executor, never()).execute(any())
    }

    @Test
    fun subscribe_whenClosed_throws() {
        val autoPagerAsync = AutoPagerAsync.from(PageAsyncImpl(emptyList()), executor)
        autoPagerAsync.close()

        val throwable = catchThrowable { autoPagerAsync.subscribe {} }

        assertThat(throwable).isInstanceOf(IllegalStateException::class.java)
        assertThat(throwable).hasMessage("Cannot subscribe after the response is closed")
        verify(executor, never()).execute(any())
    }

    @Test
    fun subscribe_whenFirstPageNonEmpty_runsHandler() {
        val page = PageAsyncImpl(listOf("item1", "item2", "item3"), hasNext = false)
        val autoPagerAsync = AutoPagerAsync.from(page, executor)

        autoPagerAsync.subscribe(handler)

        inOrder(executor, handler) {
            verify(executor, times(1)).execute(any())
            verify(handler, times(1)).onNext("item1")
            verify(handler, times(1)).onNext("item2")
            verify(handler, times(1)).onNext("item3")
            verify(handler, times(1)).onComplete(Optional.empty())
        }
    }

    @Test
    fun subscribe_whenFutureCompletesAfterClose_doesNothing() {
        val page = PageAsyncImpl(listOf("page1"))
        val autoPagerAsync = AutoPagerAsync.from(page, executor)
        autoPagerAsync.subscribe(handler)
        autoPagerAsync.close()

        page.nextPageFuture.complete(PageAsyncImpl(listOf("page2")))

        verify(handler, times(1)).onNext("page1")
        verify(handler, never()).onNext("page2")
        verify(handler, times(1)).onComplete(Optional.empty())
    }

    @Test
    fun subscribe_whenFutureErrors_callsOnComplete() {
        val page = PageAsyncImpl(emptyList())
        val autoPagerAsync = AutoPagerAsync.from(page, executor)
        autoPagerAsync.subscribe(handler)

        page.nextPageFuture.completeExceptionally(ERROR)

        verify(handler, never()).onNext(any())
        verify(handler, times(1)).onComplete(Optional.of(ERROR))
    }

    @Test
    fun subscribe_whenExecutorRejectsWork_completesOnCompleteFuture() {
        var isShutDown = false
        val shuttingDownExecutor = Executor {
            if (isShutDown) throw RejectedExecutionException() else it.run()
        }
        val page = PageAsyncImpl(listOf("page1"))
        val autoPagerAsync = AutoPagerAsync.from(page, shuttingDownExecutor)
        autoPagerAsync.subscribe(handler)
        isShutDown = true

        page.nextPageFuture.complete(PageAsyncImpl(listOf("page2"), hasNext = false))

        assertThat(autoPagerAsync.onCompleteFuture()).isCompletedExceptionally()
        verify(handler, never()).onNext("page2")
    }

    @Test
    fun subscribe_whenFutureCompletes_runsHandler() {
        val page = PageAsyncImpl(listOf("chunk1", "chunk2"))
        val autoPagerAsync = AutoPagerAsync.from(page, executor)

        autoPagerAsync.subscribe(handler)

        verify(handler, never()).onComplete(any())
        inOrder(executor, handler) {
            verify(executor, times(1)).execute(any())
            verify(handler, times(1)).onNext("chunk1")
            verify(handler, times(1)).onNext("chunk2")
        }
        clearInvocations(executor, handler)

        page.nextPageFuture.complete(PageAsyncImpl(listOf("chunk3", "chunk4"), hasNext = false))

        inOrder(executor, handler) {
            verify(executor, times(1)).execute(any())
            verify(handler, times(1)).onNext("chunk3")
            verify(handler, times(1)).onNext("chunk4")
            verify(handler, times(1)).onComplete(Optional.empty())
        }
    }

    @Test
    fun onCompleteFuture_whenNextPageFutureNotCompleted_onCompleteFutureNotCompleted() {
        val page = PageAsyncImpl(listOf("chunk1", "chunk2"))
        val autoPagerAsync = AutoPagerAsync.from(page, executor)
        autoPagerAsync.subscribe {}

        val onCompletableFuture = autoPagerAsync.onCompleteFuture()

        assertThat(onCompletableFuture).isNotCompleted
    }

    @Test
    fun onCompleteFuture_whenNextPageFutureErrors_onCompleteFutureCompletedExceptionally() {
        val page = PageAsyncImpl(listOf("chunk1", "chunk2"))
        val autoPagerAsync = AutoPagerAsync.from(page, executor)
        autoPagerAsync.subscribe {}
        page.nextPageFuture.completeExceptionally(ERROR)

        val onCompletableFuture = autoPagerAsync.onCompleteFuture()

        assertThat(onCompletableFuture).isCompletedExceptionally
    }

    @Test
    fun onCompleteFuture_whenNoNextPage_onCompleteFutureCompleted() {
        val page = PageAsyncImpl(listOf("chunk1", "chunk2"), hasNext = false)
        val autoPagerAsync = AutoPagerAsync.from(page, executor)
        autoPagerAsync.subscribe {}

        val onCompletableFuture = autoPagerAsync.onCompleteFuture()

        assertThat(onCompletableFuture).isCompleted
    }

    @Test
    fun closeFromOnNext_stopsTheCurrentPageAndDoesNotFetchAnother() {
        for (closeAt in listOf("first", "second", "third")) {
            val page = spy(PageAsyncImpl(listOf("first", "second", "third")))
            val pager = AutoPagerAsync.from(page, executor)
            val received = mutableListOf<String>()
            val completed = mutableListOf<Optional<Throwable>>()
            pager.subscribe(
                object : AsyncStreamResponse.Handler<String> {
                    override fun onNext(value: String) {
                        received.add(value)
                        if (value == closeAt) pager.close()
                    }

                    override fun onComplete(error: Optional<Throwable>) {
                        completed.add(error)
                    }
                }
            )
            assertThat(received)
                .containsExactlyElementsOf(
                    listOf("first", "second", "third").takeWhile { it != closeAt } + closeAt
                )
            verify(page, never()).nextPage()
            assertThat(completed).containsExactly(Optional.empty())
            assertThat(pager.onCompleteFuture()).isCompleted
            pager.close()
            assertThat(completed).hasSize(1)
        }
    }

    @Test
    fun closeDuringFollowingPage_stopsDeliveryWithoutFetchingThirdPage() {
        val first = PageAsyncImpl(listOf("page-one"))
        val second = spy(PageAsyncImpl(listOf("stop", "unwanted")))
        val pager = AutoPagerAsync.from(first, executor)
        val received = mutableListOf<String>()
        val completed = mutableListOf<Optional<Throwable>>()
        pager.subscribe(
            object : AsyncStreamResponse.Handler<String> {
                override fun onNext(value: String) {
                    received.add(value)
                    if (value == "stop") pager.close()
                }

                override fun onComplete(error: Optional<Throwable>) {
                    completed.add(error)
                }
            }
        )
        first.nextPageFuture.complete(second)
        assertThat(received).containsExactly("page-one", "stop")
        verify(second, never()).nextPage()
        assertThat(completed).containsExactly(Optional.empty())
    }

    @Test
    fun closeFromAnotherThread_doesNotDeliverRemainingBufferedItems() {
        val pool = Executors.newSingleThreadExecutor()
        val entered = CountDownLatch(1)
        val release = CountDownLatch(1)
        val completed = CountDownLatch(1)
        val received = mutableListOf<String>()
        val page = spy(PageAsyncImpl(listOf("first", "unwanted")))
        val pager = AutoPagerAsync.from(page, pool)
        try {
            pager.subscribe(
                object : AsyncStreamResponse.Handler<String> {
                    override fun onNext(value: String) {
                        received.add(value)
                        entered.countDown()
                        check(release.await(5, TimeUnit.SECONDS))
                    }

                    override fun onComplete(error: Optional<Throwable>) {
                        completed.countDown()
                    }
                }
            )
            assertThat(entered.await(5, TimeUnit.SECONDS)).isTrue()
            pager.close()
            release.countDown()
            assertThat(completed.await(5, TimeUnit.SECONDS)).isTrue()
            assertThat(received).containsExactly("first")
            verify(page, never()).nextPage()
        } finally {
            release.countDown()
            pager.close()
            pool.shutdownNow()
            pool.awaitTermination(5, TimeUnit.SECONDS)
        }
    }
}

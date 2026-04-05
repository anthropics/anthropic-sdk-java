// File generated from our OpenAPI spec by Stainless.

package com.anthropic.core

import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

/** A class containing timeouts for various processing phases of a request. */
class Timeout
private constructor(
    private val connect: Duration?,
    private val read: Duration?,
    private val write: Duration?,
    private val request: Duration?,
) {

    /**
     * The maximum time allowed to establish a connection with a host.
     *
     * A value of [Duration.ZERO] means there's no timeout.
     *
     * Defaults to `1.minutes`.
     */
    fun connect(): Duration = connect ?: 1.minutes

    /**
     * The maximum time allowed between two data packets when waiting for the server’s response.
     *
     * A value of [Duration.ZERO] means there's no timeout.
     *
     * Defaults to `request()`.
     */
    fun read(): Duration = read ?: request()

    /**
     * The maximum time allowed between two data packets when sending the request to the server.
     *
     * A value of [Duration.ZERO] means there's no timeout.
     *
     * Defaults to `request()`.
     */
    fun write(): Duration = write ?: request()

    /**
     * The maximum time allowed for a complete HTTP call, not including retries.
     *
     * This includes resolving DNS, connecting, writing the request body, server processing, as well
     * as reading the response body.
     *
     * A value of [Duration.ZERO] means there's no timeout.
     *
     * Defaults to `10.minutes`.
     */
    fun request(): Duration = request ?: 10.minutes

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun default() = builder().build()

        /** Returns a mutable builder for constructing an instance of [Timeout]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [Timeout]. */
    class Builder internal constructor() {

        private var connect: Duration? = null
        private var read: Duration? = null
        private var write: Duration? = null
        private var request: Duration? = null

        @JvmSynthetic fun from(timeout: Timeout) = apply {
            connect = timeout.connect
            read = timeout.read
            write = timeout.write
            request = timeout.request
        }

        /**
         * The maximum time allowed to establish a connection with a host.
         *
         * A value of [Duration.ZERO] means there's no timeout.
         *
         * Defaults to `1.minutes`.
         */
        fun connect(connect: Duration?) = apply { this.connect = connect }


        /**
         * The maximum time allowed between two data packets when waiting for the server’s response.
         *
         * A value of [Duration.ZERO] means there's no timeout.
         *
         * Defaults to `request()`.
         */
        fun read(read: Duration?) = apply { this.read = read }


        /**
         * The maximum time allowed between two data packets when sending the request to the server.
         *
         * A value of [Duration.ZERO] means there's no timeout.
         *
         * Defaults to `request()`.
         */
        fun write(write: Duration?) = apply { this.write = write }


        /**
         * The maximum time allowed for a complete HTTP call, not including retries.
         *
         * This includes resolving DNS, connecting, writing the request body, server processing, as
         * well as reading the response body.
         *
         * A value of [Duration.ZERO] means there's no timeout.
         *
         * Defaults to `10.minutes`.
         */
        fun request(request: Duration?) = apply { this.request = request }


        /**
         * Returns an immutable instance of [Timeout].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): Timeout = Timeout(connect, read, write, request)
    }

    fun assign(target: Timeout): Timeout =
        target
            .toBuilder()
            .apply {
                connect?.let(this::connect)
                read?.let(this::read)
                write?.let(this::write)
                request?.let(this::request)
            }
            .build()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is Timeout &&
            connect == other.connect &&
            read == other.read &&
            write == other.write &&
            request == other.request
    }

    override fun hashCode(): Int = contentHash(connect, read, write, request)

    override fun toString() =
        "Timeout{connect=$connect, read=$read, write=$write, request=$request}"
}

// File generated from our OpenAPI spec by Stainless.

package com.anthropic.core

import java.time.Duration
import java.util.Objects

/** A class containing timeouts for various processing phases of a request. */
class Timeout
private constructor(
    /**
     * The maximum time allowed to establish a connection with a host.
     *
     * A value of [Duration.ZERO] means there's no timeout.
     */
    @get:JvmName("connect") val connect: Duration,
    /**
     * The maximum time allowed between two data packets when waiting for the server’s response.
     *
     * A value of [Duration.ZERO] means there's no timeout.
     */
    @get:JvmName("read") val read: Duration,
    /**
     * The maximum time allowed between two data packets when sending the request to the server.
     *
     * A value of [Duration.ZERO] means there's no timeout.
     */
    @get:JvmName("write") val write: Duration,
    /**
     * The maximum time allowed for a complete HTTP call.
     *
     * This includes resolving DNS, connecting, writing the request body, server processing, as well
     * as reading the response body.
     *
     * A value of [Duration.ZERO] means there's no timeout.
     */
    @get:JvmName("total") val total: Duration,
) {

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun default() = builder().build()

        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [Timeout]. */
    class Builder internal constructor() {

        private var connect = Duration.ofMinutes(1)
        private var read = Duration.ofMinutes(1)
        private var write = Duration.ofMinutes(1)
        private var total = Duration.ofMinutes(10)

        @JvmSynthetic
        internal fun from(timeout: Timeout) = apply {
            connect = timeout.connect
            read = timeout.read
            write = timeout.write
            total = timeout.total
        }

        /**
         * The maximum time allowed to establish a connection with a host.
         *
         * A value of [Duration.ZERO] means there's no timeout.
         *
         * Defaults to 1 minute.
         */
        fun connect(connect: Duration) = apply { this.connect = connect }

        /**
         * The maximum time allowed between two data packets when waiting for the server’s response.
         *
         * A value of [Duration.ZERO] means there's no timeout.
         *
         * Defaults to 1 minute.
         */
        fun read(read: Duration) = apply { this.read = read }

        /**
         * The maximum time allowed between two data packets when sending the request to the server.
         *
         * A value of [Duration.ZERO] means there's no timeout.
         *
         * Defaults to 1 minute.
         */
        fun write(write: Duration) = apply { this.write = write }

        /**
         * The maximum time allowed for a complete HTTP call.
         *
         * This includes resolving DNS, connecting, writing the request body, server processing, as
         * well as reading the response body.
         *
         * A value of [Duration.ZERO] means there's no timeout.
         *
         * Defaults to 10 minutes.
         */
        fun total(total: Duration) = apply { this.total = total }

        fun build(): Timeout = Timeout(connect, read, write, total)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is Timeout && connect == other.connect && read == other.read && write == other.write && total == other.total /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(connect, read, write, total) /* spotless:on */

    override fun toString() = "Timeout{connect=$connect, read=$read, write=$write, total=$total}"
}

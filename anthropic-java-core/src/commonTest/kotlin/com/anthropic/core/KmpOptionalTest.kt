package com.anthropic.core

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.test.assertFailsWith

class KmpOptionalTest {

    // ========================================================================
    // Factory functions
    // ========================================================================

    @Test
    fun optionalOf_wrapsNonNullValue() {
        val opt = optionalOf("hello")
        assertTrue(opt.isPresent)
        assertEquals("hello", opt.get())
    }

    @Test
    fun optionalOfNullable_withValue_isPresent() {
        val opt = optionalOfNullable("value")
        assertTrue(opt.isPresent)
        assertEquals("value", opt.get())
    }

    @Test
    fun optionalOfNullable_withNull_isEmpty() {
        val opt = optionalOfNullable<String>(null)
        assertFalse(opt.isPresent)
    }

    @Test
    fun emptyOptional_isEmpty() {
        val opt = emptyOptional<String>()
        assertFalse(opt.isPresent)
    }

    // ========================================================================
    // Core conversions — Optional ↔ nullable
    // ========================================================================

    @Test
    fun orNull_withValue_returnsValue() {
        assertEquals("hello", optionalOf("hello").orNull())
    }

    @Test
    fun orNull_withEmpty_returnsNull() {
        assertNull(emptyOptional<String>().orNull())
    }

    @Test
    fun toOptional_withNonNull_isPresent() {
        val opt = "hello".toOptional()
        assertTrue(opt.isPresent)
        assertEquals("hello", opt.get())
    }

    @Test
    fun toOptional_withNull_isEmpty() {
        val opt: KmpOptional<String> = (null as String?).toOptional()
        assertFalse(opt.isPresent)
    }

    @Test
    fun component1_destructuring() {
        val (value) = optionalOf("hello")
        assertEquals("hello", value)

        val (empty) = emptyOptional<String>()
        assertNull(empty)
    }

    // ========================================================================
    // Presence/absence checks
    // ========================================================================

    @Test
    fun isEmpty_andIsNotEmpty() {
        assertTrue(emptyOptional<String>().isEmpty)
        assertFalse(emptyOptional<String>().isNotEmpty)
        assertFalse(optionalOf("x").isEmpty)
        assertTrue(optionalOf("x").isNotEmpty)
    }

    // ========================================================================
    // Functional transformations
    // ========================================================================

    @Test
    fun fold_withValue_callsIfPresent() {
        val result = optionalOf(42).fold(ifEmpty = { "empty" }, ifPresent = { "value=$it" })
        assertEquals("value=42", result)
    }

    @Test
    fun fold_withEmpty_callsIfEmpty() {
        val result = emptyOptional<Int>().fold(ifEmpty = { "empty" }, ifPresent = { "value=$it" })
        assertEquals("empty", result)
    }

    @Test
    fun getOrElse_withValue_returnsValue() {
        assertEquals(42, optionalOf(42).getOrElse { 0 })
    }

    @Test
    fun getOrElse_withEmpty_returnsDefault() {
        assertEquals(0, emptyOptional<Int>().getOrElse { 0 })
    }

    @Test
    fun require_withValue_returnsValue() {
        assertEquals("ok", optionalOf("ok").require("should not throw"))
    }

    @Test
    fun require_withEmpty_throws() {
        assertFailsWith<IllegalStateException> {
            emptyOptional<String>().require("missing!")
        }
    }

    // ========================================================================
    // Chaining callbacks
    // ========================================================================

    @Test
    fun onPresent_executesWhenPresent() {
        var called = false
        optionalOf("x").onPresent { called = true }
        assertTrue(called)
    }

    @Test
    fun onPresent_skipsWhenEmpty() {
        var called = false
        emptyOptional<String>().onPresent { called = true }
        assertFalse(called)
    }

    @Test
    fun onEmpty_executesWhenEmpty() {
        var called = false
        emptyOptional<String>().onEmpty { called = true }
        assertTrue(called)
    }

    @Test
    fun onEmpty_skipsWhenPresent() {
        var called = false
        optionalOf("x").onEmpty { called = true }
        assertFalse(called)
    }

    // ========================================================================
    // Collection bridge
    // ========================================================================

    @Test
    fun toList_withValue_singleElement() {
        assertEquals(listOf("a"), optionalOf("a").toList())
    }

    @Test
    fun toList_withEmpty_emptyList() {
        assertEquals(emptyList(), emptyOptional<String>().toList())
    }

    @Test
    fun toSet_withValue_singleElement() {
        assertEquals(setOf(1), optionalOf(1).toSet())
    }

    @Test
    fun asSequence_withValue_singleElement() {
        assertEquals(listOf("x"), optionalOf("x").asSequence().toList())
    }

    @Test
    fun asSequence_withEmpty_emptySequence() {
        assertEquals(emptyList(), emptyOptional<String>().asSequence().toList())
    }

    @Test
    fun asFlow_withValue_singleElement() = runTest {
        assertEquals(listOf("x"), optionalOf("x").asFlow().toList())
    }

    @Test
    fun asFlow_withEmpty_emptyFlow() = runTest {
        assertEquals(emptyList(), emptyOptional<String>().asFlow().toList())
    }

    @Test
    fun stream_returnsSameAsFlow() = runTest {
        assertEquals(listOf(42), optionalOf(42).stream().toList())
        assertEquals(emptyList(), emptyOptional<Int>().stream().toList())
    }

    @Test
    fun zip_bothPresent_transforms() {
        val result = optionalOf(1).zip(optionalOf("a")) { n, s -> "$n$s" }
        assertEquals("1a", result.get())
    }

    @Test
    fun zip_oneEmpty_returnsEmpty() {
        val result = optionalOf(1).zip(emptyOptional<String>()) { n, s -> "$n$s" }
        assertTrue(result.isEmpty)
    }

    // ========================================================================
    // Collection → Optional
    // ========================================================================

    @Test
    fun mapGetOptional_existingKey() {
        val map = mapOf("a" to 1, "b" to 2)
        assertEquals(1, map.getOptional("a").get())
    }

    @Test
    fun mapGetOptional_missingKey() {
        val map = mapOf("a" to 1)
        assertTrue(map.getOptional("z").isEmpty)
    }

    @Test
    fun firstOptional_nonEmpty() {
        assertEquals(1, listOf(1, 2, 3).firstOptional().get())
    }

    @Test
    fun firstOptional_empty() {
        assertTrue(emptyList<Int>().firstOptional().isEmpty)
    }

    @Test
    fun firstOptional_withPredicate() {
        assertEquals(2, listOf(1, 2, 3).firstOptional { it > 1 }.get())
    }

    @Test
    fun lastOptional_nonEmpty() {
        assertEquals(3, listOf(1, 2, 3).lastOptional().get())
    }

    @Test
    fun singleOptional_oneElement() {
        assertEquals(42, listOf(42).singleOptional().get())
    }

    @Test
    fun singleOptional_multipleElements() {
        assertTrue(listOf(1, 2).singleOptional().isEmpty)
    }

    // ========================================================================
    // Optional collections
    // ========================================================================

    @Test
    fun filterPresent_list() {
        val items = listOf(optionalOf("a"), emptyOptional(), optionalOf("b"))
        assertEquals(listOf("a", "b"), items.filterPresent())
    }

    @Test
    fun filterPresent_sequence() {
        val items = sequenceOf(optionalOf(1), emptyOptional(), optionalOf(2))
        assertEquals(listOf(1, 2), items.filterPresent().toList())
    }

    @Test
    fun filterPresent_flow() = runTest {
        val items = kotlinx.coroutines.flow.flowOf(
            optionalOf("x"), emptyOptional(), optionalOf("y")
        )
        assertEquals(listOf("x", "y"), items.filterPresent().toList())
    }

    @Test
    fun toOptionalMap_filtersEmpty() {
        val pairs = listOf("a" to optionalOf(1), "b" to emptyOptional(), "c" to optionalOf(3))
        assertEquals(mapOf("a" to 1, "c" to 3), pairs.toOptionalMap())
    }

    // ========================================================================
    // Multimap support
    // ========================================================================

    @Test
    fun getAll_existingKey() {
        val multimap = mapOf("a" to listOf(1, 2), "b" to listOf(3))
        assertEquals(listOf(1, 2), multimap.getAll("a"))
    }

    @Test
    fun getAll_missingKey() {
        val multimap = mapOf("a" to listOf(1))
        assertEquals(emptyList(), multimap.getAll("z"))
    }

    @Test
    fun getFirst_existingKey() {
        val multimap = mapOf("a" to listOf(1, 2))
        assertEquals(1, multimap.getFirst("a").get())
    }

    @Test
    fun getFirst_missingKey() {
        val multimap = mapOf("a" to listOf(1))
        assertTrue(multimap.getFirst("z").isEmpty)
    }
}

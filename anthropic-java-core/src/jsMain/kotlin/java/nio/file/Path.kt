// JS compile-only stub for java.nio.file.Path
package java.nio.file

interface Path {
    fun toAbsolutePath(): Path
    fun resolve(other: String): Path
    override fun toString(): String
}

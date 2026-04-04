package java.util.concurrent.locks
interface Lock { fun lock(); fun unlock(); fun tryLock(): Boolean }
class ReentrantLock : Lock { override fun lock() {}; override fun unlock() {}; override fun tryLock(): Boolean = true }

package dsa

fun main() {
    CrashCourse1.solve()
}

object CrashCourse1 {

    fun solve() {
        println("---- 1. Unordered Set ----")
        val hs = hashSetOf(10, 20, 10) // duplicates ignored
        println(20 in hs)
        hs.remove(10)
        println("Size: ${hs.size}")
        hs.forEach { println(it) }

        println("---- 2. Ordered Sets ----")
        val ts = sortedSetOf(30, 10, 20)
        println(ts) // [10, 20, 30]

        val lhs = linkedSetOf(3, 1, 2)
        println(lhs) // [3, 1, 2]

        println("---- 3. Unordered Map ----")
        val hm = hashMapOf("Alice" to 25, "Bob" to 30)
        hm["Alice"] = 26
        println(hm["Alice"])
        println(hm.getOrDefault("Eve", -1))
        println("Contains Bob? ${"Bob" in hm}")
        hm.remove("Alice")
        hm.forEach { (k, v) -> println("$k -> $v") }

        println("---- 4. Ordered Map ----")
        val tm = sortedMapOf(2 to "B", 1 to "A", 3 to "C")
        println(tm) // {1=A, 2=B, 3=C}

        val lhm = linkedMapOf(2 to "B", 1 to "A")
        println(lhm) // {2=B, 1=A}

        println("---- 5. Stack (using ArrayDeque) ----")
        val stack1 = ArrayDeque<Int>()
        val stack = ArrayDeque<Int>()
        stack.addLast(10)
        stack.addLast(20)
        println("Top: ${stack.last()}")
        println("Popped: ${stack.removeLast()}")
        println("Empty? ${stack.isEmpty()}")

        println("---- 6. Queue (FIFO) ----")
        val q = ArrayDeque<Int>()
        q.addLast(10)
        q.addLast(20)
        println("Front: ${q.first()}")
        println("Polled: ${q.removeFirst()}")
        println("Empty? ${q.isEmpty()}")

        println("---- 7. Priority Queue ----")
        val pq = java.util.PriorityQueue<Int>() // min-heap
        pq.addAll(listOf(30, 10, 20))
        println("Peek: ${pq.peek()}")
        println("Polled: ${pq.poll()}")

        val maxPQ = java.util.PriorityQueue<Int>(compareByDescending { it })
        maxPQ.addAll(listOf(30, 10))
        println("Max-Heap Poll: ${maxPQ.poll()}")

        println("---- 8. Deque ----")
        val dq = ArrayDeque<Int>()
        dq.addFirst(1)
        dq.addLast(2)
        dq.addFirst(0)
        println("First: ${dq.first()}")
        println("PollFirst: ${dq.removeFirst()}")
        println("Last: ${dq.last()}")
        println("PollLast: ${dq.removeLast()}")
    }
}

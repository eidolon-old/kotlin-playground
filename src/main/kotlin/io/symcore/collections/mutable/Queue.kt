/**
 * This file is part of the "kotlin-playground" project.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the LICENSE is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.symcore.collections.mutable

import io.symcore.collections.exception.UnderflowException

/**
 * Queue, first-in first-out (FIFO) collection
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
public class Queue<T> : Collection<T> {
    private data class QueueItem<T>(val value: T, var next: QueueItem<T>? = null)

    private var head: QueueItem<T>? = null
    private var tail: QueueItem<T>? = null

    var length: Int = 0
        private set

    val isEmpty: Boolean
        get() = head == null


    /**
     * Appends an item to the end of the queue
     */
    public fun enqueue(i: T) {
        val item = QueueItem(i)

        if (tail == null) {
            head = item
            tail = head
        } else {
            tail?.next = item
            tail = item
        }

        length++
    }

    /**
     * Returns the first item in the queue from the queue, and remove it from the queue
     */
    public fun dequeue(): T {
        if (head == null) {
            throw UnderflowException()
        }

        val result = head!!.value

        head = head?.next

        if (head == null) {
            tail = null
        }

        length--

        return result
    }

    /**
     * Returns a new Queue containing all items matching the given [predicate].
     */
    public fun filter(predicate: (T) -> Boolean): Queue<T> {
        var current = head
        var queue = Queue<T>()

        while (current != null) {
            if (predicate(current.value)) {
                queue.enqueue(current.value)
            }

            current = current.next
        }

        return queue
    }

    /**
     * Returns a new Queue with the given [transform] function applied to each element of the
     * original collection.
     */
    public fun <R> map(transform: (T) -> R): Queue<R> {
        var current = head
        val queue = Queue<R>()

        while (current != null) {
            queue.enqueue(transform(current.value))
            current = current.next
        }

        return queue
    }

    /**
     * Accumulates value starting with the first element and applying [operation] from left to right
     * to current accumulator value and each element.
     */
    public fun reduce(operation: (T, T) -> T): T {
        if (isEmpty) {
            throw UnsupportedOperationException("Empty queue cannot be reduced.")
        }

        var accumulator : T = head!!.value
        var next = head?.next

        while (next != null) {
            accumulator = operation(accumulator, next.value)
            next = next.next
        }

        return accumulator
    }

    /**
     * String representation of this collection. Does not modify collection while building string.
     */
    override public fun toString(): String {
        var string = "Queue("
        var current = head
        var delim = ""

        while (current != null) {
            string += delim + current.value.toString()
            current = current.next
            delim = ", "
        }

        string += ")"

        return string
    }

    public fun iterator(): Iterator<T> = object : Iterator<T> {
        private var current = head

        override public fun next(): T {
            val next = current!!.value

            current = current!!.next

            return next
        }

        override public fun hasNext(): Boolean {
            return current != null
        }
    }

    public fun consumingIterator(): Iterator<T> = object : Iterator<T> {
        override fun next(): T {
            return dequeue()
        }

        override fun hasNext(): Boolean {
            return head != null
        }
    }
}

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
import io.symcore.collections.mutable.Collection
import io.symcore.collections.mutable.QueueIterator

/**
 * Queue, first-in first-out (FIFO) collection
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
public class Queue<T> : Collection<T> {
    private data class QueueItem<T>(val value: T, var next: QueueItem<T>? = null)

    private var head : QueueItem<T>? = null
    private var tail : QueueItem<T>? = null

    val isEmpty : Boolean
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
        if (this.isEmpty) {
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
     * Get the iterator for this object, note: this iterator uses dequeue to iterate, meaning that
     * items are removed from the collection while iterating.
     */
    public fun iterator(): QueueIterator<T> {
        return QueueIterator(this)
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
}

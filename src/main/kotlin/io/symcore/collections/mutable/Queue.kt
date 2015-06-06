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
public class Queue<T> {
    private data class QueueItem<T>(val value : T, var next : QueueItem<T>? = null)

    private var head : QueueItem<T>? = null
    private var tail : QueueItem<T>? = null

    val isEmpty : Boolean
        get() = head == null


    /**
     * Appends an item to the end of the queue
     */
    public fun enqueue(i : T) {
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
     * Returns a new Queue with the given [transform] function applied to each element of the
     * original collection
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
}
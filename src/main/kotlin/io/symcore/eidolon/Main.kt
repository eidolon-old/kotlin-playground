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

package io.symcore.eidolon

import io.symcore.collections.mutable.Queue

fun main(args: Array<String>) {
    val queue = Queue<Int>()

    queue.enqueue(1)
    queue.enqueue(2)
    queue.enqueue(3)
    queue.enqueue(4)
    queue.enqueue(5)
    queue.enqueue(6)
    queue.enqueue(7)
    queue.enqueue(8)

    val triples = queue.map { it * 3 }
    val even = triples.filter { it % 2 == 0 }
    val sum = even.reduce { x, y -> x + y }

    for (item in queue) {
        println(item.toString())
        println("Has: " + queue.length.toString())
    }

    for (item in queue.consumingIterator()) {
        println(item.toString())
        println("Has: " + queue.length.toString())
    }

    println(sum.toString())
    println(even)
}

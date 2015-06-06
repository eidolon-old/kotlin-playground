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

    val list = arrayListOf(1, 2, 3)
    val doubled = list.map { it * 2 }

    queue.enqueue(1)
    queue.enqueue(2)
    queue.enqueue(3)

    val doubles = queue.map { it * 2 }

    println("Hello world!")
}

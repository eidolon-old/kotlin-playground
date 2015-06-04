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

/**
 * Greeter
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
public class Greeter {
    private var greeting: String
        set(value) {
            $greeting = if (value.isNotBlank()) value else "Hello "
        }

    private val name: String?

    public val message: String
        get() = greeting + (name ?: "world")


    constructor() : this(null)
    constructor(name: String?) : this(name, "Hello ")
    constructor(name: String?, greeting: String) {
        this.name = name
        this.greeting = greeting
    }
}

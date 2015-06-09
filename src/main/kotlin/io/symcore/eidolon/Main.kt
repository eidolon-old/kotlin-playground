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

import org.restlet.Component
import org.restlet.Server
import org.restlet.data.Protocol

fun main(args: Array<String>) {
    val component = Component()

    component.getServers().add(Protocol.HTTP, 8080)
    component.getDefaultHost().attach("/", javaClass<HttpTest>())

    component.start()
}

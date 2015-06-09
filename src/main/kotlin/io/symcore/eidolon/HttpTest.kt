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

import org.restlet.resource.Get
import org.restlet.resource.ServerResource

/**
 * HttpTest
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
public class HttpTest : ServerResource() {
    @Get("txt")
    public fun getSomething(): String {
        return "Resource URI  : " + getReference() + '\n' + "Root URI      : " + getRootRef() + '\n' + "Routed part   : " + getReference().getBaseRef() + '\n' + "Remaining part: " + getReference().getRemainingPart();
    }
}

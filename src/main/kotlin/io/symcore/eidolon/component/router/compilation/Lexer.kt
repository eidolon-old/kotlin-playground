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

package io.symcore.eidolon.component.router.compilation

import io.symcore.eidolon.component.router.compilation.Token

/**
 * Lexer
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
public class Lexer {
    final val regexHyphen = """^(-)"""
    final val regexPathSeperator = """^(/)"""
    final val regexString = """^([A-Za-z0-9]+)"""
    final val regexUnderscore = """^(_)"""
    final val regexVariable = """^(:[A-Za-z0-9]+)"""

}

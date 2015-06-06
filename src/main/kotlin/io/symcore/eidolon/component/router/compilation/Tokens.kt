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

interface Token

data class T_HYPHEN(val lexeme: String) : Token
data class T_PATH_SEPERATOR(val lexeme: String) : Token
data class T_STRING(val lexeme: String) : Token
data class T_UNDERSCORE(val lexeme: String) : Token
data class T_VARIABLE(val lexeme: String) : Token

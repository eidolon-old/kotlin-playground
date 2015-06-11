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

package io.symcore.http

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.http.HttpRequestDecoder
import io.netty.handler.codec.http.HttpResponseEncoder

/**
 * HttpServerInitialiser
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
public class HttpServerInitialiser : ChannelInitializer<SocketChannel>() {
    override fun initChannel(channel: SocketChannel?) {
        val pipeline = channel?.pipeline();

        pipeline?.addLast(HttpRequestDecoder())
        pipeline?.addLast(HttpResponseEncoder())
        pipeline?.addLast(HttpRequestHandler())
    }
}

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

import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.handler.codec.http.DefaultFullHttpResponse
import io.netty.handler.codec.http.HttpContent
import io.netty.handler.codec.http.HttpHeaders
import io.netty.handler.codec.http.HttpRequest
import io.netty.handler.codec.http.HttpResponseStatus.OK
import io.netty.handler.codec.http.HttpVersion.HTTP_1_1
import io.netty.handler.codec.http.HttpHeaders.*
import io.netty.handler.codec.http.HttpHeaders.*

/**
 * HttpRequestHandler
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
public class HttpRequestHandler : SimpleChannelInboundHandler<Any>() {
    override public fun channelReadComplete(ctx: ChannelHandlerContext) {
        ctx.flush();
    }

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: Any?) {
        if (msg is HttpRequest) {
            val request = msg
            val isKeepAlive = HttpHeaders.isKeepAlive(request)
            val responseBody = """{ "message": "Hello world!" }""".toByteArray()
            val response = DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(responseBody))

            response.headers().set(Names.CONTENT_TYPE, "application/json")

            if (isKeepAlive) {
                response.headers().set(Names.CONTENT_LENGTH, response.content().readableBytes())
                response.headers().set(Names.CONNECTION, Values.KEEP_ALIVE)
            }

            ctx?.writeAndFlush(response)
        }
    }
}

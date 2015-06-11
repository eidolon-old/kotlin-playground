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

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler

/**
 * HttpServer
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
public class HttpServer {
    public fun start(port: Int) {
        val bossGroup = NioEventLoopGroup()
        val workerGroup = NioEventLoopGroup()

        try {
            val bootstrap = ServerBootstrap()

            bootstrap.group(bossGroup, workerGroup)
                .channel(javaClass<NioServerSocketChannel>())
//                .handler(LoggingHandler(LogLevel.INFO))
                .childHandler(HttpServerInitialiser())

            val channel = bootstrap.bind(port).sync().channel()

            println("Server listening at 0.0.0.0:" + port.toString())

            channel.closeFuture().sync()
        } finally {
            bossGroup.shutdownGracefully()
            workerGroup.shutdownGracefully()
        }
    }
}

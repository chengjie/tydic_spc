package com.tydic.spc;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.http.HttpClientCodec;
import org.jboss.netty.handler.codec.http.HttpContentDecompressor;
import org.jboss.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

import static org.jboss.netty.channel.Channels.pipeline;

/**
 * User: chengjie
 * Date: 12-12-26
 * Time: 下午4:37
 */
public class HttpSnoopClientPipelineFactory implements ChannelPipelineFactory {
    private final boolean ssl;

    public HttpSnoopClientPipelineFactory(boolean ssl) {
        this.ssl = ssl;
    }

    public ChannelPipeline getPipeline() throws Exception {
        // Create a default pipeline implementation.
        ChannelPipeline pipeline = pipeline();

        // Enable HTTPS if necessary.
        if (ssl) {
            SSLEngine engine =
                    SecureChatSslContextFactory.getClientContext().createSSLEngine();
            engine.setUseClientMode(true);

            pipeline.addLast("ssl", new SslHandler(engine));
        }

        pipeline.addLast("codec", new HttpClientCodec());

        // Remove the following line if you don't want automatic content decompression.
        pipeline.addLast("inflater", new HttpContentDecompressor());

        // Uncomment the following line if you don't want to handle HttpChunks.
        //pipeline.addLast("aggregator", new HttpChunkAggregator(1048576));

        pipeline.addLast("handler", new HttpSnoopClientHandler());
        return pipeline;
    }
}

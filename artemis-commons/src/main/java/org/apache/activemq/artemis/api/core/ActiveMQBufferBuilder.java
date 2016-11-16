package org.apache.activemq.artemis.api.core;

import io.netty.buffer.Unpooled;
import org.apache.activemq.artemis.core.buffers.impl.ChannelBufferWrapper;

import java.nio.ByteBuffer;

/**
 * Created by johara on 16/11/16.
 */
public interface ActiveMQBufferBuilder {

	ActiveMQBuffer dynamicBuffer(final int size);

	ActiveMQBuffer andyDynamicBuffer(final int size) ;

	/**
	 * Creates a <em>self-expanding</em> ActiveMQBuffer filled with the given byte array
	 *
	 * @param bytes the created buffer will be initially filled with this byte array
	 * @return a self-expanding ActiveMQBuffer filled with the given byte array
	 */
	ActiveMQBuffer dynamicBuffer(final byte[] bytes);

	/**
	 * Creates an ActiveMQBuffer wrapping an underlying NIO ByteBuffer
	 *
	 * The position on this buffer won't affect the position on the inner buffer
	 *
	 * @param underlying the underlying NIO ByteBuffer
	 * @return an ActiveMQBuffer wrapping the underlying NIO ByteBuffer
	 */
	ActiveMQBuffer wrappedBuffer(final ByteBuffer underlying) ;

	/**
	 * Creates an ActiveMQBuffer wrapping an underlying byte array
	 *
	 * @param underlying the underlying byte array
	 * @return an ActiveMQBuffer wrapping the underlying byte array
	 */
	ActiveMQBuffer wrappedBuffer(final byte[] underlying) ;

	/**
	 * Creates a <em>fixed</em> ActiveMQBuffer of the given size
	 *
	 * @param size the size of the created ActiveMQBuffer
	 * @return a fixed ActiveMQBuffer with the given size
	 */
	ActiveMQBuffer fixedBuffer(final int size);

}

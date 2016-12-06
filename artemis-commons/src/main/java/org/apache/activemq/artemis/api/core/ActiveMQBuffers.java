/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.activemq.artemis.api.core;

import java.nio.ByteBuffer;

import edu.columbia.cs.psl.phosphor.runtime.MultiTainter;
import edu.columbia.cs.psl.phosphor.runtime.Taint;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import org.apache.activemq.artemis.core.buffers.impl.ChannelBufferWrapper;

/**
 * Factory class to create instances of {@link ActiveMQBuffer}.
 */
public final class ActiveMQBuffers {


   private static final PooledByteBufAllocator ALLOCATOR = new PooledByteBufAllocator();
   /**
    * Creates a <em>self-expanding</em> ActiveMQBuffer with the given initial size
    *
    * @param size the initial size of the created ActiveMQBuffer
    * @return a self-expanding ActiveMQBuffer starting with the given size
    */
   public static ActiveMQBuffer dynamicBuffer(final int size) {
      ByteBuf dynamicByteBuf = Unpooled.buffer(size);
      MultiTainter.taintedObject(dynamicByteBuf, new Taint("test"));
      return new ChannelBufferWrapper(dynamicByteBuf);
   }

   public static ActiveMQBuffer andyDynamicBuffer(final int size) {
      return new ChannelBufferWrapper(ALLOCATOR.buffer(size),true);
   }

   /**
    * Creates a <em>self-expanding</em> ActiveMQBuffer filled with the given byte array
    *
    * @param bytes the created buffer will be initially filled with this byte array
    * @return a self-expanding ActiveMQBuffer filled with the given byte array
    */
   public static ActiveMQBuffer dynamicBuffer(final byte[] bytes) {
      ActiveMQBuffer buff = dynamicBuffer(bytes.length);

      buff.writeBytes(bytes);

      return buff;
   }

   /**
    * Creates an ActiveMQBuffer wrapping an underlying NIO ByteBuffer
    *
    * The position on this buffer won't affect the position on the inner buffer
    *
    * @param underlying the underlying NIO ByteBuffer
    * @return an ActiveMQBuffer wrapping the underlying NIO ByteBuffer
    */
   public static ActiveMQBuffer wrappedBuffer(final ByteBuffer underlying) {
      ByteBuf wrappedBuff = Unpooled.wrappedBuffer(underlying);
      MultiTainter.taintedObject(wrappedBuff, new Taint("test"));

      ActiveMQBuffer buff = new ChannelBufferWrapper(wrappedBuff);

      buff.clear();

      return buff;
   }

   /**
    * Creates an ActiveMQBuffer wrapping an underlying byte array
    *
    * @param underlying the underlying byte array
    * @return an ActiveMQBuffer wrapping the underlying byte array
    */
   public static ActiveMQBuffer wrappedBuffer(final byte[] underlying) {
      ByteBuf wrappedBuff = Unpooled.wrappedBuffer(underlying);
      MultiTainter.taintedObject(wrappedBuff, new Taint("test"));

      return new ChannelBufferWrapper(wrappedBuff);
   }

   /**
    * Creates a <em>fixed</em> ActiveMQBuffer of the given size
    *
    * @param size the size of the created ActiveMQBuffer
    * @return a fixed ActiveMQBuffer with the given size
    */
   public static ActiveMQBuffer fixedBuffer(final int size) {
      ByteBuf dynamicByteBuf = Unpooled.buffer(size);
      MultiTainter.taintedObject(dynamicByteBuf, new Taint("test"));
      return new ChannelBufferWrapper(dynamicByteBuf);
   }

   private ActiveMQBuffers() {
      // Utility class
   }
}

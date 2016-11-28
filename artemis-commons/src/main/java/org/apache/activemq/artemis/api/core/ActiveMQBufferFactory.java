/**
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

import io.netty.util.internal.SystemPropertyUtil;

import java.nio.ByteBuffer;

/**
 * Created by johara on 16/11/16.
 */
public class ActiveMQBufferFactory {


   private static final ActiveMQBufferBuilder activeMQBufferBuilder;

   static {

      activeMQBufferBuilder = new PooledActiveMQBuffers();
//      activeMQBufferBuilder = new UnpooledActiveMQBuffers();
      //define strategy here
//      if ( SystemPropertyUtil.get( "org.apache.activemq.artemis.api.core.UsePooled" ) != null ) {
//         activeMQBufferBuilder = new PooledActiveMQBuffers();
//      }
//      else {
//         activeMQBufferBuilder = new UnpooledActiveMQBuffers();
//      }
   }


   //private static final


   public static ActiveMQBuffer dynamicBuffer(final int size) {
      return activeMQBufferBuilder.dynamicBuffer( size );
   }

   public static ActiveMQBuffer andyDynamicBuffer(final int size) {
      return activeMQBufferBuilder.dynamicBuffer( size );
   }

   /**
    * Creates a <em>self-expanding</em> ActiveMQBuffer filled with the given byte array
    *
    * @param bytes the created buffer will be initially filled with this byte array
    * @return a self-expanding ActiveMQBuffer filled with the given byte array
    */
   public static ActiveMQBuffer dynamicBuffer(final byte[] bytes) {
      return activeMQBufferBuilder.dynamicBuffer( bytes );
   }

   /**
    * Creates an ActiveMQBuffer wrapping an underlying NIO ByteBuffer
    * <p>
    * The position on this buffer won't affect the position on the inner buffer
    *
    * @param underlying the underlying NIO ByteBuffer
    * @return an ActiveMQBuffer wrapping the underlying NIO ByteBuffer
    */
   public static ActiveMQBuffer wrappedBuffer(final ByteBuffer underlying) {
      return activeMQBufferBuilder.wrappedBuffer( underlying );
   }

   /**
    * Creates an ActiveMQBuffer wrapping an underlying byte array
    *
    * @param underlying the underlying byte array
    * @return an ActiveMQBuffer wrapping the underlying byte array
    */
   public static ActiveMQBuffer wrappedBuffer(final byte[] underlying) {
      return activeMQBufferBuilder.wrappedBuffer( underlying );
   }

   /**
    * Creates a <em>fixed</em> ActiveMQBuffer of the given size
    *
    * @param size the size of the created ActiveMQBuffer
    * @return a fixed ActiveMQBuffer with the given size
    */
   public static ActiveMQBuffer fixedBuffer(final int size) {
      return activeMQBufferBuilder.fixedBuffer( size );
   }

}

/**
 * Created by panhongliang on 16/3/11.
 * LineBasedFrameDecoder
 * LineBasedFrameDecoder的工作原理是它依次遍历ByteBuf中的可读字节，判断看是否
 * 有\n或\r\n，如果有，就以此位置为结束位置。
 */
package com.phl.netty.lineBasedFrameDecoder;
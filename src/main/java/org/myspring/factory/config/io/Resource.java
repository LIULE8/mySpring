package org.myspring.factory.config.io;

import java.io.IOException;
import java.io.InputStream;


/**
 * 读取文件的类，本来应该是InputStream接口的，Resource接口是做文件操作的，为了简便直接在Resource接口里操作字节流
 *
 * @author Leo Liu
 * @create 19/11/2019
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}

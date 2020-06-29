package com.io;

import java.io.*;

public class Bio {

    public static void main(String[] args) throws Exception {
        Bio.fileRead();
    }

    public static void fileRead() {
        InputStream in = null;
        OutputStream out = null;

        try {
            in = new BufferedInputStream(new FileInputStream("C:/Users/wyg/Desktop/nginx/新建文本文档.txt"));
            out = new BufferedOutputStream(new FileOutputStream("C:/Users/wyg/Desktop/nginx/1.txt"));
            byte[] buf = new byte[1024];
            while (in.read(buf) != -1) {
                //只是写入内存
                out.write(buf);
                //刷入磁盘
                out.flush();
            }
            in.close();
          } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

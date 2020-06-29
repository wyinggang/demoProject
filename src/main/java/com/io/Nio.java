package com.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class Nio {

    public static void main(String[] args) {
        Nio.NioFileReadWrite();
    }

    public static void NioFileReadWrite(){
        RandomAccessFile aFile = null;
        try{
            FileInputStream fileInputStream = new FileInputStream("C:/Users/wyg/Desktop/nginx/新建文本文档.txt");
            FileChannel fileChannel = fileInputStream.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);
            while(bytesRead != -1) {
                //将position移动到0，limit移动到未读数据的下一位，开始读取数据
                buf.flip();
                //判断是否还有未读的数据
                while(buf.hasRemaining())
                {
                    System.out.print((char)buf.get());
                }
                //将所有未读数据移动到buffer起始处，position移动到最后一个未读数据下一位，limit设为capacity
                //在保存未读数据同时，在未读数据后开始写入数据
                buf.compact();
                //position置为0，limit置为capacity，意思是buf被清空，实际上数据还在buf里面
                buf.clear();
                bytesRead = fileChannel.read(buf);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(aFile != null){
                    aFile.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}

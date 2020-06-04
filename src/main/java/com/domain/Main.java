package com.domain;

import javafx.application.Application;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        ok:
        for(int i=0;i<10;i++)	{
            for(int j=0;j<10;j++)	{
                System.out.println("i=" + i + ",j=" + j); if(j == 5)
                    break ok;
            }
        }

        Outter outter = new Outter();

        Outter.Inner inner = outter.new Inner();

        System.out.println(inner.j);

        HashMap<Object, Object> hashMap = new HashMap<>();

        hashMap.entrySet();


        System.out.println("--------------------");


        //io流
        try {
            File file = new File("C:/Users/wyg/Desktop/a.txt");
            FileWriter outputStream = new FileWriter(new File("C:/Users/wyg/Desktop/b.txt"));
            FileReader inputStream = new FileReader(file);
            char[] bytes = new char[4];
            StringBuilder sb = new StringBuilder();
            while ((inputStream.read(bytes))!=-1) {
                outputStream.write(bytes);
                sb.append(bytes);
            }
          System.out.println(sb);
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        HashMap<Object, Object> map = new HashMap<>();

        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

        Hashtable<Object, Object> hashtable = new Hashtable<>();

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("");

    }

}

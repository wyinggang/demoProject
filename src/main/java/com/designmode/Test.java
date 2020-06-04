package com.designmode;

import com.designmode.factoryMethod.MailFactory;
import com.designmode.factoryMode.Sender;
import com.designmode.simpleFactoryMode.SenderFactory;

public class Test {

    public static void main(String[] args) {
        //简单工厂模式--静态工厂方法
        SenderFactory.produceMailSender().send();

         //工厂方法模式
        MailFactory mailFactory = new MailFactory();
        Sender sender = mailFactory.produce();
        sender.send();
    }

}

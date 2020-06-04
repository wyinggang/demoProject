package com.designmode.simpleFactoryMode;

import com.designmode.factoryMode.MailSender;
import com.designmode.factoryMode.MsgSender;
import com.designmode.factoryMode.Sender;

//静态工厂方法
public class SenderFactory {

    public static Sender produceMailSender(){
        return new MailSender();
    }

    public static  Sender produceMsgSender(){
        return new MsgSender();
    }

}
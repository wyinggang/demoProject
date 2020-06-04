package com.designmode.factoryMethod;

import com.designmode.factoryMode.MailSender;
import com.designmode.factoryMode.Sender;

public class MailFactory implements Provider {
    @Override
    public Sender produce() {
        return new MailSender();
    }
}

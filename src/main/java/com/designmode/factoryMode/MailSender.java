package com.designmode.factoryMode;

public class MailSender implements Sender {
    @Override
    public void send() {
        System.out.println("This is mailSender!");
    }
}

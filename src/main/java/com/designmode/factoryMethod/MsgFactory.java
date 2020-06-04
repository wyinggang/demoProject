package com.designmode.factoryMethod;

import com.designmode.factoryMode.MsgSender;
import com.designmode.factoryMode.Sender;

public class MsgFactory implements Provider {
    @Override
    public Sender produce() {
        return new MsgSender();
    }
}

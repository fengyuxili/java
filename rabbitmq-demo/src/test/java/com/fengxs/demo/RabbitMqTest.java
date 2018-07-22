package com.fengxs.demo;

import com.fengxs.demo.rabbitmq.Send;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RabbitMqTest extends  RabbitmqDemoApplicationTests{
    @Autowired
    Send send;

    @Test
    public void receive() {

    }

    @Test
    public void send() {
        send.sendMsg("hello rabbitmq");
    }
}

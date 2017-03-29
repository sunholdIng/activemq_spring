package com.droe.demo.activeMqDemojava.activemq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;

/**
 * Created by sunholdIng on 2017/3/28.
 */
@Service(value = "producerService")
public class ProviderMsgService {

    /**
     * 主要是产生连接和设置目的地队列
     */
    @Autowired
    private JmsTemplate JmsQueueTemplate;

    //向指定队列发送消息
    public void sendMessage(Destination destination,final String msg){

        System.out.println("向队列" + destination.toString() + "发送了消息------------" + msg);
         JmsQueueTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {

                return session.createTextMessage(msg);
            }
        });
    }

    //向默认队列发送消息
    public void sendMessage(final String msg) {
        //得到默认目的地地址
        String destination =  JmsQueueTemplate.getDefaultDestination().toString();
        System.out.println("向队列" + destination.toString() + "发送了消息------------" + msg);

        System.out.println("向队列" +destination+ "发送了消息------------" + msg);
        JmsQueueTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });

    }
}

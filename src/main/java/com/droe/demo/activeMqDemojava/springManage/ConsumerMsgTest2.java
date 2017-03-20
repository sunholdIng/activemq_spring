package com.droe.demo.activeMqDemojava.springManage;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by sunholdIng on 2017/3/20.
 */
public class ConsumerMsgTest2 implements MessageListener {

    /*  public static void main(String[] args) {
      ApplicationContext ctx = new ClassPathXmlApplicationContext("activeMqPro-core.xml");
        while(true) {
        }
    }

    public void receive(TextMessage message) throws JmsException, JMSException {
        System.out.println(message.getStringProperty("phrCode"));
        System.out.println(message.getText());
    }*/

    public void onMessage(Message message) {
        System.out.print("begin Message");
        TextMessage textMsg = (TextMessage) message;
        try {
            System.out.println("接收到一个纯文本消息。"+ textMsg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}

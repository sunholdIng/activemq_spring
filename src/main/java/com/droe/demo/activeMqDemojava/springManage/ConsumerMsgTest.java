package com.droe.demo.activeMqDemojava.springManage;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by sunholdIng on 2017/3/20.
 */
public class ConsumerMsgTest implements MessageListener {

     /* public static void main(String[] args) {
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

    }
}

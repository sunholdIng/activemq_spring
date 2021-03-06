package com.droe.demo.activeMqDemojava.springManage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created by sunholdIng on 2017/3/20.
 */
public class ProviderMsgTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("activeMqPro-core.xml");

        JmsTemplate jmsTemplate = (JmsTemplate) ctx.getBean("JmsQueueTemplate");
        for (int i = 0; i < 10; i++) {
            jmsTemplate.send(new MessageCreator() {
                public TextMessage createMessage(Session session) throws JMSException {
                    TextMessage msg = session.createTextMessage();
                    // 设置消息属性
                    msg.setStringProperty("phrCode", "C001");
                    // 设置消息内容
                    msg.setText("Hello World!");
                    return msg;
                }
            });
        }
    }
}

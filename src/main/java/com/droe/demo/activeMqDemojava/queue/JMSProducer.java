package com.droe.demo.activeMqDemojava.queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by admin on 2017/3/14.
 */
public class JMSProducer {
    //默认连接用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //默认连接密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //默认连接地址
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    //发送的消息数量
    private static final int SENDNUM = 10;

    public static void main(String[] args) {
        //连接工厂
        ConnectionFactory connectionFactory = null;
        //连接
        Connection connection = null;
        //创建会话
        Session session = null;
        //目的地
        Destination destination;
        //生产者
        MessageProducer messageProducer;
        //实例化工厂，new（）
        connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);

        try {
            //从工厂这里得到连接
            connection = connectionFactory.createConnection();
            //启动连接
            connection.start();
            //创建会话
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建一个名称为HelloWorld的消息队列
            destination = session.createQueue("messageNew");
            //destination = session.createTopic("hello world!");
            //创建消息生成者
            messageProducer = session.createProducer(destination);
            //发送消息
            sendMessage(session, messageProducer);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                //session.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 发送消息
     *
     * @param session
     * @param messageProducer 消息生产者
     * @throws Exception
     */
    public static void sendMessage(Session session, MessageProducer messageProducer) throws Exception {
        for (int i = 0; i < JMSProducer.SENDNUM; i++) {
            //创建一条文本消息
            TextMessage message = session.createTextMessage("ActiveMQ 发送消息" + i);
            System.out.println("发送消息：Activemq 发送消息" + i);
            //通过消息生产者发出消息
            messageProducer.send(message);
        }

    }

}

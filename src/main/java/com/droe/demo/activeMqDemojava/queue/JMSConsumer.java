package com.droe.demo.activeMqDemojava.queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by admin on 2017/3/14.
 * 消费者
 */
public class JMSConsumer {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;//默认连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;//默认连接密码
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;//默认连接地址

    public static void main(String[] args){
        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session;
        Destination destination;
        MessageConsumer messageConsumer;
        try{
            connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);
            connection = connectionFactory.createConnection();
            connection.start();
            //ture表示开启事务，需要进行提交什么操作。
            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("messageNew");
            messageConsumer = session.createConsumer(destination);

            while (true){
                TextMessage textMessage = (TextMessage) messageConsumer.receive(100);
                if(textMessage != null){
                    System.out.println("收到的消息:" + textMessage.getText());
                    //session 为时：CLIENT_ACKNOWLEDGE ，需要调用下面方法删除消息
                   // textMessage.acknowledge();
                }else {
                    break;
                }
            }
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}

/*
package com.ddshop.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class QueueTest {


    @Test
    public void testPro() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://120.79.90.102:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("FirstQueue!");
        MessageProducer producer = session.createProducer(queue);
        TextMessage message = session.createTextMessage("hello,is queue message");
        producer.send(message);
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testCus() throws Exception{
        System.out.println(Thread.currentThread().getName()+"外部运行");
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://120.79.90.102:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("FirstQueue!");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    System.out.println(Thread.currentThread().getName()+"内部运行");
                    TextMessage textMessage = (TextMessage)message;
                    String text = textMessage.getText();
                    System.out.print(text);
                    System.out.println(Thread.currentThread().getName()+"内部结束");
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
        System.out.println(Thread.currentThread().getName()+"外部结束");
        consumer.close();
        session.close();
        connection.close();
    }
}
*/

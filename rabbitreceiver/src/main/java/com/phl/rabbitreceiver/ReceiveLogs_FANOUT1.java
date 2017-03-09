package com.phl.rabbitreceiver;

import com.phl.config.Config;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by panhongliang on 16/1/18.
 */
public class ReceiveLogs_FANOUT1 {
    private static final String EXCHANGE_NAME = "logs";
    private static final String node="node1";
    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Config.RabbitMq.HOST);
        //远程主机需要创建用户和密码才能访问
        factory.setUsername(Config.RabbitMq.USERNAME);
        factory.setPassword(Config.RabbitMq.PASSWORD);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        /*no parameters to queueDeclare() we create a non-durable, exclusive, autodelete queue with a generated name:
        Firstly, whenever we connect to Rabbit we need a fresh, empty queue.
        To do this we could create a queue with a random name, or, even better - let the server choose a random queue name for us.
         Secondly, once we disconnect the consumer the queue should be automatically deleted.
        创建一个匿名的队列
        */
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(node+" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(node+" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}

package com.phl.rabbitreceiver;

import com.phl.config.Config;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by panhongliang on 16/1/18.
 */
public class Send_Recv {

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Config.RabbitMq.HOST);
        //远程主机需要创建用户和密码才能访问
        factory.setUsername(Config.RabbitMq.USERNAME);
        factory.setPassword(Config.RabbitMq.PASSWORD);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //queueDeclare时client和server和配置应该一样
        boolean durable=true;//队列持久化
        channel.queueDeclare(Config.RabbitMq.QUEUE_NAME, durable, false, false, null);
        //接收任务更加合理，慢的client接收更少的任务
        channel.basicQos(1);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        final Random random=new Random();
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");

                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(Config.RabbitMq.QUEUE_NAME, true, consumer);
    }
}

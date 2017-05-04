package com.phl.rabbitclient;

import com.phl.config.Config;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * Created by panhongliang on 16/1/18.
 */
public class Send {
    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Config.RabbitMq.HOST);
        //远程主机需要创建用户和密码才能访问
        factory.setUsername(Config.RabbitMq.USERNAME);
        factory.setPassword(Config.RabbitMq.PASSWORD);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //queueDeclare时client和server和配置应该一样
        boolean durable=true;//队列持久化
        channel.queueDeclare(Config.RabbitMq.QUEUE_NAME,durable , false, false, null);


        String message = "Hello World!";
        int i=0;
        while (true) {
            //MessageProperties.PERSISTENT_TEXT_PLAIN消息持久化
            channel.basicPublish("", Config.RabbitMq.QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, (message+i++).getBytes());
            System.out.println(" [x] Sent '" + message +i+ "'");
        }
      //  channel.close();
       // connection.close();
    }


}

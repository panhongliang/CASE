package com.phl.rabbitclient;

import com.phl.config.Config;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by panhongliang on 16/1/18.
 */
public class EmitLog_DIRECT {
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv)
            throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Config.RabbitMq.HOST);
        //远程主机需要创建用户和密码才能访问
        factory.setUsername(Config.RabbitMq.USERNAME);
        factory.setPassword(Config.RabbitMq.PASSWORD);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //direct:特定binding key的消息进入特定binding key的队列
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        while (true){
            String severity = getServrity();
            String message = getMessage();
            //severity:routingKey(binding key)
            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
            System.out.println(" [x] Sent severity'" +severity+":"+ message + "'");
            Thread.sleep(2000);
        }


       /* channel.close();
        connection.close();*/
    }
    private static String getServrity(){
        String serity[]={"info","error","warn"};
        Long index=Math.round(Math.random()*2);
        return serity[index.intValue()];
    }
    static int i=0;
    private static String getMessage(){
       String h= "hello world!";return h+i++;
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}

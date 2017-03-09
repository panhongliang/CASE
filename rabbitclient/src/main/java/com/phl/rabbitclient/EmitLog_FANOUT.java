package com.phl.rabbitclient;

import com.phl.config.Config;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by panhongliang on 16/1/18.
 */
public class EmitLog_FANOUT {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv)
            throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Config.RabbitMq.HOST);
        //远程主机需要创建用户和密码才能访问
        factory.setUsername(Config.RabbitMq.USERNAME);
        factory.setPassword(Config.RabbitMq.PASSWORD);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //"fanout" 广播
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        for(int i=0;i<10;i++) {
            String message = getMessage(argv);
            channel.basicPublish(EXCHANGE_NAME, "", null, (message+i).getBytes());
            System.out.println(" [x] Sent '" + message + i+"'");
        }
        channel.close();
        connection.close();
    }
    private static String getMessage(String argv[]){
      if(argv==null ||argv.length==0)return  "hello world!";
        return joinStrings(argv, " ");
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

package producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class AppRabbitMQProducer {

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        //Set username
//		factory.setUsername("ifsc");
        //Set password
//		factory.setPassword("ifsc");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("hello", false, false, false, null);

        channel.basicPublish("", "hello", null, "Test".getBytes());
        System.out.println(" [x] Sent ");

        channel.close();
        connection.close();

    }

}

package consumer;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class AppRabbitMQConsumer {

    public static void main(String[] args) throws Exception {

        //Generate a Connection Factory
        ConnectionFactory factory = new ConnectionFactory();
        //Set the RabbitMQ Server host
        factory.setHost("127.0.0.1");
        //Set username
//		factory.setUsername("ifsc");
        //Set password
//		factory.setPassword("ifsc");
        //Instantiate a new connection
        Connection connection = factory.newConnection();
        //Create a communication channel
        Channel channel = connection.createChannel();
        //Create a queue
        channel.queueDeclare("hello", false, false, false, null);
        System.out.println("Consumer is Waiting for Messages...");

        //Create a Consumer
        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            //Wait for Messages in the Queue
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                    byte[] body) throws IOException {
                String text = new String(body);
                System.out.println(" [x] Received '" + text + "'");
            }
        };
        //Consume the Queue
        channel.basicConsume("hello", true, consumer);

    }

}

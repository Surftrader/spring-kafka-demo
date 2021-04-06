package ua.com.poseal;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.com.poseal.model.MyProducer;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class SpringKafkaDemoApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
//		SpringApplication.run(SpringKafkaDemoApplication.class, args);

		String topic = "spring-kafka-demo";
		MyProducer producer = new MyProducer(topic);
		producer.send("key","value");

		producer.close();
	}

}

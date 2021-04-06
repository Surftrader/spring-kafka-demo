package ua.com.poseal;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class SpringKafkaDemoApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SpringApplication.run(SpringKafkaDemoApplication.class, args);

		Properties props = new Properties();

		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "clientId");
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		KafkaProducer<String, String> kafkaProducer = new KafkaProducer(props);
		String topic = "spring-kafka-demo";

		kafkaProducer
				.send(new ProducerRecord(topic, "Hello from JVM!"))
				.get();

		kafkaProducer.close();
	}

}

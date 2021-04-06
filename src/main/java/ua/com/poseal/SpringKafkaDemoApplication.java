package ua.com.poseal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.com.poseal.model.MyConsumer;
import ua.com.poseal.model.MyProducer;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SpringKafkaDemoApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
		SpringApplication.run(SpringKafkaDemoApplication.class, args);

		String topic = "spring-kafka-demo";
		MyProducer producer = new MyProducer(topic);

		new Thread(() -> {
			for (int i = 1; i < 100; i++) {
				try {
					producer.send(String.valueOf(i), "Hello from MyProducer!");
					TimeUnit.SECONDS.sleep(5);
				} catch (ExecutionException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		MyConsumer consumer = new MyConsumer(topic);
		consumer.consume(record -> System.out.println("Got key:" + record.key() + ", value:" + record.value()));

		TimeUnit.MINUTES.sleep(5);

		producer.close();
		consumer.close();
	}
}

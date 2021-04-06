package ua.com.poseal.model;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.Closeable;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class MyProducer implements Closeable {

    private String topic;

    private KafkaProducer<String, String> kafkaProducer = getKafkaProducer();

    public MyProducer(String topic) {
        this.topic = topic;
    }

    public MyProducer() {
    }

    public KafkaProducer<String, String> getKafkaProducer() {

        Properties props = new Properties();

        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "clientId");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return new KafkaProducer(props);
    }

    public void send(String key, String value) throws ExecutionException, InterruptedException {

        kafkaProducer
				.send(new ProducerRecord(topic, key,value))
				.get();

		kafkaProducer.close();
    }

    @Override
    public void close() throws IOException {
        kafkaProducer.close();
    }
}

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MyProducer {
    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        System.out.println("Sending data to `sample` topic");
        try (Producer<String, String> producer = new KafkaProducer<String, String>(props)) {
            int i = 0;
            while (true) {
                ProducerRecord<String, String> record = new ProducerRecord<>("sample", "key " + i, "Value " + i);
                System.out.println("Sending " + record.key() + " " + record.value());
                producer.send(record);
                i++;
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }
}

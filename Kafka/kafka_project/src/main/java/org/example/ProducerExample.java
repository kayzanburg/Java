import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerExample {
    public static void main(String[] args) {
        // Kafka üretici özelliklerini ayarla
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Kafka üretici nesnesini oluştur
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // Mesajları gönder
        for (int i = 0; i < 10; i++) {
            String key = "key-" + i;
            String value = "message-" + i;
            ProducerRecord<String, String> record = new ProducerRecord<>("test-topic", key, value);
            producer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    exception.printStackTrace();
                } else {
                    System.out.printf("Sent record(key=%s value=%s) " +
                            "meta(partition=%d, offset=%d)%n", key, value, metadata.partition(), metadata.offset());
                }
            });
        }

        // Üreticiyi kapat
        producer.close();
    }
}

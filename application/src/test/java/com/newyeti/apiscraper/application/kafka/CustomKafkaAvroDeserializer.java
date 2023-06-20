package com.newyeti.apiscraper.application.kafka;

import java.util.Map;

import io.confluent.kafka.schemaregistry.client.MockSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;

public class CustomKafkaAvroDeserializer extends KafkaAvroDeserializer{
    public CustomKafkaAvroDeserializer() {
        super();
        super.schemaRegistry = new MockSchemaRegistryClient();
    }
 
    public CustomKafkaAvroDeserializer(SchemaRegistryClient client) {
        super(new MockSchemaRegistryClient());
    }
 
    public CustomKafkaAvroDeserializer(SchemaRegistryClient client, Map<String, ?> props) {
        super(new MockSchemaRegistryClient(), props);
    }
}

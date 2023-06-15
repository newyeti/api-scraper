package com.newyeti.apiscraper.infrastructure.kafka;


import java.util.Map;

import com.newyeti.apiscraper.domain.model.avro.schema.League;

import io.confluent.kafka.schemaregistry.client.MockSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;

public class CustomKafkaAvroDeserializer extends KafkaAvroDeserializer {

    public CustomKafkaAvroDeserializer() throws Exception{
        super();
        super.schemaRegistry = new MockSchemaRegistryClient();
        super.schemaRegistry.register("apiscraper.standings.avro.topic.v1", League.getClassSchema());   
    }

    public CustomKafkaAvroDeserializer(SchemaRegistryClient client) {
        super(new MockSchemaRegistryClient());
    }

    public CustomKafkaAvroDeserializer(SchemaRegistryClient client, Map<String, ?> props) {
        super(new MockSchemaRegistryClient(), props);
    }
    
}

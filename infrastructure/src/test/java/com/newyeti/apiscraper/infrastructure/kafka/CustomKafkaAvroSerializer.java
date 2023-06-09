package com.newyeti.apiscraper.infrastructure.kafka;

import java.util.Map;

import com.newyeti.apiscraper.domain.model.avro.schema.League;

import io.confluent.kafka.schemaregistry.client.MockSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class CustomKafkaAvroSerializer extends KafkaAvroSerializer {
    public CustomKafkaAvroSerializer() throws Exception {
        super();
        super.schemaRegistry = new MockSchemaRegistryClient();
        super.schemaRegistry.register("avro.topic.standings.v1", League.getClassSchema());
    }

    public CustomKafkaAvroSerializer(SchemaRegistryClient client) {
        super(new MockSchemaRegistryClient());
    }

    public CustomKafkaAvroSerializer(SchemaRegistryClient client, Map<String, ?> props) {
        super(new MockSchemaRegistryClient(), props);
    }
}
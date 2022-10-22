package com.peaqock.aml.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class RawJsonSerializer extends StdSerializer<String> {
    public RawJsonSerializer() {
        this(null);
    }

    public RawJsonSerializer(Class<String> t) {
        super(t);
    }

    @Override
    public void serialize(String data, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(new ObjectMapper().readTree(data));
    }

}

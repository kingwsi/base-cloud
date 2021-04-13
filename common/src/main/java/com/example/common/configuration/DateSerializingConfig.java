package com.example.common.configuration;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * description: 全局日期序列化格式定义 <br>
 * date: 2021/04/07 16:40 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@JsonComponent
public class DateSerializingConfig {

    private final String pattern = "yyyy-MM-dd HH:mm:ss";

    @Bean
    public DateTimeFormatter format() {
        return DateTimeFormatter.ofPattern(pattern);
    }

    @Bean
    public ObjectMapper serializingObjectMapper(DateTimeFormatter format) {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(format));
        javaTimeModule.addSerializer(Instant.class, new JsonSerializer<Instant>() {
            @Override
            public void serialize(Instant instant, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                if (instant == null) {
                    return;
                }
                String jsonValue = format.format(instant.atZone(ZoneId.systemDefault()));
                jsonGenerator.writeString(jsonValue);
            }
        });
        javaTimeModule.addSerializer(Date.class, new DateSerializer(false, new SimpleDateFormat(pattern)));
        javaTimeModule.addDeserializer(Instant.class, new JsonDeserializer<Instant>() {
            @Override
            public Instant deserialize(JsonParser p, DeserializationContext ctxt)
                    throws IOException {
                String dateString = p.getText().trim();
                if (StringUtils.isNotBlank(dateString)) {
                    LocalDateTime parse = LocalDateTime.parse(dateString, format);
                    //+8 小时，offset 可以理解为时间偏移量
                    ZoneOffset offset = OffsetDateTime.now().getOffset();
                    return parse.toInstant(offset);
                }
                return null;
            }
        });
        return new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(javaTimeModule);
    }
}
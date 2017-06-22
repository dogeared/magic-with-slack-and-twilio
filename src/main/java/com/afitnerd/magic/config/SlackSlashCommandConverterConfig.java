package com.afitnerd.magic.config;

import com.afitnerd.magic.converter.SlackSlashCommandConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SlackSlashCommandConverterConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        SlackSlashCommandConverter slackSlashCommandConverter = new SlackSlashCommandConverter();
        MediaType mediaType = new MediaType("application","x-www-form-urlencoded", Charset.forName("UTF-8"));
        slackSlashCommandConverter.setSupportedMediaTypes(Arrays.asList(mediaType));
        converters.add(slackSlashCommandConverter);
        super.configureMessageConverters(converters);
    }
}
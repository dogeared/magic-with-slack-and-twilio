package com.afitnerd.magic.converter;

import com.afitnerd.magic.model.SlackSlashCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.Map;

public class SlackSlashCommandConverter extends AbstractHttpMessageConverter<SlackSlashCommand> {

    // no need to reinvent the wheel for parsing the query string
    private static final FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected boolean supports(Class<?> clazz) {
        return (SlackSlashCommand.class == clazz);
    }

    @Override
    protected SlackSlashCommand readInternal(Class<? extends SlackSlashCommand> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        Map<String, String> vals = formHttpMessageConverter.read(null, inputMessage).toSingleValueMap();

        return mapper.convertValue(vals, SlackSlashCommand.class);
    }

    @Override
    protected void writeInternal(SlackSlashCommand slackSlashCommand, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
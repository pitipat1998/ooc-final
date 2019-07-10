package io.muic.ooc.formatconverter.format;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OracleFriendlyFormatConverter implements FormatConverter{

    @Override
    public String convertClass(String name) {
        List<String> words = splitCamelCase(name);
        for (int i = 0; i < words.size() ; i++)
            words.set(i, StringUtils.upperCase(words.get(i)));
        return StringUtils.join(words, '_');
    }

    @Override
    public String convertProperty(String name) { return convertClass(name); }

}

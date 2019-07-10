package io.muic.ooc.formatconverter.format;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MySQLFriendlyWithPrefixFormatConverter implements FormatConverter{

    @Autowired
    private SnakeCaseFormatConverter snakeCaseFormatConverter;

    @Override
    public String convertClass(String name) {
        return "tbl_" + snakeCaseFormatConverter.convertClass(name);
    }

    @Override
    public String convertProperty(String name) {
        return snakeCaseFormatConverter.convertProperty(name);
    }

}

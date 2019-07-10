package io.muic.ooc.formatconverter.format;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OracleFriendlyWithPrefixFormatConverter implements FormatConverter{

    @Autowired
    private OracleFriendlyFormatConverter oracleFriendlyFormatConverter;

    @Override
    public String convertClass(String name) {
        return "TBL_" + oracleFriendlyFormatConverter.convertClass(name);
    }

    @Override
    public String convertProperty(String name) {
        return oracleFriendlyFormatConverter.convertProperty(name);
    }

}

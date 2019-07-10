package io.muic.ooc.formatconverter.format;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class LowerCaseFormatConverter implements FormatConverter{

    @Override
    public String convertClass(String name) { return StringUtils.lowerCase(name); }

    @Override
    public String convertProperty(String name) { return StringUtils.lowerCase(name); }

}

package io.muic.ooc.formatconverter.format;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CaseUtils;
import org.springframework.stereotype.Component;

@Component
public class PascalCaseFormatConverter implements FormatConverter{

    @Override
    public String convertClass(String name) {
        return CaseUtils.toCamelCase(
                StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(name), " "),
                true,
                null);
    }

    @Override
    public String convertProperty(String name) { return convertClass(name); }

}

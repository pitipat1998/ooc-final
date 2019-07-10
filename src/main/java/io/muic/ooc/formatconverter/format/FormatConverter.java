package io.muic.ooc.formatconverter.format;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface FormatConverter {

    String convertClass(String name);
    String convertProperty(String name);
    default List<String> splitCamelCase(String name){
        return Arrays.stream(StringUtils.splitByCharacterTypeCamelCase(name)).collect(Collectors.toList());
    }

}

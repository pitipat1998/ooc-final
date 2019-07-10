package io.muic.ooc.formatconverter;

import io.muic.ooc.formatconverter.format.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FormatConverterService {

    private Set<String> types;
    private Map<String, FormatConverter> converterMap;

    @Autowired
    public FormatConverterService(CamelCaseFormatConverter camelCaseFormatConverter,
                                  SnakeCaseFormatConverter snakeCaseFormatConverter,
                                  LowerCaseFormatConverter lowerCaseFormatConverter,
                                  UpperCaseFormatConverter upperCaseFormatConverter,
                                  KebabCaseFormatConverter kebabCaseFormatConverter,
                                  PascalCaseFormatConverter pascalCaseFormatConverter,
                                  OracleFriendlyFormatConverter oracleFriendlyFormatConverter,
                                  MySQLFriendlyWithPrefixFormatConverter mySQLFriendlyWithPrefixFormatConverter,
                                  OracleFriendlyWithPrefixFormatConverter oracleFriendlyWithPrefixFormatConverter){
        types = new HashSet<>();
        types.add("class");
        types.add("property");

        converterMap = new ConcurrentHashMap<>();
        converterMap.put("camelCase", camelCaseFormatConverter);
        converterMap.put("snake_case", snakeCaseFormatConverter);
        converterMap.put("lowercase", lowerCaseFormatConverter);
        converterMap.put("UPPERCASE", upperCaseFormatConverter);
        converterMap.put("kebab-case", kebabCaseFormatConverter);
        converterMap.put("PascalCase", pascalCaseFormatConverter);
        converterMap.put("MySQLFriendly", snakeCaseFormatConverter);
        converterMap.put("OracleFriendly", oracleFriendlyFormatConverter);
        converterMap.put("MySQLFriendlyWithPrefix", mySQLFriendlyWithPrefixFormatConverter);
        converterMap.put("OracleFriendlyWithPrefix", oracleFriendlyWithPrefixFormatConverter);

    }


    public String convert(FormatConverterDTO formatConverterDTO){
        if(!isValid(formatConverterDTO)) return null;
        FormatConverter converter = converterMap.get(formatConverterDTO.getFormat());
        String type = formatConverterDTO.getType();
        if(type.equals("class"))
            return converter.convertClass(formatConverterDTO.getName());
        else
            return converter.convertProperty(formatConverterDTO.getName());
    }

    public boolean isValid(FormatConverterDTO formatConverterDTO){
        return converterMap.containsKey(formatConverterDTO.getFormat()) &&
                types.contains(formatConverterDTO.getType());
    }

}

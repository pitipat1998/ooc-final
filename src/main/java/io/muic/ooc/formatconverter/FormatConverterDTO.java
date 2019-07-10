package io.muic.ooc.formatconverter;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class FormatConverterDTO {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String type;

    @NotNull
    @NotBlank
    private String format;

}

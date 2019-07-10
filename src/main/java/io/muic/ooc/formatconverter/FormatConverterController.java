package io.muic.ooc.formatconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class FormatConverterController {

    @Autowired
    private FormatConverterService formatConverterService;

    @PostMapping("/convert")
    public ResponseEntity<String> convert(@Valid @RequestBody FormatConverterDTO formatConverterDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return ResponseEntity.badRequest().body("Invalid Input");

        if(!formatConverterService.isValid(formatConverterDTO))
            return ResponseEntity.badRequest().body("Type Unknown");

        String convertedString = formatConverterService.convert(formatConverterDTO);
        if(convertedString == null)
            return ResponseEntity.badRequest().body("Type Unknown");

        return ResponseEntity.ok().body(convertedString);
    }

}

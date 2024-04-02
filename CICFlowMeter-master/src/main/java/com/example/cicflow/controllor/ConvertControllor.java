package com.example.cicflow.controllor;

import com.example.cicflow.utils.ProcessUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ConvertControllor
 * @Description TODO
 * @Author YU
 * @Date 2023/11/17 10:26
 * @Version 1.0
 **/

@RestController
public class ConvertControllor {

    @PostMapping("/convert")
    public String convert(@RequestParam String inputFile, @RequestParam String outputFile) {
        ProcessUtil.executeGradleTask(inputFile,outputFile);
        return "Conversion completed!";
    }
}

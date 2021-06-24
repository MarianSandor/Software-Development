package com.example.pcpartshop.controller;

import com.example.pcpartshop.dto.ConfigurationCreationDto;
import com.example.pcpartshop.dto.ConfigurationDto;
import com.example.pcpartshop.service.ConfigurationService;
import com.example.pcpartshop.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.example.pcpartshop.UrlMapping.*;

@RestController
@RequestMapping(value = CONFIGURATIONS)
@RequiredArgsConstructor
public class ConfigurationController {

    private final ConfigurationService configurationService;
    private final ReportService reportService;

    @GetMapping
    public List<ConfigurationDto> allConfigurations() {
        return configurationService.findAll();
    }

    @GetMapping(ENTITY)
    public ConfigurationDto getConfiguration(@PathVariable Long id) {
        return configurationService.get(id);
    }

    @PostMapping
    public ConfigurationDto create(@RequestBody ConfigurationCreationDto configuration) {
        return configurationService.create(configuration);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        configurationService.delete(id);
    }

    @GetMapping(EXPORT_REPORT)
    public void exportReport(@PathVariable Long id, HttpServletResponse response) throws IOException {
        String fileName = reportService.export(id);

        InputStream inputStream = new FileInputStream(fileName);
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
        inputStream.close();
    }
}

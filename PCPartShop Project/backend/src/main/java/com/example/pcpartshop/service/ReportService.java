package com.example.pcpartshop.service;

import com.example.pcpartshop.model.Configuration;
import com.example.pcpartshop.model.part.*;
import com.example.pcpartshop.repository.ConfigurationRepository;
import com.example.pcpartshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ConfigurationRepository configurationRepository;

    public String export(Long id) throws IOException {
        Configuration configuration = configurationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Configuration not found: " + id));

        StringBuilder fileName = new StringBuilder("Configuration_" + id);
        fileName.append("_");
        fileName.append(configuration.getUser().getUsername());
        fileName.append(".pdf");

        File pdfOutputFile = new File(fileName.toString());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contents = new PDPageContentStream(document, page);

        contents.beginText();
        contents.setFont(PDType1Font.HELVETICA_BOLD, 20);
        contents.newLineAtOffset(220, 700);
        contents.showText("Configuration");
        contents.endText();

        contents.beginText();
        contents.setFont(PDType1Font.TIMES_BOLD, 14);
        contents.newLineAtOffset(70, 600);
        contents.setLeading(15f);

        contents.showText(formatCPU(configuration.getCpu()));
        contents.newLine();
        contents.showText(formatGPU(configuration.getGpu()));
        contents.newLine();
        contents.showText(formatMemory(configuration.getMemory()));
        contents.newLine();
        contents.showText(formatMotherboard(configuration.getMotherboard()));
        contents.newLine();
        contents.showText(formatPSU(configuration.getPsu()));
        contents.newLine();
        contents.showText(formatStorage(configuration.getStorage()));
        contents.newLine();
        contents.showText(formatPcCase(configuration.getPcCase()));
        contents.newLine();
        contents.showText("Total: " + String.format("%.2f",configuration.getTotal()));
        contents.newLine();

        contents.newLine();
        contents.showText(configuration.getDescription());

        contents.newLine();
        contents.newLine();
        contents.setLeading(50f);
        contents.showText("Date: " + configuration.getDateCreated().format(formatter));

        contents.endText();

        contents.close();

        document.save(pdfOutputFile);
        document.close();

        return fileName.toString();
    }

    private static String formatCPU(CPU cpu) {
        return  "Processor: " +
                cpu.getBrand() + " " +
                cpu.getModel() + " " +
                cpu.getCores() + " cores " +
                cpu.getFrequency() + "ghz " +
                cpu.getIntegratedGraphics() + " " +
                cpu.getPrice() + "$";
    }

    private static String formatGPU(GPU gpu) {
        return  "Graphics Card: " +
                gpu.getBrand() + " " +
                gpu.getModel() + " " +
                gpu.getVram() + "GB " +
                gpu.getFrequency() + "ghz " +
                gpu.getPrice() + "$";
    }

    private static String formatMemory(Memory memory) {
        return  "Memory: " +
                memory.getBrand() + " " +
                memory.getModel() + " " +
                memory.getType() + " " +
                memory.getFrequency() + "ghz " +
                memory.getSize() + "GB " +
                memory.getPrice() + "$";
    }

    private static String formatMotherboard(Motherboard motherboard) {
        return  "Motherboard: " +
                motherboard.getBrand() + " " +
                motherboard.getModel() + " " +
                motherboard.getFormFactor() + " " +
                motherboard.getSocket() + " " +
                motherboard.getPrice() + "$";
    }

    private static String formatPcCase(PcCase pcCase) {
        return  "PC Case: " +
                pcCase.getBrand() + " " +
                pcCase.getModel() + " " +
                pcCase.getFormFactor() + " " +
                pcCase.getPrice() + "$";
    }

    private static String formatPSU(PSU psu) {
        return  "Power Supply: " +
                psu.getBrand() + " " +
                psu.getModel() + " " +
                psu.getFormFactor() + " " +
                psu.getWattage() + "W " +
                psu.getEfficiency() + " " +
                psu.getPrice() + "$";
    }

    private static String formatStorage(Storage storage) {
        return  "Hard Disk Drive: " +
                storage.getBrand() + " " +
                storage.getModel() + " " +
                storage.getCapacity() + "GB " +
                storage.getType() + " " +
                storage.getPrice() + "$";
    }
}
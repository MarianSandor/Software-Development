package com.example.book_store.report;

import com.example.book_store.repository.BookRepository;
import com.example.book_store.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static com.example.book_store.report.ReportType.CSV;
import static com.example.book_store.report.ReportType.PDF;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReportServiceFactoryTest {

    @Autowired
    private ReportServiceFactory reportServiceFactory;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void getReportService() throws IOException {
        ReportService csvReportService = reportServiceFactory.getReportService(CSV);
        csvReportService.export();

        ReportService pdfReportService = reportServiceFactory.getReportService(PDF);
        pdfReportService.export();
    }
}
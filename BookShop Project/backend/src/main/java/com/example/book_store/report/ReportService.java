package com.example.book_store.report;

import java.io.IOException;

public interface ReportService {
    void export() throws IOException;

    ReportType getType();

    String getFileName();
}

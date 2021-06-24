package com.example.book_store.report;

import com.example.book_store.model.Book;
import com.example.book_store.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import static com.example.book_store.repository.specifications.BookSpecifications.outOfStock;

@RequiredArgsConstructor
@Service
public class CSVReportService implements ReportService{

    private final BookRepository bookRepository;
    private final String fileName = "BooksOutOfStock.csv";

    @Override
    public void export() throws IOException {
         File csvOutputFile = new File(fileName);
         String data = bookRepository.findAll(outOfStock()).stream()
                            .map(CSVReportService::commaSeparateFields)
                            .collect(Collectors.joining("\n"));

        PrintWriter pw = new PrintWriter(csvOutputFile);
        pw.print("ID,TITLE,AUTHOR,GENRE,PRICE\n");
        pw.print(data);

        pw.close();
    }

    @Override
    public ReportType getType() {
        return ReportType.CSV;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    private static String commaSeparateFields(Book book) {
        return book.getId() + "," +
                book.getTitle() + "," +
                book.getAuthor() + "," +
                book.getGenre() + "," +
                book.getPrice();
    }
}

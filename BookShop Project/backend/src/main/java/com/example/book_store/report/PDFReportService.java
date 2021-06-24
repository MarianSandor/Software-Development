package com.example.book_store.report;

import com.example.book_store.model.Book;
import com.example.book_store.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.book_store.repository.specifications.BookSpecifications.outOfStock;

@RequiredArgsConstructor
@Service
public class PDFReportService implements ReportService{

    private final BookRepository bookRepository;
    private final String fileName = "BooksOutOfStock.pdf";

    @Override
    public void export() throws IOException {
        File pdfOutputFile = new File(fileName);
        List<String> data = bookRepository.findAll(outOfStock()).stream()
                .map(PDFReportService::formatBook)
                .collect(Collectors.toList());

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contents = new PDPageContentStream(document, page);

        contents.beginText();
        contents.setFont(PDType1Font.HELVETICA_BOLD, 20);
        contents.newLineAtOffset(220, 700);
        contents.showText("Books out of stock");
        contents.endText();

        contents.beginText();
        contents.setFont(PDType1Font.TIMES_BOLD, 14);
        contents.newLineAtOffset(50, 600);
        contents.setLeading(15f);
        for (String line : data) {
            contents.showText(line);
            contents.newLine();
        }
        contents.endText();

        contents.close();

        document.save(pdfOutputFile);
        document.close();
    }

    @Override
    public ReportType getType() {
        return ReportType.PDF;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    private static String formatBook(Book book) {
        return "id: " + book.getId() + "; " +
                "title: \"" + book.getTitle() + "\"; " +
                "author: \"" + book.getAuthor() + "\"; " +
                "genre: \"" + book.getGenre() + "\"; " +
                "price: " + book.getPrice() + ";";
    }
}

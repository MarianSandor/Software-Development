package com.example.book_store.controller;

import com.example.book_store.dto.BookDTO;
import com.example.book_store.report.ReportServiceFactory;
import com.example.book_store.report.ReportType;
import com.example.book_store.service.BookService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.example.book_store.UrlMapping.*;

@RestController
@RequestMapping(value = BOOKS)
@RequiredArgsConstructor
public class BooksController {

    private final BookService bookService;
    private final ReportServiceFactory reportServiceFactory;

    @GetMapping
    public List<BookDTO> allBooks() {
        return bookService.findAll();
    }

    @GetMapping(SEARCH_BOOKS + QUERY)
    public List<BookDTO> allBooksBy(@PathVariable String query){
        return bookService.search(query);
    }

    @GetMapping(ENTITY)
    public BookDTO getItem(@PathVariable Long id) {
        return bookService.get(id);
    }

    @PostMapping
    public BookDTO create(@RequestBody BookDTO book) {
        return bookService.create(book);
    }

    @PatchMapping(ENTITY)
    public BookDTO updateQuantity(@PathVariable Long id, @RequestBody int quantity) {
        return bookService.updateQuantity(id, quantity);
    }

    @PutMapping(ENTITY)
    public BookDTO edit(@PathVariable Long id, @RequestBody BookDTO book) {
        return bookService.edit(id, book);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    @GetMapping(EXPORT_REPORT)
    public void exportReport(@PathVariable ReportType type, HttpServletResponse response) throws IOException {
        reportServiceFactory.getReportService(type).export();

        InputStream inputStream = new FileInputStream(reportServiceFactory.getReportService(type).getFileName());
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
        inputStream.close();
    }
}

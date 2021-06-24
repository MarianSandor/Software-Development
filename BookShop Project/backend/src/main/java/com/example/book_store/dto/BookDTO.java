package com.example.book_store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO{
    private Long id;
    private String title;
    private String author;
    private String genre;
    private int quantity;
    private double price;
}

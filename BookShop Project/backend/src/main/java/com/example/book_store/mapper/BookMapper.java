package com.example.book_store.mapper;

import com.example.book_store.dto.BookDTO;
import com.example.book_store.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toDTO(Book book);

    Book fromDTO(BookDTO book);
}

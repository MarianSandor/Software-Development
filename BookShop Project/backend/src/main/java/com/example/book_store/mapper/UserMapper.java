package com.example.book_store.mapper;

import com.example.book_store.dto.UserDTO;
import com.example.book_store.model.ERole;
import com.example.book_store.model.Role;
import com.example.book_store.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Iterator;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "roles", target = "role", qualifiedByName = "getRole")
    UserDTO toDTO(User user);

    @Named("getRole")
    static ERole getRole(Set<Role> roles) {
        if (!roles.isEmpty()) {
            Iterator<Role> it = roles.iterator();

            if (it.hasNext()) {
                return it.next().getName();
            }
        }

        return null;
    }
}

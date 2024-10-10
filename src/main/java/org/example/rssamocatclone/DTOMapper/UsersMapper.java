package org.example.rssamocatclone.DTOMapper;

import org.example.rssamocatclone.dto.UsersDTO;
import org.example.rssamocatclone.models.Users;

public class UsersMapper {
    public static UsersDTO toDTO(Users user) {
        UsersDTO dto = new UsersDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhoneNumber(user.getPhoneNumber());
        return dto;
    }

    public static Users toEntity(UsersDTO dto) {
        Users user = new Users();
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
        return user;
    }
}

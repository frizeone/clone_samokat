package org.example.rssamocatclone.Services;


import org.example.rssamocatclone.dto.UsersDTO;

import java.util.List;

public interface UsersService {

    List<UsersDTO> getAllUsers();

    UsersDTO getUserById(int id);

    UsersDTO createUser(UsersDTO usersDTO);

    UsersDTO updateUser(int id, UsersDTO usersDTO);

    void deleteUser(int id);

}

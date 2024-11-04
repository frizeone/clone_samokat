package org.example.rssamocatclone.Services;


import org.example.rssamocatclone.dto.UsersDTO;
import org.example.rssamocatclone.models.Users;

import java.util.List;

public interface UsersService {

    List<UsersDTO> getAllUsers();

    UsersDTO getUserById(int id);

    UsersDTO createUser(UsersDTO usersDTO);

    UsersDTO updateUser(int id, UsersDTO usersDTO);

    void deleteUser(int id);

    void createUser(Users user);

}

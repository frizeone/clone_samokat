package org.example.rssamocatclone.Controllers;

import org.example.rssamocatclone.Services.UsersService; // Убедитесь, что у вас есть сервис для работы с пользователями
import org.example.rssamocatclone.dto.UserInput;
import org.example.rssamocatclone.models.Users;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class UsersGraphQLController {

    private final UsersService usersService;

    public UsersGraphQLController(UsersService usersService) {
        this.usersService = usersService;
    }

    @MutationMapping
    public void createUser(@Argument("userInput") UserInput userInput) {

        if (userInput == null || userInput.getEmail() == null || userInput.getPasswordHash() == null) {
            throw new IllegalArgumentException("Invalid user input");
        }

        Users newUser = new Users();
        newUser.setId(userInput.getId());
        newUser.setEmail(userInput.getEmail());
        newUser.setPasswordHash(userInput.getPasswordHash());
        newUser.setFirstName(userInput.getFirstName());
        newUser.setLastName(userInput.getLastName());
        newUser.setPhoneNumber(userInput.getPhoneNumber());
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

         usersService.createUser(newUser);
    }
}


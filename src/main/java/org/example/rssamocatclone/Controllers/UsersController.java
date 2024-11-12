package org.example.rssamocatclone.Controllers;

import org.example.rssamocatclone.Services.UsersService;
import org.example.rssamocatclone.dto.UsersDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final RabbitTemplate rabbitTemplate;

    private final UsersService usersService;

    static final String EXCHANGE_NAME = "testExchange";

    public UsersController(RabbitTemplate rabbitTemplate, UsersService usersService) {
        this.rabbitTemplate = rabbitTemplate;
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        List<UsersDTO> users = usersService.getAllUsers();
        System.out.println("Сработал метод контроллера: " + UsersController.class.getName() + "Метод getAllUser");
        for (int i = 0; i < users.size(); i++) {
            UsersDTO usersDTO = users.get(i);
            String massage = "[UsersController] [Метод getAllUsers] " + usersDTO.logMethod();
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, "first.key",massage);
            System.out.println("Сообщение отправленно в ЛОГ");
        }
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> getUserById(@PathVariable int id) {
        UsersDTO user = usersService.getUserById(id);
        System.out.println("Сработал метод контроллера: " + UsersController.class.getName() + "Метод getAllUser");
        if (user != null) {
            String massage = "[UsersController] [Метод getUserById] [+] " + user.logMethod();
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, "first.key",massage);
            return ResponseEntity.ok(user);
        } else {
            String massage = "[UsersController] [Метод getUserById] [-] " + user.logMethod();
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, "first.key",massage);
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO usersDTO) {
        UsersDTO newUser = usersService.createUser(usersDTO);
        String massage = "[Метод createUser] [Создан новый пользователь] " + newUser.logMethod();
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "first.key",massage);
        return ResponseEntity.ok(newUser);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UsersDTO> updateUser(@PathVariable int id, @RequestBody UsersDTO usersDTO) {
        UsersDTO updatedUser = usersService.updateUser(id, usersDTO);
        if (updatedUser != null) {
            String massage = "[Метод updateUser] [+] [" + id + "] " + updatedUser.logMethod();
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, "first.key",massage);
            return ResponseEntity.ok(updatedUser);
        } else {
            String massage = "[Метод updateUser] [-] [Пользователь  с id = {" + id + "} ]  Не был найден";
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, "first.key",massage);
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        String massage = "[Метод deleteUser] Пользователься с id = {" + id + "} был удален";
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "first.key",massage);
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

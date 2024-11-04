package org.example.rssamocatclone.Services.ServicesImpl;

import jakarta.transaction.Transactional;
import org.example.rssamocatclone.DTOMapper.UsersMapper;
import org.example.rssamocatclone.Services.UsersService;
import org.example.rssamocatclone.dto.UsersDTO;
import org.example.rssamocatclone.models.Users;
import org.example.rssamocatclone.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository userRepository;

    public UsersServiceImpl(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UsersDTO> getAllUsers() {
        List<Users> users = userRepository.findAll();
        return users.stream().map(UsersMapper::toDTO).collect(Collectors.toList());
    }

    public UsersDTO getUserById(int id) {
        Optional<Users> user = userRepository.findById(id);
        return user.map(UsersMapper::toDTO).orElse(null);
    }

    public UsersDTO createUser(UsersDTO usersDTO) {
        Users user = UsersMapper.toEntity(usersDTO);
        Users savedUser = userRepository.save(user);
        return UsersMapper.toDTO(savedUser);
    }


    public UsersDTO updateUser(int id, UsersDTO usersDTO) {
        Optional<Users> currentUser = userRepository.findById(id);
        if (currentUser.isPresent()) {
            Users user = UsersMapper.toEntity(usersDTO);
            user.setFirstName(currentUser.get().getFirstName());
            user.setLastName(currentUser.get().getLastName());
            user.setEmail(currentUser.get().getEmail());
            user.setPhoneNumber(currentUser.get().getPhoneNumber());
            Users savedUser = userRepository.save(user);
            return UsersMapper.toDTO(savedUser);
        }
        return null;
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void createUser(Users user) {
         userRepository.save(user);
    }
}

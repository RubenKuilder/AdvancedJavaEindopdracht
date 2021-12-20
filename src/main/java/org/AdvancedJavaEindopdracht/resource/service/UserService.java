package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.ConvertToDTO;
import org.AdvancedJavaEindopdracht.resource.dto.UserDTO;
import org.AdvancedJavaEindopdracht.resource.model.User;
import org.AdvancedJavaEindopdracht.resource.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final ConvertToDTO convertToDTO = new ConvertToDTO();

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<UserDTO> getUsers() { return userRepository.getUsers().stream().map(convertToDTO::toUserDTO).collect(Collectors.toList());}

    public UserDTO getUser(Integer id){
        return convertToDTO.toUserDTO(userRepository.getUser(id));
    }

    public UserDTO create(User user){
        return convertToDTO.toUserDTO(userRepository.postUser(user));
    }

    public void update(User user, Integer id){
        userRepository.putUser(user, id);
    }

    public void delete(Integer id){
        userRepository.deleteUser(id);
    }
}

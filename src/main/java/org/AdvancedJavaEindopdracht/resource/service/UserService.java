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

    /**
     * Maps Entity to DTO and returns a list of all users.
     *
     * @return      response entity with list of all users
     */
    public List<UserDTO> getUsers() { return userRepository.getUsers().stream().map(convertToDTO::toUserDTO).collect(Collectors.toList());}

    /**
     * Maps Entity to DTO and returns a single user.
     *
     * @param id    id of the user to find
     * @return      response entity with single user
     */
    public UserDTO getUser(Integer id){
        return convertToDTO.toUserDTO(userRepository.getUser(id));
    }

    /**
     * Maps Entity to DTO and posts a single user.
     *
     * @param user  user to post
     * @return      response entity with posted user
     */
    public UserDTO create(User user){
        return convertToDTO.toUserDTO(userRepository.postUser(user));
    }

    /**
     * Maps Entity to DTO and puts a single user.
     *
     * @param id    id of the user to put
     * @param user  user to put
     * @return      response entity with put user
     */
    public UserDTO update(User user, Integer id){
        return convertToDTO.toUserDTO(userRepository.putUser(user, id));
    }

    /**
     * Maps Entity to DTO and deletes a single user.
     *
     * @param id    id of the user to delete
     * @return      response entity with deleted user
     */
    public UserDTO delete(Integer id){
        return convertToDTO.toUserDTO(userRepository.getUser(id));
    }
}

package org.eindopdracht.resource.service;

import org.eindopdracht.resource.dto.UserDTO;
import org.eindopdracht.resource.exception.general.BadRequestException;
import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.exception.general.NoContentException;
import org.eindopdracht.resource.mapper.UserMapper;
import org.eindopdracht.resource.model.User;
import org.eindopdracht.resource.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Maps Entity to DTO and returns a list of all users.
     *
     * @return response entity with list of all users
     */
    public List<UserDTO> getUsers() {
        return userMapper.mapFromEntityList(userRepository.getUsers());
    }

    /**
     * Maps Entity to DTO and returns a single user.
     *
     * @param id id of the user to find
     * @return response entity with single user
     */
    public UserDTO getUser(Integer id) {
        try {
            return userMapper.mapFromEntity(userRepository.getUser(id));
        } catch (Exception ex) {
            throw new DataNotFoundException("id: " +id);
        }
    }

    /**
     * Maps Entity to DTO and posts a single user.
     *
     * @param userDTO user to post
     * @return response entity with posted user
     */
    public UserDTO create(UserDTO userDTO) {
        try {
            return userMapper.mapFromEntity(
                    userRepository.postUser(userMapper.mapToEntity(userDTO))
            );
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and puts a single user.
     *
     * @param id   id of the user to put
     * @param userDTO user to put
     * @return response entity with put user
     */
    public UserDTO update(UserDTO userDTO, Integer id) {
        try {
            return userMapper.mapFromEntity(userRepository.putUser(userMapper.mapToEntity(userDTO), id));
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and deletes a single user.
     *
     * @param id id of the user to delete
     * @return response entity with deleted user
     */
    public UserDTO delete(Integer id) {
        try {
            return userMapper.mapFromEntity(userRepository.deleteUser(id));
        } catch (Exception ex) {
            throw new NoContentException("id: " +id);
        }
    }
}

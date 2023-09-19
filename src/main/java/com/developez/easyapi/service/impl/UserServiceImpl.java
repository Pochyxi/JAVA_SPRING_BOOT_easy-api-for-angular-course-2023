package com.developez.easyapi.service.impl;

import com.developez.easyapi.entity.User;
import com.developez.easyapi.payload.UserDto;
import com.developez.easyapi.repository.UserRepository;
import com.developez.easyapi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl( UserRepository userRepository, ModelMapper modelMapper ) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public UserDto createUser( UserDto userDto ) {
        User user = mapToEntity( userDto );
        return mapToDto( userRepository.save( user ));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map( this::mapToDto ).collect( java.util.stream.Collectors.toList());
    }

    @Override
    public UserDto getUserById( Long id ) {
        User user = userRepository.findById( id ).orElseThrow( () -> new RuntimeException( "Utente non trovato" ));
        return mapToDto( user );
    }

    @Override
    public UserDto updateUser( UserDto userDto, Long id ) {
        User user = userRepository.findById( id ).orElseThrow( () -> new RuntimeException( "Utente non trovato" ));

        user.setName( userDto.getName() );
        user.setLastname( userDto.getLastname() );
        user.setEmail( userDto.getEmail() );
        user.setFiscalcode( userDto.getFiscalcode() );
        user.setProvince( userDto.getProvince() );
        user.setPhone( userDto.getPhone() );
        user.setAge( userDto.getAge() );

        return mapToDto( userRepository.save( user ));
    }

    @Override
    public void deleteUser( Long id ) {
        User user = userRepository.findById( id ).orElseThrow( () -> new RuntimeException( "Utente non trovato" ));
        userRepository.delete( user );
    }

    private UserDto mapToDto( User user ) {
        return modelMapper.map( user, UserDto.class );
    }

    private User mapToEntity( UserDto userDto ) {
        return modelMapper.map( userDto, User.class );
    }
}

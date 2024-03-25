package com.kahesama.demo.curso_spring_s12_api.services;

import com.kahesama.demo.curso_spring_s12_api.common.RolesCatalog;
import com.kahesama.demo.curso_spring_s12_api.entities.RoleEntity;
import com.kahesama.demo.curso_spring_s12_api.entities.UserEntity;
import com.kahesama.demo.curso_spring_s12_api.model.Role;
import com.kahesama.demo.curso_spring_s12_api.model.User;
import com.kahesama.demo.curso_spring_s12_api.model.mappers.persistence.UserPersistenceMapper;
import com.kahesama.demo.curso_spring_s12_api.repositories.RoleRepository;
import com.kahesama.demo.curso_spring_s12_api.repositories.UserRepository;
import com.kahesama.demo.curso_spring_s12_api.services.interfaces.UserServiceInt;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.kahesama.demo.curso_spring_s12_api.common.RolesCatalog.USER;

@Service
@RequiredArgsConstructor
public class UserServiceIntImpl implements UserServiceInt {
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final UserPersistenceMapper mapper;
//    private final PasswordEncoder passwordEncoder;

    /**
     * Retrieves a list of all users.
     *
     * @return List of User objects.
     */
    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return mapper.toUserList(repository.findAll());
    }

    /**
     * Saves a user.
     *
     * @param user The User object to be saved.
     * @return The saved User object.
     * @throws IllegalArgumentException if user is null.
     */
    @Transactional
    @Override
    public User save(User user) {
        Optional<RoleEntity> optionalUserRole = roleRepository.findByName(USER.getName());

        UserEntity userEntity = mapper.toUserEntity(user);

        optionalUserRole.ifPresent(roleEntity -> userEntity.getRoles().add(roleEntity));

        return mapper.toUser(repository.save(userEntity));
    }

    /*
    @Transactional(readOnly = true)
    @Override
    public User findById(Long id) {
        return repository.findById(id).map(mapper::toUser)
                .orElseThrow(UserNotFoundException::new);
    }


    @Transactional
    @Override
    public User update(Long id, User UserParam) {
        return repository.findById(id)
                .map(mapper::toUser)
                .map((User User) -> {
                    User.setName(UserParam.getName());
                    User.setDescription(UserParam.getDescription());
                    User.setPrice(UserParam.getPrice());

                    return saveFn(User);
                }).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        if(repository.findById(id).isEmpty()){
            throw new UserNotFoundException();
        }

        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsBySku(String sku) {
        return false;
    }

    private User saveFn(User User) {
        return mapper.toUser(
                repository.save(
                        mapper.toUserEntity(User)
                )
        );
    }
    */
}

package com.kahesama.demo.curso_spring_s12_api.model.mappers.persistence;

import com.kahesama.demo.curso_spring_s12_api.entities.UserEntity;
import com.kahesama.demo.curso_spring_s12_api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE //ignorar los campos que no se mapean
)
public interface UserPersistenceMapper {
    UserEntity toUserEntity(User user);

    User toUser(UserEntity entity);

    List<User> toUserList(List<UserEntity> entityList);
}

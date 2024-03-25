package com.kahesama.demo.curso_spring_s12_api.model.mappers.rest;
import com.kahesama.demo.curso_spring_s12_api.model.User;
import com.kahesama.demo.curso_spring_s12_api.model.request.UserCreateRequest;
import com.kahesama.demo.curso_spring_s12_api.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE //ignorar los campos que no se mapean
)
public interface UserRestMapper {
    User toUser(UserCreateRequest request);
    UserResponse toUserResponse(User user);
    List<UserResponse> toUserResponseList(List<User> userList);
}

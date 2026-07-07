package com.intellifinance.authservice.mapper;

import com.intellifinance.authservice.dto.request.RegisterRequest;
import com.intellifinance.authservice.dto.response.RegisterResponse;
import com.intellifinance.authservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.intellifinance.authservice.constants.AppConstants.USER_REGISTERED_SUCCESSFULLY;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterRequest request);

    @Mapping(source = "id", target = "userId")
    @Mapping(target = "message", constant = USER_REGISTERED_SUCCESSFULLY)
    RegisterResponse toRegisterResponse(User user);
}

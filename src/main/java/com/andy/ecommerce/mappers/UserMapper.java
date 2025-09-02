package com.andy.ecommerce.mappers;

import com.andy.ecommerce.dtos.reponse.GetAllUsersResponseDto;
import com.andy.ecommerce.dtos.reponse.RegisterUserAddressResponseDto;
import com.andy.ecommerce.dtos.reponse.RegisterUserResponseDto;
import com.andy.ecommerce.dtos.request.RegisterUserAddressRequestDto;
import com.andy.ecommerce.dtos.request.RegisterUserRequestDto;
import com.andy.ecommerce.entities.Address;
import com.andy.ecommerce.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User fromRegisterUserRequestDto(RegisterUserRequestDto registerUserRequestDto);
    Address fromRegisterUserAddressDto(RegisterUserAddressRequestDto registerUserAddressRequestDto);

    RegisterUserResponseDto toRegisterUserResponseDto(User user);
    RegisterUserAddressResponseDto toRegisterUserAddressResponseDto(Address address);

    GetAllUsersResponseDto toGetAllUsersResponseDto(User user);
}

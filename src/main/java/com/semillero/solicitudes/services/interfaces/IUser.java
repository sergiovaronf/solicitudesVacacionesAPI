package com.semillero.solicitudes.services.interfaces;

import com.semillero.solicitudes.models.request.UserRequest;
import com.semillero.solicitudes.models.responses.EmployeeResponse;
import com.semillero.solicitudes.models.responses.UserResponse;

import java.util.List;

public interface IUser extends ICrud<UserRequest, UserResponse, Integer> {

    List<UserResponse> getAll();
}

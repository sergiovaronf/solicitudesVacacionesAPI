package com.semillero.solicitudes.services.interfaces;

import com.semillero.solicitudes.models.request.UserRoleRequest;
import com.semillero.solicitudes.models.responses.SolicitudResponse;
import com.semillero.solicitudes.models.responses.UserRoleResponse;

import java.util.List;

public interface IUserRole extends ICrud<UserRoleRequest, UserRoleResponse, Integer> {
    List<UserRoleResponse> getAll();
}

package com.semillero.solicitudes.services.interfaces;

import com.semillero.solicitudes.models.request.EmployeeRequest;
import com.semillero.solicitudes.models.responses.EmployeeResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IEmployee extends ICrud<EmployeeRequest, EmployeeResponse, Integer> {
    List<EmployeeResponse> getAll();

    Page<EmployeeResponse> getAllPage(Integer page, Integer size);

}

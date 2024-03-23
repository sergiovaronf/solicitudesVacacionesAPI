package com.semillero.solicitudes.services.interfaces;

import com.semillero.solicitudes.models.request.SolicitudRequest;
import com.semillero.solicitudes.models.responses.SolicitudResponse;
import com.semillero.solicitudes.util.enums.SortType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISolicitud  extends ICrud<SolicitudRequest, SolicitudResponse, Integer>{
    List<SolicitudResponse> getAll();
    Page<SolicitudResponse> getAllPage(Integer page, Integer size, SortType sortType);
    List<SolicitudResponse> getAllEmployee(Integer id);
    Page<SolicitudResponse> getAllPageEmployee(Integer page, Integer size, Integer id);
    String FIELD_BY_SORT = "fechaCreacion";
}

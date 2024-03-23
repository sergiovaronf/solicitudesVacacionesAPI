package com.semillero.solicitudes.services.interfaces;

import com.semillero.solicitudes.models.request.PositionRequest;
import com.semillero.solicitudes.models.responses.PositionResponse;

import java.util.List;

public interface IPosition extends ICrud<PositionRequest, PositionResponse, Integer> {
    List<PositionResponse> getAll();
}

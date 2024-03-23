package com.semillero.solicitudes.services.interfaces;

public interface ICrud<RQ, RS, ID> {
    RS create(RQ request);
    RS read(ID id);
    RS update(RQ request, ID id);
    void delete(ID id);
}

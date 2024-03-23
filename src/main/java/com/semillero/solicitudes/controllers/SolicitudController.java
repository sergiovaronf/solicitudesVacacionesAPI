package com.semillero.solicitudes.controllers;

import com.semillero.solicitudes.models.request.SolicitudRequest;
import com.semillero.solicitudes.models.responses.SolicitudResponse;
import com.semillero.solicitudes.services.interfaces.ISolicitud;
import com.semillero.solicitudes.util.enums.SortType;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "request")
@AllArgsConstructor
public class SolicitudController {

    private final ISolicitud solicitudService;

    @PostMapping
    public ResponseEntity<SolicitudResponse> post(@Valid @RequestBody SolicitudRequest request){
        return ResponseEntity.ok(solicitudService.create(request));
    }

    @GetMapping()
    public ResponseEntity<List<SolicitudResponse>> getAll(){
        return ResponseEntity.ok(solicitudService.getAll());
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<SolicitudResponse>> getAllPage(@RequestParam Integer page, @RequestParam Integer size, @RequestParam(required = false) SortType sortType){
        if(Objects.isNull(sortType)) sortType = SortType.NONE;
        var response = solicitudService.getAllPage(page, size, sortType);
        return response.isEmpty()? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<SolicitudResponse> get(@PathVariable Integer id){
        return ResponseEntity.ok(solicitudService.read(id));
    }

    @GetMapping(path = "/employee/{id}")
    public ResponseEntity<List<SolicitudResponse>> getByEmployeeId(@PathVariable Integer id){
        return ResponseEntity.ok(solicitudService.getAllEmployee(id));
    }

    @GetMapping(path = "/employee/{id}", params = {"page", "size"})
    public ResponseEntity<Page<SolicitudResponse>> getByEmployeeIdAllPage(@PathVariable Integer id, @RequestParam Integer page, @RequestParam Integer size){
        var response = solicitudService.getAllPageEmployee(page, size, id);
        return response.isEmpty()? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<SolicitudResponse> put(@Valid @PathVariable Integer id,@Valid @RequestBody SolicitudRequest request){
        return ResponseEntity.ok(solicitudService.update(request, id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        this.solicitudService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package com.semillero.solicitudes.controllers;

import com.semillero.solicitudes.models.request.PositionRequest;
import com.semillero.solicitudes.models.responses.PositionResponse;
import com.semillero.solicitudes.models.responses.SolicitudResponse;
import com.semillero.solicitudes.services.interfaces.IPosition;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "position")
@AllArgsConstructor
public class PositionController {

    private final IPosition positionService;
    @PostMapping
    public ResponseEntity<PositionResponse> post(@RequestBody PositionRequest request){
        return  ResponseEntity.ok(positionService.create(request));
    }

    @GetMapping()
    public ResponseEntity<List<PositionResponse>> getAll(){
        return ResponseEntity.ok(positionService.getAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<PositionResponse> get(@PathVariable Integer id){
        return ResponseEntity.ok(positionService.read(id));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<PositionResponse> put(@PathVariable Integer id, @RequestBody PositionRequest request){
        return ResponseEntity.ok(positionService.update(request, id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        this.positionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

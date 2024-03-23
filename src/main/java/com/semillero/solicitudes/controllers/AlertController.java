package com.semillero.solicitudes.controllers;

import com.semillero.solicitudes.models.request.AlertRequest;
import com.semillero.solicitudes.models.request.PositionRequest;
import com.semillero.solicitudes.models.responses.AlertResponse;
import com.semillero.solicitudes.models.responses.PositionResponse;
import com.semillero.solicitudes.services.interfaces.IAlert;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "alert")
@AllArgsConstructor
public class AlertController {

    private final IAlert alertService;
    @PostMapping
    public ResponseEntity<AlertResponse> post(@RequestBody AlertRequest request){
        return  ResponseEntity.ok(alertService.create(request));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<AlertResponse> get(@PathVariable Integer id){
        return ResponseEntity.ok(alertService.read(id));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<AlertResponse> put(@PathVariable Integer id, @RequestBody AlertRequest request){
        return ResponseEntity.ok(alertService.update(request, id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        this.alertService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

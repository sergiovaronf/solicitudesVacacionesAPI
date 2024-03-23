package com.semillero.solicitudes.controllers;

import com.semillero.solicitudes.models.request.UserRoleRequest;
import com.semillero.solicitudes.models.responses.SolicitudResponse;
import com.semillero.solicitudes.models.responses.UserRoleResponse;
import com.semillero.solicitudes.services.interfaces.IUserRole;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "user-role")
@AllArgsConstructor
public class UserRoleController {

    private final IUserRole userRoleService;
    @PostMapping
    public ResponseEntity<UserRoleResponse> post(@RequestBody UserRoleRequest request){
        return  ResponseEntity.ok(userRoleService.create(request));
    }

    @GetMapping()
    public ResponseEntity<List<UserRoleResponse>> getAll(){
        return ResponseEntity.ok(userRoleService.getAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<UserRoleResponse> get(@PathVariable Integer id){
        return ResponseEntity.ok(userRoleService.read(id));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<UserRoleResponse> put(@PathVariable Integer id, @RequestBody UserRoleRequest request){
        return ResponseEntity.ok(userRoleService.update(request, id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        this.userRoleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

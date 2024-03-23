package com.semillero.solicitudes.controllers;

import com.semillero.solicitudes.models.request.PositionRequest;
import com.semillero.solicitudes.models.request.UserRequest;
import com.semillero.solicitudes.models.responses.EmployeeResponse;
import com.semillero.solicitudes.models.responses.PositionResponse;
import com.semillero.solicitudes.models.responses.UserResponse;
import com.semillero.solicitudes.services.interfaces.IUser;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "user")
@AllArgsConstructor
public class UserController {

    private final IUser userService;
    @PostMapping
    public ResponseEntity<UserResponse>post(@RequestBody UserRequest request){
        return  ResponseEntity.ok(userService.create(request));
    }

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<UserResponse> get(@PathVariable Integer id){
        return ResponseEntity.ok(userService.read(id));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<UserResponse> put(@PathVariable Integer id, @RequestBody UserRequest request){
        return ResponseEntity.ok(userService.update(request, id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

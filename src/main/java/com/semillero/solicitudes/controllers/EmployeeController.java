package com.semillero.solicitudes.controllers;

import com.semillero.solicitudes.models.request.EmployeeRequest;
import com.semillero.solicitudes.models.request.PositionRequest;
import com.semillero.solicitudes.models.responses.EmployeeResponse;
import com.semillero.solicitudes.models.responses.PositionResponse;
import com.semillero.solicitudes.services.interfaces.IEmployee;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "employee")
@AllArgsConstructor
public class EmployeeController {

    private final IEmployee employeeService;
    @PostMapping
    public ResponseEntity<EmployeeResponse> post(@Valid @RequestBody EmployeeRequest request){
        return  ResponseEntity.ok(employeeService.create(request));
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeResponse>> getAll(){
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<EmployeeResponse>> getAllPage(@RequestParam Integer page, @RequestParam Integer size){
        var response = employeeService.getAllPage(page, size);
        return response.isEmpty()? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<EmployeeResponse> get(@PathVariable Integer id){
        return ResponseEntity.ok(employeeService.read(id));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<EmployeeResponse> put(@PathVariable Integer id, @Valid @RequestBody EmployeeRequest request){
        return ResponseEntity.ok(employeeService.update(request, id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        this.employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

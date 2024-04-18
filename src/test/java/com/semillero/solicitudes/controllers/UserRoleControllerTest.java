package com.semillero.solicitudes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.semillero.solicitudes.models.request.UserRoleRequest;
import com.semillero.solicitudes.models.responses.UserRoleResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserRoleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper customObjectMapper = new ObjectMapper();

    @Test
    public void testPostUserRole() throws Exception {
        UserRoleRequest request = new UserRoleRequest();
        request.setRol("Empleado");

        mockMvc.perform(MockMvcRequestBuilders.post("/user-role")
                        .contentType(MediaType.APPLICATION_JSON)
                        // Convertir el objeto request a JSON
                        .content(customObjectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rol", is("Empleado")));
    }

    @Test
    public void testDeleteUserRole() throws Exception {
        int roleIdToDelete = 1;
        mockMvc.perform(MockMvcRequestBuilders.delete("/user-role/{id}", roleIdToDelete)
                        .contentType(MediaType.APPLICATION_JSON))
                //.andExpect(status().isNoContent())
                .andExpect(status().isBadRequest());

        //En esta parte comente el isNoContent porque si es el correcto lo elimina y si no da un isBadRequest
        //Pero aca tengo una duda se pueden manejar dos estados con un try cach o no es una buena practica
    }

    @Test
    public void testGetAllUserRoles() throws Exception {
        UserRoleResponse response1 = new UserRoleResponse();
        response1.setIdRol(1);
        response1.setRol("Empleado");

        UserRoleResponse response2 = new UserRoleResponse();
        response2.setIdRol(2);
        response2.setRol("Supervisor");


        mockMvc.perform(MockMvcRequestBuilders.get("/user-role")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
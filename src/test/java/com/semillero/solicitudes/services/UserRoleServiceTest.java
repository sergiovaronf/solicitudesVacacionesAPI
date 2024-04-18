package com.semillero.solicitudes.services;

import com.semillero.solicitudes.models.request.UserRoleRequest;
import com.semillero.solicitudes.models.responses.UserRoleResponse;
import com.semillero.solicitudes.persistence.entities.UserRoleEntity;
import com.semillero.solicitudes.persistence.repository.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class UserRoleServiceTest {

    @Mock
    private UserRoleRepository userRoleRepository;

    @InjectMocks
    private UserRoleService userRoleService;

    @Test
    public void testCreateUserRole() {
        UserRoleRequest request = new UserRoleRequest();
        request.setRol("Empleado");

        UserRoleEntity entity = new UserRoleEntity(1, "Empleado", LocalDate.now());

        Mockito.when(userRoleRepository.save(Mockito.any(UserRoleEntity.class))).thenReturn(entity);

        UserRoleResponse response = userRoleService.create(request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(entity.getRol(), response.getRol());
    }

    @Test
    public void testDeleteUserRole() {
        int roleIdToDelete = 1;
        UserRoleEntity entityToDelete = new UserRoleEntity(1, "Empleado", LocalDate.now());

        Mockito.when(userRoleRepository.findById(roleIdToDelete)).thenReturn(Optional.of(entityToDelete));

        userRoleService.delete(roleIdToDelete);

        Mockito.verify(userRoleRepository).delete(entityToDelete);
    }

    @Test
    public void testGetAllUserRoles() {
        // Arrange
        List<UserRoleEntity> userRoleEntities = Arrays.asList(
                new UserRoleEntity(1, "Empleado", LocalDate.now()),
                new UserRoleEntity(2, "Supervisor", LocalDate.now())
        );

        Mockito.when(userRoleRepository.findAll()).thenReturn(userRoleEntities);

        List<UserRoleResponse> userRoleResponses = userRoleService.getAll();

        Assertions.assertNotNull(userRoleResponses);
        Assertions.assertEquals(userRoleEntities.size(), userRoleResponses.size());

        for (int i = 0; i < userRoleEntities.size(); i++) {
            Assertions.assertEquals(userRoleEntities.get(i).getRol(), userRoleResponses.get(i).getRol());
        }
    }
}

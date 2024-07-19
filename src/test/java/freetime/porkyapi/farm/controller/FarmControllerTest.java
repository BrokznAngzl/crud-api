package freetime.porkyapi.farm.controller;

import freetime.porkyapi.farm.dao.FarmDAO;
import freetime.porkyapi.farm.model.FarmEntity;
import freetime.porkyapi.farm.service.FarmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FarmControllerTest {

    @InjectMocks
    FarmController farmController;
    @Mock
    FarmService farmService;
    @Mock
    FarmDAO farmDAO;

    private static FarmEntity farm = null;

    @BeforeEach
    void setUp() {
        farm = new FarmEntity();
        farm.setFarmID(new BigInteger("1"));
        farm.setFarmName("Beng Beng 5 Bath");
    }

    @Test
    void createFarm() {
        farmController.createFarm(farm);
        verify(farmService, times(1)).saveFarm(any(farm.getClass()), anyBoolean());
    }

    @Test
    void createFarmNotSuccess() {
        farm = null;
        farmController.createFarm(farm);
        verify(farmService, times(0)).saveFarm(any(FarmEntity.class), anyBoolean());
    }

    @Test
    void editFarm() {
        farmController.editFarm(farm);
        verify(farmService, times(1)).saveFarm(any(farm.getClass()), anyBoolean());
    }

    @Test
    void deleteFarm() {
        farmController.deleteFarm(farm);
        verify(farmService, times(1)).deleteFarm(any());
    }

    @Test
    void getAllFarms() {
        farmController.getAllFarms();
        verify(farmService, times(1)).getAllFarm();
    }

    @Test
    void getAllFarmsNotSuccess() {
        when(farmService.getAllFarm()).thenThrow(new RuntimeException());
        ResponseEntity<?> result = farmController.getAllFarms();
        assertEquals(result.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void findFarm() {
        farmController.findFarm(farm);
        verify(farmDAO, times(1)).findFarm(any(farm.getClass()));
    }

    @Test
    void findFarmNotSuccess() {
        when(farmDAO.findFarm(any())).thenThrow(new RuntimeException());
        ResponseEntity<?> result = farmController.findFarm(farm);
        assertEquals(result.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void getFarm() {
        farmController.getFarm(farm.getFarmID());
        verify(farmService, times(1)).getFarmById(any());
    }

    @Test
    void getFarmNotSuccess() {
        when(farmService.getFarmById(any())).thenThrow(new RuntimeException());
        ResponseEntity<?> result = farmController.getFarm(farm.getFarmID());
        assertEquals(result.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void getFarmByName() {
        when(farmService.findFarmByName(any())).thenThrow(new RuntimeException());
        ResponseEntity<?> result = farmController.getFarmByName(farm.getFarmName());
        assertEquals(result.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
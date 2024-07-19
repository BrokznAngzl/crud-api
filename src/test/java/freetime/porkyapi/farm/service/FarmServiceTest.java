package freetime.porkyapi.farm.service;

import freetime.porkyapi.farm.model.FarmEntity;
import freetime.porkyapi.farm.repository.FarmRepository;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FarmServiceTest {

    @InjectMocks
    private FarmService farmService;
    @Mock
    private FarmRepository farmRepository;
    private FarmEntity farm;

    @BeforeEach
    void setUp() {
        farm = new FarmEntity();
        farm.setFarmID(new BigInteger("1"));
        farm.setFarmName("Beng Beng 5 Bath");
    }

    @Test
    void saveFarm() {
        boolean isEdite = false;
        ResponseEntity<?> result = farmService.saveFarm(farm, isEdite);
        assertEquals(result.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void saveFarmNotSuccess() {
        boolean isEdit = false;
        when(farmService.saveFarm(farm, isEdit)).thenThrow(new RuntimeException());
        ResponseEntity<?> result = farmService.saveFarm(farm, isEdit);
        assertEquals(result.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void getAllFarm() {
        when(farmRepository.findAll()).thenReturn(new ArrayList<FarmEntity>(List.of(farm)));
        ResponseEntity<?> result = farmService.getAllFarm();
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getAllFarmNotSuccess() {
        when(farmRepository.findAll()).thenThrow(new RuntimeException());
        ResponseEntity<?> result = farmService.getAllFarm();
        assertEquals(result.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void getFarmById() {
        when(farmRepository.findById(any())).thenReturn(Optional.of(farm));
        ResponseEntity<?> result = farmService.getFarmById(farm.getFarmID());
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getFarmByIdNotSuccess() {
        when(farmRepository.findById(any())).thenThrow(new RuntimeException());
        ResponseEntity<?> result = farmService.getFarmById(farm.getFarmID());
        assertEquals(result.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void deleteFarm() {
        farmService.deleteFarm(farm.getFarmID());
        verify(farmRepository, times(1)).deleteById(farm.getFarmID());
    }

    @Test
    void deleteFarmNotSuccess() {
        when(farmService.deleteFarm(any())).thenThrow(new RuntimeException());
        ResponseEntity<?> result = farmService.deleteFarm(farm.getFarmID());
        assertEquals(result.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void findFarmByName() {
        when(farmRepository.findByFarmName(anyString())).thenReturn(farm);
        ResponseEntity<?> result = farmService.findFarmByName(farm.getFarmName());
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void findFarmByNameNotSuccess() {
        when(farmRepository.findByFarmName(anyString())).thenThrow(new RuntimeException());
        ResponseEntity<?> result = farmService.findFarmByName(farm.getFarmName());
        assertEquals(result.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
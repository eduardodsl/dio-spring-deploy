package br.com.dsleite.cloudparking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dsleite.cloudparking.dto.ParkingCreateDTO;
import br.com.dsleite.cloudparking.dto.ParkingDTO;
import br.com.dsleite.cloudparking.dto.ParkingPatchDTO;
import br.com.dsleite.cloudparking.mapper.ParkingMapper;
import br.com.dsleite.cloudparking.model.Parking;
import br.com.dsleite.cloudparking.service.ParkingService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/parking")
public class ParkingController {
    
    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper){
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    @Operation(summary = "Finds all parking vehicle entries")
    public ResponseEntity<List<ParkingDTO>> findAll(){
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finds one parking vehicle entry by its id")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id){
        Parking parkingList = parkingService.findById(id);
        ParkingDTO result = parkingMapper.toParkingDTO(parkingList);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Removes an parking vehicle entry from the database")
    public ResponseEntity<ParkingDTO> delete(@PathVariable String id){
        parkingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @Operation(summary = "Creates a new parking vehicle entry")
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto){
        Parking parkingCreate = parkingMapper.toParking(dto);
        Parking parking = parkingService.create(parkingCreate);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates an existing parking vehicle entry")
    public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO dto){
        Parking parkingCreate = parkingMapper.toParking(dto);
        Parking parking = parkingService.update(id, parkingCreate);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Offers patching for all available fields")
    public ResponseEntity<ParkingPatchDTO> patch(@PathVariable String id, @RequestBody ParkingPatchDTO dto){
        
        Parking parkingData = parkingMapper.toParking(dto);
        Parking parking = parkingService.findById(id);
        
        // I will later probably look for a solution using reflection and/or maps
        // todo: https://www.baeldung.com/java-method-reflection
        if(parkingData.getColor() != null) parking.setColor(parkingData.getColor());
        if(parkingData.getBill() != null) parking.setBill(parkingData.getBill());
        if(parkingData.getLicense() != null) parking.setLicense(parkingData.getLicense());
        if(parkingData.getModel() != null) parking.setModel(parkingData.getModel());
        if(parkingData.getState() != null) parking.setState(parkingData.getState());
        if(parkingData.getEntryDate() != null) parking.setEntryDate(parkingData.getEntryDate());
        if(parkingData.getExitDate() != null) parking.setExitDate(parkingData.getExitDate());

        ParkingPatchDTO result = parkingMapper.toParkingPatchDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}

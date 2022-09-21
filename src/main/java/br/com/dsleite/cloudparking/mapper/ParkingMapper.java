package br.com.dsleite.cloudparking.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.dsleite.cloudparking.dto.ParkingCreateDTO;
import br.com.dsleite.cloudparking.dto.ParkingDTO;
import br.com.dsleite.cloudparking.dto.ParkingPatchDTO;
import br.com.dsleite.cloudparking.model.Parking;

@Component
public class ParkingMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ParkingDTO toParkingDTO(Parking parking){
        return MODEL_MAPPER.map(parking, ParkingDTO.class);
    }

    public ParkingCreateDTO toParkingCreateDTO(Parking parking){
        return MODEL_MAPPER.map(parking, ParkingCreateDTO.class);
    }

    public ParkingPatchDTO toParkingPatchDTO(Parking parking){
        return MODEL_MAPPER.map(parking, ParkingPatchDTO.class);
    }

    public List<ParkingDTO> toParkingDTOList(List<Parking> parkingList){
        return parkingList.stream().map(this::toParkingDTO).collect(Collectors.toList());
    }

    public Parking toParking(ParkingDTO dto){
        return MODEL_MAPPER.map(dto, Parking.class);
    }

    public Parking toParking(ParkingCreateDTO dto){
        return MODEL_MAPPER.map(dto, Parking.class);
    }

    public Parking toParking(ParkingPatchDTO dto){
        return MODEL_MAPPER.map(dto, Parking.class);
    }

}

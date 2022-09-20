package br.com.dsleite.cloudparking.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.dsleite.cloudparking.model.Parking;

@Service
public class ParkingService {
    
    private static Map<String, Parking> parkingMap = new HashMap<>();

    static {
        Parking parking0 = new Parking("DMS-1111", "SC", "CELTA", "PRETO");
        Parking parking1 = new Parking("WAS-1234", "MG", "VW GOL", "PRETO");
        parkingMap.put(parking0.getId(), parking0);
        parkingMap.put(parking1.getId(), parking1);
    }

    public List<Parking> findAll(){
        return parkingMap.values().stream().collect(Collectors.toList());
    }

    public Parking findById(String id){
        return parkingMap.get(id);
    }

    public Parking create(Parking parking){
        String id = getUUID();
        parking.setId(getUUID());
        parking.setEntryDate(LocalDateTime.now());
        parkingMap.put(id, parking);
        return parking;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}

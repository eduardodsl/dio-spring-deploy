package br.com.dsleite.cloudparking.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.dsleite.cloudparking.exception.ParkingNotFoundException;
import br.com.dsleite.cloudparking.model.Parking;

@Service
public class ParkingService {
    
    private static Map<String, Parking> parkingMap = new HashMap<>();
    private static List<Parking> parkingList = new ArrayList<>();

    static {
        Parking parking0 = new Parking("DMS-1111", "SC", "CELTA", "PRETO");
        Parking parking1 = new Parking("WAS-1234", "MG", "VW GOL", "PRETO");
        parkingMap.put(parking0.getId(), parking0);
        parkingMap.put(parking1.getId(), parking1);
        parkingList.add(parking0);
        parkingList.add(parking1);
    }

    public List<Parking> findAll(){
        return parkingList;
        // return parkingMap.values().stream().collect(Collectors.toList());
    }

    public Parking findById(String id){
        Parking parking = parkingMap.get(id);
        if(parking == null)
            throw new ParkingNotFoundException(id);
        return parkingMap.get(id);
    }

    public Parking create(Parking parking){
        String id = getUUID();
        parking.setId(getUUID());
        parking.setEntryDate(LocalDateTime.now());
        parkingMap.put(id, parking);
        return parking;
    }

    public Parking update(String id, Parking data){
        Parking parking = findById(id);
        parking.setColor(data.getColor());
        parkingMap.replace(id, parking);
        return parking;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public void delete(String id) {
        findById(id);
        parkingMap.remove(id);
    }

    public Parking exit(String id){
        // get parking
        // update leave date
        // calculate the value
        return null;
    }

}

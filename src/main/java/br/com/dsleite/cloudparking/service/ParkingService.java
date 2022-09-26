package br.com.dsleite.cloudparking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.dsleite.cloudparking.exception.ParkingNotFoundException;
import br.com.dsleite.cloudparking.model.Parking;
import br.com.dsleite.cloudparking.repository.ParkingRepository;

@Service
public class ParkingService {
    
    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository){
        this.parkingRepository = parkingRepository;
    }

    @org.springframework.transaction.annotation.Transactional
    public List<Parking> findAll(){
        return parkingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Parking findById(String id){
        Optional<Parking> parking = this.parkingRepository.findById(id);
        if(!parking.isPresent())
            throw new ParkingNotFoundException(id);
        return parking.get();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Parking create(Parking parking){
        parking.setId(getUUID());
        parking.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parking);
        return parking;
    }

    @Transactional
    public Parking update(String id, Parking data){
        
        Parking parking = this.findById(id);
        
        if(data.getColor() != null) parking.setColor(data.getColor());
        if(data.getBill() != null) parking.setBill(data.getBill());
        if(data.getLicense() != null) parking.setLicense(data.getLicense());
        if(data.getModel() != null) parking.setModel(data.getModel());
        if(data.getState() != null) parking.setState(data.getState());
        if(data.getEntryDate() != null) parking.setEntryDate(data.getEntryDate());
        if(data.getExitDate() != null) parking.setExitDate(data.getExitDate());
        
        this.parkingRepository.save(parking);

        return parking;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Transactional
    public void delete(String id) {
        this.findById(id);
        this.parkingRepository.deleteById(id);
    }

    @Transactional
    public Parking checkOut(String id){
        Parking parking = this.findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckOut.getBill(parking));
        this.parkingRepository.save(parking);
        return parking;
    }

}

package br.com.desafio.backend.vehiclecontrolapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.desafio.backend.vehiclecontrolapi.domain.vehicles.Vehicle;
import br.com.desafio.backend.vehiclecontrolapi.dtos.VehicleDto;
import br.com.desafio.backend.vehiclecontrolapi.infrastructure.exception.BusinessException;
import br.com.desafio.backend.vehiclecontrolapi.infrastructure.exception.BusinessExceptionMessage;
import br.com.desafio.backend.vehiclecontrolapi.infrastructure.util.ObjectMapperUtil;
import br.com.desafio.backend.vehiclecontrolapi.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;

/**
 * Service class for handling operations related to vehicles.
 * <p>
 * 
 * @author Andesson Reis
 */
@Service
@RequiredArgsConstructor
public class VehicleService {

    @Autowired
    private ObjectMapperUtil objectMapperUtil;

    @Autowired
    private VehicleRepository vehicleRepository;

    /**
     * Lists all vehicles present in the database.
     * <p>
     * 
     * @return a list with all vehicles from the database, or null if none exist.
     */
    public List<VehicleDto> listVehicles() {
        return objectMapperUtil
                .mapAll(vehicleRepository
                        .findAll(), VehicleDto.class);
    }

    /**
     * Saves a vehicle in the database.
     * <p>
     * 
     * @param vehicle The vehicle to be saved.
     * @return ResponseEntity with the saved vehicle data in the form of a DTO.
     */
    public ResponseEntity<Vehicle> saveVehicle(Vehicle vehicle) {
        return vehicleRepository.findById(vehicleRepository.save(vehicle).getId()).isPresent()
                ? ResponseEntity.status(201).build()
                : ResponseEntity.badRequest().build();
    }

    /**
     * Updates a vehicle and returns its DTO representation.
     * <p>
     * 
     * @param vehicle The vehicle to be updated.
     * @return The DTO representation of the updated vehicle.
     * @throws BusinessException if the vehicle with the given ID is not found.
     */
    public VehicleDto updateVehicle(Vehicle vehicle) {
        return Optional.of(vehicle)
                .filter(vehi -> vehicleRepository.existsById(vehicle.getId()))
                .map(vehi -> objectMapperUtil.map(vehicleRepository.save(vehi), VehicleDto.class))
                .orElseThrow(() -> new BusinessException(BusinessExceptionMessage.NOT_FOUND.getMensagem()));
    }

    /**
     * Deletes a vehicle from the database based on the provided ID.
     * <p>
     * 
     * @param id The ID of the vehicle to be deleted.
     * @return The DTO representation of the deleted vehicle.
     * @throws BusinessException if the vehicle with the given ID is not found.
     */
    public VehicleDto deleteVehicleById(Long id) {
        return vehicleRepository.findById(id).map(vehicle -> {
            vehicleRepository.delete(vehicle);
            return objectMapperUtil.map(vehicle, VehicleDto.class);
        }).orElseThrow(() -> new BusinessException(BusinessExceptionMessage.NOT_FOUND.getMensagem()));
    }
}

package br.com.desafio.backend.vehiclecontrolapi.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.backend.vehiclecontrolapi.controller.v1.util.ResultError;
import br.com.desafio.backend.vehiclecontrolapi.domain.vehicles.Vehicle;
import br.com.desafio.backend.vehiclecontrolapi.dtos.VehicleDto;
import br.com.desafio.backend.vehiclecontrolapi.infrastructure.util.ObjectMapperUtil;
import br.com.desafio.backend.vehiclecontrolapi.services.VehicleService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * @author Andesson Reis
 *<p>
 * 
 *  Controller to handle HTTP requests related to the 'Vehicle' entity.
 *
 *See also: {@link Vehicle}
 */
@RestController
@RequestMapping(path = "/api/v1/")
public class VehicleController {

        @Autowired
        VehicleService vehicleService;

        @Autowired
        ObjectMapperUtil objectMapperUtil;

        // =========================================================== //
        // ====================== [ ENDPOINTS ] ====================== //
        // =========================================================== //

        /**
         * Gets the list of all vehicles.
         * <p>
         * 
         * @return ResponseEntity with a list of vehicles or an error response in case
         *         of failure.
         */
        @GetMapping(path = "/vehicles", produces = "application/json")
        public ResponseEntity<?> listVehicles() {

                return ResponseEntity.status(HttpStatus.OK)
                                .body(vehicleService.listVehicles());
        }

        /**
         * Saves a vehicle.
         * <p>
         * 
         * @param vehicleDto The DTO representing the vehicle to be saved.
         * @param result     BindingResult for validation.
         * @return ResponseEntity with a generic response entity or an error response in
         *         case of validation failure.
         */
        @CrossOrigin(origins = "*", allowedHeaders = "*")
        @PostMapping(path = "/vehicles/vehicle", consumes = "application/json", produces = "application/json")
        public ResponseEntity<?> saveVehicle(@RequestBody @Valid VehicleDto vehicleDto,
                        BindingResult result) {

                return (result.hasErrors())
                                ? ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                                .body(ResultError.getResultErrors(result))
                                : ResponseEntity.status(HttpStatus.OK)
                                                .body(this.vehicleService.saveVehicle(this.objectMapperUtil
                                                                .map(vehicleDto, new Vehicle())));
        }

        /**
         * Updates a vehicle and returns a generic response entity.
         * <p>
         * 
         * @param vehicleDto The DTO representing the vehicle to be updated.
         * @param result     BindingResult for validation.
         * @return ResponseEntity with the updated vehicle or an error response in case
         *         of validation failure.
         */
        @PutMapping(path = "/vehicles/vehicle", consumes = "application/json", produces = "application/json")
        public ResponseEntity<?> updateVehicle(@Valid @RequestBody VehicleDto vehicleDto, BindingResult result) {

                return (result.hasErrors())
                                ? ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                                .body(ResultError.getResultErrors(result))
                                : ResponseEntity.status(HttpStatus.OK)
                                                .body(this.vehicleService.updateVehicle(this.objectMapperUtil
                                                                .map(vehicleDto, new Vehicle())));
        }

        /**
         * Deletes a vehicle by its ID.
         * <p>
         * 
         * @param id The ID of the vehicle to be deleted.
         * @return ResponseEntity with a response entity or an error response in case of
         *         failure.
         */
        @Transactional
        @DeleteMapping(path = "/vehicles/vehicle/{id}", consumes = "application/json", produces = "application/json")
        public ResponseEntity<?> deleteVehicleById(@PathVariable("id") @NotNull Long id) {

                return ResponseEntity.status(HttpStatus.ACCEPTED)
                                .body(vehicleService.deleteVehicleById(id));
        }
}
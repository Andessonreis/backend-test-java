package br.com.desafio.backend.vehiclecontrolapi.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.backend.vehiclecontrolapi.controller.v1.util.ResultError;
import br.com.desafio.backend.vehiclecontrolapi.domain.establishment.Establishment;
import br.com.desafio.backend.vehiclecontrolapi.dtos.EstablishmentDto;
import br.com.desafio.backend.vehiclecontrolapi.infrastructure.util.ObjectMapperUtil;
import br.com.desafio.backend.vehiclecontrolapi.services.EstablishmentService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

/**
 * @author Andesson Reis
 * <p>
 * 
 * Controller to handle HTTP requests related to the 'Establishment' entity.
 *
 * See also: {@link Establishment}
 */
@RestController
@RequestMapping(path = "/api/v1/")
public class EstablishmentController {

    @Autowired
    EstablishmentService establishmentService;

    @Autowired
    ObjectMapperUtil objectMapperUtil;

    // =========================================================== //
    // =============== [ ENDPOINTS ] ============================= //
    // =========================================================== //

    /**
     * Gets the list of all establishments.
     *
     * @return A list of establishments or an error response in case of failure.
     */
    @GetMapping(path = "/establishments", produces = "application/json")
    public ResponseEntity<?> listEstablishments() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(establishmentService.listEstablishments());
    }

    /**
     * Saves an establishment.
     *
     * @apiNote Endpoint created since version 1.0.1
     * @return A generic response entity.
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(path = "/establishments/establishment", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> saveEstablishment(@RequestBody @Valid EstablishmentDto establishmentDto,
                                              BindingResult result) {
        return (result.hasErrors())
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResultError.getResultErrors(result))
                : ResponseEntity.status(HttpStatus.OK)
                .body(this.establishmentService.saveEstablishment(this.objectMapperUtil
                        .map(establishmentDto, new Establishment())));
    }
}

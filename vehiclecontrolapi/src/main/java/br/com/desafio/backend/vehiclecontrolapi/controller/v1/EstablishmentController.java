package br.com.desafio.backend.vehiclecontrolapi.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.desafio.backend.vehiclecontrolapi.domain.establishment.Establishment;
import br.com.desafio.backend.vehiclecontrolapi.dtos.EstablishmentDto;
import br.com.desafio.backend.vehiclecontrolapi.infrastructure.util.ObjectMapperUtil;
import br.com.desafio.backend.vehiclecontrolapi.services.EstablishmentService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

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
    // ====================== [ ENDPOINTS ] ====================== //
    // =========================================================== //

    /**
     * Gets the list of all establishments.
     *<p>
     * @return ResponseEntity with a list of establishments or an error response in case of failure.
     */
    @GetMapping(path = "/establishments", produces = { "application/json", "application/xml" })
    public ResponseEntity<?> listEstablishments() {
        return ResponseEntity.status(HttpStatus.OK).body(establishmentService.listEstablishments());
    }

    /**
     * Saves an establishment.
     *<p>
     * @param establishmentDto The DTO representing the establishment to be saved.
     * @param result BindingResult for validation.
     * @return ResponseEntity with a generic response entity or an error response in case of validation failure.
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(path = "/establishments/establishment", consumes = "application/json",produces = { "application/json", "application/xml" })
    public ResponseEntity<?> saveEstablishment(@RequestBody @Valid EstablishmentDto establishmentDto,
                                               BindingResult result) {
        return (result.hasErrors())
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResultError.getResultErrors(result))
                : ResponseEntity.status(HttpStatus.OK)
                .body(this.establishmentService.saveEstablishment(this.objectMapperUtil
                        .map(establishmentDto, new Establishment())));
    }

    /**
     * Updates an establishment and returns a generic response entity.
     *<p>
     * @param establishmentDto The DTO representing the establishment to be updated.
     * @param result BindingResult for validation.
     * @return ResponseEntity with the updated establishment or an error response in case of validation failure.
     */
    @PutMapping(path = "/establishments/establishment", consumes = "application/json", produces = { "application/json", "application/xml" })
    public ResponseEntity<?> updateEstablishment(@Valid @RequestBody EstablishmentDto establishmentDto, BindingResult result) {
        return (result.hasErrors())
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResultError.getResultErrors(result))
                : ResponseEntity.status(HttpStatus.OK)
                .body(this.establishmentService.updateEstablishments(this.objectMapperUtil
                        .map(establishmentDto, new Establishment())));
    }

    /**
     * Deletes an establishment by its ID.
     *<p>
     * @param id The ID of the establishment to be deleted.
     * @return ResponseEntity with a response entity or an error response in case of failure.
     */
    @Transactional
    @DeleteMapping(path = "/establishments/establishment/{id}", consumes = "application/json", produces = { "application/json", "application/xml" })
    public ResponseEntity<?> deleteEstablishmentId(@PathVariable("id") @NotNull Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(establishmentService.deleteEstablishmentId(id));
    }
}
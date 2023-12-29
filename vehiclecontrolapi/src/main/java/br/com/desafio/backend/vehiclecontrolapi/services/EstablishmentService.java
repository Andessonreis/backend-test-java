package br.com.desafio.backend.vehiclecontrolapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.desafio.backend.vehiclecontrolapi.domain.establishment.Establishment;
import br.com.desafio.backend.vehiclecontrolapi.dtos.EstablishmentDto;
import br.com.desafio.backend.vehiclecontrolapi.infrastructure.exception.BusinessException;
import br.com.desafio.backend.vehiclecontrolapi.infrastructure.util.ObjectMapperUtil;
import br.com.desafio.backend.vehiclecontrolapi.repositories.EstablishmentRepository;
import lombok.RequiredArgsConstructor;

/**
 * @author Andesson Reis
 * <p>
 * Service class for handling operations related to establishments.
 */
@Service
@RequiredArgsConstructor
public class EstablishmentService {

    @Autowired
    private ObjectMapperUtil objectMapperUtil;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    /**
     * Lists all establishments present in the database.
     * <p>
     *      
     * @return a list with all establishments from the database, or null if none exist.
     *       
     */
    public List<EstablishmentDto> listEstablishments() {

        return objectMapperUtil.mapAll(
                this.establishmentRepository.findAll(),
                EstablishmentDto.class);
    }

    /**
     * Saves an establishment in the database.
     * <p>
     * 
     * @param establishment The establishment to be saved.
     * @return ResponseEntity with the saved establishment data in the form of a DTO.
     *     
     */
    public ResponseEntity<Establishment> saveEstablishment(Establishment establishment) {
        // TODO: revisar logica.

        return establishmentRepository.findById(establishmentRepository.save(establishment).getId()).isPresent()
                ? ResponseEntity.status(201).build()
                : ResponseEntity.badRequest().build();
    }

}

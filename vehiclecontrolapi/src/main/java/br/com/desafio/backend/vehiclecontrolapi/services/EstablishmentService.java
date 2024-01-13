package br.com.desafio.backend.vehiclecontrolapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.backend.vehiclecontrolapi.domain.establishment.Establishment;
import br.com.desafio.backend.vehiclecontrolapi.dtos.EstablishmentDto;
import br.com.desafio.backend.vehiclecontrolapi.infrastructure.exception.BusinessException;
import br.com.desafio.backend.vehiclecontrolapi.infrastructure.exception.BusinessExceptionMessage;
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
     * @return EstablishmentDto representing the saved establishment.
     * @throws BusinessException if the CNPJ already exists in the database.
     */
    public EstablishmentDto saveEstablishment(Establishment establishment) {

        return Optional.of(establishment)
                .filter(esta -> !this.establishmentRepository.existsByCnpj(esta.getCnpj()))
                .map(esta -> objectMapperUtil.map(this.establishmentRepository.save(esta), EstablishmentDto.class))
                .orElseThrow(() -> new BusinessException(
                        BusinessExceptionMessage.ATTRIBUTE_VALUE_ALREADY_EXISTS.getMensagemValorJaExiste("CNPJ"))
                );
    }


    /**
     * Updates an establishment and returns its DTO representation.
     * <p>
     *
     * @param establishment The establishment to be updated.
     * @return The DTO representation of the updated establishment.
     * @throws BusinessException If the establishment with the provided CNPJ does not exist.
     */
    public EstablishmentDto updateEstablishments(Establishment establishment) {
          
        return Optional.of(establishment)
                .filter(esta -> this.establishmentRepository.existsByCnpj(establishment.getCnpj()))
                .map(esta -> objectMapperUtil.map(this.establishmentRepository.save(esta), EstablishmentDto.class))
                .orElseThrow(
                        () -> new BusinessException(BusinessExceptionMessage.NOT_FOUND.getMensagem())
                );
    }

    /**
     * Deletes an establishment from the database based on the provided ID.
     * <p>
     * 
     * @param id The ID of the establishment to be deleted.
     * @return The DTO representation of the deleted establishment.
     * @throws BusinessException if the establishment with the given ID is not found.
     */
    public EstablishmentDto deleteEstablishmentId(Long id) {
        
        return this.establishmentRepository.findEstablishmentById(id).map(establishment -> {
            this.establishmentRepository.delete(establishment);
            return objectMapperUtil.map(establishment, EstablishmentDto.class);
        })
        .orElseThrow(() -> new BusinessException(BusinessExceptionMessage.NOT_FOUND.getMensagem()));
    }
}
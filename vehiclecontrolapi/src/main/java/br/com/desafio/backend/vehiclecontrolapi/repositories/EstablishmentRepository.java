package br.com.desafio.backend.vehiclecontrolapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.backend.vehiclecontrolapi.domain.establishment.Establishment;

/*
 * @author Andesson Reis
 */
public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {

    /**
    * Checks if an establishment with the specified ID exists.
    *<p>
    * @param id The ID of the Establishment to be searched.
    * @return true if the establishment with the specified ID exists, false otherwise.
    */
   boolean existsById(Long id);

   /**
    * Checks if an establishment with the specified CNPJ exists.
    *<p>
    * @param cnpj The CNPJ of the establishment to be searched.
    * @return true if the establishment with the specified CNPJ exists, false otherwise.
    */
   boolean existsByCnpj(String cnpj);

    /**
     * Retrieves an optional containing the establishment with the specified ID.
     *
     * @param id The ID of the establishment to be retrieved.
     * @return Optional containing the establishment with the specified ID, or an empty Optional if not found.
     */
    Optional<Establishment> findEstablishmentById(Long id);
}
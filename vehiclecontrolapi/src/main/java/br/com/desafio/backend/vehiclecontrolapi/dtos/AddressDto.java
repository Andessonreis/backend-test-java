package br.com.desafio.backend.vehiclecontrolapi.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Andesson Reis
 * <p>
 * DTO for responding to requests related to the 'Address' entity.
 * 
 * @param uf            State abbreviation
 * @param city          City name
 * @param neighborhood  Neighborhood name
 * @param street        Street name
 * @param establishment Associated establishment
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    Long id;

    @NotBlank(message = "Value cannot be null")
    String uf;

    @NotBlank(message = "Value cannot be null")
    String city;

    @NotBlank(message = "Value cannot be null")
    String neighborhood;

    @NotBlank(message = "Value cannot be null")
    String street;

    EstablishmentDto establishment;

}
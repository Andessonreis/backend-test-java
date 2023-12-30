package br.com.desafio.backend.vehiclecontrolapi.dtos;

import org.hibernate.validator.constraints.Range;

import br.com.desafio.backend.vehiclecontrolapi.domain.address.Address;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Andesson Reis
 * <p>
 * DTO for responding to requests related to the 'Establishment' entity.
 * 
 * @param name                    Establishment name
 * @param cnpj                    CNPJ (Cadastro Nacional da Pessoa Jurídica) number
 * @param motorcycleParkingSpaces Number of parking spaces for motorcycles
 * @param carParkingSpaces        Number of parking spaces for cars
 * @param address                 Establishment address
 * @param phone                   Contact phone number
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstablishmentDto {
    private Long id;

    @NotBlank(message = "Value cannot be null or empty")
    private String name;

    @NotBlank(message = "Value cannot be null or empty")
    private String cnpj;

    @Column(nullable = false)
    @NotNull(message = "Value cannot be null or empty")
    @Range(min = 1, message = "Motorcycle spots must be greater than zero")
    private Integer motorcycleParkingSpaces;

    @Column(nullable = false)
    @NotNull(message = "Value cannot be null or empty")
    @Range(min = 1, message = "Car spots must be greater than zero")
    private Integer carParkingSpaces;

    @NotNull(message = "Value cannot be null")
    private Address address;

    @Size(min = 11, max = 11, message = "Value must have a length of 11 characters")
    @Pattern(regexp = "^[0-9]*", message = "Value must contain only numeric characters")
    @NotBlank(message = "Value cannot be null")
    private String phone;
}
package br.com.desafio.backend.vehiclecontrolapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Andesson Reis
 *
 * DTO para resposta às requisições direcionadas à entidade 'Establishment'.
 * @param name
 * @param CNPJ
 * @param phone
 * @param address
 * @param motorcycleParkingSpaces
 * @param carParkingSpaces
 */
public record EstablishmentDto(
    @JsonProperty(value = "name")
    String name,
    @JsonProperty(value = "CNPJ")
    String CNPJ,
    @JsonProperty(value = "phone")
    String phone,
    @JsonProperty(value = "address")
    AddressDto address,
    @JsonProperty(value = "motorcycleParkingSpaces")
    Integer motorcycleParkingSpaces,
    @JsonProperty(value = "carParkingSpaces")
    Integer carParkingSpaces
) {}

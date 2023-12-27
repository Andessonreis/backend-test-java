package br.com.desafio.backend.vehiclecontrolapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Andesson Reis
 *
 * DTO para resposta às requisições direcionadas à entidade 'Address'.
 * @param UF
 * @param city
 * @param neighborhood
 * @param street
 * @param establishment
 */
public record AddressDto(

    @JsonProperty(value = "UF")
    String UF,
    @JsonProperty(value = "city")
    String city,
    @JsonProperty(value = "neighborhood")
    String neighborhood,
    @JsonProperty(value = "street")
    String street,
    @JsonProperty(value = "establishment")
    EstablishmentDto establishment
     
) {} 
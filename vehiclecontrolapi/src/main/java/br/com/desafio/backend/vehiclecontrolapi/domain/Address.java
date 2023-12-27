package br.com.desafio.backend.vehiclecontrolapi.domain;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entity class representing an address.
 * 
 * @author Andesson Reis
 */
@Entity(name = "address")
@Table(name = "address")
@Data
public class Address {

    /**
     * The unique identifier for the address.
     */
    private UUID id;

    /**
     * The state (UF) of the address.
     */
    private String UF;

    /**
     * The city of the address.
     */
    private String city;

    /**
     * The neighborhood of the address.
     */
    private String neighborhood;

    /**
     * The street of the address.
     */
    private String street;

    /**
     * The establishment associated with this address.
     */
    @OneToOne
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;
}

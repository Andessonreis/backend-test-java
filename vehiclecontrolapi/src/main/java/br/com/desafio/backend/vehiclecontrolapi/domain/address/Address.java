package br.com.desafio.backend.vehiclecontrolapi.domain.address;

import java.io.Serializable;
import java.util.UUID;

import br.com.desafio.backend.vehiclecontrolapi.domain.establishment.Establishment;
import br.com.desafio.backend.vehiclecontrolapi.infrastructure.model.PersistenceEntity;
import io.micrometer.common.lang.NonNullFields;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity class representing an address.
 * 
 * @author Andesson Reis
 */
@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NonNullFields
@EqualsAndHashCode(callSuper = false)
public class Address extends PersistenceEntity implements Serializable{

    /**
     * The unique identifier for the address.
     */

    private Long id;

    /**
     * The state (UF) of the address.
     */
    private String uf;

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

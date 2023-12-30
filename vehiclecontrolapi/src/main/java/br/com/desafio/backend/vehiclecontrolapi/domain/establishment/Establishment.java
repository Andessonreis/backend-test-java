package br.com.desafio.backend.vehiclecontrolapi.domain.establishment;

import java.io.Serializable;

import org.hibernate.validator.constraints.Range;

import br.com.desafio.backend.vehiclecontrolapi.domain.address.Address;
import br.com.desafio.backend.vehiclecontrolapi.infrastructure.model.PersistenceEntity;

import io.micrometer.common.lang.NonNullFields;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity class representing an establishment.
 * 
 * @author Andesson Reis
 */
@Entity
@Table(name = "establishment")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@NonNullFields
@EqualsAndHashCode(callSuper = false)
public class Establishment extends PersistenceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the address.
     */
    @NotNull
    private Long id;

    /**
     * 
     * The name of the establishment.
     * 
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     *
     * The CNPJ (National Register of Legal Entities) of the establishment,
     * which is unique and cannot be null.
     *
     */
    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    /**
     * 
     * The address of the establishment.
     *
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    /**
     *
     * The phone number of the establishment, which cannot be null.
     * 
     */
    @Column(name = "phone", nullable = false)
    private String phone;

    /**
     * 
     * The number of parking spaces for motorcycles, which cannot be null.
     * 
     */

    @Range(min = 1, message = "Motorcycle spots must be greater than zero")
    private Integer motorcycleParkingSpaces;

    /**
     * 
     * The number of parking spaces for cars, which cannot be null.
     * 
     */
    @Range(min = 1, message = "Car spots must be greater than zero")
    private Integer carParkingSpaces;
}

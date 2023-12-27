package br.com.desafio.backend.vehiclecontrolapi.domain;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * Entity class representing an establishment.
 * See also: {@link Class}
 * </p>
 * 
 * @author Andesson Reis
 */
@Entity(name = "establishment")
@Table(name = "establishment")
@Data
@EqualsAndHashCode(of = "id")
public class Establishment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

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
    private String CNPJ;

    /**
     * 
     * The address of the establishment.
     *
     */
    @OneToOne
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
    @Column(name = "motorcycle_parking_spaces", nullable = false)
    private Integer motorcycleParkingSpaces;

    /**
     * 
     * The number of parking spaces for cars, which cannot be null.
     * 
     */
    @Column(name = "car_parking_spaces", nullable = false)
    private Integer carParkingSpaces;
}

package br.com.desafio.backend.vehiclecontrolapi.domain.vehicles;

import java.io.Serializable;

import br.com.desafio.backend.vehiclecontrolapi.dtos.VehicleDto;
import br.com.desafio.backend.vehiclecontrolapi.infrastructure.model.PersistenceEntity;
import io.micrometer.common.lang.NonNullFields;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity class representing a vehicle.
 * 
 * @author Andesson Reis
 */
@Entity
@Table(name = "vehicles")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@NonNullFields
@EqualsAndHashCode(callSuper = false)
public class Vehicle extends PersistenceEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the vehicle.
     */
    @NotNull
    private Long id;

    /**
     * The brand of the vehicle.
     */
    @Column(name = "brand", nullable = false)
    private String brand;

    /**
     * The model of the vehicle.
     */
    @Column(name = "model", nullable = false)
    private String model;

    /**
     * The color of the vehicle.
     */
    @Column(name = "color", nullable = false)
    private String color;

    /**
     * The license plate of the vehicle. It must be unique.
     */
    @Column(name = "plate", nullable = false, unique = true)
    private String plate;

    /**
     * The type of the vehicle (e.g., MOTORCYCLE, CAR).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type", nullable = false)
    private VehicleType vehicleType;
}

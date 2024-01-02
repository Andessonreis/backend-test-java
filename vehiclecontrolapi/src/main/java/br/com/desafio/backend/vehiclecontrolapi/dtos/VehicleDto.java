package br.com.desafio.backend.vehiclecontrolapi.dtos;

import br.com.desafio.backend.vehiclecontrolapi.domain.vehicles.VehicleType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Andesson Reis
 * <p>
 * DTO for responding to requests related to the 'Vehicle' entity.
 *
 * @param brand  Vehicle brand
 * @param model  Vehicle model
 * @param color  Vehicle color
 * @param plate  Vehicle license plate
 * @param type   Type of the vehicle (e.g., MOTORCYCLE, CAR)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    private Long id;

    @NotBlank(message = "Value cannot be null or empty")
    private String brand;
    
    @NotBlank(message = "Value cannot be null or empty")
    private String model;
    
    @NotBlank(message = "Value cannot be null or empty")
    private String color;
    
    @NotBlank(message = "The license plate field cannot be empty")
    @Pattern(regexp = "^[a-zA-Z]{3}[0-9][0-9a-zA-Z][0-9]{2}$", 
    message = "The entered license plate is invalid")
    private String plate;
    
    @NotNull(message = "The vehicle type must be Car or Motorcycle")
    private VehicleType type;
    
}

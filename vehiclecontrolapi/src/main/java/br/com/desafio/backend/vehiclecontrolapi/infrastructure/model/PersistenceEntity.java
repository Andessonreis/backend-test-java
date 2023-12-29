package br.com.desafio.backend.vehiclecontrolapi.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;

/*
 * @author Andesson Reis
 * 
 */
@MappedSuperclass
@Data
public class PersistenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
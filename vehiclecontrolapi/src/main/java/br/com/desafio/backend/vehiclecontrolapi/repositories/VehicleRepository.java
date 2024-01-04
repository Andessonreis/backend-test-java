package br.com.desafio.backend.vehiclecontrolapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.backend.vehiclecontrolapi.domain.vehicles.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{}

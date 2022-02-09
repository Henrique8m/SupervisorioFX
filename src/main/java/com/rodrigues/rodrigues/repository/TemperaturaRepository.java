package com.rodrigues.rodrigues.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodrigues.rodrigues.temperaruras.CoroaTemperatura;

public interface TemperaturaRepository extends JpaRepository<CoroaTemperatura, Long>{

}

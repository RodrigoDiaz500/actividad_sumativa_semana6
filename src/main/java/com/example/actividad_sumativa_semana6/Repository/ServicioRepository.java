package com.example.actividad_sumativa_semana6.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.actividad_sumativa_semana6.Model.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    // No necesitas agregar métodos adicionales, ya que JpaRepository proporciona findAllById
}

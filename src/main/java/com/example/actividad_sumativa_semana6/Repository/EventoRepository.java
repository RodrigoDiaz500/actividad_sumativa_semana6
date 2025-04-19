package com.example.actividad_sumativa_semana6.Repository;

import com.example.actividad_sumativa_semana6.Model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
}

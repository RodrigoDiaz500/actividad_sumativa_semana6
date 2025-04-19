package com.example.actividad_sumativa_semana6.Repository;

import com.example.actividad_sumativa_semana6.Model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
}

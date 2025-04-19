package com.example.actividad_sumativa_semana6.Service;

import com.example.actividad_sumativa_semana6.Model.Evento;
import com.example.actividad_sumativa_semana6.Model.Participante;
import com.example.actividad_sumativa_semana6.Repository.EventoRepository;
import com.example.actividad_sumativa_semana6.Repository.ParticipanteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final ParticipanteRepository participanteRepository;

    public EventoService(EventoRepository eventoRepository, ParticipanteRepository participanteRepository) {
        this.eventoRepository = eventoRepository;
        this.participanteRepository = participanteRepository;
    }

    // Crear evento
    public Evento crearEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    // Obtener todos los eventos
    public List<Evento> obtenerEventos() {
        return eventoRepository.findAll();
    }

    // Obtener evento por ID
    public Evento obtenerEventoPorId(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento no encontrado con ID: " + id));
    }

    // Actualizar evento
    public Evento actualizarEvento(Long id, Evento eventoActualizado) {
        Evento existente = obtenerEventoPorId(id);
        existente.setNombre(eventoActualizado.getNombre());
        existente.setFecha(eventoActualizado.getFecha());
        existente.setDescripcion(eventoActualizado.getDescripcion());
        return eventoRepository.save(existente);
    }

    // Eliminar evento
    public void eliminarEvento(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new EntityNotFoundException("Evento no encontrado con ID: " + id);
        }
        eventoRepository.deleteById(id);
    }

    // Inscribir participante a evento
    public Participante inscribirParticipante(Long eventoId, Participante participante) {
        Evento evento = obtenerEventoPorId(eventoId);
        participante.setEvento(evento);
        return participanteRepository.save(participante);
    }

    // Obtener participantes por evento
    public List<Participante> obtenerParticipantesPorEvento(Long eventoId) {
        Evento evento = obtenerEventoPorId(eventoId);
        return evento.getParticipantes();
    }

    // Actualizar participante (solo actualiza el nombre del participante, no la mascota)
    public Participante actualizarParticipante(Long id, Participante participanteActualizado) {
        Participante existente = participanteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Participante no encontrado con ID: " + id));

        // Solo actualizamos el nombre del participante
        existente.setNombre(participanteActualizado.getNombre());

        return participanteRepository.save(existente);
    }

    // Eliminar participante
    public void eliminarParticipante(Long id) {
        if (!participanteRepository.existsById(id)) {
            throw new EntityNotFoundException("Participante no encontrado con ID: " + id);
        }
        participanteRepository.deleteById(id);
    }
}

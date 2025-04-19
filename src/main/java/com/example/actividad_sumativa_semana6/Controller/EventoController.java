package com.example.actividad_sumativa_semana6.Controller;

import com.example.actividad_sumativa_semana6.Model.Evento;
import com.example.actividad_sumativa_semana6.Model.Participante;
import com.example.actividad_sumativa_semana6.Service.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    // Crear evento
    @PostMapping("/eventos")
    public ResponseEntity<Evento> crearEvento(@RequestBody Evento evento) {
        Evento nuevoEvento = eventoService.crearEvento(evento);
        return ResponseEntity.ok(nuevoEvento);
    }

    // Obtener todos los eventos
    @GetMapping("/eventos")
    public ResponseEntity<List<Evento>> obtenerEventos() {
        List<Evento> eventos = eventoService.obtenerEventos();
        return ResponseEntity.ok(eventos);
    }

    // Obtener evento por ID
    @GetMapping("/eventos/{id}")
    public ResponseEntity<Evento> obtenerEvento(@PathVariable Long id) {
        try {
            Evento evento = eventoService.obtenerEventoPorId(id);
            return ResponseEntity.ok(evento);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar evento
    @PutMapping("/eventos/{id}")
    public ResponseEntity<Evento> actualizarEvento(@PathVariable Long id, @RequestBody Evento evento) {
        try {
            Evento eventoActualizado = eventoService.actualizarEvento(id, evento);
            return ResponseEntity.ok(eventoActualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar evento
    @DeleteMapping("/eventos/{id}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable Long id) {
        try {
            eventoService.eliminarEvento(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Inscribir participante a un evento
    @PostMapping("/eventos/{eventoId}/participantes")
    public ResponseEntity<Participante> inscribirParticipante(@PathVariable Long eventoId, @RequestBody Participante participante) {
        Participante participanteInscrito = eventoService.inscribirParticipante(eventoId, participante);
        return ResponseEntity.ok(participanteInscrito);
    }

    // Obtener participantes por evento
    @GetMapping("/eventos/{eventoId}/participantes")
    public ResponseEntity<List<Participante>> obtenerParticipantesPorEvento(@PathVariable Long eventoId) {
        List<Participante> participantes = eventoService.obtenerParticipantesPorEvento(eventoId);
        return ResponseEntity.ok(participantes);
    }

    // Actualizar participante
    @PutMapping("/participantes/{id}")
    public ResponseEntity<Participante> actualizarParticipante(@PathVariable Long id, @RequestBody Participante participante) {
        try {
            Participante participanteActualizado = eventoService.actualizarParticipante(id, participante);
            return ResponseEntity.ok(participanteActualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar participante
    @DeleteMapping("/participantes/{id}")
    public ResponseEntity<Void> eliminarParticipante(@PathVariable Long id) {
        try {
            eventoService.eliminarParticipante(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

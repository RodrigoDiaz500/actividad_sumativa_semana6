package com.example.actividad_sumativa_semana6.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.actividad_sumativa_semana6.Service.FacturaService;
import com.example.actividad_sumativa_semana6.Model.Factura;
import com.example.actividad_sumativa_semana6.Model.FacturaRequest;  // Importar el DTO
import com.example.actividad_sumativa_semana6.Model.Servicio;

@RestController
@RequestMapping("/api")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    // Servicios
    @PostMapping("/servicios")
    public Servicio crearServicio(@RequestBody Servicio servicio) {
        return facturaService.crearServicio(servicio);
    }

    @GetMapping("/servicios")
    public List<Servicio> obtenerServicios() {
        return facturaService.obtenerServicios();
    }

    // Facturas
    @PostMapping("/facturas")
    public Factura crearFactura(@RequestBody FacturaRequest facturaRequest) {
        return facturaService.crearFactura(facturaRequest.getServicios());  // Usar el DTO para extraer los servicios
    }

    @GetMapping("/facturas")
    public List<Factura> obtenerFacturas() {
        return facturaService.obtenerFacturas();
    }

    @GetMapping("/facturas/{id}")
    public ResponseEntity<Factura> obtenerFactura(@PathVariable Long id) {
        return facturaService.obtenerFacturaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/facturas/{id}/pagar")
    public ResponseEntity<Factura> pagarFactura(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(facturaService.pagarFactura(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/facturas/{id}")
    public ResponseEntity<Void> eliminarFactura(@PathVariable Long id) {
        facturaService.eliminarFactura(id);
        return ResponseEntity.noContent().build();
    }
}

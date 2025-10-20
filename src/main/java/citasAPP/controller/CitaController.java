package citasAPP.controller;

import citasAPP.entity.Cita;
import citasAPP.service.interfaces.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*")
public class CitaController {

    private final CitaService citaService;

    // Inyección por constructor
    @Autowired
    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    // Obtener todas las citas
    @GetMapping
    public ResponseEntity<List<Cita>> listarCitas() {
        List<Cita> citas = citaService.listarCitas();
        return ResponseEntity.ok(citas);
    }

    // Obtener una cita por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCitaPorId(@PathVariable Long id) {
        try {
            Cita cita = citaService.obtenerCitaPorId(id);
            return ResponseEntity.ok(cita);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error al obtener la cita: " + e.getMessage());
        }
    }

    // Crear una nueva cita
    @PostMapping
    public ResponseEntity<String> crearCita(@RequestBody Cita cita) {
        try {
            citaService.crearCita(cita);
            return ResponseEntity.ok("Cita creada correctamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error al crear la cita: " + e.getMessage());
        }
    }

    //Actualizar una cita existente
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarCita(@PathVariable Long id, @RequestBody Cita cita) {
        try {
            cita.setId(id);
            citaService.actualizarCita(cita);
            return ResponseEntity.ok("Cita actualizada correctamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error al actualizar la cita: " + e.getMessage());
        }
    }

    //Eliminar una cita
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCita(@PathVariable Long id) {
        try {
            citaService.eliminarCita(id);
            return ResponseEntity.ok("Cita eliminada correctamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error al eliminar la cita: " + e.getMessage());
        }
    }
}

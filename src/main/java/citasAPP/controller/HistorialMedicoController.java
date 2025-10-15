package citasAPP.controller;


import citasAPP.dto.HistorialMedicoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import citasAPP.service.interfaces.HistorialMedicoService;

import java.util.List;

@RestController
@RequestMapping("/api/historiales-medicos")
public class HistorialMedicoController {

    private final HistorialMedicoService historialService;

    public HistorialMedicoController(HistorialMedicoService historialService) {
        this.historialService = historialService;
    }

    @PostMapping
    public ResponseEntity<HistorialMedicoDto> crearHistorial(@RequestBody HistorialMedicoDto dto) {
        HistorialMedicoDto creado = historialService.crearHistorial(dto);
        return ResponseEntity.ok(creado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialMedicoDto> obtenerHistorial(@PathVariable Long id) {
        HistorialMedicoDto dto = historialService.obtenerHistorialPorId(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<HistorialMedicoDto>> listarHistoriales() {
        List<HistorialMedicoDto> lista = historialService.listarHistoriales();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialMedicoDto> actualizarHistorial(@PathVariable Long id, @RequestBody HistorialMedicoDto dto) {
        HistorialMedicoDto actualizado = historialService.actualizarHistorial(id, dto);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHistorial(@PathVariable Long id) {
        historialService.eliminarHistorial(id);
        return ResponseEntity.noContent().build();
    }
}


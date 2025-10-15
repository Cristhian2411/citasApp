package citasAPP.controller;

import citasAPP.dto.OrdenMedicaDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import citasAPP.service.interfaces.OrdenMedicaService;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes-medicas")
public class OrdenMedicaController {

    private final OrdenMedicaService ordenMedicaService;

    public OrdenMedicaController(OrdenMedicaService ordenMedicaService) {
        this.ordenMedicaService = ordenMedicaService;
    }

    @PostMapping
    public ResponseEntity<OrdenMedicaDto> crearOrden(@RequestBody OrdenMedicaDto dto) {
        OrdenMedicaDto creada = ordenMedicaService.crearOrden(dto);
        return ResponseEntity.ok(creada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenMedicaDto> obtenerOrden(@PathVariable Long id) {
        OrdenMedicaDto dto = ordenMedicaService.obtenerOrdenPorId(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<OrdenMedicaDto>> listarOrdenes() {
        List<OrdenMedicaDto> ordenes = ordenMedicaService.listarOrdenes();
        return ResponseEntity.ok(ordenes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenMedicaDto> actualizarOrden(@PathVariable Long id, @RequestBody OrdenMedicaDto dto) {
        OrdenMedicaDto actualizada = ordenMedicaService.actualizarOrden(id, dto);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long id) {
        ordenMedicaService.eliminarOrden(id);
        return ResponseEntity.noContent().build();
    }
}

package citasAPP.controller;

import citasAPP.dto.MedicamentoDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import citasAPP.service.interfaces.MedicamentoService;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @PostMapping
    public ResponseEntity<MedicamentoDto> crearMedicamento(@RequestBody MedicamentoDto dto) {
        MedicamentoDto creado = medicamentoService.crearMedicamento(dto);
        return ResponseEntity.ok(creado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoDto> obtenerMedicamento(@PathVariable Long id) {
        MedicamentoDto dto = medicamentoService.obtenerMedicamentoPorId(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoDto>> listarMedicamentos() {
        List<MedicamentoDto> medicamentos = medicamentoService.listarMedicamentos();
        return ResponseEntity.ok(medicamentos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoDto> actualizarMedicamento(@PathVariable Long id, @RequestBody MedicamentoDto dto) {
        MedicamentoDto actualizado = medicamentoService.actualizarMedicamento(id, dto);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMedicamento(@PathVariable Long id) {
        medicamentoService.eliminarMedicamento(id);
        return ResponseEntity.noContent().build();
    }
}

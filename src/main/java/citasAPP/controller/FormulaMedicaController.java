package citasAPP.controller;


import citasAPP.dto.FormulaMedicaDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import citasAPP.service.interfaces.FormulaMedicaService;

import java.util.List;

@RestController
@RequestMapping("/api/formulas")
@CrossOrigin(origins = "*")
public class FormulaMedicaController {

    @Autowired
    private FormulaMedicaService formulaMedicaService;

    @GetMapping
    public List<FormulaMedicaDto> listarFormulas() {
        return formulaMedicaService.listarFormulas();
    }

    @GetMapping("/{id}")
    public FormulaMedicaDto obtenerFormulaPorId(@PathVariable Long id) {
        return formulaMedicaService.obtenerFormulaPorId(id);
    }

    @PostMapping
    public FormulaMedicaDto crearFormula(@RequestBody FormulaMedicaDto dto) {
        return formulaMedicaService.crearFormula(dto);
    }

    @PutMapping("/{id}")
    public FormulaMedicaDto actualizarFormula(@PathVariable Long id, @RequestBody FormulaMedicaDto dto) {
        return formulaMedicaService.actualizarFormula(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminarFormula(@PathVariable Long id) {
        formulaMedicaService.eliminarFormula(id);
    }
}


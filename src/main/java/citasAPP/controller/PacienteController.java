package citasAPP.controller;

import citasAPP.dto.PacienteDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import citasAPP.service.interfaces.PacienteService;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<PacienteDto> listarPacientes() {
        return pacienteService.listarPacientes();
    }

    @GetMapping("/{id}")
    public PacienteDto obtenerPacientePorId(@PathVariable Long id) {
        return pacienteService.obtenerPacientePorId(id);
    }

    @PostMapping
    public PacienteDto crearPaciente(@RequestBody PacienteDto pacienteDto) {
        return pacienteService.crearPaciente(pacienteDto);
    }

    @PutMapping("/{id}")
    public PacienteDto actualizarPaciente(@PathVariable Long id, @RequestBody PacienteDto pacienteDto) {
        return pacienteService.actualizarPaciente(id, pacienteDto);
    }

    @DeleteMapping("/{id}")
    public void eliminarPaciente(@PathVariable Long id) {
        pacienteService.eliminarPaciente(id);
    }
}
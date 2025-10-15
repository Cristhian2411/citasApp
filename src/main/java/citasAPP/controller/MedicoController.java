package citasAPP.controller;

import citasAPP.dto.MedicoDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import citasAPP.service.interfaces.MedicoService;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
@CrossOrigin(origins = "*")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public List<MedicoDto> listarMedicos() {
        return medicoService.listarMedicos();
    }

    @GetMapping("/{id}")
    public MedicoDto obtenerMedicoPorId(@PathVariable Long id) {
        return medicoService.obtenerMedicoPorId(id);
    }

    @PostMapping
    public MedicoDto crearMedico(@RequestBody MedicoDto medicoDto) {
        return medicoService.crearMedico(medicoDto);
    }

    @PutMapping("/{id}")
    public MedicoDto actualizarMedico(@PathVariable Long id, @RequestBody MedicoDto medicoDto) {
        return medicoService.actualizarMedico(id, medicoDto);
    }

    @DeleteMapping("/{id}")
    public void eliminarMedico(@PathVariable Long id) {
        medicoService.eliminarMedico(id);
    }
}

package citasAPP.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*") // permite llamadas desde cualquier origen
public class TestController {

    @GetMapping("/protegido")
    public ResponseEntity<String> protegido() {
        return ResponseEntity.ok("Acceso permitido: token JWT válido y verificado correctamente.");
    }

    @GetMapping("/publico")
    public ResponseEntity<String> publico() {
        return ResponseEntity.ok("Endpoint público accesible sin token.");
    }
}

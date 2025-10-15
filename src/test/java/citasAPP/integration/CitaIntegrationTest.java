package citasAPP.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CitaIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listarCitas_deberiaRetornarRegistros() throws Exception {
        mockMvc.perform(get("/api/citas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))  // porque cargamos 2 citas
                .andExpect(jsonPath("$[0].motivo").value("Chequeo general"));
    }

    @Test
    void obtenerCitaPorId_deberiaRetornarCitaCorrecta() throws Exception {
        mockMvc.perform(get("/api/citas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.motivo").value("Chequeo general"));
    }
}

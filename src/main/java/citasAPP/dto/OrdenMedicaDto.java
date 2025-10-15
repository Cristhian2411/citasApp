package citasAPP.dto;

import java.time.LocalDate;

public class OrdenMedicaDto {

    private Long id;
    private String tipoOrden;
    private String resultado;
    private String descripcion;
    private String estado;
    private LocalDate fecha;
    private Long medicoId;
    private Long pacienteId;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipoOrden() { return tipoOrden; }
    public void setTipoOrden(String tipoOrden) { this.tipoOrden = tipoOrden; }

    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Long getMedicoId() { return medicoId; }
    public void setMedicoId(Long medicoId) { this.medicoId = medicoId; }

    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
}

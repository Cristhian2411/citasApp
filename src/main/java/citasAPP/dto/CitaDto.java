package citasAPP.dto;

import citasAPP.entity.Cita;
import java.time.LocalDate;
import java.time.LocalTime;

public class CitaDto {
    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private String motivo;
    private Long idPaciente;
    private Long idMedico;

    public CitaDto() {}

    public CitaDto(Cita cita) {
        this.id = cita.getId();
        this.fecha = cita.getFecha();
        this.hora = cita.getHora();
        this.motivo = cita.getMotivo();
        this.idPaciente = cita.getIdPaciente();
        this.idMedico = cita.getIdMedico();
    }

    public Cita toEntity() {
        Cita cita = new Cita();
        cita.setId(this.id);
        cita.setFecha(this.fecha);
        cita.setHora(this.hora);
        cita.setMotivo(this.motivo);
        cita.setIdPaciente(this.idPaciente);
        cita.setIdMedico(this.idMedico);
        return cita;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }
}

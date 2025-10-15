package citasAPP.dto;

import citasAPP.entity.FormulaMedica;
import java.time.LocalDate;

public class FormulaMedicaDto {

    private Long id;
    private LocalDate fecha;
    private String observaciones;
    private Long pacienteId;
    private Long medicoId;
    private Long medicamentoId;

    public FormulaMedicaDto() {
    }

    public FormulaMedicaDto(FormulaMedica formula) {
        this.id = formula.getId();
        this.fecha = formula.getFecha();
        this.observaciones = formula.getObservaciones();
        this.pacienteId = formula.getPaciente() != null ? formula.getPaciente().getId() : null;
        this.medicoId = formula.getMedico() != null ? formula.getMedico().getId() : null;
        this.medicamentoId = formula.getMedicamento() != null ? formula.getMedicamento().getId() : null;
    }

    public FormulaMedica toEntity() {
        FormulaMedica formula = new FormulaMedica();
        formula.setId(this.id);
        formula.setFecha(this.fecha);
        formula.setObservaciones(this.observaciones);
        return formula;
    }

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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public Long getMedicamentoId() {
        return medicamentoId;
    }

    public void setMedicamentoId(Long medicamentoId) {
        this.medicamentoId = medicamentoId;
    }
}

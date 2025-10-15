package citasAPP.dto;

import citasAPP.entity.Medico;

public class MedicoDto {

    private Long id;
    private String nombre;
    private String especialidad;
    private String telefono;
    private String correo;

    public MedicoDto() {}

    public MedicoDto(Medico medico) {
        this.id = medico.getId();
        this.nombre = medico.getNombre();
        this.especialidad = medico.getEspecialidad();
        this.telefono = medico.getTelefono();
        this.correo = medico.getCorreo();
    }

    public Medico toEntity() {
        Medico medico = new Medico();
        medico.setId(this.id);
        medico.setNombre(this.nombre);
        medico.setEspecialidad(this.especialidad);
        medico.setTelefono(this.telefono);
        medico.setCorreo(this.correo);
        return medico;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}


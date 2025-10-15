package citasAPP.dto;

import citasAPP.entity.Paciente;

public class PacienteDto {

    private Long id;
    private String nombre;
    private String documento;
    private String telefono;
    private String correo;
    private String direccion;

    public PacienteDto() {}

    public PacienteDto(Paciente paciente) {
        this.id = paciente.getId();
        this.nombre = paciente.getNombre();
        this.documento = paciente.getDocumento();
        this.telefono = paciente.getTelefono();
        this.correo = paciente.getCorreo();
        this.direccion = paciente.getDireccion();
    }

    public Paciente toEntity() {
        Paciente paciente = new Paciente();
        paciente.setId(this.id);
        paciente.setNombre(this.nombre);
        paciente.setDocumento(this.documento);
        paciente.setTelefono(this.telefono);
        paciente.setCorreo(this.correo);
        paciente.setDireccion(this.direccion);
        return paciente;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}

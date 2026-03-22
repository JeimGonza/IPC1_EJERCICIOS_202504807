// HERENCIA: Tarea hereda de EntidadAcademica
public class Tarea extends EntidadAcademica {

    // ENCAPSULAMIENTO: atributos privados propios de Tarea
    private String descripcion;
    private String fechaEntrega;

    public Tarea(String titulo, String codigo, String descripcion, String fechaEntrega) {
        super(titulo, codigo);  // Llama al constructor de la clase padre
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
    }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(String fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    // POLIMORFISMO: implementación específica de mostrarInfo()
    @Override
    public void mostrarInfo() {
        System.out.println("      [Tarea] " + getNombre() + " (Código: " + getCodigo() + ")");
        System.out.println("      Descripción : " + descripcion);
        System.out.println("      Entrega     : " + fechaEntrega);
    }
}

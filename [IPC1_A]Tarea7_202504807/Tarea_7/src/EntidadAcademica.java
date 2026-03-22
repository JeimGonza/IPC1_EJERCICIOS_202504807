// ABSTRACCIÓN: Clase abstracta que define el contrato común
public abstract class EntidadAcademica {

    // ENCAPSULAMIENTO: atributos privados
    private String nombre;
    private String codigo;

    public EntidadAcademica(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    // Getters y Setters (Encapsulamiento)
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    // Método abstracto que cada subclase debe implementar (Abstracción + Polimorfismo)
    public abstract void mostrarInfo();
}

import java.util.ArrayList;

// HERENCIA: Curso también hereda de EntidadAcademica
public class Curso extends EntidadAcademica {

    // ENCAPSULAMIENTO: atributos privados propios de Curso
    private String tutor;
    private ArrayList<Tarea> tareas;

    public Curso(String nombre, String codigo, String tutor) {
        super(nombre, codigo);
        this.tutor = tutor;
        this.tareas = new ArrayList<>();
    }

    public String getTutor() { return tutor; }
    public void setTutor(String tutor) { this.tutor = tutor; }

    public ArrayList<Tarea> getTareas() { return tareas; }

    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
        System.out.println("  ✔ Tarea \"" + tarea.getNombre() + "\" agregada al curso \"" + getNombre() + "\".");
    }

    // POLIMORFISMO: implementación específica de mostrarInfo()
    @Override
    public void mostrarInfo() {
        System.out.println("  [Curso] " + getNombre() + " | Código: " + getCodigo() + " | Tutor: " + tutor);
        if (tareas.isEmpty()) {
            System.out.println("      (Sin tareas registradas)");
        } else {
            for (Tarea t : tareas) {
                t.mostrarInfo();  // POLIMORFISMO en acción
            }
        }
    }
}

import java.util.ArrayList;

// Clase gestora que maneja la lista de cursos
public class GestorAcademico {

    // ENCAPSULAMIENTO: lista privada de cursos
    private ArrayList<Curso> cursos;

    public GestorAcademico() {
        this.cursos = new ArrayList<>();
    }

    public void agregarCurso(Curso curso) {
        cursos.add(curso);
        System.out.println("  ✔ Curso \"" + curso.getNombre() + "\" creado exitosamente.");
    }

    // Busca un curso por su código
    public Curso buscarCurso(String codigo) {
        for (Curso c : cursos) {
            if (c.getCodigo().equalsIgnoreCase(codigo)) {
                return c;
            }
        }
        return null;
    }

    // POLIMORFISMO: llama a mostrarInfo() de cada objeto (Curso)
    public void mostrarTodo() {
        if (cursos.isEmpty()) {
            System.out.println("  (No hay cursos registrados aún)");
            return;
        }
        System.out.println("\n========== CURSOS Y TAREAS ==========");
        for (EntidadAcademica entidad : cursos) {
            entidad.mostrarInfo();  // Polimorfismo: cada objeto muestra su propia versión
            System.out.println("  ------------------------------------");
        }
    }

    public boolean hayCursos() {
        return !cursos.isEmpty();
    }
}

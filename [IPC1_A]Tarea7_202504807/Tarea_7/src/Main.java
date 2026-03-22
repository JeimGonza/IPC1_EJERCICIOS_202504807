import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorAcademico gestor = new GestorAcademico();
        int opcion;

        System.out.println("============================================");
        System.out.println("   Sistema de Gestión de Cursos y Tareas   ");
        System.out.println("   Escuela de Ciencias y Sistemas - USAC   ");
        System.out.println("============================================");

        do {
            System.out.println("\n-------- MENÚ PRINCIPAL --------");
            System.out.println("  1. Crear curso");
            System.out.println("  2. Agregar tarea a un curso");
            System.out.println("  3. Mostrar cursos y tareas");
            System.out.println("  4. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    crearCurso(scanner, gestor);
                    break;
                case 2:
                    agregarTarea(scanner, gestor);
                    break;
                case 3:
                    gestor.mostrarTodo();
                    break;
                case 4:
                    System.out.println("\n  Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("  ✘ Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 4);

        scanner.close();
    }

    // ── Crear curso ──────────────────────────────────────────
    private static void crearCurso(Scanner scanner, GestorAcademico gestor) {
        System.out.println("\n--- Crear nuevo curso ---");
        System.out.print("Nombre del curso  : ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Código del curso  : ");
        String codigo = scanner.nextLine().trim();

        System.out.print("Tutor responsable : ");
        String tutor = scanner.nextLine().trim();

        if (nombre.isEmpty() || codigo.isEmpty() || tutor.isEmpty()) {
            System.out.println("  ✘ Todos los campos son obligatorios.");
            return;
        }

        Curso nuevoCurso = new Curso(nombre, codigo, tutor);
        gestor.agregarCurso(nuevoCurso);
    }

    // ── Agregar tarea ─────────────────────────────────────────
    private static void agregarTarea(Scanner scanner, GestorAcademico gestor) {
        if (!gestor.hayCursos()) {
            System.out.println("  ✘ Primero debe crear al menos un curso.");
            return;
        }

        System.out.println("\n--- Agregar tarea a un curso ---");
        System.out.print("Código del curso destino : ");
        String codigoCurso = scanner.nextLine().trim();

        Curso curso = gestor.buscarCurso(codigoCurso);
        if (curso == null) {
            System.out.println("  ✘ No se encontró un curso con ese código.");
            return;
        }

        System.out.print("Título de la tarea   : ");
        String titulo = scanner.nextLine().trim();

        System.out.print("Código de la tarea   : ");
        String codigoTarea = scanner.nextLine().trim();

        System.out.print("Descripción          : ");
        String descripcion = scanner.nextLine().trim();

        System.out.print("Fecha de entrega     : ");
        String fechaEntrega = scanner.nextLine().trim();

        if (titulo.isEmpty() || descripcion.isEmpty() || fechaEntrega.isEmpty()) {
            System.out.println("  ✘ Todos los campos son obligatorios.");
            return;
        }

        Tarea nuevaTarea = new Tarea(titulo, codigoTarea, descripcion, fechaEntrega);
        curso.agregarTarea(nuevaTarea);
    }

    // ── Leer entero con manejo de error ───────────────────────
    private static int leerEntero(Scanner scanner) {
        try {
            int valor = Integer.parseInt(scanner.nextLine().trim());
            return valor;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

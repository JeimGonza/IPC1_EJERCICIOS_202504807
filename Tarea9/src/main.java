public class main {
    // PARTE 1: NODO SIMPLE
    static class NodoEstudiante {
        String carnet;
        String nombre;
        double nota;
        NodoEstudiante siguiente;

        public NodoEstudiante(String carnet, String nombre, double nota) {
            this.carnet = carnet;
            this.nombre = nombre;
            this.nota = nota;
            this.siguiente = null;
        }
    }

    // PARTE 1: LISTA SIMPLEMENTE ENLAZADA
    static class ListaEstudiantes {
        NodoEstudiante head;
        public ListaEstudiantes() {
            this.head = null;
        }

        // Inserta al inicio de la lista
        public void agregarInicio(String carnet, String nombre, double nota) {
            NodoEstudiante nuevo = new NodoEstudiante(carnet, nombre, nota);
            nuevo.siguiente = head;
            head = nuevo;
        }

        // Inserta al final de la lista
        public void agregarFinal(String carnet, String nombre, double nota) {
            NodoEstudiante nuevo = new NodoEstudiante(carnet, nombre, nota);
            if (head == null) {
                head = nuevo;
                return;
            }
            NodoEstudiante actual = head;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }

        // Elimina el nodo con ese carnet
        public void eliminarPorCarnet(String carnet) {
            if (head == null) {
                System.out.println("La lista esta vacia.");
                return;
            }
            if (head.carnet.equals(carnet)) {
                head = head.siguiente;
                return;
            }
            NodoEstudiante actual = head;
            while (actual.siguiente != null) {
                if (actual.siguiente.carnet.equals(carnet)) {
                    actual.siguiente = actual.siguiente.siguiente;
                    return;
                }
                actual = actual.siguiente;
            }
            System.out.println("Estudiante no encontrado.");
        }

        // Retorna el nodo o null si no existe
        public NodoEstudiante buscarPorCarnet(String carnet) {
            NodoEstudiante actual = head;
            while (actual != null) {
                if (actual.carnet.equals(carnet)) {
                    return actual;
                }
                actual = actual.siguiente;
            }
            return null;
        }

        // Imprime todos los estudiantes en orden
        public void imprimirLista() {
            if (head == null) {
                System.out.println("La lista esta vacia.");
                return;
            }
            NodoEstudiante actual = head;
            while (actual != null) {
                System.out.println("Carnet: " + actual.carnet + " | Nombre: " + actual.nombre + " | Nota: " + actual.nota);
                actual = actual.siguiente;
            }
        }

        // Retorna el promedio de notas
        public double obtenerPromedio() {
            if (head == null) {
                return 0;
            }
            double suma = 0;
            int cantidad = 0;
            NodoEstudiante actual = head;
            while (actual != null) {
                suma += actual.nota;
                cantidad++;
                actual = actual.siguiente;
            }
            return suma / cantidad;
        }

        // Retorna el estudiante con mayor nota
        public NodoEstudiante obtenerMejorNota() {
            if (head == null) {
                return null;
            }
            NodoEstudiante mejor = head;
            NodoEstudiante actual = head.siguiente;
            while (actual != null) {
                if (actual.nota > mejor.nota) {
                    mejor = actual;
                }
                actual = actual.siguiente;
            }
            return mejor;
        }
    }



    // PARTE 2: NODO DOBLE
    static class NodoEstudianteDoble {
        String carnet;
        String nombre;
        double nota;
        NodoEstudianteDoble siguiente;
        NodoEstudianteDoble anterior;

        public NodoEstudianteDoble(String carnet, String nombre, double nota) {
            this.carnet = carnet;
            this.nombre = nombre;
            this.nota = nota;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    // PARTE 2: LISTA DOBLEMENTE ENLAZADA
    static class ListaDobleEstudiantes {
        NodoEstudianteDoble head;
        NodoEstudianteDoble tail;

        public ListaDobleEstudiantes() {
            this.head = null;
            this.tail = null;
        }
        // Inserta al inicio de la lista
        public void agregarInicio(String carnet, String nombre, double nota) {
            NodoEstudianteDoble nuevo = new NodoEstudianteDoble(carnet, nombre, nota);
            if (head == null) {
                head = nuevo;
                tail = nuevo;
                return;
            }
            nuevo.siguiente = head;
            head.anterior = nuevo;
            head = nuevo;
        }

        // Inserta al final de la lista
        public void agregarFinal(String carnet, String nombre, double nota) {
            NodoEstudianteDoble nuevo = new NodoEstudianteDoble(carnet, nombre, nota);
            if (head == null) {
                head = nuevo;
                tail = nuevo;
                return;
            }
            nuevo.anterior = tail;
            tail.siguiente = nuevo;
            tail = nuevo;
        }

        // Elimina el nodo con ese carnet actualizando prev y next
        public void eliminarPorCarnet(String carnet) {
            if (head == null) {
                System.out.println("La lista esta vacia.");
                return;
            }
            NodoEstudianteDoble actual = head;
            while (actual != null) {
                if (actual.carnet.equals(carnet)) {
                    if (actual.anterior != null) {
                        actual.anterior.siguiente = actual.siguiente;
                    } else {
                        head = actual.siguiente;
                    }
                    if (actual.siguiente != null) {
                        actual.siguiente.anterior = actual.anterior;
                    } else {
                        tail = actual.anterior;
                    }
                    return;
                }
                actual = actual.siguiente;
            }
            System.out.println("Estudiante no encontrado.");
        }

        // Recorre HEAD -> TAIL
        public void imprimirAdelante() {
            if (head == null) {
                System.out.println("La lista esta vacia.");
                return;
            }
            NodoEstudianteDoble actual = head;
            while (actual != null) {
                System.out.println("Carnet: " + actual.carnet + " | Nombre: " + actual.nombre + " | Nota: " + actual.nota);
                actual = actual.siguiente;
            }
        }

        // Recorre TAIL -> HEAD
        public void imprimirAtras() {
            if (tail == null) {
                System.out.println("La lista esta vacia.");
                return;
            }
            NodoEstudianteDoble actual = tail;
            while (actual != null) {
                System.out.println("Carnet: " + actual.carnet + " | Nombre: " + actual.nombre + " | Nota: " + actual.nota);
                actual = actual.anterior;
            }
        }

        // Inserta manteniendo orden ascendente por nota
        public void insertarOrdenado(String carnet, String nombre, double nota) {
            NodoEstudianteDoble nuevo = new NodoEstudianteDoble(carnet, nombre, nota);

            if (head == null) {
                head = nuevo;
                tail = nuevo;
                return;
            }

            if (nota <= head.nota) {
                nuevo.siguiente = head;
                head.anterior = nuevo;
                head = nuevo;
                return;
            }

            NodoEstudianteDoble actual = head;
            while (actual.siguiente != null && actual.siguiente.nota < nota) {
                actual = actual.siguiente;
            }

            if (actual.siguiente == null) {
                nuevo.anterior = actual;
                actual.siguiente = nuevo;
                tail = nuevo;
            } else {
                nuevo.siguiente = actual.siguiente;
                nuevo.anterior = actual;
                actual.siguiente.anterior = nuevo;
                actual.siguiente = nuevo;
            }
        }
    }

    // =============================================
    // MAIN - PRUEBAS
    // =============================================
    public static void main(String[] args) {

        // ===== PARTE 1: Lista Simplemente Enlazada =====
        System.out.println("===== LISTA SIMPLEMENTE ENLAZADA =====");

        ListaEstudiantes lista = new ListaEstudiantes();

        lista.agregarFinal("202504807", "Jeimy Mariana", 85.0);
        lista.agregarFinal("202604508", "Jose Rene", 90.0);
        lista.agregarInicio("20250123", "Ana Maldonado", 78.0);
        System.out.println("\n-- Imprimir lista --");
        lista.imprimirLista();

        System.out.println("\n-- Buscar carnet 202504807 --");
        NodoEstudiante encontrado = lista.buscarPorCarnet("202504807");
        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado.nombre);
        } else {
            System.out.println("No encontrado.");
        }

        System.out.println("\n-- Promedio de notas --");
        System.out.println("Promedio: " + lista.obtenerPromedio());

        System.out.println("\n-- Mejor nota --");
        NodoEstudiante mejor = lista.obtenerMejorNota();
        System.out.println("Mejor: " + mejor.nombre + " con " + mejor.nota);

        System.out.println("\n-- Eliminar carnet 202300001 --");
        lista.eliminarPorCarnet("202300001");
        lista.imprimirLista();


        // ===== PARTE 2: Lista Doblemente Enlazada =====
        System.out.println("\n===== LISTA DOBLEMENTE ENLAZADA =====");

        ListaDobleEstudiantes listaDoble = new ListaDobleEstudiantes();

        listaDoble.agregarFinal("202300010", "Pedro Ramos", 72.0);
        listaDoble.agregarFinal("202300011", "Laura Diaz", 88.0);
        listaDoble.agregarInicio("202300012", "Jorge Ruiz", 65.0);

        System.out.println("\n-- Imprimir adelante (HEAD -> TAIL) --");
        listaDoble.imprimirAdelante();

        System.out.println("\n-- Imprimir atras (TAIL -> HEAD) --");
        listaDoble.imprimirAtras();

        System.out.println("\n-- Eliminar carnet 202300011 --");
        listaDoble.eliminarPorCarnet("202300011");
        listaDoble.imprimirAdelante();

        System.out.println("\n-- Insertar ordenado por nota --");
        ListaDobleEstudiantes listaOrdenada = new ListaDobleEstudiantes();
        listaOrdenada.insertarOrdenado("202510455", "Sofia Castro", 80.0);
        listaOrdenada.insertarOrdenado("202658417", "Luis Vega", 60.0);
        listaOrdenada.insertarOrdenado("202521456", "Elena Mora", 95.0);
        listaOrdenada.insertarOrdenado("202563697", "Diego Leon", 75.0);
        listaOrdenada.imprimirAdelante();
    }
}
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Tarea 11 - Ejercicio 2
 * Extractor y Transformador de Texto
 * Carné: 202504807
 */
public class Ejercicio2 {

    // Texto constante proporcionado en el enunciado
    private static final String TEXTO =
            "UNIVERSIDAD DE SAN CARLOS DE GUATEMALA\n" +
                    "Facultad de Ingeniería — Boletín Informativo 2024-04-25\n" +
                    "\n" +
                    "Actividades programadas:\n" +
                    "- Conferencia el 2024-05-01 a las 10:30 en el Edificio T3\n" +
                    "- Examen parcial el 2024-05-15 a las 08:00 en el Edificio S11\n" +
                    "- Defensa de proyecto el 2024-06-10 a las 14:30 en el Edificio T7\n" +
                    "\n" +
                    "Contactos del departamento:\n" +
                    "- Coordinador: coord.ipc1@ingenieria.usac.edu.gt Tel: 2418-8000\n" +
                    "- Auxiliar 1: aux01_ipc1@ingenieria.usac.edu.gt Tel: 5555-1234\n" +
                    "- Auxiliar 2: aux02.ipc1@gmail.com Tel: 4321-9876\n";

    // Método 1 - Extraer fechas en formato YYYY-MM-DD

    public static void extraerFechas() {
        // Grupos: (\d{4}) año, (\d{2}) mes, (\d{2}) día separados por guión
        Pattern patron = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
        Matcher matcher = patron.matcher(TEXTO);

        System.out.println("=== Fechas encontradas ===");
        while (matcher.find()) {
            String anio = matcher.group(1);
            String mes  = matcher.group(2);
            String dia  = matcher.group(3);
            System.out.println("Año: " + anio + " | Mes: " + mes + " | Día: " + dia);
        }
    }
 //Método 2 - Extraer correos electrónicos del texto

    public static void extraerCorreos() {
        // [a-zA-Z0-9][a-zA-Z0-9._]* => parte local (no inicia con . ni _)
        // @ => arroba
        // [a-zA-Z0-9.-]+ => dominio (puede tener subdominio)
        // \.[a-zA-Z]{2,} => extensión de al menos 2 letras
        Pattern patron = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        Matcher matcher = patron.matcher(TEXTO);

        System.out.println("=== Correos encontrados ===");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }



    // Método 3 - Censurar datos sensibles en un String dado

    public static String censurarDatos(String texto) {
        // Paso 1: reemplazar correos electrónicos
        // [a-zA-Z0-9][a-zA-Z0-9._]* => parte local válida
        // @[a-zA-Z0-9.-]+\.[a-zA-Z]{2,} => dominio
        Pattern patronCorreo = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        Matcher matcherCorreo = patronCorreo.matcher(texto);
        String resultado = matcherCorreo.replaceAll("[CORREO]");

        // Paso 2: reemplazar teléfonos con formato XXXX-XXXX (4 dígitos, guión, 4 dígitos)
        Pattern patronTel = Pattern.compile("\\d{4}-\\d{4}");
        Matcher matcherTel = patronTel.matcher(resultado);
        resultado = matcherTel.replaceAll("[TEL]");

        return resultado;
    }

    public static void main(String[] args) {

        System.out.println("========== MÉTODO 1: Extraer Fechas ==========");
        extraerFechas();

        System.out.println("\n========== MÉTODO 2: Extraer Correos ==========");
        extraerCorreos();

        System.out.println("\n========== MÉTODO 3: Censurar Datos Sensibles ==========");

        // Caso 1: ejemplo del enunciado
        String caso1 = "Llama a 5555-1234 o escribe a juan@usac.edu.gt para más info.";
        System.out.println("Entrada: " + caso1);
        System.out.println("Salida:  " + censurarDatos(caso1));

        System.out.println();

        // Caso 2: texto con múltiples datos sensibles
        String caso2 = "Contacto: aux01_ipc1@ingenieria.usac.edu.gt Tel: 4321-9876";
        System.out.println("Entrada: " + caso2);
        System.out.println("Salida:  " + censurarDatos(caso2));

        System.out.println();

        // Caso 3: aplicar censura al texto completo
        System.out.println("--- Texto completo censurado ---");
        System.out.println(censurarDatos(TEXTO));
    }
}
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Tarea 11 - Ejercicio 1
 * Validador de Datos de Estudiante
 * Carné: 202504807
 */
public class Ejercicio1 {

    public static boolean validarCarnet(String carnet) {
        // \d{9} => exactamente 9 dígitos, ^ y $ anclan inicio y fin
        Pattern patron = Pattern.compile("^\\d{9}$");
        Matcher matcher = patron.matcher(carnet);
        return matcher.matches();
    }

    //Método 2 - Valida correo institucional USAC

    public static boolean validarCorreoUSAC(String correo) {
        // [a-zA-Z0-9] => primer carácter (no punto ni guión bajo)
        // [a-zA-Z0-9._]* => resto de la parte local
        // @usac\.edu\.gt => dominio fijo
        Pattern patron = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9._]*@usac\\.edu\\.gt$");
        Matcher matcher = patron.matcher(correo);
        return matcher.matches();
    }

    // * Método 3 - Valida número de teléfono guatemalteco

    public static boolean validarTelefono(String telefono) {
        // [3456] => primer dígito válido para celular en Guatemala
        // \d{3} => tres dígitos más => total 4 dígitos en primer bloque
        // -? => guión opcional
        // \d{4} => cuatro dígitos finales
        Pattern patron = Pattern.compile("^[3456]\\d{3}-?\\d{4}$");
        Matcher matcher = patron.matcher(telefono);
        return matcher.matches();
    }

    public static void main(String[] args) {

        System.out.println("===== Método 1: Validar Carnet USAC =====");
        String[] carnets = {"202300123", "202512345", "20230012", "2023ABC12", "202504807", "123456789"};
        for (String c : carnets) {
            System.out.println(c + " => " + (validarCarnet(c) ? "Válido" : "Inválido"));
        }

        System.out.println("\n===== Método 2: Validar Correo USAC =====");
        String[] correos = {
                "juan.perez@usac.edu.gt",
                "carla_001@usac.edu.gt",
                "juan@gmail.com",
                ".juan@usac.edu.gt",
                "_admin@usac.edu.gt",
                "estudiante123@usac.edu.gt"
        };
        for (String correo : correos) {
            System.out.println(correo + " => " + (validarCorreoUSAC(correo) ? "Válido" : "Inválido"));
        }

        System.out.println("\n===== Método 3: Validar Teléfono Guatemalteco =====");
        String[] telefonos = {
                "5555-1234",
                "30001234",
                "1234-5678",
                "555-1234",
                "4321-9876",
                "6000-0001"
        };
        for (String tel : telefonos) {
            System.out.println(tel + " => " + (validarTelefono(tel) ? "Válido" : "Inválido"));
        }
    }
}
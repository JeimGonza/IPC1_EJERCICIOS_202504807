# Tarea 11 — Expresiones Regulares en Java (`java.util.regex`)

**Curso:** Introducción a la Programación y Computación 1  
**Facultad de Ingeniería — ECYS, USAC**  
**Nombre:** Jeimy Mariana González López
**Carné:** 202504807  

---

## Contenido

1. [Investigación: java.util.regex](#1-investigación-javautilregex)  
   - [¿Qué es una expresión regular?](#qué-es-una-expresión-regular)  
   - [Clases principales](#clases-principales)  
   - [Sintaxis básica de patrones](#sintaxis-básica-de-patrones)  
   - [Flujo de uso en Java](#flujo-de-uso-en-java)  
2. [Estructura del proyecto](#2-estructura-del-proyecto)  
3. [Ejercicio 1 — Validador de Datos de Estudiante](#3-ejercicio-1--validador-de-datos-de-estudiante)  
4. [Ejercicio 2 — Extractor y Transformador de Texto](#4-ejercicio-2--extractor-y-transformador-de-texto)  
5. [Capturas de funcionamiento](#5-capturas-de-funcionamiento)

---

## 1. Investigación: `java.util.regex`

### ¿Qué es una expresión regular?

Una **expresión regular** (regex) es una secuencia de caracteres que define un patrón de búsqueda. Se usan para:

- **Validar** formato de datos (correos, teléfonos, carnets).
- **Buscar** coincidencias dentro de un texto.
- **Extraer** partes específicas con grupos de captura.
- **Transformar** texto reemplazando patrones.

En Java, el soporte para expresiones regulares está integrado en el paquete `java.util.regex`, disponible desde Java 1.4.

---

### Clases principales

#### `Pattern`

Representa un patrón compilado. Se crea con el método estático `Pattern.compile(String regex)`.  
Compilar el patrón una sola vez y reutilizarlo es más eficiente que compilarlo cada vez.

```java
Pattern patron = Pattern.compile("^\\d{9}$");
```

> **Nota:** En Java los strings usan `\` como carácter de escape, por eso cada `\` de regex se escribe como `\\`. Por ejemplo, `\d` (dígito) se escribe `"\\d"`.

#### `Matcher`

Realiza las operaciones de búsqueda sobre un texto (`CharSequence`) usando el patrón compilado.  
Se obtiene desde `Pattern` mediante `patron.matcher(texto)`.

```java
Matcher matcher = patron.matcher(cadena);
```

Métodos más usados de `Matcher`:

| Método | Descripción |
|---|---|
| `matches()` | `true` si **toda** la cadena coincide con el patrón |
| `find()` | `true` si hay al menos una coincidencia (recorre el texto) |
| `group()` | Retorna el texto de la última coincidencia encontrada |
| `group(n)` | Retorna el contenido del grupo de captura número `n` |
| `replaceAll(reemplazo)` | Reemplaza todas las coincidencias |
| `replaceFirst(reemplazo)` | Reemplaza solo la primera coincidencia |

---

### Sintaxis básica de patrones

| Elemento | Significado | Ejemplo |
|---|---|---|
| `.` | Cualquier carácter (excepto salto de línea) | `a.b` → `axb`, `a1b` |
| `\d` | Dígito `[0-9]` | `\d{4}` → `2024` |
| `\w` | Carácter de palabra `[a-zA-Z0-9_]` | `\w+` → `hola_123` |
| `\s` | Espacio en blanco | `\s+` → ` ` |
| `^` | Inicio de cadena | `^\d` → empieza con dígito |
| `$` | Fin de cadena | `\d$` → termina con dígito |
| `[abc]` | Uno de los caracteres indicados | `[346]` → `3`, `4` o `6` |
| `[^abc]` | Cualquier carácter excepto los indicados | `[^.]` |
| `{n}` | Exactamente n repeticiones | `\d{9}` → 9 dígitos |
| `{n,m}` | Entre n y m repeticiones | `\d{2,4}` |
| `*` | 0 o más repeticiones | `\d*` |
| `+` | 1 o más repeticiones | `\d+` |
| `?` | 0 o 1 repetición (opcional) | `-?` → guión opcional |
| `(...)` | Grupo de captura | `(\d{4})-(\d{2})` |
| `\|` | Alternativa (OR) | `jpg\|png` |
| `\.` | Punto literal (escapado) | `usac\.edu\.gt` |

---

### Flujo de uso en Java

```java
import java.util.regex.Pattern;
import java.util.regex.Matcher;

// 1. Compilar el patrón
Pattern patron = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");

// 2. Crear el Matcher sobre el texto de entrada
Matcher matcher = patron.matcher("Fecha: 2024-05-01");

// 3. Buscar coincidencias
while (matcher.find()) {
    System.out.println("Año:  " + matcher.group(1));  // 2024
    System.out.println("Mes:  " + matcher.group(2));  // 05
    System.out.println("Día:  " + matcher.group(3));  // 01
}
```

**Diferencia entre `matches()` y `find()`:**

- `matches()` → evalúa si **toda** la cadena coincide. Se usa para validación.
- `find()` → busca el patrón **dentro** de la cadena. Se usa para extracción.

---

## 2. Estructura del proyecto

```
Tarea11/
├── src/
│   ├── Ejercicio1.java   # Validador de datos de estudiante
│   └── Ejercicio2.java   # Extractor y transformador de texto
└── README.md
```

---

## 3. Ejercicio 1 — Validador de Datos de Estudiante

**Archivo:** `Ejercicio1.java`

Contiene tres métodos estáticos de validación. Cada uno retorna `true` si el dato es válido y `false` si no.

### Método 1 — Carnet universitario

**Regla:** Exactamente 9 dígitos (4 de año + 5 de correlativo), sin guiones ni espacios.

**Patrón:** `^\d{9}$`

| `^` | `\d{9}` | `$` |
|---|---|---|
| Inicio | Exactamente 9 dígitos | Fin |

| Entrada | Resultado |
|---|---|
| `202300123` |  Válido |
| `202512345` |  Válido |
| `20230012` |  Inválido (8 dígitos) |
| `2023ABC12` |  Inválido (contiene letras) |

### Método 2 — Correo institucional USAC

**Regla:** Parte local con letras, dígitos, puntos y guiones bajos; no puede iniciar con `.` ni `_`; dominio obligatorio `@usac.edu.gt`.

**Patrón:** `^[a-zA-Z0-9][a-zA-Z0-9._]*@usac\.edu\.gt$`

| Entrada | Resultado |
|---|---|
| `juan.perez@usac.edu.gt` |  Válido |
| `carla_001@usac.edu.gt` |  Válido |
| `juan@gmail.com` | Inválido (dominio incorrecto) |
| `.juan@usac.edu.gt` |  Inválido (inicia con punto) |

### Método 3 — Teléfono guatemalteco

**Regla:** 8 dígitos, con o sin guión en el centro. Primer dígito debe ser 3, 4, 5 o 6.

**Patrón:** `^[3456]\d{3}-?\d{4}$`

| Entrada | Resultado |
|---|---|
| `5555-1234` |  Válido |
| `30001234` |  Válido |
| `1234-5678` |  Inválido (inicia con 1) |
| `555-1234` |  Inválido (7 dígitos) |

---

## 4. Ejercicio 2 — Extractor y Transformador de Texto

**Archivo:** `Ejercicio2.java`

Trabaja sobre un texto constante del boletín de la Facultad de Ingeniería.

### Método 1 — Extraer fechas

Busca todas las fechas en formato `YYYY-MM-DD` usando **3 grupos de captura**.

**Patrón:** `(\d{4})-(\d{2})-(\d{2})`

Salida esperada:
```
Año: 2024 | Mes: 04 | Día: 25
Año: 2024 | Mes: 05 | Día: 01
Año: 2024 | Mes: 05 | Día: 15
Año: 2024 | Mes: 06 | Día: 10
```

### Método 2 — Extraer correos electrónicos

Encuentra todos los correos en el texto e imprime uno por línea.

**Patrón:** `[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}`

Salida esperada:
```
coord.ipc1@ingenieria.usac.edu.gt
aux01_ipc1@ingenieria.usac.edu.gt
aux02.ipc1@gmail.com
```

### Método 3 — Censurar datos sensibles

Recibe un `String` y reemplaza en orden:
1. Correos → `[CORREO]`
2. Teléfonos con formato `XXXX-XXXX` → `[TEL]`

**Ejemplo:**

```
Entrada: "Llama a 5555-1234 o escribe a juan@usac.edu.gt para más info."
Salida:  "Llama a [TEL] o escribe a [CORREO] para más info."
```

> El orden de reemplazo importa: los correos se censuran primero para que el `@` no interfiera con el patrón de teléfono.

---

## 5. Capturas de funcionamiento

### Ejercicio 1 — Validador de Datos

> **Agregar aquí captura de pantalla de la ejecución de `Ejercicio1.java`**  
> *(Ejecutar en el IDE y tomar captura de la consola con los resultados de los tres métodos)*

![Ejercicio 1](Capturas/Ejercicio1.png)

---

### Ejercicio 2 — Extractor y Transformador

> **Agregar aquí captura de pantalla de la ejecución de `Ejercicio2.java`**  
> *(Debe mostrar las fechas extraídas, los correos encontrados y los tres casos de censura)*

![Ejercicio 2](Capturas/Ejercicio2.1.png)
![Ejercicio 2](Capturas/Ejercicio2.2.png)

---

## Referencias

- Oracle. (2024). *Class Pattern* — `java.util.regex`. Java SE 21 Documentation.  
  https://docs.oracle.com/en/java/docs/api/java.base/java/util/regex/Pattern.html
- Oracle. (2024). *Class Matcher* — `java.util.regex`. Java SE 21 Documentation.  
  https://docs.oracle.com/en/java/docs/api/java.base/java/util/regex/Matcher.html
- Horstmann, C. S. (2019). *Core Java, Volume I — Fundamentals* (11.ª ed.). Pearson.

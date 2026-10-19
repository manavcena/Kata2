# Kata 2 - Procesamiento de Datos y Arquitectura I/O (Video Games)

## ¿De qué va el proyecto?
Esta aplicación procesa un catálogo de videojuegos en formato CSV para calcular el total de juegos por género. Lo importante del diseño es que está pensado para no depender de archivos físicos ni de la consola de forma fija: las clases trabajan con las abstracciones `InputStream` y `OutputStream`, por lo que podemos cambiar de dónde leemos los datos o a dónde los enviamos sin romper el programa.

## Arquitectura y Clases
- **`VideoGame`**: Un `record` simple e inmutable para guardar los atributos de cada videojuego (id, título, género, plataforma, año y nota).
- **`VideoGameLoader` / `CsvVideoGameLoader`**: Se encarga de transformar el texto del CSV en objetos. Como recibe un `InputStream`, le da igual si los datos vienen de un archivo en disco, de memoria RAM (`ByteArrayInputStream`) o de la red.
- **`VideoGameStatistics`**: Recibe la lista de videojuegos en memoria y hace los cálculos con Streams de Java (`groupingBy` y `counting`). No sabe ni le importa de dónde salió el archivo.
- **`StatsWriter` / `PlainTextStatsWriter`**: Escribe el mapa con las estadísticas. Como recibe un `OutputStream`, podemos mandar el texto a `System.out` (consola) o a un `FileOutputStream` (`resumen.txt`).

## Repeticiones en Git
El proyecto se fue construyendo en tres iteraciones reflejadas en los commits:
1. **Repetición 1:** Montaje de la estructura básica leyendo el CSV local y sacando el resultado por consola.
2. **Repetición 2:** Prueba del polimorfismo leyendo datos simulados desde memoria y guardando el resultado en un archivo `resumen.txt`.
3. **Repetición 3:** Control de errores (filas corruptas o archivos que no existen) y limpieza general del código.

## Pruebas en el Main
La clase `Main.java` ejecuta 4 pruebas para comprobar que todo funciona:
1. **Caso normal:** Lee `videogames.csv` y muestra el recuento en la terminal.
2. **Redirección de I/O:** Lee datos cargados en memoria y los guarda dentro del archivo `resumen.txt`.
3. **Fila corrupta:** Procesa un stream con una línea rota a propósito. La ignora avisando por `System.err` y sigue procesando los datos válidos.
4. **Archivo inexistente:** Intenta abrir un archivo fantasma y captura la excepción `FileNotFoundException` sin que el programa se rompa.

## Cómo ejecutarlo
1. Abrir el proyecto en **IntelliJ IDEA**.
2. Ir a `src/main/java/software/ulpgc/katas/Main.java`.
3. Hacer clic derecho sobre `Main.java` y darle a **Run 'Main.main()'**.

## Vídeo explicativo
https://youtu.be/PfrlJPmutbo

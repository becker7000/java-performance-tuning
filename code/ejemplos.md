
```shell
29    3       3       java.lang.Object::<init> (1 bytes)
```

```java

public double calcularTotal(){
        ...
}

```

```shell
    VM Warning: CodeCache is full. Compiler has been disabled.
```

### Comandos aplicados: 
```shell

// Con la impresión de números primos activada
java -XX:+PrintCompilation Main 50

// Volver a ejecutar el comando anterior
// Pero con la impresión de números primos desactivada
java -XX:+PrintCompilation Main 5000

// Generando un archivo log 
// Aunque su analisis es dificil 
java -XX:+UnlockDiagnosticVMOptions -XX:+LogCompilation Main 50

// Información de la cache:
java -XX:+PrintCodeCache Main 50

// Ajustar el tamaño máximo de la cache de código compilado
// Y visualizar los cambios
// Todo en tiempo de ejecución (JIT)
java -XX:ReservedCodeCacheSize=140m -XX:+PrintCodeCache Main 5000 // Último comando

---
 
// Deshabilitar la ejecución por niveles
java -XX:-TieredCompilation -XX:+PrintCompilation Main 5000
 
// Revisar el número de núcleos en nuestro ordenador:
wmic cpu get NumberOfCores,NumberOfLogicalProcessors
// Otra forma (grafica): administrador de tareas, rendimiento, CPU
// Otra forma más (grafica): win + r, msinfo32, procesador
 
// Propiedades de la JVM
// CICompilerCount muestra el número de hilos
// con esta forma es difícil encontrar la propiedad CICompilerCount
java -XX:+PrintFlagsFinal

// Procesos de la JVM
jps

// Procesos de la JVM con más detalle
jps -l

// Número de hilos por proceso JVM:
jinfo -flag CICompilerCount 20304
jinfo -flag CICompilerCount 560
jinfo -flag CICompilerCount 6444 // Proceso no válido

// Volver a probar y ver el tiempo total:
java -XX:+PrintCompilation Main 15000 // 1788 milisegundos

// Modificando el número de hilos para una aplicación:
java -XX:CICompilerCount=4 -XX:+PrintCompilation Main 15000 // 1794 milisegundos
java -XX:CICompilerCount=5 -XX:+PrintCompilation Main 15000 // 1776 milisegundos
java -XX:CICompilerCount=6 -XX:+PrintCompilation Main 15000 // 1768 milisegundos
java -XX:CICompilerCount=7 -XX:+PrintCompilation Main 15000 // 1772 milisegundos

// Umbral de invocaciones de un método en un proceso
// antes de convertirlo a código nativo
// 10000 por defecto
jinfo -flag CompileThreshold 560
jinfo -flag CompileThreshold 20304


// Reducir el umbral de invocaciones
java -XX:CICompilerCount=6 -XX:CompileThreshold=1000 -XX:+PrintCompilation Main 15000 // 1760 milisegundos


```
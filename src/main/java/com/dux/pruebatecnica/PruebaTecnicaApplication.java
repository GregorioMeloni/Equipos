//Paquete al que pertenece la clase PruebaTecnicaApplication
package com.dux.pruebatecnica;
//Importa clase SpringApplication que se utiliza para arrancar la aplicación Spring Boot desde método main()
import org.springframework.boot.SpringApplication;
//Importa decorador @SpringBootApplication para marcar clase principal de la aplicación Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Combina 3 decoradores en uno: @Configuration, @EnableAutoConfigurationy @ComponentScan.
//Activa configuración automática de Spring Boot    
@SpringBootApplication
//Declara clase PruebaTecnicaApplication
public class PruebaTecnicaApplication {
    //Define metodo principal main
    public static void main(String[] args) {
        //Llamada al metodo run() para que SpringApplication inicia la aplicación Spring Boot
        SpringApplication.run(PruebaTecnicaApplication.class, args);
        
        // Crear una instancia de la clase Equipo
        Equipo equipo = new Equipo();
        
        // Llamada de los setters para establecer los valores de los atributos de la instancia equipo
        // 1L representa numero entero 1 pero como tipo Long en vez de int
        // De esta manera se evita cualquier error de compilacion. Si un valor es mayor que el rango permitido para un int puede provocar error
        // Metodo setId() espera argumento tipo Long por lo que se utiliza 1L para asegurarse de que pase
        // un valor de este tipo long a dicho método, de esta manera evitamos que no haya ningun problema de tipo en la asignación.

        equipo.setId(1L);
        equipo.setNombre("Real Madrid");
        equipo.setLiga("La Liga");
        equipo.setPais("España");
        
        // Se obtienen valores delos atirbutos conn los get y se muestra datos por consola
        System.out.println("ID: " + equipo.getId());
        System.out.println("Nombre: " + equipo.getNombre());
        System.out.println("Liga: " + equipo.getLiga());
        System.out.println("País: " + equipo.getPais());
    }
}
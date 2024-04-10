//Definimos interfaz llamada EquipoRepository que es un repositorio que maneja operaciones
//relacionadas con la persistencia de objetos de la clase Equipo. Esta interfaz se utiliza para
//interfactuar con la capa de persistencia de la aplicación y proporciona metodos para realizar
//operaciones CRUD en la entidad Equipo

//Define el paquete al que pertenece la interfaz
package com.dux.pruebatecnica.repository;

//Importamos clases y decoradores para la definicion de la interfaz
//Para trabajar con listas de objetos
import java.util.List;
//Interfaz que proporciona métodos para realizar operaciones CRUD en una entidad JPA
import org.springframework.data.jpa.repository.JpaRepository;
//Decorador @Repository indica que la interfaz es un componente de tipo repositorio
import org.springframework.stereotype.Repository;
//Clase Equipo a utilizar en la interfaz
import com.dux.pruebatecnica.Equipo;

@Repository
//EquipoRepository hereda métodos para realizar operaciones CRUD en la  entidad Equipo.
//El parametro <Equipo> especifica la entidad con la que se trabajará y el parametro <Long>
//especifica el tipo del identificador primario de la entidad
public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    //Este metodo define una consulta personalizada para buscar objetos Equipo cuyo nombre contenga
    //la cadena proporcionada como argumento. 
    //Este método devuelve una lista de objetos Equipo que cumplen con el criterio de búsqueda
    List<Equipo> findByNombreContaining(String nombre);
}
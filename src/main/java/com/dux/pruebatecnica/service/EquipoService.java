//Se define clase EquipoService actuando como capa de servicio que encapsula la logica de negocio
//relacionada con los objetos Equipo proporcionando métodos para realizar operaciones CRUD y de 
//búsqueda. Estos metodos utilizan el EquipoRepository para interactuar con la capa de persistencia

//Paquete al que pertenece la clase EquipoService
package com.dux.pruebatecnica.service;
//Importacion clases necesarias y decorador
import com.dux.pruebatecnica.Equipo;
import com.dux.pruebatecnica.repository.EquipoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

//Indica que esta clase es un componente de servicio para definir una capa de servicio 
//en la arquitectura de la aplicación. La clase estará disponible para ser inyectada (inyeccion de dependencias) 
//en otras partes de la aplicación donde se necesite su funcionalidad.
//Esto permite modularidad, reutilizacion y mantenimiento del código.
@Service
public class EquipoService {
    //Declara atributo equipoRepository de tipo EquipoRepository que se utilizará
    //para acceder a los métodos de persistencia definidos en la interfaz EquipoRepository
    private final EquipoRepository equipoRepository;

    //Define constructor que recibe parámetro equipoRepository e inicializa el atributo 
    //equipoRepository de la clase con el valor proporcionado.
    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    //Métodos de servicio
    
    //Retorna todos los equipos almacenados en la BD
    public List<Equipo> obtenerTodosLosEquipos() {
        return equipoRepository.findAll();
    }
    
    //Retorna un equipo por su ID
    public Optional<Equipo> obtenerEquipoPorId(Long id) {
        return equipoRepository.findById(id);
    }

    //Busca equipos cuyo nombre contiene la cadena proporcionada
    public List<Equipo> buscarEquiposPorNombre(String nombre) {
        return equipoRepository.findByNombreContaining(nombre);
    }

    //Guarda un nuevo equipo en la BD
    public Equipo guardarEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    //Actualiza un equipo existente con la información proporcionada.
    public void actualizarEquipo(Long id, Equipo equipoActualizado) {
        //Utiliza el método findById(id) del repositorio de equipos (equipoRepository) para
        //buscar un equipo por su ID. Este método devuelve un objeto Optional<Equipo> que puede
        //contener o no un equipo dependiendo de si se encontró un equipo con el ID indicado.
        Optional<Equipo> optionalEquipo = equipoRepository.findById(id);
        //Verifica si el objeto Optional<Equipo> contiene un equipo usando el método isPresent().
        //Si optionalEquipo contiene un equipo (es decir, si el equipo existe en la base de datos), 
        //se ejecuta el bloque de código dentro del if.
        if (optionalEquipo.isPresent()) {
            //Obtiene el equipo existente de optionalEquipo 
            //utilizando el método get() de Optional<Equipo>.
            Equipo equipo = optionalEquipo.get();
            //Actualiza los atributos del equipo existente con la información del equipo actualizado pasado
            //como argumento al método. Esto se hace utilizando los setters del equipo existente.
            equipo.setNombre(equipoActualizado.getNombre());
            equipo.setLiga(equipoActualizado.getLiga());
            equipo.setPais(equipoActualizado.getPais());
            //Guarda los cambios actualizados del equipo en la BD usando el método save()
            //del repositorio de equipos (equipoRepository)
            equipoRepository.save(equipo);
        }
    }

    //Elimina un equipo de la BD por su ID
    public void eliminarEquipo(Long id) {
        equipoRepository.deleteById(id);
    }
    
}

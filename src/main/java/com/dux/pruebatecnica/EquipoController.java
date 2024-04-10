//Este es un controlador REST que maneja solicitudes relacionadas a los equipos de futbol
package com.dux.pruebatecnica;

//Importa clase EquipoService que maneja logica de negocio de los equipos
import com.dux.pruebatecnica.service.EquipoService;
//Importa enumeración HttpStatus que contiene códigos de estado HTTP estándar
import org.springframework.http.HttpStatus;
/*Importa clase ResponseEntity que representa la respuesta HTTP. 
Las partes de esta respuesta que el servidor envía al cliente son:

Código de Estado (Statud Code - Resultado de la solicitud HTTP):
    200 OK: Solicitud Exitosa.
    201 Created: Solicitud exitosa y se creó nuevo recurso.
    400 Bad Request: Solicitud no entendible debido a sintaxis incorrecta
    404 Not Found: Recurso solicitado no encontrado en el servidor
    500 Internal Server Error: Servidor encontró condición inesperada que le impidió completar la solicitud
Encabezados (Headers - Metadatos adicionales que dan informacion sobre la respuesta y controlan cómo se debe manejar):
    Content-Type: Especifica tipo de contenido del cuerpo de la respuesta (por ejemplo, application/json para JSON).
    Content-Length: Indica longitud del cuerpo de la respuesta en bytes.
    Location: Especifica la URL del recurso recién creado en respuestas 201 Created.
    Set-Cookie: Configura una cookie en el navegador del cliente.
Los encabezados se encuentran en líneas separadas dentro de la respuesta HTTP, después de la línea que contiene el código de estado.
Cuerpo (Body - Contenido real de la respuesta HTTP):
    Puede contener cualquier tipo de dato como texto, HTML, JSON, XML, imágenes, etx.
    Proporciona datos solicitados o cualquier mensaje adicional que el servidor desee enviar al cliente
    Ej.: una solicitud GET exitosa devuelve datos de una BD, el cuerpo puede contener datos solicitados en formato JSON o XML
    El cuerpo se encuentra después de los encabezados separado de ellos por una línea en blanco.
    En las respuestas JSON el cuerpo contendrá los datos estructurados que el cliente solicitó o la información de error*/
import org.springframework.http.ResponseEntity;
//Importa anotaciones de MVC para la creación de controladores REST
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//Indica que esta clase es un controlador REST y que cada método de la clase anotado con este decorador devolverá datos en lugar de una vista HTML.
@RestController
//Especifica que todas las solicitudes HTTP dirigidas a /equipos serán manejadas por este controlador.
@RequestMapping("/equipos")
public class EquipoController {
    //Declara un campo de tipo EquipoService utilizado para realizar operaciones
    private final EquipoService equipoService;
    //Define constructor que toma un objeto EquipoService como argumento e inicializa
    //el campo equipoService
    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }


    //Maneja solicitudes GET a la ruta /equipos para obtener todos los equipos.
    @GetMapping
    public ResponseEntity<List<Equipo>> obtenerTodosLosEquipos() {
        //Utiliza el método obtenerTodosLosEquipos() de EquipoService
        List<Equipo> equipos = equipoService.obtenerTodosLosEquipos();
        //devuelve una respuesta con la lista de equipos utilizando ResponseEntity.ok().
        return ResponseEntity.ok(equipos);
    }


    //Maneja las solicitudes GET a la ruta /equipos/{id} para obtener un equipo por su ID. 
    @GetMapping("/{id}")
    public ResponseEntity<Equipo> obtenerEquipoPorId(@PathVariable Long id) {
        //Utiliza el método obtenerEquipoPorId(Long id) de EquipoService
        Optional<Equipo> equipo = equipoService.obtenerEquipoPorId(id);
        return equipo.map(ResponseEntity::ok)
        //devuelve una respuesta con el equipo si se encuentra, o una respuesta 404 Not Found si no se encuentra.
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }


    //Maneja las solicitudes GET a la ruta /equipos/buscar para buscar equipos por nombre. 
    @GetMapping("/buscar")
    public ResponseEntity<List<Equipo>> buscarEquiposPorNombre(@RequestParam String nombre) {
        //Utiliza el método buscarEquiposPorNombre(String nombre) de EquipoService
        List<Equipo> equipos = equipoService.buscarEquiposPorNombre(nombre);
        //devuelve una respuesta con la lista de equipos encontrados.
        return ResponseEntity.ok(equipos);
    }

    //Maneja las solicitudes POST a la ruta /equipos para crear un nuevo equipo. 
    @PostMapping
    public ResponseEntity<Equipo> crearEquipo(@Validated @RequestBody Equipo equipo) {
        //Utiliza el método guardarEquipo(Equipo equipo) de EquipoService para guardar el nuevo equipo
        Equipo nuevoEquipo = equipoService.guardarEquipo(equipo);
        //devuelve una respuesta con el equipo creado y el código de estado 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEquipo);
    }


    //Maneja las solicitudes PUT a la ruta /equipos/{id} para actualizar un equipo existente. 
    @PutMapping("/{id}")
    //@PathVariable y @RequestBody mapean (obtiene) datos de la solicitud HTTP a parámetros de método en un controlador.
    //En este ejemplo @PathVariable mapea variables/valores de la URL, indica que el parámetro id del método
    //obtenerEquipoPorId(Long id) se obtendrá de la URL de la solicitud. Ej.: Si la URL de la solicitud es /equipos/1, el valor de id será 1.
    //En este ejemplo @RequestBody mapea datos del cuerpo de la solicitud HTTP a un objeto de la clase Equipo
    //Cuando se realiza una solicitud POST o PUT con datos en formato JSON en el cuerpo de la solicitud,
    //se convierten esos datos en un objeto Equipo y lo pasa como argumento al método 
    //crearEquipo(Equipo equipo) o actualizarEquipo(Long id, Equipo equipoActualizado) respectivamente.
    public ResponseEntity<Equipo> actualizarEquipo(@PathVariable Long id, @RequestBody Equipo equipoActualizado) {
        //Utiliza el método actualizarEquipo(Long id, Equipo equipoActualizado) de EquipoService para actualizar el equipo
        equipoService.actualizarEquipo(id, equipoActualizado);
        //devuelve una respuesta con el código de estado 200 OK.
        return ResponseEntity.ok().build();
    }

    //Maneja las solicitudes DELETE a la ruta /equipos/{id} para eliminar un equipo existente. 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEquipo(@PathVariable Long id) {
        //Utiliza el método eliminarEquipo(Long id) de EquipoService para eliminar el equipo 
        equipoService.eliminarEquipo(id);
        //devuelve una respuesta con el código de estado 204 No Content.
        return ResponseEntity.noContent().build();
    }

    // Manejo de errores de validación
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errores = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validación fallida: " + String.join(", ", errores));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}


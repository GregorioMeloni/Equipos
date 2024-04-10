//Este es el controlador de manejo de excepciones globales proporcionando respuestas
//de error personalizadas para cada tipo de excepción
package com.dux.pruebatecnica;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.FieldError;


//Permite que la clase actúe como asesor global para todos los controladores en la
//aplicación, centraliza el maneja de excepciones en un solo lugar.
@ControllerAdvice
public class GlobalExceptionHandler {

    //Indica que el método manejará excepciones específicas cuando se produce una
    //excepción del tipo especificado
    @ExceptionHandler(EquipoNotFoundException.class)
    //handleEquipoNotFoundException: Este método se maneja con excepciones del tipo
    //EquipoNotFoundException. Cuando se lanza una excepción de este tipo, el método
    //crea un objeto ErrorResponse con un código de estado 404(NOT_FOUND) y el mensaje de
    // error proporcionado por la excepción. Luego, devuelve una respuesta de entidad con
    // el estado y el cuerpo del error.
    public ResponseEntity<ErrorResponse> handleEquipoNotFoundException(EquipoNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    //handleDataIntegrityViolationException: Este método maneja excepciones del tipo
    //DataIntegrityViolationException, que ocurren cuando se produce un error
    //de integridad de datos en la base de datos. Similar al método anterior, crea un objeto
    //ErrorResponsecon un código de estado 400(BAD_REQUEST) y un mensaje de error
    //personalizado, y luego devuelve una respuesta de entidad con este error.
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Error de integridad de datos: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    //handleMethodArgumentNotValidException: Este método maneja excepciones del tipo
    //MethodArgumentNotValidException, que ocurren cuando falla la validación de los
    //argumentos de un método, por ejemplo, en un formulario web. En este caso, el método
    //recopila todos los mensajes de error de validación de los campos y los agrega a una lista.
    //Luego, crea un objeto ErrorResponse con un código de estado 400(BAD_REQUEST) y el
    //mensaje de error personalizado que contiene todos los errores de validación.
    //Finalmente, devuelve una respuesta de entidad con este error.
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errores = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validación fallida: " + String.join(", ", errores));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Puedes agregar más manejadores de excepciones aquí para otros tipos de errores
}

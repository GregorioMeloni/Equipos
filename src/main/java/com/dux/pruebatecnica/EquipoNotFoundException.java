package com.dux.pruebatecnica;

//Se define excepción personalizada que es una clase que hereda de la clase RuntimeException que es una excepcion
//de tiempo de ejecución y no requiere que sea capturada o declarada en la firma
//de losmétodos que la lanzan.
//Esta clase EquipoNotFoundException se utiliza para indicar que no se ha encontrado un equipo
public class EquipoNotFoundException extends RuntimeException {
    //Constructor que toma un parametro tipo string que representa el  mensaje
    //de error que se asociará a la excepción
    public EquipoNotFoundException(String message) {
        //Se llama al constructor de la clase padre RuntimeException pasando el mensaje
        //de error recibido como argumento, esto inicializa la excepción con el mensaje proporcionado
        super(message);
    }
}


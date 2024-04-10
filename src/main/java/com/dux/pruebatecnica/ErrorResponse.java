//Se define clase para representar una respuesta de error en la aplicación
//que incluye el código de error y mensaje
//La clase pertenece a este paquete
package com.dux.pruebatecnica;

public class ErrorResponse {
    //Se almacenará el código de error asociado con la respuesta.
    private int codigo;
    //Se almacenará el mensaje de error asociado con la respuesta.
    private String mensaje;

    //Constructor que toma esos parámetros utilizados para inicializar los campos
    //código y mensaje de la instancia de ErrorResponse
    public ErrorResponse(int codigo, String mensaje) {
        //Asigna el valor del parámetro código al campo código de la instancia actual
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    //Método que devuelve el valor del campo codigo.
    public int getCodigo() {
        return codigo;
    }
    //Establece el valor del campo codigo según el valor proporcionado como argumento.
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    //Devuelve el valor del campo mensaje.
    public String getMensaje() {
        return mensaje;
    }

    //Establece el valor del campo mensaje según el valor proporcionado como argumento.
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

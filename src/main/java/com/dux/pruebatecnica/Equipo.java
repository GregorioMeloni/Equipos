//Paquete al que pertenece la clase Equipo, sirve para organizar y estructurar el código
package com.dux.pruebatecnica;
//Persistencia: Transformar los objetos en filas de tablas de la base de datos relacional 
//(desmaterializacion) y proceso inverso sería la materialización.
//Permite que el almacenamiento de los datos persista más allá de la duración de la ejecución del programa
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

//Decorador de una entidad de persistencia (los objetos pueden ser almacenados y recuperados de una base de datos)
@Entity
//Decorador que especifica el nombre de la tabla donde se almacenan los objetos de la clase Equipos
@Table(name = "equipos")

public class Equipo {
    //Declaración de atributos de la instancia (objeto) equipo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Column especifica detalles de cómo se mapearán los atributos de la clase a las columnas
    //de la tabla en la BD. Se especifica que los atributos no pueden ser nulos.
    @NotBlank(message = "El nombre del equipo es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "La liga del equipo es obligatoria")
    @Column(nullable = false)
    private String liga;


    @NotBlank(message = "El país del equipo es obligatorio")
    @Column(nullable = false)
    private String pais;

    //Constructor por defecto sin argumentos.
    //Si en Java no se define ningun constructor en una clase, el compilador proporciona uno por defecto sin argumentos como este (podriamos quitarlo sin problemas)
    //Como definimos luego un constructor con argumentos, en este caso el constructor sin parametros no tiene utilidad.
    //si en el futuro necesitara inicializar objetos Equipo sin proporcionar 
    //valores específicos para sus atributos, podría usar el constructor por defecto para esa tarea.

    public Equipo() {
    }

    //Constructor con argumentos que toma los valores de nombre, liga y pais
    public Equipo(String nombre, String liga, String pais) {
        this.nombre = nombre;
        this.liga = liga;
        this.pais = pais;
    }

    // Getters y setters: para acceder y modificar los valores de los atributos de la clase

    //Tipo de dato Long (numeros enteros tamaño grande, variable de 64 bits)
    //La diferencia con int es que este ultimo es de variable de 32 bits por lo que el rango es menor
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Devuelve valor actual del atributo
    public String getNombre() {
        return nombre;
    }
    //Asigna nuevo valor al atributo
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
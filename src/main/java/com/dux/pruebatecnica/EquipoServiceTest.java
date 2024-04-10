package com.dux.pruebatecnica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.dux.pruebatecnica.repository.EquipoRepository;
import com.dux.pruebatecnica.service.EquipoService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EquipoServiceTest {

    @Mock
    private EquipoRepository equipoRepository;

    @InjectMocks
    private EquipoService equipoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    //En el caso del método testObtenerTodosLosEquipos(), el escenario de prueba
    // implica simular el comportamiento del repositorio de equipos para devolver
    // una lista de equipos cuando se llama al método findAll()del repositorio.
    // Luego, se llama al método obtenerTodosLosEquipos()del servicio de equipos y
    // se verifica si el tamaño de la lista devuelta es igual a 2, ya que se supone
    // que el repositorio devuelve dos equipos.
    public void testObtenerTodosLosEquipos() {
        // Configuración de datos de prueba
        Equipo equipo1 = new Equipo();
        Equipo equipo2 = new Equipo();
        when(equipoRepository.findAll()).thenReturn(Arrays.asList(equipo1, equipo2));

        // Ejecución del método bajo prueba
        List<Equipo> equipos = equipoService.obtenerTodosLosEquipos();

        // Verificación del resultado
        assertEquals(2, equipos.size());
    }

    @Test
    //Para el método testObtenerEquipoPorId(), el escenario de prueba implica
    // simular el comportamiento del repositorio de equipos para devolver un
    // equipo específico cuando se llama al método findById()del repositorio con
    // un ID determinado. Luego, se llama al método obtenerEquipoPorId()del
    // servicio de equipos con el mismo ID y se verifica si el ID del equipo
    // devuelto es igual al ID esperado.
    public void testObtenerEquipoPorId() {
        // Configuración de datos de prueba
        long id = 1L;
        Equipo equipo = new Equipo();
        equipo.setId(id);
        when(equipoRepository.findById(id)).thenReturn(Optional.of(equipo));

        // Ejecución del método bajo prueba
        Optional<Equipo> resultado = equipoService.obtenerEquipoPorId(id);

        // Verificación del resultado
        assertEquals(id, resultado.get().getId());
    }
}

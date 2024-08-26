package com.ceiba.biblioteca.LogicaNegocio;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import com.ceiba.biblioteca.Entidades.Prestamo;
import com.ceiba.biblioteca.Repositorio.PrestamoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //  hacemos esta notacion para indicar a Spring que esta clase estara en el contenedor de spring para luego inyectarla
public class PrestamoLogica {

    @Autowired // hace inyeccion de dependecias automaticamente
    private PrestamoRepositorio prestamoRepositorio; // la inyeccion se aplica la variable prestamoRepositorio de tipo PrestamoRepositorio

    public void validarTipoUsuario(int tipoUsuario) { // Metodo en donde validamos el tipo de Usuario
        if (tipoUsuario < 1 || tipoUsuario > 3) { // || sigfica "O", es decir, si cumple una de las dos tira el mensaje
            throw new IllegalArgumentException("Tipo de usuario no permitido en la biblioteca"); // creamos una instancia de exepcion personalizada 
                                                                                                   // de manera manual
        }
    }

    public void validarUsuarioInvitado(String identificacionUsuario) {
        List<Prestamo> prestamos = prestamoRepositorio.findAll(); // Listamos todos prestamos realizados
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getIdentificacionUsuario().equals(identificacionUsuario) && prestamo.getTipoUsuario() == 3) {  //Comparamos la identificación del usuario del préstamo (prestamo.getIdentificacionUsuario()) con la identificación que esta en la solicitud
                throw new IllegalArgumentException("El usuario con identificación " + identificacionUsuario + " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");// lanzamos una exrcpcionS
            }
        }
    }

    public LocalDate calcularFechaDevolucion(int tipoUsuario, LocalDate fechaActual) { // utlizo casos para saber los dias habiles dependiendo del tipo de usuario
        int dias = 0;
        switch (tipoUsuario) {
            case 1:
                dias = 10;
                break;
            case 2:
                dias = 8;
                break;
            case 3:
                dias = 7;
                break;
        }
        return addDaysSkippingWeekends(fechaActual, dias);
    }

    private LocalDate addDaysSkippingWeekends(LocalDate date, int days) { // Calculamos la fecha de devolucion excluyendo los fines de semana
        LocalDate result = date;                                          // uso el addDaysSkippingWeekends(agregar dias saltando fines de semana) por convecion por los test
        int addedDays = 0; 
        while (addedDays < days) { // ciclo while para ejecutar el bucle mientras los dias que se vayan agregando sea menor que los dias habiles de prestamo
            result = result.plusDays(1); //se agrega un dia a la fecha en donde comienzan a contar los dias para la devolucion
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) { //verificamos que no sea sabado ni domingo
                ++addedDays; // agreganos/se suma un dia mas
            }
        }
        return result;
    }
}


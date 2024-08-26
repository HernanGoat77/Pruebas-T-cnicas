package com.ceiba.biblioteca;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ceiba.biblioteca.Entidades.Prestamo;
import com.ceiba.biblioteca.Servicios.PrestamoServicio;
import com.ceiba.biblioteca.LogicaNegocio.PrestamoLogica;

// creamos la clase controlador para exponer la API REST para el usuario/cliente
@RestController // con esto, convertimos la clase en un conjunto de endpoint que van a ser publicados para ser consumidos por cualquier cliente/usuario
@RequestMapping("/prestamo")// a partir de esta ruta podemos acceder a los endpoints
public class PrestamoControlador {

    @Autowired //inyeccion de depencia
    private PrestamoServicio prestamoServicio;

    @Autowired
    private PrestamoLogica prestamoLogica;

    @PostMapping // solicitud POST protocolo HTTP. Maneja la creación de un nuevo préstamo a través de una solicitud POST.
    public ResponseEntity<?> crearPrestamo(@RequestBody Prestamo prestamo){//La clase ResponseEntity en Spring MVC es una clase genérica que representa toda la respuesta HTTP
        try { // utlizamos un try-catch para capturar errores/exepciones
            prestamoLogica.validarTipoUsuario(prestamo.getTipoUsuario()); //Realizamos validaciones de tipo de usuario
            if (prestamo.getTipoUsuario() == 3) { // la utilizamos para encapsular tanto la respuesta exitosa como los posibles errores que pueden ocurrir durante el procesamiento de una solicitud HTTP
                prestamoLogica.validarUsuarioInvitado(prestamo.getIdentificacionUsuario());//verificamos si un usuario invitado ya tiene un libro prestado
            }
            LocalDate fechaDevolucion = prestamoLogica.calcularFechaDevolucion(prestamo.getTipoUsuario(), LocalDate.now());//Calcula la fecha máxima de devolución 
            prestamo.setFechaDevolucion(fechaDevolucion);
            Prestamo prestamoCreado = prestamoServicio.crearPrestamo(prestamo); //Creamos un nuevo objeto de tipo Prestamo y Llamamo al servicio de préstamos para crear un nuevo préstamo en la base de datos

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaMaximaDevolucion = fechaDevolucion.format(formatter);

            PrestamoRespuesta respuesta = new PrestamoRespuesta( 
                    prestamoCreado.getId(),
                    fechaMaximaDevolucion
            );
            return ResponseEntity.ok(respuesta);// respuesta exitosa: Retorna un objeto JSON
        } catch (IllegalArgumentException e) { //  HttpStatus.BAD_REQUEST la utilizamos para especificar que la respuesta HTTP debería ser 400 cuando ocurre un error de validación
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RespuestaError(e.getMessage()));//: Si ocurre un error de validación, retorna un código de estado HTTP 400 (Bad Request) junto con un objeto JSON RespuestaError que contiene el mensaje de error.
        }
    }

    @GetMapping
    public List<Prestamo> listaPrestamos(){
        return prestamoServicio.listarPrestamos();
    }

    @GetMapping("{id}") // solicitud GET protocolo HTTP, obtemos toda la informacion de un prestamo por el id
    public ResponseEntity<?> buscarPrestamoById(@PathVariable("id") Long id){ //Este método busca un préstamo específico por su identificador (id).
        Prestamo prestamo = prestamoServicio.buscarPrestamoPorId(id);
        if (prestamo != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaMaximaDevolucion = prestamo.getFechaDevolucion().format(formatter);
            PrestamoRespuestaCompleta respuesta = new PrestamoRespuestaCompleta( // respuesta en un objeto JSON
                    prestamo.getId(),
                    prestamo.getIsbn(),
                    prestamo.getIdentificacionUsuario(),
                    prestamo.getTipoUsuario(),
                    fechaMaximaDevolucion
            );
            return ResponseEntity.ok(respuesta);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RespuestaError("Préstamo no encontrado"));
        }
    }

    @DeleteMapping("{id}")
    public void borrarPrestamoById(@PathVariable("id") Long id){
        prestamoServicio.borrarPrestamo(id);
    }
}


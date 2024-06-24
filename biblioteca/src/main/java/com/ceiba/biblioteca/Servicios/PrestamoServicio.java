package com.ceiba.biblioteca.Servicios;


import com.ceiba.biblioteca.Entidades.Prestamo;
import com.ceiba.biblioteca.Repositorio.PrestamoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class PrestamoServicio {

    @Autowired //inyectando a PrestamoService la variable restamoRepo para poder utlizarla dentro de esta clase
    private PrestamoRepositorio prestamoRepo;

    //Creamos todos los metodos CRUD para la API REST

    public Prestamo crearPrestamo(Prestamo prestamo) { // este metodo recibe como parametro prestamo que es un objeto de tipo Prestamo, que es el objeto que se almacena en la DB
        return prestamoRepo.save(prestamo); // Devuelve el objeto Prestamo que ha sido guardado en la DB
    }

    public void borrarPrestamo(Long id) { //Este método elimina un préstamo de la base de datos según su identificador 
        prestamoRepo.deleteById(id);
    }

    public List<Prestamo> listarPrestamos() { // lsitamos todos los  préstamos almacenados en la DB
        return prestamoRepo.findAll(); // retotna una lista de objetos Prestamos
    }

    public Prestamo buscarPrestamoPorId(Long id) { // busca un préstamo en la base de datos por su identificador (id)
        return prestamoRepo.findById(id).orElse(null); // Devuelve el objeto Prestamo encontrado, o null si no existe un préstamo con ese id.
    }

    public Prestamo modificarPrestamo(Prestamo prestamo) {
        return prestamoRepo.save(prestamo);
    }

  
}

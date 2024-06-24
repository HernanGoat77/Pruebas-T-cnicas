package com.ceiba.biblioteca.Repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.biblioteca.Entidades.Prestamo;
/**
 * Interfaz de repositorio para la entidad Prestamo.
 * Extiende JpaRepository que permite métodos CRUD básicos sobre nuestra DB en memoria.
 */

@Repository // Aqui indicamos que esta interfaz es un componente de Spring para manejar acceso a datos, los datos en la entidad Prestamo
public interface PrestamoRepositorio extends JpaRepository<Prestamo, Long> {//Prestamo representa un objeto o en pocas palabras un préstamo en la base de datos
                                                                            // uso Long como identificador unico (id) en la entidad Prestamo por el cual se aplican los meetodos CRUD
}

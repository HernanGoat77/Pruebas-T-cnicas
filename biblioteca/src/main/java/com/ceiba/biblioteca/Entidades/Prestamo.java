package com.ceiba.biblioteca.Entidades;


import javax.persistence.*;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;



@Entity
public class Prestamo {


    @Id // indicamos que el atributo id es la clave primaria dentro de la entidad usuario
    @GeneratedValue(strategy = GenerationType.IDENTITY) // se genera de forma IDENTITY para ahorrarnos el trabajo de hacerlo manaul
    private Long id;
    
    @Column(length = 10, nullable = false, unique = true)// le indicamos el tamaño(varchar(x) en DB), que no sea nulo y que sea unico
    private String isbn;
    
    @Column(length = 10, nullable = false)
    private String identificacionUsuario;

    @Column // indicamos que el siguiente atributo es un colummna en nuestra tabla PRESTAMO de la DB
    private int tipoUsuario;


    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate fechaDevolucion;

    // Finalmente creamos los metodos Getters y Setters


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getIsbn() {
        return isbn;
    }


    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }


    public void setIdentificacionUsuario(String identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
    }


    public int getTipoUsuario() {
        return tipoUsuario;
    }


    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }


}

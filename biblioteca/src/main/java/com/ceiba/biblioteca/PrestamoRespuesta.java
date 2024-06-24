package com.ceiba.biblioteca;

public class PrestamoRespuesta {

    private Long id;
    private String fechaMaximaDevolucion;

    public PrestamoRespuesta(Long id, String fechaMaximaDevolucion) { // hacemos este constructor aqui para inicializar los atributos
        this.id = id;
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }

    // Metodos Getters y Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFechaMaximaDevolucion() {
        return fechaMaximaDevolucion;
    }

    public void setFechaMaximaDevolucion(String fechaMaximaDevolucion) {
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }
}

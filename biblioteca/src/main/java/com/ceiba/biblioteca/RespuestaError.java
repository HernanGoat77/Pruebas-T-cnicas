package com.ceiba.biblioteca;

// clase para capturar mensajes de error y devolverlos como respuesta cuando ocurre algún problema en tu aplicación.
public class RespuestaError {

    private String mensaje; //atributo

    public RespuestaError(String mensaje) { // contructor para inicializar el atributo
        this.mensaje = mensaje;
    }

    // Metodos Getters y Setters 

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

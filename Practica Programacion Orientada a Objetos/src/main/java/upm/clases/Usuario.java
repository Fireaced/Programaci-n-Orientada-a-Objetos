package upm.clases;

import upm.excepciones.InvalidAtrributeExcepcion;

import java.util.Objects;
public class Usuario {
    private static final  int EDAD_MINIMA = 14;
    private static final int EDAD_MAXIMA = 100;
    private static final int LONGITUD_MINIMA = 3;
    private String nombre;
    private String contrasenia;
    private int edad;
    private int numero;
    private int id;

    public static Builder builder(){
        return new Builder();
    }

    public String toString() {
        return "Usuario Creado: " + "nombre:" + nombre + "; edad:" + edad + "; móvil:" + numero + "; clave:" + contrasenia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContrasenia(String contrasenia) {
        if(contrasenia.length()<LONGITUD_MINIMA){
            throw new InvalidAtrributeExcepcion("La longitud es incorrecta, tiene que ser mayor que 3" + contrasenia);
        }
        this.contrasenia = contrasenia;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setEdad(int edad) {
        if(edad<EDAD_MINIMA || edad >EDAD_MAXIMA){
            throw new InvalidAtrributeExcepcion("Tienes que tener entre 14 y 100 años para poder registrarte");
        }
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getId() {
        return id;
    }

    public int getNumero() {
        return numero;
    }

    public static class Builder{
        private final Usuario usuario;
         public Builder(){
             this.usuario=new Usuario();
         }
        public Builder nombre(String nombre){
            this.usuario.setNombre(nombre);
            return this;
        }
        public Builder edad(int edad){
            this.usuario.setEdad(edad);
            return this;
        }
        public Builder numero(int numero){
            this.usuario.setNumero(numero);
            return this;
        }
        public Builder contrasenia(String contrasenia){
            this.usuario.setContrasenia(contrasenia);
            return this;
        }
        public Usuario build(){
            return this.usuario;
        }
    }
}
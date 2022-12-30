/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author vivic
 */
public class Gato {

    private static Integer contador = 0;
    private Integer ID;
    private String nombre;
    private String color;
    private String sexo;
    private String fechaNacimiento;
    private String raza;

    public Gato() {
        ID = contador++;
    }

    public Integer getID() {
        return ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    @Override
    public String toString() {
        return this.nombre + " - " + this.color
                + " - " + this.raza + " - " + this.sexo
                + " - " + this.fechaNacimiento;
    }

}

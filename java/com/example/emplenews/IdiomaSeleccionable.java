package com.example.emplenews;

public class IdiomaSeleccionable {
    private String pais;

    private int bandera;

    public IdiomaSeleccionable(String nombre, int icono)
    {
        super();
        this.pais = nombre;
        this.bandera = icono;
    }

    public String getNombre()
    {
        return pais;
    }

    public void setNombre(String nombre)
    {
        this.pais = nombre;
    }

    public int getIcono()
    {
        return bandera;
    }

    public void setIcono(int icono)
    {
        this.bandera = icono;
    }
}

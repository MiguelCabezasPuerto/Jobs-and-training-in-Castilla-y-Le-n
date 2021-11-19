package com.example.emplenews;

import java.io.Serializable;

/**
 * Clase de la oferta de empleo
 *
 * Contiene informacion de cada oferta de empleo
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class OfertasEmpleo implements Serializable {
    /**
     * Título de oferta
     */
    String titulo;
    /**
     * Provincia de la oferta
     */
    String provincia;
    /**
     * Fecha de publicacion de la oferta
     */
    String fecha_publicacion;
    /**
     * Descripcion de la oferta
     */
    String descripcion;
    /**
     * Fuente de la oferta
     */
    String fuente;
    /**
     * Municipio de la oferta
     */
    String ciudad;
    /**
     * Coordenadas de la oferta
     */
    String coordenadas;
    /**
     * Identificador unico de la oferta
     */
    String id;
    /**
     * Fecha de ultima actualizacion de la oferta
     */
    String actualizacion;
    /**
     * Enlace de la oferta a la oficina del portal de empleo
     */
    String enlace;
    /**
     * Id de la provincia de la oferta
     */
    String idProvincia;
    /**
     * Id del municipio de la oferta
     */
    String idLocalidad;
    /**
     * Latitud de la oferta
     */
    String latitud;
    /**
     * Longitud de la oferta
     */
    String longitud;


    /**
     * Constructor por defecto
     */
    public OfertasEmpleo(){

    }
    /**
     * Devuelve la latitud de la oferta de empleo
     * @return <ul>
     *          <li>La latitud de la oferta</li>
     *          </ul>
     */
    public String getLatitud() {
        return latitud;
    }
    /**
     * Inserta la latitud de la oferta
     */
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }
    /**
     * Devuelve la longitud de la oferta de empleo
     * @return <ul>
     *          <li>La longitud de la oferta</li>
     *          </ul>
     */
    public String getLongitud() {
        return longitud;
    }
    /**
     * Inserta la longitud de la oferta
     */
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    /**
     * Devuelve el título de la oferta de empleo
     * @return <ul>
     *          <li>El título de la oferta</li>
     *          </ul>
     */
    public String getTitulo() {
        return titulo;
    }
    /**
     * Inserta el título de la oferta
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    /**
     * Devuelve la provincia de la oferta de empleo
     * @return <ul>
     *          <li>La provincia de la oferta</li>
     *          </ul>
     */
    public String getProvincia() {
        return provincia;
    }
    /**
     * Inserta la provincia de la oferta
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    /**
     * Devuelve la fecha de publicacion de la oferta de empleo
     * @return <ul>
     *          <li>La fecha de publicacion de la oferta</li>
     *          </ul>
     */
    public String getFecha_publicacion() {
        return fecha_publicacion;
    }
    /**
     * Inserta la fecha de publicacion de la oferta
     */
    public void setFecha_publicacion(String fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }
    /**
     * Devuelve la descripcion de la oferta de empleo
     * @return <ul>
     *          <li>La descripcion de la oferta</li>
     *          </ul>
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Inserta la descripcion de publicacion de la oferta
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Devuelve la fuente de la oferta de empleo
     * @return <ul>
     *          <li>La fuente de la oferta</li>
     *          </ul>
     */
    public String getFuente() {
        return fuente;
    }
    /**
     * Inserta la fuente de publicacion de la oferta
     */
    public void setFuente(String fuente) {
        this.fuente = fuente;
    }
    /**
     * Devuelve el municipio de la oferta de empleo
     * @return <ul>
     *          <li>El municipio de la oferta</li>
     *          </ul>
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Inserta la fuente de publicacion de la oferta
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    /**
     * Devuelve las coordenadas de la oferta de empleo
     * @return <ul>
     *          <li>Las coordenadas de la oferta</li>
     *          </ul>
     */
    public String getCoordenadas() {
        return coordenadas;
    }
    /**
     * Inserta las coordenadas de publicacion de la oferta
     */
    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }


    /**
     * Devuelve el id de la oferta de empleo
     * @return <ul>
     *          <li>El id de la oferta</li>
     *          </ul>
     */
    public String getId() {
        return id;
    }
    /**
     * Inserta el id de publicacion de la oferta
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Devuelve la fecha de ultima actualizacion de la oferta de empleo
     * @return <ul>
     *          <li>La fecha de ultima actualizacion de la oferta</li>
     *          </ul>
     */
    public String getActualizacion() {
        return actualizacion;
    }
    /**
     * Inserta la fecha de ultima actualizacion de la oferta
     */
    public void setActualizacion(String actualizacion) {
        this.actualizacion = actualizacion;
    }

    /**
     * Devuelve el enlace de la oferta de empleo
     * @return <ul>
     *          <li>El enlace de la oferta</li>
     *          </ul>
     */
    public String getEnlace() {
        return enlace;
    }
    /**
     * Inserta el enlace de publicacion de la oferta
     */
    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    /**
     * Devuelve el id de provincia de la oferta de empleo
     * @return <ul>
     *          <li>El id de la oferta</li>
     *          </ul>
     */
    public String getIdProvincia() {
        return idProvincia;
    }
    /**
     * Inserta el id de provincia de publicacion de la oferta
     */
    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }
    /**
     * Devuelve el id de municipio de la oferta de empleo
     * @return <ul>
     *          <li>El id de la oferta</li>
     *          </ul>
     */
    public String getIdLocalidad() {
        return idLocalidad;
    }
    /**
     * Inserta el id de municipio de publicacion de la oferta
     */
    public void setIdLocalidad(String idLocalidad) {
        this.idLocalidad = idLocalidad;
    }
}

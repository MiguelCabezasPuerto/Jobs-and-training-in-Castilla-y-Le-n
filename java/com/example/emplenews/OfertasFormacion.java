package com.example.emplenews;

import java.io.Serializable;

/**
 * Clase de la oferta de formación
 *
 * Contiene informacion de cada oferta de formación
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */

public class OfertasFormacion implements Serializable {
    /**
     * Título de oferta
     */
    String titulo;
    /**
     * Organismo que promueve la acción formativa
     */
    String organismo;
    /**
     * Presencialidad o no requerida en el desarrollo de la formación
     */
    String tipo;
    /**
     * Municipio donde se llevará acabo la acción formativa
     */
    String ciudad;
    /**
     * Coordenadas marcadas para la oferta
     */
    String coordenadas;
    /**
     * Fechas de desarrollo de la acción formativa
     */
    String fechas;
    /**
     * Horas de duración
     */
    String duracion;
    /**
     * Descripción de la acción formativa
     */
    String descripcion;
    /**
     * Ámbito laboral
     */
    String materia;
    /**
     * Forma de inscripción
     */
    String inscripcion;
    /**
     * Empleados o desempleados
     */
    String destinatarios;
    /**
     * Dirección de la entidad organizadora
     */
    String lugar;
    /**
     * Nº de plazas disponibles
     */
    String plazas;
    /**
     * Otra información adicional de interés
     */
    String infoAdicional;
    /**
     * Última actualización de la oferta
     */
    String actualizacion;
    /**
     * Enlace a la web de la oficina de empleo
     */
    String enlace;
    /**
     * Id único de la oferta
     */
    String id;
    /**
     * Id único de la provincia donde se desarrolla la formación
     */
    String cod_provincia;
    /**
     * Latitud
     */
    String latitud;
    /**
     * Longitud
     */
    String longitud;


    /**
     * Constructor por defecto
     */
    public OfertasFormacion(){

    }

    /**
     * Devuelve el título de la oferta de formacion
     * @return <ul>
     *          <li>El título de la oferta/li>
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
     * Devuelve el organismo de la oferta de formacion
     * @return <ul>
     *          <li>El organismo de la oferta/li>
     *          </ul>
     */
    public String getOrganismo() {
        return organismo;
    }
    /**
     * Inserta el organismo de la oferta
     */
    public void setOrganismo(String organismo) {
        this.organismo = organismo;
    }

    /**
     * Devuelve el tipo de la oferta de formacion
     * @return <ul>
     *          <li>El tipo de la oferta/li>
     *          </ul>
     */
    public String getTipo() {
        return tipo;
    }
    /**
     * Inserta el tipo de la oferta
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    /**
     * Devuelve el municipio de la oferta de formacion
     * @return <ul>
     *          <li>El municipio de la oferta/li>
     *          </ul>
     */
    public String getCiudad() {
        return ciudad;
    }
    /**
     * Inserta el municipio de la oferta
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    /**
     * Devuelve las coordenadas de la oferta de formacion
     * @return <ul>
     *          <li>Las coordenadas de la oferta/li>
     *          </ul>
     */
    public String getCoordenadas() {
        return coordenadas;
    }
    /**
     * Inserta las coordenadas de la oferta
     */
    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }
    /**
     * Devuelve las fechas de la oferta de formacion
     * @return <ul>
     *          <li>Las fechas de la oferta/li>
     *          </ul>
     */
    public String getFechas() {
        return fechas;
    }
    /**
     * Inserta las fechas de la oferta
     */
    public void setFechas(String fechas) {
        this.fechas = fechas;
    }
    /**
     * Devuelve la duración de la oferta de formacion
     * @return <ul>
     *          <li>La duración de la oferta/li>
     *          </ul>
     */
    public String getDuracion() {
        return duracion;
    }
    /**
     * Inserta la duración de la oferta
     */
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    /**
     * Devuelve la descripción de la oferta de formacion
     * @return <ul>
     *          <li>La descripción de la oferta/li>
     *          </ul>
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Inserta la descripción de la oferta
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Devuelve la materia de la oferta de formacion
     * @return <ul>
     *          <li>La materia de la oferta/li>
     *          </ul>
     */
    public String getMateria() {
        return materia;
    }
    /**
     * Inserta la materia de la oferta
     */
    public void setMateria(String materia) {
        this.materia = materia;
    }
    /**
     * Devuelve la forma de inscripción de la oferta de formacion
     * @return <ul>
     *          <li>La forma de inscripción de la oferta/li>
     *          </ul>
     */
    public String getInscripcion() {
        return inscripcion;
    }
    /**
     * Inserta la forma de inscripción de la oferta
     */
    public void setInscripcion(String inscripcion) {
        this.inscripcion = inscripcion;
    }
    /**
     * Devuelve los destinatarios de la oferta de formacion
     * @return <ul>
     *          <li>Los destinatarios de la oferta/li>
     *          </ul>
     */
    public String getDestinatarios() {
        return destinatarios;
    }
    /**
     * Inserta los destinatarios de la oferta
     */
    public void setDestinatarios(String destinatarios) {
        this.destinatarios = destinatarios;
    }
    /**
     * Devuelve el lugar de la oferta de formacion
     * @return <ul>
     *          <li>El lugar de la oferta/li>
     *          </ul>
     */
    public String getLugar() {
        return lugar;
    }
    /**
     * Inserta el lugar de la oferta
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    /**
     * Devuelve las plazas de la oferta de formacion
     * @return <ul>
     *          <li>Las plazas de la oferta/li>
     *          </ul>
     */
    public String getPlazas() {
        return plazas;
    }
    /**
     * Inserta el nº de plazas de la oferta
     */
    public void setPlazas(String plazas) {
        this.plazas = plazas;
    }
    /**
     * Devuelve la información adicional de la oferta de formacion
     * @return <ul>
     *          <li>La información adicional de la oferta/li>
     *          </ul>
     */
    public String getInfoAdicional() {
        return infoAdicional;
    }
    /**
     * Inserta la información adicional de la oferta
     */
    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }

    /**
     * Devuelve la última actualización de la oferta de formacion
     * @return <ul>
     *          <li>La última actualización de la oferta/li>
     *          </ul>
     */
    public String getActualizacion() {
        return actualizacion;
    }
    /**
     * Inserta la fecha de última actualización de la oferta
     */
    public void setActualizacion(String actualizacion) {
        this.actualizacion = actualizacion;
    }
    /**
     * Devuelve el enlace de la oferta de formacion
     * @return <ul>
     *          <li>El enlace de la oferta/li>
     *          </ul>
     */
    public String getEnlace() {
        return enlace;
    }
    /**
     * Inserta el enlace de la oferta
     */
    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }
    /**
     * Devuelve el id de la oferta de formacion
     * @return <ul>
     *          <li>El id de la oferta/li>
     *          </ul>
     */
    public String getId() {
        return id;
    }
    /**
     * Inserta el id de la oferta
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Devuelve el código de provincia de la oferta de formacion
     * @return <ul>
     *          <li>El código de provincia de la oferta/li>
     *          </ul>
     */
    public String getCod_provincia() {
        return cod_provincia;
    }
    /**
     * Inserta el código de provincia de la oferta
     */
    public void setCod_provincia(String cod_provincia) {
        this.cod_provincia = cod_provincia;
    }
    /**
     * Devuelve la latitud de la oferta de formacion
     * @return <ul>
     *          <li>La latitud de la oferta/li>
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
     * Devuelve la longitud de la oferta de formacion
     * @return <ul>
     *          <li>La longitud de la oferta/li>
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
}

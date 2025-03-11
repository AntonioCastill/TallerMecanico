package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Revision {
    private static final float PRECIO_HORA= 30.0f;
    private static final float PRECIO_DIA= 10.0f;
    private static final float PRECIO_MATERIAL= 1.5f;
    static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;
    private Cliente cliente;
    private Vehiculo vehiculo;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){

        if (cliente == null) throw new NullPointerException("El cliente no puede ser nulo.");
        if (vehiculo == null) throw new NullPointerException("El vehículo no puede ser nulo.");
        if (fechaInicio == null) throw new NullPointerException("La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())) throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");

        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = null;
        this.horas = 0;
        this.precioMaterial = 0;

    }
    public Revision (Revision revision){
        if (revision == null) throw new NullPointerException("La revisión no puede ser nula.");
        this.cliente = new Cliente(revision.cliente);
        this.vehiculo = revision.vehiculo;
        this.fechaInicio = revision.fechaInicio;
        this.fechaFin = revision.fechaFin;
        this.horas = revision.horas;
        this.precioMaterial = revision.precioMaterial;


    }


    public Cliente getCliente(){
        return cliente;

    }
    private void setCliente(Cliente cliente){
        if (cliente == null) throw new NullPointerException("El cliente no puede ser nulo.");


    }
    public Vehiculo getVehiculo(){
        return vehiculo;

    }
    private void setVehiculo(Vehiculo vehiculo){
        if (vehiculo == null) throw new NullPointerException("El vehículo no puede ser nulo.");

    }
    public LocalDate getFechaInicio(){
        return fechaInicio;

    }
    private void setFechaInicio(LocalDate fechaInicio){
        if (fechaInicio == null) throw new NullPointerException("La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())) throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");

    }
    public LocalDate getFechaFin(){
        return fechaFin;
    }
    private void setFechaFin(LocalDate fechaFin){
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        if (fechaFin.isBefore(fechaInicio)){
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }

    }
    public int getHoras(){
        return horas;

    }
    public void anadirHoras(int horas){
        if (horas <= 0){
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        } else if (estaCerrada()) {
            throw new TallerMecanicoExcepcion("No se puede añadir horas, ya que la revisión está cerrada.");
        }
        this.horas += horas;
    }
    public float getPrecioMaterial(){
        return precioMaterial;
    }

    public void anadirPrecioMaterial(float precioMaterial){
        if (precioMaterial <= 0){
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        } else if (estaCerrada()) {
            throw new TallerMecanicoExcepcion("No se puede añadir precio del material, ya que la revisión está cerrada.");
        }
        this.precioMaterial += precioMaterial;
    }
    public boolean estaCerrada(){
        return fechaFin != null;
    }


    public void cerrar(LocalDate fechaFin){

        if (fechaFin == null) throw new NullPointerException("La fecha de fin no puede ser nula.");
        if (fechaFin.isAfter(LocalDate.now())) throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        if (fechaFin.isBefore(fechaInicio)) throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        if (estaCerrada()) throw new TallerMecanicoExcepcion("La revisión ya está cerrada.");

        this.fechaFin = fechaFin;
    }
    public float getPrecio(){

        long dias = fechaFin != null ? ChronoUnit.DAYS.between(fechaInicio, fechaFin) : 0;
        return (horas * PRECIO_HORA) + (dias * PRECIO_DIA) + (precioMaterial * PRECIO_MATERIAL);
    }
    private float getDias(){

        return ChronoUnit.DAYS.between(fechaInicio, fechaFin != null ? fechaFin : LocalDate.now());


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return cliente.equals(revision.cliente) && vehiculo.equals(revision.vehiculo) && fechaInicio.equals(revision.fechaInicio);
    }

    @Override
    public int hashCode() {
        return cliente.hashCode() + vehiculo.hashCode() + fechaInicio.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s - %s: (%s - %s), %d horas, %.2f € en material%s",
                cliente.toString(),
                vehiculo.toString(),
                fechaInicio.format(FORMATO_FECHA),
                fechaFin != null ? fechaFin.format(FORMATO_FECHA) : "",
                horas,
                precioMaterial,
                fechaFin != null ? String.format(", %.2f € total", getPrecio()) : "");
    }
}
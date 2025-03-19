package org.iesalandalus.programacion.tallermecanico.modelo.negocio;


import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Revisiones {
    private Set<Revision> coleccionRevisiones;

    public Revisiones() {
        this.coleccionRevisiones = new HashSet<>();
    }

    public List<Revision> get() {
        return new ArrayList<>(coleccionRevisiones);
    }

    public List<Revision> get(Cliente cliente) {
        return coleccionRevisiones.stream()
                .filter(r -> r.getCliente().equals(cliente))
                .collect(Collectors.toList());
    }

    public List<Revision> get(Vehiculo vehiculo) {
        return coleccionRevisiones.stream()
                .filter(r -> r.getVehiculo().equals(vehiculo))
                .collect(Collectors.toList());
    }

    public void insertar(Revision revision) {
        if (revision == null) {
            throw new NullPointerException("No se puede insertar una revisión nula.");
        }
        if (!comprobarRevision(revision.getCliente(), revision.getVehiculo(), revision.getFechaInicio())) {
            throw new TallerMecanicoExcepcion("El vehículo está actualmente en revisión.");
        }
        coleccionRevisiones.add(revision);
    }

    public boolean comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) {
        return coleccionRevisiones.stream().noneMatch(r ->
                r.getVehiculo().equals(vehiculo) &&
                        (r.getFechaFin() == null || r.getFechaFin().isAfter(fechaRevision))
        );
    }

    public Revision buscar(Revision revision) {
        if (revision == null) {
            throw new NullPointerException("No puedo operar sobre una revisión nula.");
        }
        return coleccionRevisiones.stream()
                .filter(r -> r.equals(revision))
                .findFirst()
                .orElse(null);
    }

    public void borrar(Revision revision) {
        if (revision == null) {
            throw new NullPointerException("No se puede borrar una revisión nula.");
        }
        if (!coleccionRevisiones.remove(revision)) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
    }

    public void anadirHoras(Revision revision, int horas) {
        Revision rev = buscar(revision);
        if (rev == null) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        rev.anadirHoras(horas);
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) {
        Revision rev = buscar(revision);
        if (rev == null) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        rev.anadirPrecioMaterial(precioMaterial);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) {
        Revision rev = buscar(revision);
        if (rev == null) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        rev.cerrar(fechaFin);
    }
}
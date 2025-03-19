package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public class Cliente {
    private static final String ER_NOMBRE = "([A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)([A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)*";
    private static final String ER_DNI = "\\d{8}[TRWAGMYFPDXBNJZSQVHLCKE]";
    private static final String ER_TELEFONO = "\\d{9}";

    private String nombre;
    private String dni;
    private String telefono;


    public Cliente(String nombre, String dni, String telefono) {
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    public Cliente(Cliente cliente1) {
        Objects.requireNonNull(cliente1, "No es posible copiar un cliente nulo.");
        this.nombre = cliente1.nombre;
        this.dni = cliente1.dni;
        this.telefono = cliente1.telefono;
    }

    public void setNombre(String nombre)  {
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo.");
        if (!nombre.matches(ER_NOMBRE)) {
            throw new IllegalArgumentException("El nombre no tiene un formato válido.");
        }
        this.nombre = nombre;
    }

    private void setDni(String dni) {
        Objects.requireNonNull(dni, "El DNI no puede ser nulo.");
        if (!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        }
        if (!comprobarLetraDNI(dni)) {
            throw new IllegalArgumentException("La letra del DNI no es correcta.");
        }
        this.dni = dni;
    }

    public void setTelefono(String telefono) {
        Objects.requireNonNull(telefono, "El teléfono no puede ser nulo.");
        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        }
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getTelefono() {
        return telefono;
    }

    private boolean comprobarLetraDNI(String dni) {
        String letrasDni = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numeroDni = Integer.parseInt(dni.substring(0, 8));
        char letraDni = letrasDni.charAt(numeroDni%23);
        return letraDni == dni.charAt(8);
    }

    public static Cliente get(String dni) {
        Objects.requireNonNull(dni, "El DNI no puede ser nulo.");
        return new Cliente("Nombre Valido", dni, "643386336");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", nombre, dni, telefono);
    }
}
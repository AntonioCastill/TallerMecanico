package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.HashMap;
import java.util.Map;

public enum Opcion {

    INSERTAR_CLIENTE(10, "Insertar cliente"),
    BUSCAR_CLIENTE(11,"Buscar cliente"),
    BORRAR_CLIENTE(12,"Borrar cliente"),
    LISTAR_CLIENTES(13,"Listar clientes"),
    MODIFICAR_CLIENTE(14,"Modificar cliente"),
    INSERTAR_VEHICULO(21,"Insertar vehiculo"),
    BUSCAR_VEHICULO(22,"Buscar vehiculo"),
    BORRAR_VEHICULO(23,"Borrar vehiculo"),
    LISTAR_VEHICULOS(24,"Listar vehículos"),
    INSERTAR_REVISION(31,"Insertar revisión"),
    BUSCAR_REVISION(32,"Buscar revisión"),
    BORRAR_REVISION(33,"Borrar revisión"),
    LISTAR_REVISIONES(34,"Listar revisiones"),
    LISTAR_REVISIONES_CLIENTE(35,"Listar revisiones cliente"),
    LISTAR_REVISIONES_VEHICULO(36,"Listar revisiones vehiculo"),
    ANADIR_HORAS_REVISION(37,"Anadir horas revisión"),
    ANADIR_PRECIO_MATERIAL_REVISION(38,"Anadir precio material"),
    CERRAR_REVISION(40,"Cerrar revisión"),
    SALIR(1, "Salir");


   private final int numeroOpcion;
   private final String mensaje;
   private static final Map<Integer, Opcion> opciones = new HashMap<>();


   static {

       for (Opcion opcion : values()){
           opciones.put(opcion.numeroOpcion, opcion);
       }

   }

    private Opcion(int numeroOpcion, String mensaje){
        this.numeroOpcion = numeroOpcion;
        this.mensaje = mensaje;
    }

    public static boolean esValida (int numeroOpcion) {
        return opciones.containsKey(numeroOpcion);
    }

    public static Opcion get (int numeroOpcion){
        if(!esValida(numeroOpcion)){
            throw new IllegalArgumentException("El número de la opción no es correcto.");
        }

        return opciones.get(numeroOpcion);
    }









    @Override
    public String toString() {
        return String.format("[numeroOpcion=%s, mensaje=%s]", numeroOpcion, mensaje);
    }
}

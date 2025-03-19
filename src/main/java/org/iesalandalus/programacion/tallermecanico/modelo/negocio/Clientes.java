package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


    public class Clientes {
        private HashSet<Cliente> listaClientes;


        public Clientes() {
            this.listaClientes = new HashSet<>();
        }


        public List<Cliente> get() {
            return new ArrayList<>(listaClientes);
        }


        public void insertar(Cliente cliente) {
            if (cliente == null) {
                throw new NullPointerException("No se puede insertar un cliente nulo.");
            }
            if (!listaClientes.add(cliente)) {
                throw new TallerMecanicoExcepcion("Ya existe un cliente con ese DNI.");
            }
        }


        public Cliente buscar(Cliente cliente) {
            if (cliente == null) {
                throw new NullPointerException("No se puede buscar un cliente nulo.");
            }
            return listaClientes.contains(cliente) ? cliente : null;
        }


        public void borrar(Cliente cliente) {
            if (cliente == null) {
                throw new NullPointerException("No se puede borrar un cliente nulo.");
            }
            if (!listaClientes.remove(cliente)) {
                throw new TallerMecanicoExcepcion("No existe ningún cliente con ese DNI.");
            }
        }


        public void modificar(Cliente cliente, String nombre, String telefono) {
            if (cliente == null) {
                throw new NullPointerException("No se puede modificar un cliente nulo.");
            }
            if (!listaClientes.contains(cliente)) {
                throw new TallerMecanicoExcepcion("No existe ningún cliente con ese DNI.");
            }
            if (nombre != null && !nombre.isEmpty()) {
                cliente.setNombre(nombre);
            }
            if (telefono != null && !telefono.isEmpty()) {
                cliente.setTelefono(telefono);
            }
        }
    }


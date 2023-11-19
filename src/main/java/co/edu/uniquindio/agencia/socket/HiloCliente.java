package co.edu.uniquindio.agencia.socket;

import co.edu.uniquindio.agencia.exceptions.AtributoVacioException;
import co.edu.uniquindio.agencia.modelo.*;
import javafx.event.Event;
import lombok.extern.java.Log;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

@Log
public class HiloCliente implements Runnable{
    private final Socket socket;
    private final AgenciaServidor agencia;


    public HiloCliente(Socket socket, AgenciaServidor agencia){
        this.socket = socket;
        this.agencia = agencia;
    }
    @Override
    public void run() {
        try {
            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream( socket.getOutputStream() );

            //Se lee el mensaje enviado por el cliente
            Mensaje mensaje = (Mensaje) in.readObject();

            //Se captura el tipo de mensaje
            String tipo = mensaje.getTipo();

            //Se captura el contenido del mensaje
            Object contenido = mensaje.getContenido();

            //Según el tipo de mensaje se invoca el método correspondiente
            switch (tipo) {
                case "agregarCliente":
                    agregarCliente((Cliente) contenido, out);
                    break;
                case "listarClientes":
                    listarClientes(out);
                    break;
                case "listarDestinos":
                    listarDestinos(out);
                    break;
                case "listarPaquetesTuristicos":
                    listarPaquetesTuristicos(out);
                    break;
                case "setDestino":
                    setDestino((Destino) contenido, out);
                    break;
                case "setPaqueteTuristico":
                    setPaqueteTuristico((PaqueteTuristico) contenido, out);
                    break;
                case "verificarDatos":
                    verificarDatos((Cliente) contenido, out);
                    break;
                case "agregarDestino":
                    agregarDestino((Destino) contenido, out);
                    break;
                case "obtenerDestino":
                    obtenerDestino((Destino) contenido, out);
                    break;
                case "actualizarDestino":
                    actualizarDestino((Destino) contenido, out);
                    break;
                case "eliminarDestino":
                    eliminarDestino((Destino) contenido, out);
                    break;
                case "agregarPaqueteTuristico":
                    agregarPaqueteTuristico((PaqueteTuristico) contenido, out);
                    break;
                case "actualizarPaqueteTuristico":
                    actualizarPaqueteTuristico((PaqueteTuristico) contenido, out);
                    break;
                case "eliminarPaqueteTuristico":
                    eliminarPaqueteTuristico((PaqueteTuristico) contenido, out);
                    break;
                case "obtenerPaquete":
                    obtenerPaquete((PaqueteTuristico) contenido, out);
                    break;
                case "agregarGuiaTuristico":
                    agregarGuiaTuristico((GuiaTuristico) contenido, out);
                    break;
                case "obtenerGuia":
                    obtenerGuia((GuiaTuristico) contenido, out);
                    break;
                case "eliminarGuia":
                    eliminarGuiaTuristico((GuiaTuristico) contenido, out);
                    break;
                case "actualizarGuiaTuristico":
                    actualizarGuiaTuristico((GuiaTuristico) contenido, out);
                    break;
                case "listarGuiasTuristicos":
                    listarGuiasTuristicos(out);
                    break;
                case "getCliente":
                    getCliente(out);
                    break;
                case "actualizarCliente":
                    actualizarCliente((Cliente) contenido, out);
                    break;
                case "agregarBusqueda":
                    agregarBusqueda((BusquedasDestinos) contenido, out);
                    break;
                case "listarPaquetesTuristicosPorDestinos":
                    listarPaquetesTuristicosPorDestinos(out);
                    break;
                case "getPaqueteTuristico":
                    getPaqueteTuristico(out);
                    break;
                case "obtenerGuiaNombre":
                    obtenerGuiaNombre((GuiaTuristico) contenido, out);
                    break;
                case "agregarReserva":
                    agregarReserva((Reserva) contenido, out);
                    break;
                case "listarReservas":
                    listarReservas(out);
                    break;
                case "cancelarReserva":
                    cancelarReserva((Reserva) contenido, out);
                    break;
                case "confirmarReserva":
                    confirmarReserva((Reserva) contenido, out);
                    break;
                case "setReserva":
                    setReserva((Reserva) contenido, out);
                    break;
                case "listarBusquedas":
                    listarBusquedas(out);
                    break;
                case "getReserva":
                    getReserva(out);
                    break;
                case "setReservas":
                    setReservas((ArrayList<Reserva>) contenido, out);
                    break;
                case "getDestino":
                    getDestino(out);
                    break;
            }
            //Se cierra la conexión del socket para liberar los recursos asociados
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void verificarDatos(Cliente cliente, ObjectOutputStream out) throws IOException {
        try {
            boolean estado = agencia.verificarDatos(cliente.getNombre(), cliente.getIdentificacion(), 0);
            out.writeObject(new Mensaje("Exito", estado));
        } catch (Exception e) {
            out.writeObject(new Mensaje("Error", e.getMessage()));
        }
    }


    public void agregarCliente(Cliente cliente, ObjectOutputStream out) throws IOException {
        try {
            agencia.agregarCliente(cliente.getIdentificacion(), cliente.getNombre(), cliente.getCorreo(), cliente.getTelefono(), cliente.getDireccion(), cliente.getBusquedasDestinos());
            out.writeObject("Cliente agregado correctamente");
        }catch (Exception e){
            out.writeObject(e.getMessage());
        }
    }

    public void actualizarCliente(Cliente cliente, ObjectOutputStream out) throws IOException {
        try {
            agencia.actualizarCliente(cliente.getIdentificacion(), cliente.getNombre(), cliente.getCorreo(), cliente.getTelefono(), cliente.getDireccion(), cliente.getBusquedasDestinos());
            out.writeObject("Cliente actualizado correctamente");
        }catch (Exception e){
            out.writeObject(e.getMessage());
        }
    }

    public void agregarDestino(Destino destino, ObjectOutputStream out) throws IOException {
        try {
            agencia.registrarDestino(destino.getNombre(), destino.getCiudad(), destino.getDescripcion(), destino.getImagenes().get(0), destino.getClima(), destino.getCalificaciones(), destino.getComentarios());
            out.writeObject("Destino agregado correctamente");
        }catch (Exception e){
            out.writeObject(e.getMessage());
        }
    }

    public void actualizarDestino(Destino destino, ObjectOutputStream out) throws IOException {
        try {
            agencia.actualizarDestino(destino.getNombre(), destino.getCiudad(), destino.getDescripcion(), destino.getImagenes().get(0), destino.getClima(), destino.getCalificaciones(), destino.getComentarios());
            out.writeObject("Destino actualizado correctamente");
        }catch (Exception e){
            out.writeObject(e.getMessage());
        }
    }

    public void eliminarDestino(Destino destino, ObjectOutputStream out) throws IOException {
        try {
            agencia.eliminarDestino(destino.getNombre());
            out.writeObject("Destino eliminado correctamente");
        }catch (Exception e){
            out.writeObject(e.getMessage());
        }
    }

    public void agregarPaqueteTuristico(PaqueteTuristico paqueteTuristico, ObjectOutputStream out) throws IOException {
        try {
            agencia.registrarPaquete(paqueteTuristico.getNombre(), paqueteTuristico.getDestinos(), paqueteTuristico.getDuracion(), paqueteTuristico.getServiciosAdicionales(), paqueteTuristico.getPrecio(), paqueteTuristico.getCupoMaximo(), paqueteTuristico.getFechaInicio(), paqueteTuristico.getFechaFin());
            out.writeObject("Paquete Turistico agregado correctamente");
        }catch (Exception e){
            out.writeObject(e.getMessage());
        }
    }

    public void actualizarPaqueteTuristico(PaqueteTuristico paqueteTuristico, ObjectOutputStream out) throws IOException {
        try {
            agencia.actualizarPaquete(paqueteTuristico.getNombre(), paqueteTuristico.getDestinos(), paqueteTuristico.getDuracion(), paqueteTuristico.getServiciosAdicionales(), paqueteTuristico.getPrecio(), paqueteTuristico.getCupoMaximo(), paqueteTuristico.getFechaInicio(), paqueteTuristico.getFechaFin());
            out.writeObject("Paquete Turistico actualizado correctamente");
        }catch (Exception e){
            out.writeObject(e.getMessage());
        }
    }

    public void eliminarPaqueteTuristico(PaqueteTuristico paqueteTuristico, ObjectOutputStream out) throws IOException {
        try {
            agencia.eliminarPaquete(paqueteTuristico.getNombre());
            out.writeObject("Paquete Turistico eliminado correctamente");
        }catch (Exception e){
            out.writeObject(e.getMessage());
        }
    }

    public void listarClientes(ObjectOutputStream out) throws IOException {
        out.writeObject(agencia.listarClientes());
    }

    public void listarDestinos(ObjectOutputStream out) throws IOException {
        out.writeObject(agencia.listarDestinos());
    }

    public void listarPaquetesTuristicos(ObjectOutputStream out) throws IOException {
        out.writeObject(agencia.listarPaquetesTuristicos());
    }

    public void listarPaquetesTuristicosPorDestinos(ObjectOutputStream out) throws IOException {
        out.writeObject(agencia.buscarPaquetesPorDestino());
    }

    public void listarGuiasTuristicos(ObjectOutputStream out) throws IOException {
        out.writeObject(agencia.listarGuiasTuristicos());
    }

    public void listarReservas(ObjectOutputStream out) throws IOException {
        out.writeObject(agencia.listarReservas());
    }

    public void listarBusquedas(ObjectOutputStream out) throws IOException {
        out.writeObject(agencia.listarBusquedas());
    }

    public void setDestino(Destino destino, ObjectOutputStream out) throws IOException{
        try{
            agencia.setDestino(destino);
        }catch (Exception e){
            out.writeObject(e.getMessage());
        }
    }
    public void setPaqueteTuristico(PaqueteTuristico paqueteTuristico, ObjectOutputStream out) throws IOException {
        try {
            synchronized (agencia) {
                agencia.setPaqueteTuristico(paqueteTuristico);
            }
        } catch (Exception e) {
            out.writeObject(e.getMessage());
        }
    }

    public void setReserva(Reserva reserva, ObjectOutputStream out) throws IOException{
        try{
            agencia.setReserva(reserva);
        }catch (Exception e){
            out.writeObject(e.getMessage());
        }
    }

    public void setReservas(ArrayList<Reserva> reservas, ObjectOutputStream out) throws IOException{
        try{
            agencia.setReservas(reservas);
        }catch (Exception e){
            out.writeObject(e.getMessage());
        }
    }

    public void getReserva(ObjectOutputStream out) throws IOException {
        out.writeObject(agencia.getReserva());
    }

    public void getDestino(ObjectOutputStream out) throws IOException {
        out.writeObject(agencia.getDestino());
    }

    public void getPaqueteTuristico(ObjectOutputStream out) throws IOException {
        synchronized (agencia) {
            out.writeObject(agencia.getPaqueteTuristico());
        }
    }

    public void obtenerDestino(Destino destino, ObjectOutputStream out) throws IOException {
        try {
            // Llamada al método obtenerDestino de AgenciaServidor
            Destino destinoEncontrado = agencia.obtenerDestino(destino.getNombre(), 0);

            // Si el destino se encontró, enviarlo al cliente
            if (destinoEncontrado != null) {
                out.writeObject(destinoEncontrado);
            } else {
                // Si el destino no se encontró, enviar un mensaje de error al cliente
                out.writeObject("Destino no encontrado");
            }
        } catch (Exception e) {
            // Si ocurre una excepción, enviar el mensaje de error al cliente
            out.writeObject(e.getMessage());
        }
    }

    public void obtenerPaquete(PaqueteTuristico paqueteTuristico, ObjectOutputStream out) throws IOException {
        try {
            // Llamada al método obtenerDestino de AgenciaServidor
            PaqueteTuristico paqueteTuristicoEncontrado = agencia.obtenerPaqueteNombre(paqueteTuristico.getNombre(), 0);

            // Si el destino se encontró, enviarlo al cliente
            if (paqueteTuristicoEncontrado != null) {
                out.writeObject(paqueteTuristicoEncontrado);
            } else {
                // Si el destino no se encontró, enviar un mensaje de error al cliente
                out.writeObject("Paquete Turistico no encontrado");
            }
        } catch (Exception e) {
            // Si ocurre una excepción, enviar el mensaje de error al cliente
            out.writeObject(e.getMessage());
        }
    }

    public void agregarGuiaTuristico(GuiaTuristico guiaTuristico, ObjectOutputStream out) throws IOException {
        try {
            agencia.registrarGuiaTuristico(guiaTuristico.getNombre(), guiaTuristico.getIdentificacion(), guiaTuristico.getIdiomas(), guiaTuristico.getExperiencia(), guiaTuristico.getCalificaciones(), guiaTuristico.getComentarios());
            out.writeObject("Guia Turistico agregado correctamente");
        }catch (Exception e){
            out.writeObject(e.getMessage());
        }
    }

    public void actualizarGuiaTuristico(GuiaTuristico guiaTuristico, ObjectOutputStream out) throws IOException {
        try {
            agencia.actualizarGuiaTuristico(guiaTuristico.getNombre(), guiaTuristico.getIdentificacion(), guiaTuristico.getIdiomas(), guiaTuristico.getExperiencia(), guiaTuristico.getCalificaciones(), guiaTuristico.getComentarios());
            out.writeObject("Guia Turistico actualizado correctamente");
        }catch (Exception e){
            out.writeObject(e.getMessage());
        }
    }

    public void obtenerGuia(GuiaTuristico guiaTuristico, ObjectOutputStream out) throws IOException {
        try {
            GuiaTuristico guiaTuristicoEncontrado = agencia.obtenerGuiaTuristico(guiaTuristico.getIdentificacion(), 0);

            if (guiaTuristicoEncontrado != null) {
                out.writeObject(guiaTuristicoEncontrado);
            } else {
                out.writeObject("Guia Turistico no encontrado");
            }
        } catch (Exception e) {
            // Si ocurre una excepción, enviar el mensaje de error al cliente
            out.writeObject(e.getMessage());
        }
    }

    public void obtenerGuiaNombre(GuiaTuristico guiaTuristico, ObjectOutputStream out) throws IOException {
        try {
            GuiaTuristico guiaTuristicoEncontrado = agencia.obtenerGuiaTuristicoNombre(guiaTuristico.getNombre(), 0);

            if (guiaTuristicoEncontrado != null) {
                out.writeObject(guiaTuristicoEncontrado);
            } else {
                out.writeObject("Guia Turistico no encontrado");
            }
        } catch (Exception e) {
            // Si ocurre una excepción, enviar el mensaje de error al cliente
            out.writeObject(e.getMessage());
        }
    }

    public void eliminarGuiaTuristico(GuiaTuristico guiaTuristico, ObjectOutputStream out) throws IOException {
        try {
            agencia.eliminarGuiaTuristico(guiaTuristico.getIdentificacion());
            out.writeObject("Guia Turistico eliminado correctamente");
        }catch (Exception e){
            out.writeObject(e.getMessage());
        }
    }

    public void getCliente(ObjectOutputStream out) throws IOException {
        out.writeObject(agencia.getClienteAutenticado());
    }

    public void agregarBusqueda(BusquedasDestinos busquedasDestinos, ObjectOutputStream out) throws IOException {
        try {
            agencia.registrarBusqueda(busquedasDestinos);
            out.writeObject("Busqueda agregado correctamente");
        }catch (Exception e){
            out.writeObject(e.getMessage());
        }
    }

    public void agregarReserva(Reserva reserva, ObjectOutputStream out) throws IOException {
        try {
            agencia.registrarReserva(reserva.getCliente(), reserva.getCantidadDePersonas(), reserva.getPaqueteTuristico(), reserva.getGuiaTuristico(), reserva.getEstado(), reserva.getFechaDeSolicitud(), reserva.getFechaDeViaje());
            out.writeObject("Reserva agregada correctamente");
        } catch (Exception e) {
            out.writeObject(e.getMessage());
        }
    }

    public void cancelarReserva(Reserva reserva, ObjectOutputStream out) throws IOException {
        try {
            agencia.cancelarReserva(reserva);
            out.writeObject("Reserva cancelada correctamente");
        } catch (Exception e) {
            out.writeObject(e.getMessage());
        }
    }


    public void confirmarReserva(Reserva reserva, ObjectOutputStream out) throws IOException {
        try {
            agencia.confirmarReserva(reserva);
            out.writeObject("Reserva confirmada correctamente");
        }catch (Exception e){
            out.writeObject(e.getMessage());
        }
    }

}

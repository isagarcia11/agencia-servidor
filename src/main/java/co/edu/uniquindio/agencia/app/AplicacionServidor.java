package co.edu.uniquindio.agencia.app;

import co.edu.uniquindio.agencia.modelo.AgenciaServidor;
import co.edu.uniquindio.agencia.socket.HiloCliente;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AplicacionServidor {

    public static void main(String[] args) {
        int puerto = 1234;
        int hilosMaximos = 10;

        //Se crea la instancia de la clase principal que contiene toda la lógica del proyecto
        AgenciaServidor agenciaServidor = new AgenciaServidor();

        //Se crea la instancia de ExecutorService con el máximo de hilos permitidos
        ExecutorService executorService = Executors.newFixedThreadPool(hilosMaximos);

        //Se crea el ServerSocket en el puerto 1234
        try(ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Esperando conexión...");
            while (true) {

                //Se obtiene la conexión del cliente
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado");

                //Se crea un hilo para la conexión del cliente y se agrega al executorService
                executorService.execute(new HiloCliente(clienteSocket, agenciaServidor));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
    }

}

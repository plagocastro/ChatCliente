package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private ServerSocket serverSocket;
    private int nCliente;

    public Servidor(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void encenderServer() throws IOException {
        try {
            if (nCliente <= 10) {
                while (true) {
                    Socket socket = serverSocket.accept();
                    System.out.println("Nuevo cliente connectado");
                    HiloCliente cliente = new HiloCliente(socket);

                    Thread thread = new Thread(cliente);
                    thread.start();
                    nCliente++;
                }
            }
            else {
                System.out.println("Sala de chat llena :(");
            }
        } catch (IOException e) {

        }
    }


    public void cerrarSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        Servidor servidor = new Servidor(serverSocket);
        servidor.encenderServer();
    }
}



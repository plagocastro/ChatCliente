package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

    private ServerSocket serverSocket;
    int nCliente=1;

    public Servidor(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void encenderServer() throws IOException {

        try {
                while (true) {
                    Socket socket = serverSocket.accept();
                    System.out.println("Nuevo cliente connectado");
                    System.out.println("Hay conectados "+ nCliente+" clientes");
                    HiloCliente cliente = new HiloCliente(socket);

                    Thread thread = new Thread(cliente);
                    thread.start();
                    nCliente++;
                    if(!socket.isConnected()){
                        nCliente--;
                    }
                }

        } catch (IOException e) {
            cerrarSocket();
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
        Scanner teclado = new Scanner(System.in);
        System.out.println("Que puerto quieres que ocupe el servidor: ");
        String Puerto = teclado.nextLine();
        System.out.println("Esperando conexión.......");
        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(Puerto));
        Servidor servidor = new Servidor(serverSocket);
        servidor.encenderServer();
    }
}



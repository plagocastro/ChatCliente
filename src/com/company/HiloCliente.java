package com.company;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class HiloCliente implements Runnable {//implementamos la clase runnable para poder usar los hilos

    public static ArrayList<HiloCliente> hiloClientes = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String Nick;


    public HiloCliente(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter((new OutputStreamWriter(socket.getOutputStream())));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.Nick = bufferedReader.readLine();
            hiloClientes.add(this);
            broadcastMessage("SERVER: " + Nick + " ha entrado en el chat :)");

        } catch (IOException e) {
            cerrarConexiones(socket, bufferedReader, bufferedWriter);
        }
    }

    private void cerrarConexiones(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        //este método cerrará todas las conexiones y métodos de lectura en caso de error
        cerrarCliente();
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String msn;

        while (socket.isConnected()) {
            try {
                msn = bufferedReader.readLine();
                broadcastMessage(msn);

            } catch (IOException e) {
                cerrarConexiones(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    public void broadcastMessage(String enviarMensaje) {//El broadcast lo usamos para distribuir los mensajes a los demas hilos
        for (HiloCliente hiloCliente : hiloClientes) {
            try {
                if (!hiloCliente.Nick.equals(Nick)) {//Compara los nicks de los usuarios y lo envía a todos los que tengan uno distinto
                    hiloCliente.bufferedWriter.write((enviarMensaje));
                    hiloCliente.bufferedWriter.newLine();
                    hiloCliente.bufferedWriter.flush(); //Flush es utilizado para enviar si o si los bytes guardados por el buffered
                }
            } catch (IOException e) {
                cerrarConexiones(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public void cerrarCliente() {
        //Lo quitamos del array y mostramos por pantalla el mensaje de salida
        hiloClientes.remove(this);
        broadcastMessage("[SERVER]: " + Nick + " ha dejado el chat :(");
    }
}

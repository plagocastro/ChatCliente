package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class hilos extends Thread {

    server s;
    DataOutputStream salida;
    DataInputStream entrada;
    String msn = "";

    public hilos(server ser, Socket socket, int num){
        s = ser;
        s.sCliente = socket;
        s.nCliente = num;
    }

    public void run() {
        try {
            if (s.nCliente <= 10) {

                Scanner teclado = new Scanner(System.in);
                entrada = new DataInputStream(s.sCliente.getInputStream());
                salida = new DataOutputStream(s.sCliente.getOutputStream());

                while (true && !msn.equals("Salida()")) {

                    s.mensajeRecibido = entrada.readUTF();//Leemos respuesta
                    salida.writeUTF(s.mensajeRecibido);
                    System.out.println(s.mensajeRecibido);
                }
            }
            else{
                System.out.println("Sala de chat llena");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    /*
                        if (nomHilo != "") {
                        pMsn = entrada.readUTF();
                        System.out.println("Cambio su puerto a " + pMsn + "!!\n->");
                        pMsn = String.valueOf(s.PUERTO2);
                        s.sServidor = new ServerSocket(s.PUERTO2);
                        s.sCliente = new Socket();
                        s.sCliente = s.sServidor.accept();

                    }
     */
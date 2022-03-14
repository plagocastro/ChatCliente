package com.company;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class server {

    static int PUERTO = 6666;
    ServerSocket sServidor;
    Socket sCliente;
    String mensajeRecibido;
    String Nick;
    int nCliente = 0;

    public void initServidor() {


        try {
            Scanner teclado = new Scanner(System.in);
            sServidor = new ServerSocket(PUERTO);
            sCliente = new Socket();
            System.out.println("Esperando conexión...");

            while (true) {
                sCliente = sServidor.accept();
                hilos h =  new hilos(this, sCliente, nCliente);
                new Thread(h).start(); //Thread(Runnable threadOb) dentro de los paréntesis tiene que ir el hilo para lanzar
                nCliente=nCliente + 1;
            }
        } catch (Exception e) {

        }
    }


    public static void main(String[] args) {
        server o = new server();
        o.initServidor();
    }
}


/*
   nMsn = entrada.readUTF();
            System.out.println("Se conecto " + nMsn + "...");
            Nick = nMsn;

            mensajeRecibido = entrada.readUTF();//Leemos respuesta
                System.out.println(mensajeRecibido);
                msn = teclado.nextLine();
                salida.writeUTF("[Servidor]: " + msn);//enviamos mensaje
 */
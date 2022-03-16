package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    String msn = "";
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String Nick;

    public Cliente(Socket socket, String Nick) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter((new OutputStreamWriter(socket.getOutputStream())));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.Nick = Nick;
        } catch (IOException e) {
            cerrarConexiones(socket, bufferedReader, bufferedWriter);
        }
    }

    public void enviarmensaje() {
        try {
            bufferedWriter.write((Nick));
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner teclado = new Scanner(System.in);
            if (!msn.equals("/sair")) {

                while (true) {
                    msn = teclado.nextLine();
                    bufferedWriter.write("[" + Nick + "]: " + msn);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }

            }
            else{
                socket.close();
                System.exit(0);
            }
        } catch (IOException e) {
            cerrarConexiones(socket, bufferedReader, bufferedWriter);
        }
    }

    public void escuchaMensaje() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msnDelResto;

                while (socket.isConnected()) {
                    try {
                        msnDelResto = bufferedReader.readLine();
                        System.out.println(msnDelResto);
                    } catch (IOException e) {
                        cerrarConexiones(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    private void cerrarConexiones(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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

    public static void main(String[] args) throws IOException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Dinos tu nick: ");
        String Nick = teclado.nextLine();
        System.out.println("Dinos a que host quieres conectarte: ");
        String ip = teclado.nextLine();
        System.out.println("Dinos el puerto al que quieres conectarte: ");
        String Puerto = teclado.nextLine();
        Socket socket = new Socket(ip, Integer.parseInt(Puerto));
        Cliente cliente = new Cliente(socket, Nick);
        cliente.escuchaMensaje();
        cliente.enviarmensaje();
    }

}

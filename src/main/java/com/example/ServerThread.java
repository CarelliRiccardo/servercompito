package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread{
    Socket s;
    ArrayList lista=new ArrayList();
    public ServerThread (Socket s){
        this.s=s;
    }
    public void run(){
        try {
            System.out.println("un client si è connesso");
    

        
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            String tRicevuto;
            do{
                tRicevuto = in.readLine();
                System.out.println("Il client ha inviato " + tRicevuto);

                if(tRicevuto.equals("@"))
                {
                    System.out.println("modifica stringa");
                    out.writeBytes("modifica stringa" + '\n');
                }
                else if(tRicevuto.equals("lista"))
                {
                    String l=lista.toString();
                    out.writeBytes(l+"\n");
                    
                }
               else
                {
                    System.out.println("aggiungi nota");
                    lista.add(tRicevuto);
                    out.writeBytes("nota aggiunta" + '\n');
                }
            }while(!tRicevuto.equals("u"));
            s.close();
                
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server !");
            System.exit(1);
        }
    }
}

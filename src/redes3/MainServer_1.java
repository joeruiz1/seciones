package redes3;


import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @see http://www.jc-mouse.net/
 * @author mouse
 */
public class MainServer_1 {

    /**
     * Puerto
     */
    private final static int PORT = 80;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            //Socket de servidor para esperar peticiones de la red
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor> Servidor iniciado");
            System.out.println("Servidor> En espera de cliente...");
            //Socket de cliente
            Socket clientSocket;
            while (true) {
                //en espera de conexion, si existe la acepta
                clientSocket = serverSocket.accept();
                //Para leer lo que envie el cliente
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //para imprimir datos de salida                
                PrintStream output = new PrintStream(clientSocket.getOutputStream());
                //se lee peticion del cliente
                String request = input.readLine();
                if (request.equals("GET /favicon.ico HTTP/1.1")) {

                } else {
                    System.out.println("Cliente> petición [" + request + "]");
                    String strOutput = process(request, clientSocket);
                    System.out.println("Servidor> Resultado de petición");
                    System.out.println("Servidor> \"" + strOutput + "\"");
                }

                //se procesa la peticion y se espera resultado
                //Se imprime en consola "servidor"
                //se imprime en cliente
                output.flush();//vacia contenido
//                output.println("<h1>hola</h1>");                
                //cierra conexion
                clientSocket.close();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * procesa peticion del cliente y retorna resultado
     *
     * @param request peticion del cliente
     * @return String
     */
    public static String process(String request, Socket clientSocket) throws IOException {
        PrintStream output = new PrintStream(clientSocket.getOutputStream());
        String result = "";
        String[] pagre = request.split("");
        String pags = "";
        for (int i = 5; i < pagre.length; i++) {
            if (pagre[i].equals(" ")) {
                break;
            } else {
                pags = pags + pagre[i];

            }

        }
        if (pags.equals("index")) {
            File file = new File("C:\\Users\\fernando stiven\\Desktop\\www\\index.html");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            String pagina = "";
            while ((linea = br.readLine()) != null) {
                pagina = pagina + linea;
            }

            output.print(pagina);
            fr.close();
            pags = "pagina cargada";
//        } else {if (pags.equals("david")) {
//                 File file = new File("C:\\Users\\Labing\\Desktop\\www\\david.html");
//            FileReader fr = new FileReader(file);
//            BufferedReader br = new BufferedReader(fr);
//
//            String linea;
//            String pagina = "";
//            while ((linea = br.readLine()) != null) {
//                pagina = pagina + linea;
//            }
//
//            output.print(pagina);
//            fr.close();
//            pags = "pagina cargada";
//            }else{
//            if (pags.equals("meji")) {
//                 File file = new File("C:\\Users\\Labing\\Desktop\\www\\meji.html");
//            FileReader fr = new FileReader(file);
//            BufferedReader br = new BufferedReader(fr);
//
//            String linea;
//            String pagina = "";
//            while ((linea = br.readLine()) != null) {
//                pagina = pagina + linea;
//            }
//
//            output.print(pagina);
//            fr.close();
//            pags = "pagina cargada";
//            }else{
//            
//                if (pags.equals("carlos")) {
//                     File file = new File("C:\\Users\\Labing\\Desktop\\www\\carlos.html");
//            FileReader fr = new FileReader(file);
//            BufferedReader br = new BufferedReader(fr);
//
//            String linea;
//            String pagina = "";
//            while ((linea = br.readLine()) != null) {
//                pagina = pagina + linea;
//            }

            output.print(pagina);
            fr.close();
            pags = "pagina cargada";
                }else{
File file = new File("C:\\Users\\Labing\\Desktop\\www\\error.html");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            String pagina = "";
            while ((linea = br.readLine()) != null) {
                pagina = pagina + linea;
            }

            output.print(pagina);
            fr.close();
            pags = "pagina de error cargada";
        }
        
 
            
        

        return pags;
    

}
}

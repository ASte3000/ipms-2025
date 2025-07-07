package com.iprody;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server started at http://localhost:8080");

        while (true) {
            Socket socket = serverSocket.accept();
            String systemTime = new Date().toString();
            System.out.println("New client connected: " + systemTime);

            var reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            var writer = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream()));


            while (!reader.ready());

            while (reader.ready()) {
                System.out.println(reader.readLine());
            }

            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: text/html; charset=UTF-8");
            writer.println();
            writer.println("<h1>Hello from there</h1>");
            writer.printf("<p>It worx: %s</p>", systemTime);
            writer.flush();

            socket.close();
        }

    }
}

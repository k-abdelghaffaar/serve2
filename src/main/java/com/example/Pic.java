package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Pic extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("image/gif");
        String imagePath = getServletContext().getRealPath("/minions.gif");

        OutputStream out2;
        try (FileInputStream fileInputStream = new FileInputStream(imagePath)) {
            out2 = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                out2.write(buffer, 0, bytesRead);
            }
        }
        out2.close();

        // if (file.exists()) {
        //     response.setContentType("application/vnd.ms-excel");
        //     response.setHeader("Content-Disposition", "attachment; filename=\"sheet.xlsx\"");
        //     response.setContentLengthLong(file.length());
            
        //     try {
        //         FileInputStream in = new FileInputStream(file);
        //         OutputStream out = response.getOutputStream();
        //         byte[] buffer = new byte[1024];
        //         int bytesRead;
        //         while ((bytesRead = in.read(buffer)) != -1) {
        //             out.write(buffer, 0, bytesRead);
        //         }
        //     }
        //     catch(IOException e){
        //         response.getWriter().println("file error occured!");
        //     }
        // } else {
        //     response.getWriter().println("<br>file not found!");
        // } 
    }
   
}
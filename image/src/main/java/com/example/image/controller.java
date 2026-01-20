package com.example.image;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import java.io.File;

@WebServlet("/submit")
@MultipartConfig
public class controller extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("img");
        String fileName = filePart.getSubmittedFileName();
        String uploadPath = getServletContext().getRealPath("") + File.separator + fileName;
        for (Part part : req.getParts()) {
            part.write(uploadPath);
        }
        req.setAttribute("image", fileName);
        req.getRequestDispatcher("result.jsp").forward(req, resp);
        // resp.getWriter().print("The file uploaded sucessfully");
    }
}

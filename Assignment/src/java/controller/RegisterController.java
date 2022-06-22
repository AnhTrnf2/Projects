/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDBContext;
import dal.UserDBContext;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Tuan Anh Tran
 */
public class RegisterController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HashMap<String, String> country_codes = new HashMap<>();
            JSONParser parser = new JSONParser();
            String files_path = getServletContext().getInitParameter("files_path");
            FileReader reader = new FileReader(files_path + "admin\\CountryCodes.json");
            JSONArray array = (JSONArray) parser.parse(reader);
            
            for(Object o : array) {
                JSONObject country = (JSONObject) o;
                String name = (String) country.get("name");
                String dial_code = (String) country.get("dial_code");
                country_codes.put(name, dial_code);
            }
            TreeMap<String, String> sorted = new TreeMap<>();
            sorted.putAll(country_codes);
            reader.close();
            request.setAttribute("country_codes", sorted);
            request.getRequestDispatcher("view/register.jsp").forward(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String full_name = first_name + last_name;
        String country_code = request.getParameter("country_code");
        String phone_number = request.getParameter("phone_number");
        String password = request.getParameter("password");
        String images_path = getServletContext().getInitParameter("images_path");
        String files_path = getServletContext().getInitParameter("files_path");
        String default_images_path = images_path + "admin\\default.png";
        String actual_phone_number = country_code.replace("+", "") + phone_number;
        AccountDBContext adb = new AccountDBContext();
        UserDBContext udb = new UserDBContext();
        adb.addAccount(actual_phone_number, password, full_name, default_images_path);
        User user = udb.getUser(phone_number);
        String images_directory = images_path + "\\user\\" + user.getUserID();
        String files_directory = files_path + "\\user\\" + user.getUserID();
        File images_dir = new File(images_directory);
        File files_dir = new File(files_directory);
        images_dir.mkdir();
        files_dir.mkdir();
        
        response.sendRedirect("login");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

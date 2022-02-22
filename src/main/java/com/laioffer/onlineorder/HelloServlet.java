package com.laioffer.onlineorder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.onlineorder.entity.Customer;
import org.json.JSONObject;
import org.apache.commons.io.IOUtils;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")  // must write in this form
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        // Hello
        /*
        PrintWriter out = response.getWriter();
        String customer = request.getParameter("customer");
        out.println("<html><body>");
        out.println("<h1>Hello: " + customer + "</h1>");
        out.println("</body></html>");

         */
        JSONObject customer = new JSONObject();
        customer.put("email", "sun@laioffer.com");
        customer.put("first_name", "rick");
        customer.put("last_name", "sun");
        customer.put("age", 50);
        response.getWriter().print(customer);


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException {
        // Read customer information from request body

        /*
        JSONObject jsonRequest = new JSONObject(IOUtils.toString(request.getReader()));

        String email = jsonRequest.getString("email");
        String firstName = jsonRequest.getString("first_name");
        String lastName = jsonRequest.getString("last_name");
        int age = jsonRequest.getInt("age");
        // Print customer information to IDE console
        System.out.println("Email is: " + email);
        System.out.println("First name is: " + firstName);
        System.out.println("Last name is: " + lastName);
        System.out.println("Age is: " + age);

         */
        //Use object mapper form Jackson package to convert a json object
        ObjectMapper objectMapper = new ObjectMapper();
        Customer customer = objectMapper.readValue(IOUtils.toString(request.getReader()),Customer.class);
        System.out.println(customer.getEmail());
        System.out.println(customer.getFirstName());
        System.out.println(customer.getLastName());

        // Return status = ok as response body to the client
        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "ok");
        response.getWriter().print(jsonResponse);
    }

    public void destroy() {
    }
}
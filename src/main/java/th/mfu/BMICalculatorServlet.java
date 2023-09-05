package th.mfu;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calbmi") // Add webservlet annotation to specify the URL mapping
public class BMICalculatorServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get parameters from the request: "weight" and "height"
        double weight = Double.parseDouble(request.getParameter("weight"));
        double height = Double.parseDouble(request.getParameter("height"));

        // Calculate BMI
        double bmi = calculateBMI(weight, height);

        // Determine the build from BMI
        String build = determineBuild(bmi);

        // Add BMI and build to the request's attribute
        request.setAttribute("bmi", bmi);
        request.setAttribute("build", build);

        // Forward to a JSP for displaying the results
        request.getRequestDispatcher("/bmiResult.jsp").forward(request, response);
    }

    private double calculateBMI(double weight, double height) {
        // Calculate BMI using the formula: BMI = weight (kg) / (height (m) * height (m))
        return weight / (height * height);
    }

    private String determineBuild(double bmi) {
        // Determine build based on BMI
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 24.9) {
            return "Normal";
        } else if (bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}


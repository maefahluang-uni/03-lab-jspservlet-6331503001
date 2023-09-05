import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calbmi")
public class BMICalculatorServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Step 2: Get parameters from the request: "weight" and "height"
        String weightParam = request.getParameter("weight");
        String heightParam = request.getParameter("height");

        try {
            // Step 3: Calculate BMI
            double weight = Double.parseDouble(weightParam);
            double height = Double.parseDouble(heightParam);
            double bmi = Math.round(weight / (height * height));

            // Step 4: Determine the build from BMI
            String buildType;
            if (bmi < 18.5) {
                buildType = "Underweight";
            } else if (bmi < 25) {
                buildType = "Normal";
            } else if (bmi < 30) {
                buildType = "Overweight";
            } else if (bmi < 35) {
                buildType = "Obese";
            } else {
                buildType = "Extremely Obese";
            }

            // Step 5: Add BMI and build to the request's attributes
            request.setAttribute("bmi", bmi);
            request.setAttribute("buildType", buildType);

            // Step 6: Forward to the JSP for display
            request.getRequestDispatcher("/bmi_result.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Handle invalid input (non-numeric weight or height)
            request.setAttribute("error", "Invalid input. Please enter numeric values for weight and height.");
            request.getRequestDispatcher("/index.html").forward(request, response);
        }
    }
}


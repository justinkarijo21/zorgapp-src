import java.time.format.DateTimeFormatter;

public class BmiGrafiek {
    
    public static void printBmiGraph(Patient patient) {
        if (patient.bmiHistory.isEmpty()) {
            System.out.println("No BMI history available for this patient.");
            return;
        }
        
        System.out.println("\n=== BMI History Graph for " + patient.firstName + " " + patient.surname + " ===");
        
        // Find min and max BMI for scaling
        double minBmi = Double.MAX_VALUE;
        double maxBmi = Double.MIN_VALUE;
        
        for (Patient.BmiEntry entry : patient.bmiHistory) {
            if (entry.bmi < minBmi) minBmi = entry.bmi;
            if (entry.bmi > maxBmi) maxBmi = entry.bmi;
        }
        
        // Add some padding
        minBmi = Math.max(0, minBmi - 2);
        maxBmi = maxBmi + 2;
        
        // Graph dimensions
        int graphHeight = 20;
        
        // Create the graph
        for (int row = graphHeight; row >= 0; row--) {
            double currentBmi = minBmi + (maxBmi - minBmi) * row / graphHeight;
            
            // Print BMI value on the left
            if (row % 4 == 0) {
                System.out.printf("%5.1f |", currentBmi);
            } else {
                System.out.print("      |");
            }
            
            // Print the graph points
            for (Patient.BmiEntry entry : patient.bmiHistory) {
                double normalizedBmi = (entry.bmi - minBmi) / (maxBmi - minBmi);
                int graphRow = (int) (normalizedBmi * graphHeight);
                
                if (graphRow == row) {
                    System.out.print(" * ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
        
        // Print bottom axis
        System.out.print("      +");
        for (Patient.BmiEntry entry : patient.bmiHistory) {
            System.out.print("---");
        }
        System.out.println();
        
        // Print dates
        System.out.print("        ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        for (Patient.BmiEntry entry : patient.bmiHistory) {
            System.out.printf("%-3s", entry.date.format(formatter));
        }
        System.out.println("\n");
        
        // Print summary
        System.out.println("BMI Categories:");
        System.out.println("Underweight: < 18.5");
        System.out.println("Normal: 18.5 - 24.9");
        System.out.println("Overweight: 25.0 - 29.9");
        System.out.println("Obese: >= 30.0");
        System.out.println();
    }
}

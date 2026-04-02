import java.time.format.DateTimeFormatter;

public class BmiGrafiek {
    
    public static void printBmiGraph(Patient patient) {
        if (patient.weightHistory.isEmpty()) {
            System.out.println("\nNo weight changes recorded yet for this patient.");
            System.out.println("Edit patient weight/length to see BMI graph.");
            return;
        }
        
        System.out.println("\n=== BMI Graph for " + patient.firstName + " " + patient.surname + " ===");
        
        double minBmi = Double.MAX_VALUE;
        double maxBmi = Double.MIN_VALUE;
        
        for (Patient.WeightEntry entry : patient.weightHistory) {
            double bmi = entry.calculateBmi();
            if (bmi < minBmi) minBmi = bmi;
            if (bmi > maxBmi) maxBmi = bmi;
        }
        
        minBmi = Math.max(0, minBmi - 2);
        maxBmi = maxBmi + 2;
        

        int graphHeight = 20;
        
     
        for (int row = graphHeight; row >= 0; row--) {
            double currentBmi = minBmi + (maxBmi - minBmi) * row / graphHeight;
            
            // Print BMI value on the left
            if (row % 4 == 0) {
                System.out.printf("%5.1f |", currentBmi);
            } else {
                System.out.print("      |");
            }
            
         
            for (Patient.WeightEntry entry : patient.weightHistory) {
                double bmi = entry.calculateBmi();
                double normalizedBmi = (bmi - minBmi) / (maxBmi - minBmi);
                int graphRow = (int) (normalizedBmi * graphHeight);
                
                if (graphRow == row) {
                    System.out.print(" * ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
        
       
        System.out.print("      +");
        for (Patient.WeightEntry entry : patient.weightHistory) {
            System.out.print("---");
        }
        System.out.println();
        
        //Print datum 
        
        System.out.print("        ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        for (Patient.WeightEntry entry : patient.weightHistory) {
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

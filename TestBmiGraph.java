import java.time.LocalDate;

public class TestBmiGraph {
    public static void main(String[] args) {
        // Create a test patient with BMI history
        Patient testPatient = new Patient(9999, "Test", "Patient", LocalDate.of(1990, 1, 1), 70.0, 1.75);

        // Add some BMI entries
        testPatient.addBmiEntry(LocalDate.of(2023, 1, 1), 22.5);
        testPatient.addBmiEntry(LocalDate.of(2023, 4, 1), 23.1);
        testPatient.addBmiEntry(LocalDate.of(2023, 7, 1), 24.2);
        testPatient.addBmiEntry(LocalDate.of(2023, 10, 1), 23.8);
        testPatient.addBmiEntry(LocalDate.of(2024, 1, 1), 24.5);

        // Print the BMI graph
        BmiGrafiek.printBmiGraph(testPatient);
    }
}
package p3collandstreamsassignment3;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.IntConsumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class TextAreaPaneController implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    private Button buttonShow;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public interface MyFunctionalInterface {

        public void saySomething(int value);
    }

    public interface MyFunctionalInterface2 {

        public String toUpperCase(String value);
    }

    public interface MyFunctionalInterface3 {

        public String welcomeToLambdas();
    }

    public interface MyFunctionalInterface4 {

        public double toCube(int value);
    }

    @FXML
    private void buttonShowHandle(ActionEvent event) {
        // First Question :
        // a) :
//        new IntConsumer() { // anonymous inner class
//            @Override
//            public void accept(int value) {
//                System.out.printf("%d ", value);
//            }
//        };
        MyFunctionalInterface print = value -> System.out.printf("%d ", value); // %d numerical value >> %s string
        print.saySomething(1220);
        // b): (String s) -> {return s.toUpperCase();} >> String::toUpperCase bu using method refrence opertaor
        MyFunctionalInterface2 upperCase = String::toUpperCase; // %d numerical value >> %s string by using member reference
        String str = "It's Easy To make Things Hard but it's so hard to make things easy";
        textArea.setText("Question 1-b : \n" + upperCase.toUpperCase(str) + "\n");
        // c) :
        MyFunctionalInterface3 welcome = () -> "Welcome to lambdas!"; // %d numerical value >> %s string by using member reference
        textArea.appendText("Question 1-c : \n" + welcome.welcomeToLambdas() + "\n");
        // d) :
        MyFunctionalInterface4 cube = value -> (Math.pow(value, 3)); // %d numerical value >> %s string by using member reference
        // MyFunctionalInterface4 cube = (int value) -> (value*value*value); // %d numerical value >> %s string by using member reference
        textArea.appendText("Question 1-d >> The Cube of 5 : " + cube.toCube(5));
        // Second Question : 
        textArea.appendText("\nQuestion 2 : Charcaters and Counts : \n");
        Pattern p = Pattern.compile("\\d*"); // zero or more character
        try {
            Files
                    .lines(Paths.get("text.txt"))
                    .flatMap(character -> p.splitAsStream(character))
                    .collect(Collectors.groupingBy(String::toUpperCase, Collectors.counting()))
                    .forEach((character, count) -> textArea.appendText(character + ": " + count + "\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Third Question :
        textArea.appendText("Departments and Counts : \n");
        Employee[] employees = {
            new Employee(1, "Ahmad", "IT", 1201.0), // nope (it's > 1200 )
            new Employee(2, "Sami", "Sales", 950.8), // salary >= 800 && < 1200
            new Employee(5, "Huda", "IT", 1010.5), // salary >= 800 && < 1200
            new Employee(7, "Ali", "Marketing", 1300.0), // nope (it's > 1200)
            new Employee(4, "Hani", "Sales", 1050.0) // salary >= 800 && < 1200
        };
        List<Employee> listEmployees = Arrays.asList(employees);
        listEmployees.stream()
                .filter(e -> (e.getSalary() >= 800 && e.getSalary() < 1200))// filter employees depends on the salary
                .map(e -> new Employee(e.getId(), e.getName(), e.getDepartment(), e.getSalary() * 1.02))// turn every employee object into new employee, where the salary is multiplied by 1,02
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting())) // collect all the employees, group them by their department, and return count of employee in each department by using method reference opertaor
                .forEach((dept, count) -> textArea.appendText(dept + ", Count : " + count + "\n"));// print the department and the count
    }
}

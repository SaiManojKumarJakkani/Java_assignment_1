import java.io.*;
import java.util.*;

public class Tester {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter input file location: ");
        String inputFile = scanner.nextLine();


        System.out.println("Enter output file location: ");
        String outputFile = scanner.nextLine();
        CSV csv_file = new CSV(inputFile);

        while (true) {
            String write_file = null;
            System.out.println("Enter operation type (sort/convert/add/edit/delete/quit):");
            String operation = scanner.nextLine();
            try {

                if ("sort".equalsIgnoreCase(operation)) {
                    Collections.sort(csv_file.data, (a, b) -> a[0].compareTo(b[0]));

                    csv_file.writeCSV(outputFile);
                    csv_file.displayFileContents();
                    csv_file = new CSV(inputFile);
                    continue;

                } else if ("convert".equalsIgnoreCase(operation)) {
                    csv_file.convertToFahrenheit();
                    write_file = outputFile;
                    csv_file.writeCSV(write_file);
                    csv_file.displayFileContents();
                    csv_file = new CSV(inputFile);
                    continue;

                } else if ("add".equalsIgnoreCase(operation)) {
                    System.out.print("Enter Country Name: ");
                    String countryName = scanner.nextLine();

                    System.out.print("Enter Temperature in Celsius: ");
                    String temperature = scanner.nextLine();
                    write_file = inputFile;

                } else if ("edit".equalsIgnoreCase(operation)) {
                    csv_file.displayFileContents();
                    System.out.print("Enter the row number to edit: ");
                    int index = Integer.parseInt(scanner.nextLine())-1;
                    System.out.print("Which one would you like to edit:\n 1:country \n 2:temperature?: \n");
                    String option = scanner.nextLine();
                    System.out.print("Enter enter corresponding value: ");
                    String value = scanner.nextLine();

                    csv_file.editRecord(index,option,value );
                    write_file = inputFile;
                } else if ("delete".equalsIgnoreCase(operation)) {
                    csv_file.displayFileContents();
                    System.out.print("Enter the row number to Delete: ");
                    String index = scanner.nextLine();
                    csv_file.deleteRecord(index);
                    write_file = inputFile;

                } else if ("quit".equalsIgnoreCase(operation)) {
                    break;
                } else {
                    System.out.println("Invalid operation type. Please select one of these operation types (sort/convert/add/edit/delete/quit) ");
                    continue;
                }
                csv_file.writeCSV(write_file);
                csv_file.displayFileContents();
            }
            catch (Exception e) {
                System.out.println(e);
                System.exit(1);

            }
        }

    }

}
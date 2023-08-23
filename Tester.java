import java.io.*;
import java.util.*;

public class Tester {
    public static void main(String[] args) throws Exception{
        String inputFile = "/Users/cb-it-01-1958/Desktop/Java@Chargebee/asg1_sample.csv";
        String outputFile = "/Users/cb-it-01-1958/Desktop/Java@Chargebee/asg1_sample_verify";
        CSV csv_file = new CSV(inputFile);
        String arr[]=new String[6];
        arr[0]="sort";
        arr[1]="convert";
        arr[2]="add";
        arr[3]="edit";
        arr[4]="delete";
        arr[5]="quit" ;
        for(int i=0;i<arr.length ;i++){
            String write_file = null;
            String operation = arr[i];
            if ("sort".equalsIgnoreCase(operation)) {
                Collections.sort(csv_file.data, (a, b) -> a[0].compareTo(b[0]));
                csv_file.writeCSV(outputFile+"_"+arr[i]+".csv");
                csv_file.displayFileContents();
                csv_file = new CSV(inputFile);
                continue;

            } else if ("convert".equalsIgnoreCase(operation)) {
                csv_file.convertToFahrenheit();
                write_file = outputFile+"_"+arr[i]+".csv";
                csv_file.writeCSV(write_file);
                csv_file.displayFileContents();
                csv_file = new CSV(inputFile);
                continue;

            } else if ("add".equalsIgnoreCase(operation)) {
                String countryName = "Russia";
                String temperature = "23";
                csv_file.addRecord(countryName,temperature );
                write_file = outputFile+"_"+arr[i]+".csv";

            } else if ("edit".equalsIgnoreCase(operation)) {
                csv_file.displayFileContents();
                int option=1;
                int index=1;
                String value = "China";
                csv_file.editRecord(index,option,value );
                write_file = outputFile+"_"+arr[i]+".csv";
            } else if ("delete".equalsIgnoreCase(operation)) {
                int index=2;
                csv_file.deleteRecord(index);
                write_file = outputFile+"_"+arr[i]+".csv";

            } else if ("quit".equalsIgnoreCase(operation)) {
                break;
            } else {
                System.out.println("Invalid operation type. Please select one of these operation types (sort/convert/add/edit/delete/quit) ");
                continue;
            }
            csv_file.writeCSV(write_file);
            csv_file.displayFileContents();
        }

    }

}
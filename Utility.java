import java.io.*;
import java.util.*;

class CSV{
    List<String[]> data =null;
    String [] header=null;
    public CSV(String inputFile){

        data = new ArrayList<>();
        try{

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            data.add(parts);
        }
        reader.close();
        header = data.get(0);
        data.remove(0);
        }
        catch(Exception e){
            System.out.println(e);
            System.exit(1);
        }

    }
    
    public void writeCSV(String outputFile) throws Exception {
        try{
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        writer.write(header[0]+","+header[1]);
        writer.newLine(); 
        for (String[] record : data) {
            writer.write(record[0]+","+record[1]);
            writer.newLine();
        }
        writer.close();
        }
        catch(Exception e){
            System.out.println(e);
            System.exit(1);
        }
    }

    public void  displayFileContents(){
        System.out.println("Row No  "+header[0]+"-->"+header[1]);
        int count=1;
        for (String[] record : data) {
              System.out.println(""+count+"       "+record[0]+"-->"+record[1]);
              count=count+1;
        }
    }
    
    public  void convertToFahrenheit() {
        for (String[] record : data) {
            try {
                double celsius = Double.parseDouble(record[1]);
                double fahrenheit = (celsius * 9/5) + 32;
                record[1] = String.valueOf(fahrenheit);
            } catch (Exception e) {
                System.out.println("Exception: Ignoring invalid temperature values");
            }
        }
    }
    
    public void addRecord(Scanner scanner) {
        System.out.print("Enter Country Name: ");
        String countryName = scanner.nextLine();

        System.out.print("Enter Temperature in Celsius: ");
        String temperature = scanner.nextLine();

        boolean found = false;
        for (String[] record : data) {
            if (record[0].equalsIgnoreCase(countryName)) {
                record[1] = temperature; 
                found = true;
                break;
            }
        }
        if (!found) {
            String[] newRecord = { countryName, temperature };
            data.add(newRecord);
        }
    }

    public  void deleteRecord(Scanner scanner) {
        System.out.print("Enter the row number to Delete: ");
        String index = scanner.nextLine();
        int count = Integer.parseInt(index)-1;
        data.remove(count);
    }

    public  void  editRecord(Scanner scanner) {
        System.out.print("Enter the row number to edit: ");
        int index = Integer.parseInt(scanner.nextLine())-1;
        System.out.print("Which one would you like to edit:\n 1:country \n 2:temperature?: \n");
        String option = scanner.nextLine();
        if(Integer.parseInt(option)==2){
            System.out.print("Enter temperature value: ");
            String temp = scanner.nextLine();
            data.get(index)[1]=temp;
        }
        else{
            System.out.print("Enter country name: \n");
            String country = scanner.nextLine();
            if( country.equals(data.get(index)[0])){
            
                return;
            }
            int count=0;
            for (String[] record : data) {
                if (record[0].equalsIgnoreCase(country)) {
                    data.remove(count);
                    break;
                }
                count=count+1;
            }
            if( count<index){
                data.get(index-1)[0]=country;}
            else{ data.get(index)[0]=country;}


        }
    }
}


public class Utility {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter input file location: ");
        String inputFile = scanner.nextLine();
        CSV csv_file = new CSV(inputFile);

        System.out.println("Enter output file location: ");
        String outputFile = scanner.nextLine();

        while(true){
            String write_file=null;
            System.out.println("Enter operation type (sort/convert/add/edit/delete/quit):");
            String operation = scanner.nextLine();
            try{

                if ("sort".equalsIgnoreCase(operation)) {
                    Collections.sort(csv_file.data, (a, b) -> a[0].compareTo(b[0]));
                    write_file=outputFile;
                    csv_file.writeCSV(write_file);
                    csv_file.displayFileContents();
                    csv_file = new CSV(inputFile);
                    continue;
    
                } else if ("convert".equalsIgnoreCase(operation)) {
                    csv_file.convertToFahrenheit();
                    write_file=outputFile;
                    csv_file.writeCSV(write_file);
                    csv_file.displayFileContents();
                    csv_file = new CSV(inputFile);
                    continue;
    
                } else if ("add".equalsIgnoreCase(operation)) {
                    csv_file.addRecord(scanner);
                    write_file=inputFile;

                } else if ("edit".equalsIgnoreCase(operation)) {
                    csv_file.displayFileContents();
                    csv_file.editRecord(scanner);
                    write_file=inputFile;
                } else if ("delete".equalsIgnoreCase(operation)) {
                    csv_file.displayFileContents();
                    csv_file.deleteRecord(scanner);
                    write_file=inputFile;

                } else if ("quit".equalsIgnoreCase(operation)) {
                    break;
                } else {
                    System.out.println("Invalid operation type. Please select one of these operation types (sort/convert/add/edit/delete/quit) ");
                    continue;
                }
            csv_file.writeCSV(write_file);
            csv_file.displayFileContents();
            }
            catch(Exception e){
                System.out.println(e);
                System.exit(1);

            }
    }

    }
}
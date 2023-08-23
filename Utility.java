import java.io.*;
import java.util.*;

class CSV{
    List<String[]> data =null;
    String [] header=null;

    public CSV(String inputFile) throws Exception{


        data = new ArrayList<>();
        BufferedReader reader=null;
        try{
        reader = new BufferedReader(new FileReader(inputFile));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            data.add(parts);
        }

        header = data.get(0);
        data.remove(0);
        }
        catch(Exception e){
            System.out.println(e);
            System.exit(1);
        }
        finally {
            reader.close();
        }

    }

    
    public void writeCSV(String outputFile) throws Exception {
        BufferedWriter writer=null;
        try{
        writer = new BufferedWriter(new FileWriter(outputFile));
        writer.write(header[0]+","+header[1]);
        writer.newLine(); 
        for (String[] record : data) {
            writer.write(record[0]+","+record[1]);
            writer.newLine();
        }

        }
        catch(Exception e){
            System.out.println(e);
            System.exit(1);
        }
        finally {
            writer.close();
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
    
    public void addRecord(String countryName,String temperature ) {
        if(countryName.equals("") || (temperature.equals(""))){
            System.out.println("Null values are not accepted. Please try again");
            return;
        }
        try{Integer.parseInt(temperature);}
        catch(Exception e)
        {
            System.out.println("Please enter valid values for temperature. Please try again");
            return;
        }
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

    public  void deleteRecord(int  index) {
        data.remove(index);
    }

    public  void  editRecord(int index,  int option, String value) {
        if(option==2){
            data.get(index)[1]=value;
        }
        else{
            if( value.equals(data.get(index)[0])){
                return;
            }
            int count=0;
            for (String[] record : data) {
                if (record[0].equalsIgnoreCase(value)) {
                    data.remove(count);
                    break;
                }
                count=count+1;
            }
            if( count<=index){
                data.get(index-1)[0]=value;}
            else{ data.get(index)[0]=value;}

        }
    }
}


public class Utility {
    public static void main(String[] args){}
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter input file location: ");
//        String inputFile = scanner.nextLine();
//        CSV csv_file = new CSV(inputFile);
//
//        System.out.println("Enter output file location: ");
//        String outputFile = scanner.nextLine();
//
//        while(true){
//            String write_file=null;
//            System.out.println("Enter operation type (sort/convert/add/edit/delete/quit):");
//            String operation = scanner.nextLine();
//            try{
//
//                if ("sort".equalsIgnoreCase(operation)) {
//                    Collections.sort(csv_file.data, (a, b) -> a[0].compareTo(b[0]));
//                    write_file=outputFile;
//                    csv_file.writeCSV(write_file);
//                    csv_file.displayFileContents();
//                    csv_file = new CSV(inputFile);
//                    continue;
//
//                } else if ("convert".equalsIgnoreCase(operation)) {
//                    csv_file.convertToFahrenheit();
//                    write_file=outputFile;
//                    csv_file.writeCSV(write_file);
//                    csv_file.displayFileContents();
//                    csv_file = new CSV(inputFile);
//                    continue;
//
//                } else if ("add".equalsIgnoreCase(operation)) {
//                    csv_file.addRecord(scanner);
//                    write_file=inputFile;
//
//                } else if ("edit".equalsIgnoreCase(operation)) {
//                    csv_file.displayFileContents();
//                    csv_file.editRecord(scanner);
//                    write_file=inputFile;
//                } else if ("delete".equalsIgnoreCase(operation)) {
//                    csv_file.displayFileContents();
//                    csv_file.deleteRecord(scanner);
//                    write_file=inputFile;
//
//                } else if ("quit".equalsIgnoreCase(operation)) {
//                    break;
//                } else {
//                    System.out.println("Invalid operation type. Please select one of these operation types (sort/convert/add/edit/delete/quit) ");
//                    continue;
//                }
//            csv_file.writeCSV(write_file);
//            csv_file.displayFileContents();
//            }
//            catch(Exception e){
//                System.out.println(e);
//                System.exit(1);
//
//            }
//    }
//
//    }
}
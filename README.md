# Java_assignment_1
This Java utility is designed to perform various operations on a CSV file containing country temperature data where country name is Unique. Follow the steps below to use the utility:


To use this Java utility run the following command:

java -jar Utility.jar



Then give the input file location(sample: Users/user1/Desktop/application/input.csv) as input;

Then give the output file location(sample: Users/user1/Desktop/application/output.csv) as input;

Then give any operation from (sort/convert/add/edit/delete/quit) as input.


Here,

if 'sort' is given as an input, then the CSV file will be sorted by country name and it will be stored in the output file location.

if 'convert' is given as an input, then all the values of the temperature in the CSV file will be converted into Fahrenheit and it will be stored in the output file location.

if 'add' is given as an input, then it will ask "Enter Country Name" and then "Enter Temperature in Celsius". Provide those input and changes will be saved in the input file

if 'edit' is given as an input, then it will first display all the rows of the file along with their Row numbers. Then it asks for the row number which you want to edit. After providing that input it asks "Which one would you like to edit:
 1:country 
 2:temperature?: ". Then please provide the appropriate option number. Then provide the corresponding input. Then the changes will be saved in the input file

if 'delete' is given as an input, It will first display all the rows of the file along with their Row numbers. Then it asks for the row number which you want to delete. Then provide that row number to delete that row and the changes will be saved in the input file.

if any of the above operations are selected and completed,  it again gives you the option of choosing any operations that you want to do on the updated CSV file.


if you want to close the application, give 'quit' as the input.



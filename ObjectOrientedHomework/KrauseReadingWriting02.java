import jdk.jshell.Snippet;
// *****NOTE: I programmed this in a Windows 10 environment... the linux VM ran too slow on my PC
//By: Amber Krause
//Homework #3

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class KrauseReadingWriting02 {
    public static void main(String[] args) {
        printMenu();              //calls & executes the method "printMenu()"
        errorCheck();             //calls & executes the method "errorCheck()"
    }

    public static void printMenu() {                        // method prints the main menu
        System.out.println("1. Enter the Student's Name: \n" +
                "2. Enter the Student's Academic Year: \n" +
                "3. Enter the Student's GPA: \n" +
                "4. Display Student's Information: \n" +
                "5. Write Data To File \n" +
                "6. Read Data From File \n" +
                "7. Search for Student Name \n" +
                "8. Exit \n");
    }

    public static void errorCheck() {                    // checks if there is any errors with the user input & lets users input info
        String num1 = ("1. Enter the Student's Name: ");
        String num2 = ("2. Enter the Student's Academic Year: ");
        String num3 = ("3. Enter the Student's GPA: ");
        String num4 = ("4. Display Student's Information: ");
        String num5 = ("5. Write Data To File");
        String num6 = ("6. Read Data from file");
        String num7 = ("7. Search for student name");
        String num8 = ("8. Exit");
        String enterVal = ("Please enter which number you want to answer: ");

        System.out.println(enterVal);
        Scanner scannerInput = new Scanner(System.in);                 //variable that will store user's input number
        String num;
        String name = ("");
        String year = ("");
        double gpa = (99.0);
        int inputNum = 0;

        while (true) {                                                  // will keep looping unless program is ended
            if (inputNum > 8) {                                        // if the number is greater than 5
                System.out.println("That number is invalid. Please try again.");
                System.out.println(enterVal);
            }

            if (inputNum == 1) {                                       //repeats the process
                System.out.println(num1);
                name = scannerInput.next();
                if (name.isEmpty()) {                                  //if the name is empty
                    System.out.println("That answer is invalid. Please try again.");
                    name = "";
                }
                System.out.println(enterVal);                          //enter the value again
            }

            if (inputNum == 2) {
                System.out.println(num2);
                year = scannerInput.next();
                if (!year.isEmpty() && year.equalsIgnoreCase("Freshman") || year.equalsIgnoreCase("Sophomore")
                        || year.equalsIgnoreCase("Junior") || year.equalsIgnoreCase("Senior")) {  //if the year equals one of the following
                    System.out.println(enterVal);
                } else {
                    System.out.println("That answer is invalid. Please try again."); //if the year does not match any of the options above
                    year = "";
                    System.out.println(enterVal);
                }
            }

            if (inputNum == 3) {
                Scanner scanme = new Scanner(System.in);
                System.out.println(num3);
                try {                    //seeing if the answer is valid
                    gpa = scanme.nextDouble(); //if the gpa scanner is not inputted as a double
                } catch (Exception e) {
                    System.out.println("You did not put the right answer in");  //it will output this
                }

                if (gpa > 4.0 || gpa < 0.0) {                                  // GPA must be between these bounds (data validation)
                    System.out.println("That answer is invalid. Please try again.");
                    gpa = 99.0;
                }
                System.out.println(enterVal);
            }

            if (inputNum == 4) {
                if (name == "") {
                    System.out.println("Name has not been filled yet.");             //if you are trying to display #4 it will not display unless you have answered all prompts
                }
                if (year == "") {
                    System.out.println("Year has not been filled yet.");
                }
                if (gpa == 99.0) {
                    System.out.println("GPA has not been filled yet.");
                }
                if (name != "" && year != "" && gpa != 99.0) {
                    System.out.println(num4);
                    System.out.println(num1 + name + "\n" + num2 + year + num3 + gpa); // will print out all of info from the questions
                }
                System.out.println(enterVal);
            }

            if (inputNum == 5) {
                writeToFile(name, year, gpa);                                              // if input is #5 --> call write to file
                System.out.println(enterVal);
            }
            if (inputNum == 6) {
                readFromFile();
                System.out.println(enterVal);
            }
            if (inputNum == 7) {
                searchFromFile();
                System.out.println(enterVal);
            }
            if (inputNum == 8) {                                                // if input is #5 --> exit
                System.exit(0);
            }
            try {
                num = scannerInput.next();                                      //does this again and makes sure the information is not causing errors
                inputNum = Integer.parseInt(num);
            } catch (Exception e) {
                inputNum = 0;
                System.out.println("HEY! Put in a number 1-8 instead.");

            }
        }
    }

    public static void writeToFile(String name1, String year2, Double gpa3) {           //here is where the writing to file comes in
        Scanner scan1 = new Scanner(System.in);
        File myFile = new File("myText.csv");
        if (name1 == "") {                                                              // if information from above is not filled out yet, it will not write to the file until completed
            System.out.println("Name has not been filled yet.");
        }
        if (year2 == "") {
            System.out.println("Year has not been filled yet.");
        }
        if (gpa3 == 99.0) {
            System.out.println("GPA has not been filled yet.");
        }
        if (name1 != "" && year2 != "" && gpa3 != 99.0) {
            try {
                FileWriter writeMyFile = new FileWriter(myFile, true);                // will create new file (or modify it) if the file isn't already created
                if (myFile.createNewFile()) {
                    System.out.println("File Created");
                } else {
                    System.out.println("File already exists and will be written to");        // will still write/append the file if the file already exists.. it will add to the file
                }
                writeMyFile.write("\n" + "" + name1 + "," + year2 + "," + gpa3 + "\n");  //will write this string
                writeMyFile.close();
            } catch (IOException e) {
                System.out.println("Error when writing to file");
            }
        }
    }


    public static void readFromFile() { //method that reads from the file myText
        File myFile1 = new File("myText.csv"); //
        try {
            Scanner myScanner = new Scanner(myFile1);                                                //is going to read from my file only if there is data on it
            while (myScanner.hasNextLine()) {                                                        // when the file has another line read it
                String data = myScanner.nextLine();                                             // and print it
                System.out.println(data);
            }
        } catch (Exception ex) {                                                                //gives error if it cannot read from file
            System.out.println("Error. Cannot read from file");
        }
    }

    public static void searchFromFile() {                                           //method that searches for names in csv
        File myFile = new File("myText.csv");
        Scanner input = new Scanner(System.in);
        System.out.println("What name would you like to search for?: ");
        String search = input.nextLine();

        try {
            Scanner myScanner = new Scanner(myFile);                                   //is going to read from my file only if there is data on it
            boolean found = false;
            while (myScanner.hasNextLine()) {                                                      // when the file has another line read it
                String data = myScanner.nextLine();                                                // and print it
                if (data.contains(search)) {
                    System.out.println(data);
                    found = true;
                }
                if (!myScanner.hasNextLine()&&!found){                                           //only if the scanner doesn't have next line and the name is not found
                    System.out.println("Student name not found.");
                }
            }
        }
        catch (Exception ex) {                                                                   //gives error if it cannot read from file
            System.out.println("Error. Cannot read from file");
        }
    }
}

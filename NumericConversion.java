import java.util.Scanner;
public class NumericConversion {
    // this method decodes an entire hexidecimal string and returns the decimal value
    public static long hexStringDecode(String hex) {
        int hexLength = hex.length();
        long personResult = 0;
        short powerOf = 0;
        /* this while loop will decode the hexidecimal string one character at a time by using the hexCharDecode method
        it will continue to execute until there are no more characters in the hexidecimal string */
        while (hexLength > 0) {
            char desiredChar = hex.charAt(hexLength-1);
            short hexValue = hexCharDecode(desiredChar);
            personResult = personResult + (hexValue * (long)(Math.pow(16,powerOf)));
            hexLength = hexLength - 1;
            powerOf ++;
        }
        return personResult;
    }

    //this method is used for the hexStringDecode method. It converts each character in the hexidecimal string to its deciaml value
    public static short hexCharDecode(char digit) {
        short digitValue = 0;
        //this switch statement converts each character in the string of the hexidecimal value to an integer.
        switch (digit) {
            case '0':
                digitValue = 0;
                break;
            case '1':
                digitValue = 1;
                break;
            case '2':
                digitValue = 2;
                break;
            case '3':
                digitValue = 3;
                break;
            case '4':
                digitValue = 4;
                break;
            case '5':
                digitValue = 5;
                break;
            case '6':
                digitValue = 6;
                break;
            case '7':
                digitValue = 7;
                break;
            case '8':
                digitValue = 8;
                break;
            case '9':
                digitValue = 9;
                break;
            case 'A':
            case 'a':
                digitValue = 10;
                break;
            case 'B':
            case 'b':
                digitValue = 11;
                break;
            case 'C':
            case 'c':
                digitValue = 12;
                break;
            case 'D':
            case 'd':
                digitValue = 13;
                break;
            case 'E':
            case 'e':
                digitValue = 14;
                break;
            case 'F':
            case 'f':
                digitValue = 15;
                break;
        }
        //it then returns the value to go through the other method. (hexStringDecode)
        return digitValue;
    }

    //this method decodes a binary string and returns it as a decimal value.
    public static short binaryStringDecode(String binary){
        int numLength = binary.length();
        int powerOfNum = 0;
        int personNumber = 0;

        /* this while loop checks see what character value is in each slot of the binary number. (Either 0 or 1.)
        if it is 0, the program just increases the power of 2 and moves onto the next slot.
        if it is 1, the program will raise 2 to the correct power and add it to the running total. */
        while (numLength > 0) {
            if (binary.charAt(numLength-1) == '0') {
                powerOfNum ++;
            }
            else {
                personNumber = personNumber + (int)Math.pow(2,powerOfNum);
                powerOfNum ++;
            }
            numLength = numLength - 1;
        }
        return (short)personNumber;
    }

    /* the binaryStringDecode method was used before this to find the decimal value of the binary number entered.
    that decimal value will then enter this method so that I can convert it to a hexidecimal value. */
    public static String binaryToHex(String binary) {
        String hexiDecimal = "";
        long personRemainder;

        int numLength = binary.length();
        int powerOfNum = 0;
        int personNumber = 0;

        /* this while loop checks see what character value is in each slot of the binary number. (Either 0 or 1.)
        if it is 0, the program just increases the power of 2 and moves onto the next slot.
        if it is 1, the program will raise 2 to the correct power and add it to the running total. */
        while (numLength > 0) {
            if (binary.charAt(numLength - 1) == '0') {
                powerOfNum++;
            } else {
                personNumber = personNumber + (int) Math.pow(2, powerOfNum);
                powerOfNum++;
            }
            numLength = numLength - 1;
        }

        /* this while loop converts the numbers of the decimal string to the correct letters/numbers that will
        make up the final hexidecimal string. */
        while (personNumber > 0) {
            personRemainder = personNumber % 16;
            if (personRemainder >= 10) {
                //if the value of the decimal is 10 and above, then it converts to a letter in the hexidecimal format
                String personHex = String.valueOf(personRemainder);
                char personLastDigit = personHex.charAt(1);
                switch (personLastDigit) {
                    case '0':
                        personHex = "A";
                        break;
                    case '1':
                        personHex = "B";
                        break;
                    case '2':
                        personHex = "C";
                        break;
                    case '3':
                        personHex = "D";
                        break;
                    case '4':
                        personHex = "E";
                        break;
                    case '5':
                        personHex = "F";
                        break;
                }
            hexiDecimal = hexiDecimal + personHex;
            }
            //if the value of the decimal is less than 10, then it stays the same.
            else {
                String personHex = String.valueOf(personRemainder);
                hexiDecimal = hexiDecimal + personHex;
            }
            personNumber = personNumber / 16;
        }

        /* the final product of the while loop above produces a string that must be flipped in order to produce
        correct hexidecimal value. this next chunk of code flips the string. */
        String willReverse = "";
        int hexLength = hexiDecimal.length() - 1;
        while (hexLength >= 0) {
            willReverse = willReverse + hexiDecimal.charAt(hexLength);
            hexLength --;
        }
        //and finally it returns the correct version of the hexidecimal number.
        return willReverse;
    }

    //this is the main method and it will keep running until the user decides to exit.
    public static void main(String[]args) {
        boolean runProgram = true;
        Scanner keyboard = new Scanner(System.in);
        while (runProgram = true) {
            //this chunk of code prints the menu
            System.out.println("Decoding Menu");
            System.out.println("-------------");
            System.out.println("1. Decode hexadecimal");
            System.out.println("2. Decode binary");
            System.out.println("3. Convert binary to hexadecimal");
            System.out.println("4. Quit");

            System.out.print("Please enter an option: ");
            int personOption;
            personOption = keyboard.nextInt();

            //this if statement shows how I used the program to convert a hexidecimal into a decimal value.
            if (personOption == 1) {
                System.out.print("Please enter the numeric string to convert: ");
                String personHex = keyboard.next();
                //some hexidecimals start with the value 0x, this if statement eliminates that so it can decode the correct numbers/letters.
                if (personHex.charAt(1) == 'x') {
                    personHex = personHex.substring(2);
                }
                long personResult = hexStringDecode(personHex);

                System.out.println("Result: " + personResult);
                System.out.println("");
            }

            //this else if statement shows how I used the program to convert a binary number to a decimal number
            else if (personOption == 2) {
                System.out.print("Please enter the numeric string to convert: ");
                String personBinary = keyboard.next();
                //some binary numbers start with the value 0b, this if statement eliminates that so it can decode the correct numbers.
                if (personBinary.charAt(1) == 'b') {
                    personBinary = personBinary.substring(2);
                }
                String personNum = String.valueOf(personBinary);

                short personResult = binaryStringDecode(personNum);
                System.out.println("Result: " + personResult);
                System.out.println("");
            }

            //this else if statement shows how I used the program to convert a binary number to a decimal to a hexidecimal number.
            else if (personOption == 3) {
                System.out.print("Please enter the numeric string to convert: ");
                String personBinary = keyboard.next();
                if (personBinary.charAt(1) == 'b') {
                    personBinary = personBinary.substring(2);
                }

                //this chunk of code uses the binaryToHex method to convert the decimal to a hexidecimal.
                String converttoHex = String.valueOf(personBinary);
                String finalHex = binaryToHex(converttoHex);

                System.out.println("Result: " + finalHex);
                System.out.println("");
            }

            //this final else if statement will exit the program when the user is done.
            else {
                System.out.print("Goodbye!");
                break;
            }

        }
    }

}

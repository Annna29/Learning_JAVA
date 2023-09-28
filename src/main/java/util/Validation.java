package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validation {

    public static boolean validateDetails(String text, int length){
      return text.length() >= length;
    }

    public static boolean validatePassword( String text, int length){
        return text.length() >= length && (text.contains("#") || text.contains("%")||text.contains("@") ||text.contains("$"));
    }

    public static boolean validateNumber(int number){
        return number > 0;
    }
    public static boolean validateDiscount(int number){
        return number >= 0 && number <= 100;
    }
    public static boolean validateProcesor(String procesor){
        return procesor.length()>=2;
    }
    public static boolean validateVolume(int number){
        return number>=10 && number <=500;
    }

    public static boolean validateNumberKg(int number){
        return number > 0 && number<=20;
    }

    public static boolean validateID(int number){
        return number >= 0;
    }

    public static int readANumber(ValidationType val) {
        Scanner scanner = new Scanner(System.in);
        int result = -1;
        boolean isValid = false;
        do {
            try {
                result = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You did not enter a number... Please retry!");
                scanner.nextLine();
            }
            switch (val) {
                case NUMBER    -> isValid = Validation.validateNumber(result);
                case DISCOUNT  -> isValid = Validation.validateDiscount(result);
                case VOLUME    -> isValid = Validation.validateVolume(result);
                case NUMBER_KG -> isValid = Validation.validateNumberKg(result);
                case ID        -> isValid = Validation.validateID(result);

            }
        }
        while (!isValid);

        return result;
    }

    public static String readAString(ValidationType val){
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        String result;
        do {
            result = scanner.nextLine();
            switch (val) {
                case DETAILS   -> isValid = Validation.validateDetails (result, Constants.DETAILS_LENGTH);
                case PASSWORD  -> isValid = Validation.validatePassword(result, Constants.USERNAME_PASSWORD_LENGTH);
                case USERNAME ->  isValid = Validation.validateDetails(result,Constants.USERNAME_PASSWORD_LENGTH);
                case PROCESSOR -> isValid = Validation.validateProcesor(result);
                case SEARCHING_TEXT -> isValid=Validation.validateDetails(result,Constants.MINIMUM_LENGTH_FOR_SEARCHING);

            }
        }
        while (!isValid);

        return result;
    }
}

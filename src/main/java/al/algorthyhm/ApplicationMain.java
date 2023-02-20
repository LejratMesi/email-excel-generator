package al.algorthyhm;

import al.algorthyhm.service.ExcelService;

import java.util.Scanner;

public class ApplicationMain {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        /*System.out.println("Vendosni pathin e filet Excel jultem :");
        String path = input.nextLine();
*/
        ExcelService reader = new ExcelService("path");
        reader.write();

    }
}
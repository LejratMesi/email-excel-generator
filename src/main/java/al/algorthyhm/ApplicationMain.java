package al.algorthyhm;

import al.algorthyhm.service.ExcelService;
import al.algorthyhm.utils.EmailUtils;

import java.util.Scanner;

public class ApplicationMain {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        EmailUtils emailUtils = EmailUtils.getInstance();
        System.out.println("Programi po vazhdon");
        System.out.println("Ju lutem vendosni pathin e filet excel qe doni te lexoni");
        String path = input.nextLine();
        System.out.println("Ju lutem vendosni pathin e filet excel qe doni te gjenerohet");
        String destinationPath = input.nextLine();
        System.out.println("Ju lutem vendosni emaili");
        emailUtils.username = input.nextLine();
        System.out.println("Ju lutem vendosni tokenin e emailit");
        emailUtils.password = input.nextLine();
        System.out.println("Programi po gjeneron excelin");


        
        ExcelService reader = new ExcelService(path, destinationPath);
        reader.write();

    }
}
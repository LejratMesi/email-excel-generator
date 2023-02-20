package al.algorthyhm;

import al.algorthyhm.service.ExcelService;

import java.util.Scanner;

public class ApplicationMain {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Programi po vazhdon");
        System.out.println("Programi po gjeneron excelin");
        ExcelService reader = new ExcelService("path");
        reader.write();
        System.out.println("Gjenerimi i excelit mbraoj .....");
    }
}
package al.algorthyhm.utils;

import al.algorthyhm.pojo.Person;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public static void writeData(List<Person> personList, Sheet sheet) throws AddressException {
        int startRow = 0;
        EmailUtils emailUtils = EmailUtils.getInstance();
        List<InternetAddress> recipientAddresses;
        for (Person person:personList) {
            List<String> emailList = person.generateRandomEmails();
            recipientAddresses = new ArrayList<>();
            for (String email : emailList) {
                Row row = sheet.createRow(startRow + 1);
                Cell cell = row.createCell(0);
                cell.setCellValue(person.getProviders().getCompay());
                Cell cell2 = row.createCell(1);
                cell2.setCellValue(person.getEmri());
                Cell cell3 = row.createCell(2);
                cell3.setCellValue(email);
                recipientAddresses.add(new InternetAddress(email));
                startRow++;
            }

            emailUtils.sendEmail(recipientAddresses,person.getProviders().getCompay(),person.getEmri());
        }



    }

    public static void createHeaders(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Company");
        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Name");
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Email");
    }
}

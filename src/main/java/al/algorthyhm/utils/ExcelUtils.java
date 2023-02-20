package al.algorthyhm.utils;

import al.algorthyhm.pojo.Person;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

public class ExcelUtils {

    public static void writeData(List<Person> personList, Sheet sheet) {
        int startRow = 0;
        // Write the data to the Sheet
        for (int i = 0; i < personList.size(); i++) {
            Person person = personList.get(i);
            List<String> emailList = person.generateRandomEmails();
            for ( int j = 0 ; j< emailList.size(); j ++){
                Row row = sheet.createRow(startRow + 1);
                Cell cell = row.createCell(0);
                cell.setCellValue(person.getProviders().getCompay());
                Cell cell2 = row.createCell(1);
                cell2.setCellValue(person.getEmri());
                Cell cell3 = row.createCell(2);
                cell3.setCellValue(emailList.get(j));
                startRow++;
            }


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

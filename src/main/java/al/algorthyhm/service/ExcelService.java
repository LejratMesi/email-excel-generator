package al.algorthyhm.service;import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import al.algorthyhm.pojo.Person;
import al.algorthyhm.pojo.Providers;
import al.algorthyhm.utils.ExcelUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelService {

    private static Logger logger = LogManager.getLogger(ExcelService.class);

    private String filePath;

    public ExcelService(String filePath) {
        this.filePath = filePath;
    }

    public List<Person> read() {

        logger.info("read method >>>>>>>>>");
        try {
            FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\Lejrat Mesi\\Desktop/New Microsoft Excel Worksheet.xlsx"));
            Workbook workbook = new XSSFWorkbook(inputStream);

            // Getting the first sheet in the Workbook
            Sheet sheet = workbook.getSheetAt(0);
            int rowNumber = 0;
            Providers provider = new Providers();
            List<Person> personList = new ArrayList<>();

            for (Row row : sheet) {

                if (rowNumber == 0){
                    provider.setProvider(row.getCell(row.getLastCellNum()- 1).getStringCellValue());
                    provider.setCompay(row.getCell(row.getFirstCellNum()).getStringCellValue());
                    rowNumber++;
                } else {
                     Person person = new Person();
                     for (Cell cell : row) {
                         String [] personInfo = cell.getStringCellValue().split(" ");
                         person.setEmri(personInfo[0]);
                         person.setMbiemri(personInfo[1]);
                     }
                     person.setProviders(provider);
                    personList.add(person);
                }

            }

            // Closing the Workbook and the FileInputStream
            workbook.close();
            inputStream.close();
            logger.info("end of read method");
            return personList;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void write(){
        logger.info("write method >>>>>>>>>");
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Algorthyhm");

        ExcelUtils.createHeaders(sheet);
        List<Person> personList = read();
        ExcelUtils.writeData(personList,sheet);

        // Write the Workbook to a file
        try (FileOutputStream outputStream = new FileOutputStream("test.xlsx")) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the Workbook
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
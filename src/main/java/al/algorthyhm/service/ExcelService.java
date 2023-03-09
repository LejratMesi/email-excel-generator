package al.algorthyhm.service;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import al.algorthyhm.pojo.Person;
import al.algorthyhm.pojo.Providers;
import al.algorthyhm.utils.ExcelUtils;
import jakarta.mail.internet.AddressException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelService {

    private final static Logger logger = LogManager.getLogger(ExcelService.class);

    private String sourcePath;
    private String destiantionPath;

    public ExcelService(String filePath, String destinationPath) {
        this.sourcePath = filePath;
        this.destiantionPath = destinationPath;
    }

    public List<Person> read() {

        logger.info("read method >>>>>>>>>");
        try {
            FileInputStream inputStream = new FileInputStream(sourcePath);
            Workbook workbook = new XSSFWorkbook(inputStream);

            // Getting the first sheet in the Workbook
            Sheet sheet = workbook.getSheetAt(0);
            Providers provider = null;
            List<Person> personList = new ArrayList<>();

            for (Row row : sheet) {
                logger.info(row.getLastCellNum());
                if (row.getLastCellNum() == 7){
                    provider = new Providers();
                    provider.setProvider(row.getCell(row.getFirstCellNum()+2).getStringCellValue());
                    provider.setCompay(row.getCell(row.getFirstCellNum()).getStringCellValue());
                } else {
                     Person person = new Person();
                     String [] personInfo = row.getCell(1).getStringCellValue().split(" ");
                     person.setEmri(personInfo[0]);
                     person.setMbiemri(personInfo[1]);
                     person.setProviders(provider);
                     personList.add(person);
                }

            }

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
        try {
            ExcelUtils.writeData(personList,sheet);
        } catch (AddressException e) {
            e.printStackTrace();
        }

        try (FileOutputStream outputStream = new FileOutputStream(destiantionPath)) {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

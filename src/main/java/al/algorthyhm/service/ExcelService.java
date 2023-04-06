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
    private final static Integer INDEX_OF_NAME_IN_CELL = 1;
    private final static Integer INDEX_OF_FIRST_ROW = 0;
    private final static Integer INDEX_OF_COMPANY_IN_CELL = 2;
    private final static String NAME_OF_SHEET = "Algorthyhm";

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
            Sheet sheet = workbook.getSheetAt(0);
            Providers provider = null;
            List<Person> personList = new ArrayList<>();

            for (Row row : sheet) {

                if (row.getRowNum() == INDEX_OF_FIRST_ROW) {
                    continue;
                }

                if (row.getCell(0) != null && !row.getCell(0).getStringCellValue().isEmpty()) {
                    provider = new Providers();
                    provider.setProvider(row.getCell(INDEX_OF_COMPANY_IN_CELL).getStringCellValue());
                    provider.setCompay(row.getCell(row.getFirstCellNum()).getStringCellValue());
                } else {
                    if(row.getCell(INDEX_OF_NAME_IN_CELL) != null && !row.getCell(INDEX_OF_NAME_IN_CELL).getStringCellValue().isEmpty()) {
                        Person person = new Person();
                        String[] personInfo = row.getCell(INDEX_OF_NAME_IN_CELL).getStringCellValue().split(" ");
                        person.setEmri(personInfo[0]);
                        person.setMbiemri(personInfo[1]);
                        person.setProviders(provider);
                        personList.add(person);
                    }
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
        Sheet sheet = workbook.createSheet(NAME_OF_SHEET);

        ExcelUtils.createHeaders(sheet);
        List<Person> personList = read();
        try {
            ExcelUtils.writeData(personList,sheet);
        } catch (AddressException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try (FileOutputStream outputStream = new FileOutputStream(destiantionPath)) {
            workbook.write(outputStream);
            logger.info("Exceli u gjenerua");
            workbook.close();
        } catch (IOException e) {

            System.out.println(e.getMessage());
        }

    }

}

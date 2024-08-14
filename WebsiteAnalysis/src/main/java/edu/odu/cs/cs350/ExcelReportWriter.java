package edu.odu.cs.cs350;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Creates an excel file
 * 
 * @author wbrow004
 */
public class ExcelReportWriter extends ReportWriter {

    private FileOutputStream destination;

    /**
     * Default Constructor
     * 
     * @throws FileNotFoundException
     */
    public ExcelReportWriter() {
        this.destination = null;
    }

    /**
     * Sets the source data from a website object
     * @param site website information that can be used to report data
     */
    @Override
    public void setSourceData(Website site) {
        this.website = site;
    }

    /**
     * Set this ExcelReportWriter's baseFileName
     * @param baseFieldName name of the report passed from the website data
     */
    @Override
    public void setBaseName(String baseFieldName) {
        this.baseFileName = baseFieldName;
    }

    /*
     * Writes an output a website to an Excel document
     */
    @Override
    public void write() {
        // Try-Catch here instead of adding throws IOException
        try {
            this.destination = new FileOutputStream(new File("Report.xlsx"));

            writeDataTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Formats the data to the Excel workbook in cells and columns
     */
    private void writeDataTable() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Report");

        // Output the header
        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Page");
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("# Images");
        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("# CSS");
        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("# Scripts");
        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("# Links (Intra-Page)");
        headerCell = headerRow.createCell(6);
        headerCell.setCellValue("# Links (Internal)");
        headerCell = headerRow.createCell(7);
        headerCell.setCellValue("# Links (External)");

        // Start at the next row after the header (above)
        int indexRow = 1;

        for (HTMLDocument document : website.getHTMLDocuments()) {
            Row row = sheet.createRow(indexRow);

            Cell cell = row.createCell(0);
            cell.setCellValue(indexRow);

            // Set data found from the HTML document to cells
            // column starts at 1 for formatting

            cell = row.createCell(1);
            cell.setCellValue(document.getPath().toString());

            cell = row.createCell(2);
            cell.setCellValue(document.getImages().size());

            cell = row.createCell(3);
            cell.setCellValue(document.getStyleSheets().size());

            cell = row.createCell(4);
            cell.setCellValue(document.getScripts().size());

            cell = row.createCell(5);
            cell.setCellValue(sizeOfAnchors(document, ResourceKind.ARCHIVE));

            cell = row.createCell(6);
            cell.setCellValue(sizeOfAnchors(document, ResourceKind.AUDIO));

            cell = row.createCell(7);
            cell.setCellValue(sizeOfAnchors(document, ResourceKind.VIDEO));

            ++indexRow;
        }

        // Write and save the Excel document
        workbook.write(this.destination);
        workbook.close();
    }

    /**
     *  Utility that pulls the number of of anchors specified
     * 
     * @param document document that contains the anchors
     * @param kind type of anchor resource specified
     * @return returns the size of the anchor
     */
    private int sizeOfAnchors(HTMLDocument document, ResourceKind kind) {
        int value = 0;
            for (var anchor : document.getAnchors()) {
                if (anchor.getResourceKind() == kind)
                {
                    value++;
                }
            }
        return value;
    }

}

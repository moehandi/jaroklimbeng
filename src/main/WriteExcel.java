/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class WriteExcel {

    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;
    private String inputFile;

    public void setOutputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public void write() throws IOException, WriteException {
        File file = new File(inputFile);
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Hasil Prediksi", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        createLabel(excelSheet);
//        createContent(excelSheet);

        workbook.write();
        workbook.close();
    }

    public void createLabel(WritableSheet sheet)
            throws WriteException {
        // Lets create a times font
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
        // Define the cell format
        times = new WritableCellFormat(times10pt);
        // Lets automatically wrap the cells
        times.setWrap(true);

        // create create a bold font with unterlines
        WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
                UnderlineStyle.NO_UNDERLINE);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        // Lets automatically wrap the cells
        timesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);
        cv.setAutosize(true);

        // Write a few headers
        addCaption(sheet, 0, 0, "No");
        addCaption(sheet, 1, 0, "Tanggal yang akan diprediksi");
        addCaption(sheet, 2, 0, "Hasil Prediksi");
        addCaption(sheet, 3, 0, "Input Layer");
        addCaption(sheet, 4, 0, "Hidden Layer");
        addCaption(sheet, 5, 0, "Output Layer");
        addCaption(sheet, 6, 0, "Learning Rate");
        addCaption(sheet, 7, 0, "Momentum");
        addCaption(sheet, 8, 0, "Max Error");
        addCaption(sheet, 9, 0, "Max Iterasi");
        //addCaption(sheet, 10, 0, "MSE");
        


    }

    public void createContent(WritableSheet sheet, String tanggalHariIni, String tanggalEsokHari, String prediksiHariIni, String prediksiEsokHari, String hiddenLayer, String maxError, String maxIterasi, String momentum, String learningRate) throws WriteException, RowsExceededException {
        
        addLabel(sheet, 0, 2, "1");
        addLabel(sheet, 1, 2, tanggalHariIni);
        addLabel(sheet, 2, 2, prediksiHariIni);
        addLabel(sheet, 3, 2, "3");
        addLabel(sheet, 4, 2, hiddenLayer);
        addLabel(sheet, 5, 2, "1");
        addLabel(sheet, 6, 2, learningRate);
        addLabel(sheet, 7, 2, momentum);
        addLabel(sheet, 8, 2, maxError);
        addLabel(sheet, 9, 2, maxIterasi);
        

        addLabel(sheet, 0, 3, "2");
        addLabel(sheet, 1, 3, tanggalEsokHari);
        addLabel(sheet, 2, 3, prediksiEsokHari);
        addLabel(sheet, 3, 3, "3");
        addLabel(sheet, 4, 3, hiddenLayer);
        addLabel(sheet, 5, 3, "1");
        addLabel(sheet, 6, 3, learningRate);
        addLabel(sheet, 7, 3, momentum);
        addLabel(sheet, 8, 3, maxError);
        addLabel(sheet, 9, 3, maxIterasi);
        

    }

    private void addCaption(WritableSheet sheet, int column, int row, String s)
            throws RowsExceededException, WriteException {
        Label label;
        label = new Label(column, row, s, timesBoldUnderline);
        sheet.addCell(label);
    }

    private void addNumber(WritableSheet sheet, int column, int row,
            Integer integer) throws WriteException, RowsExceededException {
        Number number;
        number = new Number(column, row, integer, times);
        sheet.addCell(number);
    }

    private void addLabel(WritableSheet sheet, int column, int row, String s)
            throws WriteException, RowsExceededException {
        Label label;
        label = new Label(column, row, s, times);
        sheet.addCell(label);
    }
}

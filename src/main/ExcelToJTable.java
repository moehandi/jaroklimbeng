/*
 * Moehandi's Java Source Code 
 */
package main;

/**
 *
 * @author moehandi
 */
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import jxl.Cell;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelToJTable extends JFrame {

    static JTable table;
    static JScrollPane scroll;
    // header is Vector contains table Column
    static Vector headers = new Vector();
    // Model is used to construct JTable
    static DefaultTableModel model = null;
    // data is Vector contains Data from Excel File
    static Vector data = new Vector();
    static JButton jbClick;
    static JFileChooser jChooser;
    static int tableWidth = 0; // set the tableWidth
    static int tableHeight = 0; // set the tableHeight

    public ExcelToJTable() {
        super("Import Excel To JTable");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.white);
        jChooser = new JFileChooser();
        jbClick = new JButton("Select Excel File");
        buttonPanel.add(jbClick, BorderLayout.CENTER);
        // Show Button Click Event
        jbClick.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                jChooser.showOpenDialog(null);

                File file = jChooser.getSelectedFile();
                if (!file.getName().endsWith("xls")) {
                    JOptionPane.showMessageDialog(null,
                            "Please select only Excel file.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    fillData(file);
                    model = new DefaultTableModel(data,
                            headers);
                    tableWidth = model.getColumnCount()
                            * 150;
                    tableHeight = model.getRowCount()
                            * 25;
                    table.setPreferredSize(new Dimension(
                            tableWidth, tableHeight));

                    table.setModel(model);
                }
            }
        });

        table = new JTable();
        table.setAutoCreateRowSorter(true);
        model = new DefaultTableModel(data, headers);

        table.setModel(model);
        table.setBackground(Color.pink);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setEnabled(false);
        table.setRowHeight(25);
        table.setRowMargin(4);

        tableWidth = model.getColumnCount() * 150;
        tableHeight = model.getRowCount() * 25;
        table.setPreferredSize(new Dimension(
                tableWidth, tableHeight));

        scroll = new JScrollPane(table);
        scroll.setBackground(Color.pink);
        scroll.setPreferredSize(new Dimension(300, 300));
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(buttonPanel,
                BorderLayout.NORTH);
        getContentPane().add(scroll,
                BorderLayout.CENTER);
        setSize(600, 600);
        setResizable(true);
        setVisible(true);
    }

    /**
     * Fill JTable with Excel file data.
     *
     * @param file file :contains xls file to display in jTable
     */
    void fillData(File file) {

        Workbook workbook = null;
        try {
            try {
                workbook = Workbook.getWorkbook(file);
            } catch (IOException ex) {
                Logger.getLogger(
                        ExcelToJTable.class.
                        getName()).log(Level.SEVERE,
                                null, ex);
            }
            Sheet sheet = workbook.getSheet(0);

            headers.clear();
            for (int i = 0; i < sheet.getColumns(); i++) {
                Cell cell1 = sheet.getCell(i, 0);
                headers.add(cell1.getContents());
            }

            data.clear();
            for (int j = 1; j < sheet.getRows(); j++) {
                Vector d = new Vector();
                for (int i = 0; i < sheet.getColumns(); i++) {

                    Cell cell = sheet.getCell(i, j);

                    d.add(cell.getContents());

                }
                d.add("\n");
                data.add(d);
            }
        } catch (BiffException e) {
        }
    }

}

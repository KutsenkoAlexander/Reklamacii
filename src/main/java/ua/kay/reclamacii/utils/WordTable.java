package ua.kay.reclamacii.utils;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import ua.kay.reclamacii.models.Defect;
import ua.kay.reclamacii.models.DefectSolution;
import ua.kay.reclamacii.models.Record;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.List;

import static ua.kay.reclamacii.utils.UnixDateHandler.fromUnixTimeStamp;

public class WordTable {

    XWPFDocument document = new XWPFDocument();
    XWPFTable table = document.createTable();

    XWPFTableRow row = table.getRow(0);
    XWPFTableCell cell = row.getCell(0);

    public WordTable() {
        CTBody body = document.getDocument().getBody();
        if(!body.isSetSectPr()){
            body.addNewSectPr();
        }
        CTSectPr section = body.getSectPr();
        if(!section.isSetPgSz()){
            section.addNewPgSz();
        }
        CTPageSz pageSize = section.getPgSz();
        pageSize.setOrient(STPageOrientation.LANDSCAPE);
        pageSize.setW(BigInteger.valueOf(16840));
        pageSize.setH(BigInteger.valueOf(11900));

        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        CTPageMar pageMar = sectPr.addNewPgMar();
        pageMar.setLeft(BigInteger.valueOf(500L));
        pageMar.setTop(BigInteger.valueOf(500L));
        pageMar.setRight(BigInteger.valueOf(500L));
        pageMar.setBottom(BigInteger.valueOf(500L));
    }

    public XWPFDocument getDocument() {
        return document;
    }

    public void createTable(Record record){
        createHeaderTable();
        try {
            createBodyTable(1L, record);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void createTable(List<Record> recordList){
        createHeaderTable();
        Long count = 1L;
        for(Record record : recordList){
            try {
                createBodyTable(count++, record);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void createHeaderTable(){
        createFirstHeaderCell("№ п/п");
        createHeaderEmptyCell("Дата внесения записи");
        createHeaderEmptyCell("Тип изделия");
        createHeaderEmptyCell("Наименование изделия");
        createHeaderEmptyCell("Потребитель");
        createHeaderEmptyCell("Способ устранения дефекта");
        createHeaderEmptyCell("Акт исследования/технический акт");
        createHeaderEmptyCell("Причина дефекта");
        createHeaderEmptyCell("Классификация дефекта");
        createHeaderEmptyCell("Меры по дефекту");
        createHeaderEmptyCell();
        createHeaderEmptyCell();
        createHeaderEmptyCell("Восстановление");
        createHeaderEmptyCell();
        createHeaderEmptyCell("Отгрузка");
        createHeaderEmptyCell();
        createHeaderEmptyCell("Ответственный исполнитель");
        createHeaderEmptyCell("Рекламация удовлетворена");
        row=table.createRow();
        mergeHeaderCallVertically(0);
        mergeHeaderCallVertically(1);
        mergeHeaderCallVertically(2);
        mergeHeaderCallVertically(3);
        mergeHeaderCallVertically(4);
        mergeHeaderCallVertically(5);
        mergeHeaderCallVertically(6);
        mergeHeaderCallVertically(7);
        mergeHeaderCallVertically(8);
        createHeaderSubCell(9, "Перечень мер");
        createHeaderSubCell(10, "Назначенный срок");
        createHeaderSubCell(11, "Дата выполнения");
        createHeaderSubCell(12, "Назначенный срок");
        createHeaderSubCell(13, "Дата выполнения");
        createHeaderSubCell(14, "Назначенный срок");
        createHeaderSubCell(15, "Дата выполнения");
        mergeHeaderCallVertically(16);
        mergeHeaderCallVertically(17);
        mergeHeaderCellHorizontally(table,0,9,11);
        mergeHeaderCellHorizontally(table,0,12,13);
        mergeHeaderCellHorizontally(table,0,14,15);
    }

    private void createBodyTable(Long countItems, Record record) throws ParseException {
        row=table.createRow();
        createBodyCellWithText(0, String.valueOf(countItems));
        createBodyCellWithText(1, fromUnixTimeStamp(record.getEntryDate()));
        createBodyCellWithText(2, record.getCtp().getProductType().getName());
        createBodyCellWithText(3, record.getProductName());
        createBodyCellWithText(4, record.getCtp().getConsumer().getName());
        createBodyCellWithText(5, record.getSolutions());
        createBodyCellWithText(6, record.getActNumber());
        for (Defect defect : record.getDefects()){
            createBodyCellWithText(7, defect.getCause());
            createBodyCellWithText(8, defect.getSprClassDefect().getName());
        }
        for(DefectSolution defectSolution : record.getDefectSolutions()){
            createBodyCellWithText(9, defectSolution.getMeasures());
            createBodyCellWithText(10, defectSolution.getAppointmentDate());
            createBodyCellWithText(11, defectSolution.getCompleteDate());
        }
        createBodyCellWithText(12, record.getRecoveryProduct().getAppointmentRecoveryDate());
        createBodyCellWithText(13, record.getRecoveryProduct().getCompleteRecoveryDate());
        createBodyCellWithText(14, record.getShipment().getAppointmentDate());
        createBodyCellWithText(15, record.getShipment().getCompleteDate());
        createBodyCellWithText(16, record.getUser());
        if(record.getChekedReklamacia() != null && record.getChekedReklamacia() == 1L){
            createBodyCellWithText(17, "Да");
        } else {
            createBodyCellWithText(17, "Нет");
        }
    }

    private void createFirstHeaderCell(String text){
        cell.setColor("E2E2E2");
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);

        XWPFParagraph paragraph = cell.getParagraphs().get(0);
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        paragraph.setIndentationLeft(0);
        paragraph.setIndentationRight(0);
        paragraph.setIndentationHanging(0);

        XWPFRun run = paragraph.createRun();
        run.setBold(true);
        run.setFontSize(10);
        run.setText(text);
        run.setFontFamily("Times New Roman");

        CTTcPr tcpr = cell.getCTTc().addNewTcPr();
        CTVMerge vMerge=tcpr.addNewVMerge();
        vMerge.setVal(STMerge.RESTART);
    }

    private void createHeaderEmptyCell(){
        cell=row.createCell();
        CTTcPr tcpr = cell.getCTTc().addNewTcPr();
        CTVMerge vMerge=tcpr.addNewVMerge();
        vMerge.setVal(STMerge.RESTART);
    }

    private void createHeaderEmptyCell(String text){
        cell=row.createCell();
        createFirstHeaderCell(text);
    }

    private void createHeaderSubCell(int id, String text){
        cell=row.getCell(id);
        createFirstHeaderCell(text);
    }

    private void mergeHeaderCallVertically(int id){
        cell=row.getCell(id);
        CTTcPr tcpr = cell.getCTTc().addNewTcPr();
        CTVMerge vMerge=tcpr.addNewVMerge();
        vMerge.setVal(STMerge.CONTINUE);
    }

    private void mergeHeaderCellHorizontally(XWPFTable table, int row, int fromCol, int toCol) {
        for(int colIndex = fromCol; colIndex <= toCol; colIndex++){
            CTHMerge hmerge = CTHMerge.Factory.newInstance();
            if(colIndex == fromCol){
                hmerge.setVal(STMerge.RESTART);
            } else {
                hmerge.setVal(STMerge.CONTINUE);
            }
            XWPFTableCell cell = table.getRow(row).getCell(colIndex);
            CTTcPr tcPr = cell.getCTTc().getTcPr();
            if (tcPr != null) {
                tcPr.setHMerge(hmerge);
            } else {
                tcPr = CTTcPr.Factory.newInstance();
                tcPr.setHMerge(hmerge);
                cell.getCTTc().setTcPr(tcPr);
            }
        }
    }

    private void createBodyCellWithText(int position, String text){
        cell=row.getCell(position);
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.TOP);

        XWPFParagraph paragraph = cell.getParagraphs().get(0);
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        paragraph.setIndentationLeft(0);
        paragraph.setIndentationRight(0);
        paragraph.setIndentationHanging(0);

        XWPFRun run = paragraph.createRun();
        run.setBold(false);
        run.setFontSize(10);
        run.setText(text);
        run.addBreak();
        run.setFontFamily("Times New Roman");

        CTTcPr tcpr = cell.getCTTc().addNewTcPr();
        CTVMerge vMerge=tcpr.addNewVMerge();
        vMerge.setVal(STMerge.RESTART);
    }
}

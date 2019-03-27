package com.study.excel.controller;

import com.study.excel.util.DateFormatThreadLocal;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("/export")
public class ExportController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ExportController.class);

    @Autowired
    private BankingService bankingService;

    @RequestMapping("/excel")
    @CrossOrigin("http://www.kotak.com:9090")
    public void exportExcel(@RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
                            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date endDate,
                            HttpServletResponse response) {

        Date nowDate = new Date();
        BankOpenCloseBalanceDto bankOpenCloseBalanceDto = bankingService.handlerBankOpenCloseBalance(startDate, endDate, nowDate);
        List<Banking> bankingList = bankingService.queryListByWhere(bankOpenCloseBalanceDto.getStartDate(), bankOpenCloseBalanceDto.getEndDate(), nowDate);

        String fileName = "Report-" + DateFormatUtils.formatDateToString(nowDate);
        try {
            response.setHeader("Content-type", "application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + ".xlsx");
            this.getExport(response.getOutputStream(), bankingList, bankOpenCloseBalanceDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将生成的Excel写入到输出流里面
     *
     * @param out
     */
    private void getExport(OutputStream out, List<Banking> list, BankOpenCloseBalanceDto bankOpenCloseBalanceDto) {
        // 1.创建Excel工作薄对象
        SXSSFWorkbook wb = new SXSSFWorkbook();
        // 2.创建Excel工作表对象
        SXSSFSheet sheet = wb.createSheet();
        // 3.创建单元格
        CellStyle cellStyle = wb.createCellStyle();
        // 4.设置单元格的样式
//        cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        SXSSFRow row;
        Banking bank;
        // 5. 设置前12行表头信息
        row = sheet.createRow(0);
        row.createCell(2).setCellStyle(cellStyle);
        row.createCell(2).setCellValue("Account Statement");

        row = sheet.createRow(1);
        row.createCell(0).setCellValue("SHABRI INVESTMENTS PRIVATE LIMITED");

        row = sheet.createRow(2);
        row.createCell(4).setCellValue("Cust. Reln. No.");
        row.createCell(5).setCellValue("3030***73");

        row = sheet.createRow(3);
        row.createCell(4).setCellValue("Account No.");
        row.createCell(5).setCellValue("312928320");

        row = sheet.createRow(4);
        row.createCell(4).setCellValue("Period");
        row.createCell(5).setCellValue("From " + DateFormatUtils.formatDateToString(bankOpenCloseBalanceDto.getStartDate(), "dd/MM/yyyy")
                + " To " + DateFormatUtils.formatDateToString(bankOpenCloseBalanceDto.getEndDate(), "dd/MM/yyyy"));


        row = sheet.createRow(5);
        row.createCell(0).setCellValue("NO 1-1 3TH FLOOR VINAYAKA");
        row.createCell(4).setCellValue("Currency");
        row.createCell(5).setCellValue("INR");

        row = sheet.createRow(6);
        row.createCell(0).setCellValue("TOWERS 1ST CROSS GANDHINAGAR");
        row.createCell(4).setCellValue("Branch");
        row.createCell(5).setCellValue("NEW DELHI - PREET VIHA");

        row = sheet.createRow(7);
        row.createCell(0).setCellValue(".");
        row.createCell(4).setCellValue("Nomination Regd");
        row.createCell(5).setCellValue("N");

        row = sheet.createRow(8);
        row.createCell(0).setCellValue("BENGALURU-560009");
        row.createCell(4).setCellValue("Nominee Name");
        row.createCell(5).setCellValue("");

        row = sheet.createRow(9);
        row.createCell(0).setCellValue("KARNATAKA, INDIA");

        row = sheet.createRow(11);
        row.createCell(0).setCellValue("Sl. No.");
        row.createCell(1).setCellValue("Date");
        row.createCell(2).setCellValue("Description");
        row.createCell(3).setCellValue("Chq / Ref number");
        row.createCell(4).setCellValue("Amount");
        row.createCell(5).setCellValue("Dr / Cr");
        row.createCell(6).setCellValue("Balance");
        row.createCell(7).setCellValue("Dr / Cr");

        for (int i = 0; i < list.size(); i++) {
            bank = list.get(i);
            // 5.创建单元格的行
            row = sheet.createRow(i + 12);
            // 6.设置单元格属性值和样式
            row.createCell(0).setCellStyle(cellStyle);
            row.createCell(0).setCellValue(i + 1);
            row.createCell(1).setCellStyle(cellStyle);
            row.createCell(1).setCellValue(DateFormatThreadLocal.format(bank.getStartDate()));
            row.createCell(2).setCellStyle(cellStyle);
            row.createCell(2).setCellValue(bank.getDescrioton());
            row.createCell(3).setCellStyle(cellStyle);
            row.createCell(3).setCellValue(bank.getRefNumber());
            row.createCell(4).setCellStyle(cellStyle);
            row.createCell(4).setCellValue(bank.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            row.createCell(5).setCellStyle(cellStyle);
            row.createCell(5).setCellValue(bank.getType());
            row.createCell(6).setCellStyle(cellStyle);
            row.createCell(6).setCellValue(bank.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            row.createCell(7).setCellStyle(cellStyle);
            row.createCell(7).setCellValue(bank.getTypes());

        }
        // 8. 设置附加单元格信息
        for (int i = 0; i < 4; i++) {
            row = sheet.createRow(12 + list.size() + i + 1);

            if (i == 0) {
                row.createCell(0).setCellStyle(cellStyle);
                row.createCell(0).setCellValue("Opening balance");
                // as on 25/11/2018   INR 152,670.46
                row.createCell(1).setCellStyle(cellStyle);
                row.createCell(1).setCellValue("as on " + DateFormatUtils.formatDateToString(bankOpenCloseBalanceDto.getStartDate(), "dd/MM/yyyy")
                        + "   INR " + bankOpenCloseBalanceDto.getOpeningBalance());
            }

            if (i == 1) {
                row.createCell(0).setCellStyle(cellStyle);
                row.createCell(0).setCellValue("Closing balance");
                // as on 25/11/2018   INR 152,670.46
                row.createCell(1).setCellStyle(cellStyle);
                row.createCell(1).setCellValue("as on " + DateFormatUtils.formatDateToString(bankOpenCloseBalanceDto.getEndDate(), "dd/MM/yyyy")
                        + "   INR " + bankOpenCloseBalanceDto.getClosingBalance());
            }

            if (i == 2) {
                row.createCell(0).setCellStyle(cellStyle);
                row.createCell(0).setCellValue("Call 1800 102 6022 24 Hrs. Toll Free or email at service.bank@kotak.com");
            }

            if (i == 3) {
                row.createCell(0).setCellStyle(cellStyle);
                row.createCell(0).setCellValue("Write to us at Customer Contact Centre. Kotak Mahindra Bank Ltd. Post Box Number 16344, Mumbai 400 013");
            }
        }
        try {
            // 8.将Excel写入到输出流里面
            wb.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

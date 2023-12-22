package elenapltnkv;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FilesParsingTest {
    ClassLoader cl = FilesParsingTest.class.getClassLoader();
    @Test
    public void openReadPDFfile() throws Exception {
        open("https://yandex.ru/company/prospectus");
        File downloadPdf = $("a[href='https://company-docs.s3.yandex.net/prospectus/" +
                "%D0%A1%D0%BE%D0%BE%D0%B1%D1%89%D0%B5%D0%BD%D0%B8%D0%B5_%D0%BE_%D0%BF%D0%BE%D1%80%D1%8F%D0%B4%D0%BA%" +
                "D0%B5_%D0%B4%D0%BE%D1%81%D1%82%D1%83%D0%BF%D0%B0_%D0%BA_%D0%B8%D0%BD%D1%84%D0%BE%D1%80%D0%BC%" +
                "D0%B0%D1%86%D0%B8%D0%B8_17_11_2023.pdf']").download();

        PDF content = new PDF(downloadPdf);
        assertThat(content.author).contains("КонсультантПлюс");

    }

    @Test
    public void openReadEXLSTest() throws Exception{
        try(InputStream resourceAsStream= cl.getResourceAsStream("Contact list.xlsx")){
            XLS content = new XLS(resourceAsStream);
            assertThat(content.excel.getSheetAt(0).getRow(11).getCell(5).getStringCellValue()).contains("Philadelphia");
            System.out.println();
        }


    }
    //TO DO csv zip
}



package elenapltnkv;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import elenapltnkv.model.Dic;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
    public void openReadEXLSTest() throws Exception {
        try (InputStream resourceAsStream = cl.getResourceAsStream("Contact list.xlsx")) {
            XLS content = new XLS(resourceAsStream);
            assertThat(content.excel.getSheetAt(0).getRow(11).getCell(5).getStringCellValue()).contains("Philadelphia");
            System.out.println();
        }


    }

    //TODO csv zip
    @Test
    public void openReadCSVTest() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("UserTurnoversRegistry.csv");
                CSVReader reader = new CSVReader(new InputStreamReader(resource))
        ) {
            List<String[]> contents = reader.readAll();
            assertThat(contents.get(0)[0]).contains("Date");
            System.out.println();
        }

    }

    @Test
    public void openReadZIPTest() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("UserTurnoversRegistry.csv.zip");
                ZipInputStream zis = new ZipInputStream(resource)
        ) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                assertThat(entry.getName()).isEqualTo("20231221.UserTurnoversRegistry.csv");
            }
        }

    }

    @Test
    public void gsonParseTest() throws Exception {
        Gson gson = new Gson();
        try (
                InputStream resource = cl.getResourceAsStream("dic.json");
                InputStreamReader reader = new InputStreamReader(resource)
        ) {
           JsonObject jsonObject= gson.fromJson(reader, JsonObject.class);
           assertThat(jsonObject.get("name").getAsString()).isEqualTo("emt2");
        }
    }

    @Test
    public void gsonParseImproveTest() throws Exception {
        Gson gson = new Gson();
        try (
                InputStream resource = cl.getResourceAsStream("dic.json");
                InputStreamReader reader = new InputStreamReader(resource)
        ) {
            Dic jsonObject= gson.fromJson(reader, Dic.class);
            assertThat(jsonObject.id).isEqualTo(0);
            assertThat(jsonObject.name).isEqualTo("emt2");
            assertThat(jsonObject.type).isEqualTo("CARD");
        }
    }

}




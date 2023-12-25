package elenapltnkv;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DzTest {
    //TODO Запаковать в zip архив несколько разных файлов - pdf, xlsx
    //TODO– Положить его в ресурсы
    //TODO– Реализовать чтение и проверку содержимого каждого файла из архива
    ClassLoader ld = DzTest.class.getClassLoader();

    @Test
    public void openPDFtest() throws Exception {
        ZipFile zf = new ZipFile(new File("src/test/resources/к_празднику.zip"));
        ZipInputStream is = new ZipInputStream(ld.getResourceAsStream("к_празднику.zip"));
        ZipEntry entry;
        while((entry = is.getNextEntry()) != null) {
            org.assertj.core.api.Assertions.assertThat(entry.getName()).isEqualTo("к_празднику.pdf");
            try (InputStream inputStream = zf.getInputStream(entry)) {
                PDF content = new PDF(inputStream);
                assertThat(content.text).contains("ТОРТ ЧАРОДЕЙКА");
            }
        }
    }

    @Test
    public void openReadPDFfile() throws Exception {
        File simplePdf = new File("src/test/resources/к_празднику.pdf");
        PDF content = new PDF(simplePdf);
        assertThat(content.text).contains("ТОРТ ЧАРОДЕЙКА");

    }

    //TODO – Реализовать разбор json  файла библиотекой
    // Jackson https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core/2.13.1
    //– Придумать реальный объект и описать его в виде  json
    //– В идеале json должен содержать массив
}




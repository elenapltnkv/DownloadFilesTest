package elenapltnkv;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class SelenideFileTest {
    @Test

    public void downLoadFileTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadFile = $(".eYoABg").download();
        try (InputStream ip = new FileInputStream(downloadFile);) {
            byte[] bytes = ip.readAllBytes();
            String textContents = new String(bytes, StandardCharsets.UTF_8);
            assertThat(textContents).contains("Contributions to JUnit 5 are both welcomed");
            ip.close();
        }
    }

    @Test
    public void selenideUploadFile(){
        open("https://fineuploader.com/demos.html");
        $("input[type='file']").uploadFromClasspath("free-icon-linux-2333464.png");
        $(".qq-upload-file").shouldHave(text("free-icon-linux-2333464.png"));
    }
}

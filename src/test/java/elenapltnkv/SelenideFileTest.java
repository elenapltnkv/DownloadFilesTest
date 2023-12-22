package elenapltnkv;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideFileTest {
    @Test

    public void downLoadFileTest() throws FileNotFoundException {
        open("https://github.com/pytorch/pytorch?tab=readme-ov-file#license");
        File downloadFile = $(By.xpath("//article/p[79]/a")).download();
    }
}

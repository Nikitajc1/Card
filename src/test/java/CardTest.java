import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardTest {

    @Test
    void testCard() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иван");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $(".Success_successBlock__2L3Cw").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void invalidValueName() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("123");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("span.input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void invalidValuePhone() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иван");
        $("[data-test-id=phone] input").setValue("+7999888776644");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone] span.input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void invalidValuePhoneAndNameShouldPointAtName() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Ivan");
        $("[data-test-id=phone] input").setValue("+7999888776644");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name] span.input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldBeRedTextWhenInvalidCheckbox() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иван");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("button").click();
        $("[data-test-id=agreement] span.checkbox__text").getCssValue("color").equals("#ff5c5c");
    }
}

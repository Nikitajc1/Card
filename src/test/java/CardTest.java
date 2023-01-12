import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

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
}

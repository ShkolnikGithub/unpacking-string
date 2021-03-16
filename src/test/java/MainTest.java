import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    //    первый пример из ТЗ
    @Test
    public void firstTest() {
        String expected = "xyzxyzxyzxyxyxyxyz";
        String actual = Main.getResultString("3[xyz]4[xy]z");
        Assert.assertEquals(expected, actual);
    }

    //    второй пример из ТЗ
    @Test
    public void secondTest() {
        String expected = "xxxyxxxy";
        String actual = Main.getResultString("2[3[x]y]");
        Assert.assertEquals(expected, actual);
    }

    //    первый тест на валидность
    @Test
    public void firstCheckTest() {
        Assert.assertFalse(Main.checkString("11111"));
    }

    //    второй тест на валидность
    @Test
    public void secondCheckTest() {
        Assert.assertTrue(Main.checkString("3[x]"));
    }

    //    третий тест на валидность
    @Test
    public void thirdCheckTest() {
        Assert.assertFalse(Main.checkString("3[x"));
    }
}

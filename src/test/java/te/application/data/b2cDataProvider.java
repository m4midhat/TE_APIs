package te.application.data;

import org.testng.annotations.DataProvider;

public class b2cDataProvider {

    @DataProvider(name = "dataForB2C")
    public static Object[][] dataTable() {
        return new Object[][] {
                {"1", "en"},
                {"1", "ru"},
                {"1", "ar"}
        };
    }

}

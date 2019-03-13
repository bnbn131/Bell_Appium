package main.java.com.example.myapplication;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class TestAppium {
    private static WebDriver driver;
    private static AndroidDriver androidDriver;


    //Пример взят из интернета, переделано под необходимые нужды
    @BeforeClass
    public static void setUp() throws MalformedURLException {
        System.out.println("ЗАпуск драйвера");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus_26");

        capabilities.setCapability("appPackage", "com.android.contacts");
        capabilities.setCapability("appActivity", "com.android.contacts.activities.PeopleActivity");
        androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver = androidDriver;

    }

    @AfterClass
    public static void teardown() {
        driver.quit();
        System.out.println("Остановка драйвера");
    }


    @Test
    public void newContactTest() {
        System.out.println("Начало теста на создание");

        System.out.println("Нажата кнопка Создать");
        driver.findElement(By.xpath("//android.widget.ImageButton[@resource-id=\"com.android.contacts:id/floating_action_button\"]")).click();
        System.out.println("Имя");
        WebElement firstname_edittext = driver.findElement(By.xpath("//android.widget.EditText[contains(@text,'First name')]")); // ищем поле ввода по тексту в нем
        firstname_edittext.sendKeys("VALENTINE");

        System.out.println("Фамилия");
        WebElement lastname_edittext = driver.findElement(By.xpath("//android.widget.EditText[contains(@text,'Last name')]"));
        lastname_edittext.sendKeys("VALENTINOV");

        System.out.println("Номер");
        androidDriver.hideKeyboard();
        WebElement phone_edittext = driver.findElement(By.xpath("//android.widget.EditText[contains(@text,'Phone')]"));
        phone_edittext.clear();
        phone_edittext.sendKeys("8-800-555-35-35");

        System.out.println("Сохранить");
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.contacts:id/editor_menu_save_button\"]")).click();

    }

    @Test
    public void delNewContaqtTest() {
        System.out.println("Начало теста на удаление");
        androidDriver.launchApp();
          String nameSearch;

        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.android.contacts:id/menu_search\"]")).click();
        WebElement search = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.android.contacts:id/search_view\"]"));
        nameSearch = "VALENTINE" + " " + "VALENTINOV";
        search.sendKeys(nameSearch);

        driver.findElement(By.xpath("//android.widget.ListView[@resource-id=\"android:id/list\"]"));

        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.android.contacts:id/cliv_name_textview\"]")).click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        System.out.println("Меню");
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='More options']")).click();

        System.out.println("Удаление");
        driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Delete')]")).click();
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();


    }
}
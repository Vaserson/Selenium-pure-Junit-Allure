package com.github.vaserson.selenium_demo_automation.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Epic("Demo Page SeleniumBase Tests")
@Feature("Selenium Base functionality Verification")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DemoTests {
    static WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @BeforeAll
    static void setUp() {
        driver.navigate().to("https://seleniumbase.io/demo_page");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    @Test
    @Order(1)
    @Story("Verify text input field")
    @Description("Enter text into the input field and verify the value")
    @DisplayName("Input field should accept text")
    void inputFieldTest() {
        WebElement textInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myTextInput")));
        textInput.sendKeys("Hello World!");

        Assertions.assertEquals("Hello World!", textInput.getAttribute("value"));
    }

    @Test
    @Order(2)
    @Story("Verify textarea field")
    @Description("Enter multiline text into the textarea and verify the value")
    @DisplayName("Textarea should accept multiline text")
    void textAreaTest() {
        WebElement textArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myTextarea")));
        textArea.sendKeys("Hello World!\nHello World!\nHello World!");

        Assertions.assertEquals("Hello World!\nHello World!\nHello World!", textArea.getAttribute("value"));
    }

    @Test
    @Order(3)
    @Story("Verify Pre-Filled Text Field")
    @Description("Enter text into Pre-Filled Text Field and verify the value")
    @DisplayName("Pre-Filled Text Field should accept text")
    void preFilledTextFieldTest() {
        SoftAssertions softAssertion = new SoftAssertions();
        WebElement textField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myTextInput2")));
        softAssertion.assertThat(textField.getAttribute("value")).isEqualTo("Text...");
        textField.clear();
        textField.sendKeys("Hello World!!!");
        softAssertion.assertThat(textField.getAttribute("value")).isEqualTo("Hello World!!!");
        softAssertion.assertAll();
    }

    @Test
    @Order(4)
    @Story("Verify text input field with placeholder")
    @Description("Enter text into the input field with placeholder and verify the value")
    @DisplayName("Input field with placeholder should accept text")
    void inputFieldWithPlaceholderTest() {
        WebElement textInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("placeholderText")));
        textInput.sendKeys("Hello World!");
        Assertions.assertEquals("Hello World!", textInput.getAttribute("value"));
    }

    @Test
    @Order(5)
    @Story("Verify text input field with placeholder")
    @Description("Enter text into the input field with placeholder and verify the value")
    @DisplayName("Input field with placeholder should accept text")
    void hoverDropdownTest() {
        SoftAssertions softAssertion = new SoftAssertions();
        WebElement h3Title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3")));
        softAssertion.assertThat(h3Title.getText()).isEqualTo("Automation Practice");
        WebElement hoverDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myDropdown")));
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverDropdown);
        actions.perform();
        WebElement dropdownOption1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dropOption1")));
        dropdownOption1.click();
        softAssertion.assertThat(h3Title.getText()).isEqualTo("Link One Selected");

        actions.moveToElement(hoverDropdown);
        actions.perform();
        WebElement dropdownOption2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dropOption2")));
        dropdownOption2.click();
        softAssertion.assertThat(h3Title.getText()).isEqualTo("Link Two Selected");

        actions.moveToElement(hoverDropdown);
        actions.perform();
        WebElement dropdownOption3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dropOption3")));
        dropdownOption3.click();
        softAssertion.assertThat(h3Title.getText()).isEqualTo("Link Three Selected");

        hoverDropdown.click();
        softAssertion.assertThat(h3Title.getText()).isEqualTo("Automation Practice");
        softAssertion.assertAll();
    }

    @Test
    @Order(6)
    @Story("UI Behavior: Button changes text and color indicators")
    @Description("Verify that clicking the button updates its text, a read-only input field value, and a paragraph's text to reflect the new color.")
    @DisplayName("Button click should update color-related texts to Purple")
    void buttonChangesTextsTest() {
        SoftAssertions softAssertion = new SoftAssertions();

        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myButton")));
        WebElement readOnlyTextField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("readOnlyText")));
        WebElement paragraphText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pText")));

        softAssertion.assertThat(button.getText()).isEqualTo("Click Me (Green)");
        softAssertion.assertThat(readOnlyTextField.getAttribute("value")).isEqualTo("The Color is Green");
        softAssertion.assertThat(paragraphText.getText()).isEqualTo("This Text is Green");

        button.click();
        softAssertion.assertThat(button.getText()).isEqualTo("Click Me (Purple)");
        softAssertion.assertThat(readOnlyTextField.getAttribute("value")).isEqualTo("The Color is Purple");
        softAssertion.assertThat(paragraphText.getText()).isEqualTo("This Text is Purple");

        softAssertion.assertAll();
    }

    @Test
    @Order(7)
    @Story("UI Slider Interaction")
    @Description("Verify that clicking different positions on the slider changes the progress bar value accordingly.")
    @DisplayName("Slider should update progress bar value on click")
    void inputSliderTest() {
        SoftAssertions softAssertion = new SoftAssertions();

        WebElement inputSlider = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mySlider")));
        Dimension sliderSize = inputSlider.getSize();
        Point sliderPosition = inputSlider.getLocation();

        WebElement progressBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("progressBar")));
        softAssertion.assertThat(progressBar.getAttribute("value")).isEqualTo("50");

        int xPosition = sliderPosition.x + (sliderSize.width / 10 * 2);
        int yPosition = sliderPosition.y + sliderSize.height / 2;

        Actions actions = new Actions(driver);
        actions.moveToLocation(xPosition, yPosition)
                .click()
                .perform();

        softAssertion.assertThat(progressBar.getAttribute("value")).isEqualTo("20");

        xPosition = sliderPosition.x + (sliderSize.width / 10 * 8);
        yPosition = sliderPosition.y + sliderSize.height / 2;

        actions.moveToLocation(xPosition, yPosition)
                .click()
                .perform();

        softAssertion.assertThat(progressBar.getAttribute("value")).isEqualTo("80");

        softAssertion.assertAll();
    }

    @Test
    @Order(8)
    @Story("Dropdown Interaction and Progress Meter Update")
    @Description("Verify that selecting different options from a dropdown menu updates the meter bar and its label accordingly.")
    @DisplayName("Dropdown selection should correctly update the meter value")
    void selectDropdownTest() {
        SoftAssertions softAssertion = new SoftAssertions();

        WebElement selectDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mySelect")));
        WebElement meterLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("meterLabel")));
        softAssertion.assertThat(meterLabel.getText()).contains("25");
        Select select = new Select(selectDropdown);
        select.selectByIndex(3);

        WebElement meterBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("meterBar")));
        softAssertion.assertThat(meterBar.getAttribute("value")).isEqualTo("1");
        softAssertion.assertThat(meterLabel.getText()).contains("100");

        select.selectByIndex(2);
        softAssertion.assertThat(meterBar.getAttribute("value")).isEqualTo("0.75");
        softAssertion.assertThat(meterLabel.getText()).contains("75");

        select.selectByIndex(1);
        softAssertion.assertThat(meterBar.getAttribute("value")).isEqualTo("0.5");
        softAssertion.assertThat(meterLabel.getText()).contains("50");

        select.selectByIndex(0);
        softAssertion.assertThat(meterBar.getAttribute("value")).isEqualTo("0.25");
        softAssertion.assertThat(meterLabel.getText()).contains("25");

        softAssertion.assertAll();
    }

    @Test
    @Order(9)
    @Story("iFrame Interaction and Element Visibility")
    @Description("Verify that text inside an iframe can be read correctly and that elements outside the iframe are not visible while inside.")
    @DisplayName("Switching to iframe should isolate visibility to its content only")
    void iFrameGettingTextTest() {
        SoftAssertions softAssertion = new SoftAssertions();
        WebElement iFrameLabelText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Image in iFrame:']")));
        softAssertion.assertThat(iFrameLabelText.isDisplayed()).isTrue();
        driver.switchTo().frame(1);
        WebElement iFrameText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4")));
        softAssertion.assertThat(iFrameText.getText()).isEqualTo("iFrame Text");

        List<WebElement> elementsOutside = driver.findElements(By.xpath("//*[text()='Image in iFrame:']"));
        softAssertion.assertThat(elementsOutside).as("Element from outside iframe should not be found inside iframe").isEmpty();

        driver.switchTo().defaultContent();

        elementsOutside = driver.findElements(By.xpath("//*[text()='Image in iFrame:']"));
        softAssertion.assertThat(elementsOutside).as("Being outside iframe should found element").hasSize(1);

        softAssertion.assertAll();
    }

    @Test
    @Order(10)
    @Story("Drag and Drop Functionality")
    @Description("Ensure that an image can be dragged from one drop area to another and back, verifying its visibility after each action.")
    @DisplayName("Image should be draggable between drop areas")
    void dragAndDropTest() {
        SoftAssertions softAssertion = new SoftAssertions();
        WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkBox1")));
        checkbox.click();

        WebElement draggableImage1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='drop1']/img")));
        WebElement dragArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("drop1")));
        WebElement dropArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("drop2")));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(draggableImage1, dropArea).perform();

        WebElement draggableImage2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='drop2']/img")));

        softAssertion.assertThat(draggableImage2.isDisplayed()).isTrue();

        actions.dragAndDrop(draggableImage2, dragArea).perform();

        softAssertion.assertThat(draggableImage1.isDisplayed()).isTrue();

        softAssertion.assertAll();
    }

    @Test
    @Order(11)
    @Story("Checkbox State Validation Across Main Page and iFrame")
    @Description("Verify that checkboxes on the main page and inside an iFrame can be selected and deselected, and their state is accurately reflected.")
    @DisplayName("Checkboxes should reflect correct selection state on interaction")
    void checkboxesTest() {
        SoftAssertions softAssertion = new SoftAssertions();
        WebElement checkbox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkBox2")));
        softAssertion.assertThat(checkbox2.isSelected()).isFalse();
        checkbox2.click();
        softAssertion.assertThat(checkbox2.isSelected()).isTrue();
        WebElement checkbox3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkBox3")));
        softAssertion.assertThat(checkbox3.isSelected()).isFalse();
        checkbox3.click();
        softAssertion.assertThat(checkbox3.isSelected()).isTrue();
        WebElement checkbox4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkBox4")));
        softAssertion.assertThat(checkbox4.isSelected()).isFalse();
        checkbox4.click();
        softAssertion.assertThat(checkbox4.isSelected()).isTrue();

        driver.switchTo().frame("myFrame3");
        WebElement checkbox6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkBox6")));
        softAssertion.assertThat(checkbox6.isSelected()).isFalse();
        checkbox6.click();
        softAssertion.assertThat(checkbox6.isSelected()).isTrue();
        driver.switchTo().defaultContent();

        WebElement checkbox5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkBox5")));
        softAssertion.assertThat(checkbox5.isSelected()).isTrue();
        checkbox5.click();
        softAssertion.assertThat(checkbox5.isSelected()).isFalse();

        softAssertion.assertAll();
    }

    @Test
    @Order(12)
    @Story("Radio Button Selection Behavior")
    @Description("Verify that selecting one radio button deselects the previously selected one, and the correct state is maintained.")
    @DisplayName("Radio buttons should switch selection correctly")
    void radioButtonsTest() {
        SoftAssertions softAssertion = new SoftAssertions();
        WebElement radioButton1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("radioButton1")));
        WebElement radioButton2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("radioButton2")));

        softAssertion.assertThat(radioButton1.isSelected()).isTrue();
        softAssertion.assertThat(radioButton2.isSelected()).isFalse();

        radioButton2.click();

        softAssertion.assertThat(radioButton1.isSelected()).isFalse();
        softAssertion.assertThat(radioButton2.isSelected()).isTrue();

        softAssertion.assertAll();
    }

    @Test
    @Order(13)
    @Story("Navigation and Page Verification")
    @Description("Verify that clicking navigation links opens the correct pages and the browser returns to the original page successfully.")
    @DisplayName("Navigation links should open correct URLs and titles")
    void navigationTest() {
        SoftAssertions softAssertion = new SoftAssertions();

        WebElement link1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("seleniumbase.com")));
        WebElement link2;
        WebElement link3;
        WebElement link4;

        link1.click();

        softAssertion.assertThat(driver.getCurrentUrl()).isEqualTo("https://seleniumbase.com/");
        softAssertion.assertThat(driver.getTitle()).isEqualTo("SeleniumBase - Web Automation & Testing with Python");

        driver.navigate().back();
        link2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("seleniumbase.io")));
        link2.click();

        softAssertion.assertThat(driver.getCurrentUrl()).isEqualTo("https://seleniumbase.io/");
        softAssertion.assertThat(driver.getTitle()).isEqualTo("SeleniumBase Docs");

        driver.navigate().back();
        link3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("on GitHub")));
        link3.click();

        softAssertion.assertThat(driver.getCurrentUrl()).isEqualTo("https://github.com/seleniumbase/SeleniumBase");
        softAssertion.assertThat(driver.getTitle()).isEqualTo("GitHub - seleniumbase/SeleniumBase: Python APIs for web automation, testing, and bypassing bot-detection.");

        driver.navigate().back();
        link4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Demo Page")));
        link4.click();

        softAssertion.assertThat(driver.getCurrentUrl()).isEqualTo("https://seleniumbase.io/demo_page/");
        softAssertion.assertThat(driver.getTitle()).isEqualTo("Web Testing Page");

        softAssertion.assertAll();
    }


}

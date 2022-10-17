import com.github.javafaker.Faker;

import config.BaseTest;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTest extends BaseTest {

    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            phone = faker.phoneNumber().subscriberNumber(10),
            currentAddress = faker.address().fullAddress(),
            filename = "mem.jpg",
            filepath = "src/test/resources/files/",
            gender,
            year,
            month,
            day,
            subject,
            hobbies,
            state,
            city;

    Integer yearInt,
            mouthInt,
            dayInt,
            intSubjects,
            intHobbies,
            intState;

    @Test
    void checkStudentRegistrationFormTest() {
        open("https://demoqa.com/automation-practice-form");
        //Выбираем пол рандомно
        final String[] genderArray = {"Male", "Female", "Other"};
        Random random = new Random();
        int index = random.nextInt(genderArray.length);
        gender = genderArray[index];

        //Генерируем случайный год
        yearInt = Utils.generateRandomInt(1900, 2100);
        year = Integer.toString(yearInt);

        //Генерируем случайный месяц
        final String[] mouthArray = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        mouthInt = random.nextInt(mouthArray.length);
        month = mouthArray[mouthInt];

        //Генерируем случайный день
        dayInt = Utils.generateRandomInt(1, 28);
        day = Integer.toString(dayInt);
        //Проверяем число - если в числе меньше двух разрядов, то добавляем в строку 0
        if (dayInt < 10) {
            day = "0" + day;
        }

        //Выбираем случайный subject
        final String[] subjectsArray = {"Computer Science", "Math", "Arts", "Biology"};
        intSubjects = random.nextInt(genderArray.length);
        subject = subjectsArray[intSubjects];

        //Выбираем хобби
        final String[] hobbiesArray = {"Sports", "Reading", "Music"};
        intHobbies = random.nextInt(hobbiesArray.length);
        hobbies = hobbiesArray[intHobbies];

        //Выбираем штат
        final String[] stateArray = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        intState = random.nextInt(stateArray.length);
        state = stateArray[intState];

        //В зависимости от штата выбираем город
        city = Utils.getCityByState(state);

        //Заполняем поля
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(this.gender)).click();
        $("#userNumber").setValue(phone);

        //Работа с календарем
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--0" + day + "").click();

        //Заполняем тему и хобби
        $("#subjectsInput").setValue(subject).pressEnter();
        $("#hobbiesWrapper").$(byText(hobbies)).click();

        //Загрузка файла
        $("#uploadPicture").uploadFile(new File(filepath + filename));

        //Заполнение адреса
        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();

        //Отправляем анкету
        $("#submit").click();

        //Проверяем заполнение анкеты
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        Utils.checkTable("Student name", firstName + " " + lastName);
        Utils.checkTable("Student Email", email);
        Utils.checkTable("Gender", this.gender);
        Utils.checkTable("Mobile", phone);
        Utils.checkTable("Date of Birth", day + " " + month + "," + year);
        Utils.checkTable("Subjects", subject);
        Utils.checkTable("Hobbies", hobbies);
        Utils.checkTable("Picture", filename);
        Utils.checkTable("Address", currentAddress);
        Utils.checkTable("State and City", state + " " + city);

    }

    @Test
    void negativeCheckStudentRegistrationFormTest() {
        open("https://demoqa.com/automation-practice-form");
        //Выбираем пол рандомно
        final String[] genderArray = {"Male", "Female", "Other"};
        Random random = new Random();
        int index = random.nextInt(genderArray.length);
        gender = genderArray[index];

        //Генерируем случайный год
        yearInt = Utils.generateRandomInt(1900, 2100);
        year = Integer.toString(yearInt);

        //Генерируем случайный месяц
        final String[] mouthArray = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        mouthInt = random.nextInt(mouthArray.length);
        month = mouthArray[mouthInt];

        //Генерируем случайный день
        dayInt = Utils.generateRandomInt(1, 28);
        day = Integer.toString(dayInt);
        //Проверяем число - если в числе меньше двух разрядов, то добавляем в строку 0
        if (dayInt < 10) {
            day = "0" + day;
        }

        //Выбираем случайный subject
        final String[] subjectsArray = {"Computer Science", "Math", "Arts", "Biology"};
        intSubjects = random.nextInt(genderArray.length);
        subject = subjectsArray[intSubjects];

        //Выбираем хобби
        final String[] hobbiesArray = {"Sports", "Reading", "Music"};
        intHobbies = random.nextInt(hobbiesArray.length);
        hobbies = hobbiesArray[intHobbies];

        //Выбираем штат
        final String[] stateArray = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        intState = random.nextInt(stateArray.length);
        state = stateArray[intState];

        //В зависимости от штата выбираем город
        city = Utils.getCityByState(state);

        //Заполняем поля
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(this.gender)).click();
        $("#userNumber").setValue(phone);

        //Работа с календарем
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--0" + day + "").click();

        //Заполняем тему и хобби
        $("#subjectsInput").setValue(subject).pressEnter();
        $("#hobbiesWrapper").$(byText(hobbies)).click();

        //Загрузка файла
        $("#uploadPicture").uploadFile(new File(filepath + filename));

        //Заполнение адреса
        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();

        //Отправляем анкету
        $("#submit").click();

        //Проверяем заполнение анкеты
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        Utils.checkTable("Student name", firstName + " " + lastName);
        Utils.checkTable("Student Email", email);
        Utils.checkTable("Gender", this.gender);
        Utils.checkTable("Mobile", phone);
        Utils.checkTable("Date of Birth", day + " " + month + "," + year);
        Utils.checkTable("Subjects", subject);
        Utils.checkTable("Hobbies", hobbies);
        Utils.checkTable("Picture", filename);
        Utils.checkTable("Address", currentAddress);
        Utils.checkTable("State and City", state + " " + city);

    }


}

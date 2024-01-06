package utils;

import dao.Greid;
import entity.*;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

@UtilityClass
public class TestDataImporter {

    public static void importData(SessionFactory sessionFactory) {
        @Cleanup Session session= sessionFactory.openSession();
        session.beginTransaction();
        Category category1 = saveCategory(session, "Питьевая вода");
        Category category2 = saveCategory(session, "Конфеты");
        Category category3 = saveCategory(session, "Мясо");
        Category category4 = saveCategory(session, "Unknown");
        Category category5 = saveCategory(session, "categoryName1");

        Ranks rank1 = saveRank(session,Greid.MANAGER,80_000L);
        Ranks rank2 = saveRank(session,Greid.EMPLOYEE,50_000L);


        Suppliers supplierOne = saveSupplier(session, "Horns&&Hoofs", "Perm, commi 35", "horn@gmail.com",
                "8-988-342-65-98");
        Suppliers supplierTwo = saveSupplier(session, "Beaks&&Feathers", "Unknown", "beak@gmail.com",
                "1-111-111-11-99");
        Suppliers supplierThree = saveSupplier(session, "Noses&&Tails", "Kitezh", "nose@yandex.ru",
                "8-455-876-23-21");

        saveUser(session, "Imir", LocalDate.parse("1995-01-11"), "111", Status.MANAGER);
        saveUser(session, "Vadim", LocalDate.parse("1984-08-12"), "334", Status.ADMIN);
        saveUser(session, "Ivan", LocalDate.parse("1984-11-09"), "56789", Status.ADMIN);
        saveUser(session, "Evgeniy", LocalDate.parse("1990-09-09"), "333", Status.ADMIN);
        saveUser(session, "Ivan", LocalDate.parse("1995-01-01"), "999", Status.ADMIN);

        saveProduct(session, supplierOne, "Шоколад", 5000L, 150L, category2);
        saveProduct(session, supplierOne, "horns", 700L, 100L, category1);
        saveProduct(session, supplierThree, "Мясо", 125L, 500L, category3);

        saveEmployee(session, "Ivanov", "Oleg", "Ivanovich", LocalDate.parse("1992-11-21"),
                "8-911-432-89-61", "Moskow, Novyi Arbat 22, kv.2555",rank1);
        saveEmployee(session, "Sergeev", "Kirill", "Antonovich", LocalDate.parse("1994-09-11"),
                "8-925-444-89-17", "Moskow, Komissarov 125, kv.800",rank2);
        saveEmployee(session, "Иванов", "Сергей", "Вадимович", LocalDate.parse("1990-05-17"),
                "8-933-555-55-55", "Владивосток, Красная 54, кв.3421",rank1);
        saveEmployee(session, "TTT", "VVV", "FFF", LocalDate.parse("2011-01-01"),
                "888", "RRR",rank2);
        saveEmployee(session, "Filatov", "Evgeniy", "Vasilievich", LocalDate.parse("1994-12-02"),
                "8(936)-512-38-74", "Ectb, Stroitiley 125, d 14, corp4, kv 98",rank1);
        saveEmployee(session, "Tolstoy", "Lev", "Anatolievich", LocalDate.parse("1905-05-19"),
                "8(977)-55538-55", "SPB",rank1);
        saveEmployee(session, "Filatov", "Ivan", "Vasilievich", LocalDate.parse("1975-03-02"),
                "8(936)-512-38-74", "Ectb, Stroitiley 125, d 14, corp4, kv 98",rank2);
        saveEmployee(session, "Tunov", "Vadim", "Sadyikov", LocalDate.parse("1990-03-29"),
                "8-992-456-91-00", "Vladivostok, veteranov 54,d 6,corp 9, kv 100",rank2);
        saveEmployee(session, "Tunov", "Leonid", "Sadyikov", LocalDate.parse("1990-03-29"),
                "8-992-555-10-00", "Vladivostok, veteranov 54,d 6,corp 9, kv 100",rank2);
        saveEmployee(session, "Pletnev", "Dmitriy", "Olegovich", LocalDate.parse("1974-04-04"),
                "8(936)-512-55-99", "MSO",rank2);
        saveEmployee(session, "Pletnev", "Dmitriy", "Olegovich", LocalDate.parse("1974-05-05"),
                "8(936)-512-55-99", "MSO",rank2);

        saveProduct(session,supplierOne,"Шоколад",1L,2L,category2);
        saveProduct(session,supplierOne,"horns",1L,2L,category1);
        saveProduct(session,supplierOne,"Мясо",1L,2L,category3);
       session.getTransaction().commit();


    }

    private static Category saveCategory(Session session, String categoryName) {
        Category category = Category.builder()
                .categoryName(categoryName)
                .build();
        session.persist(category);
        return category;
    }

    private static Ranks saveRank(Session session, Greid rankName, Long salary) {
        Ranks rank = Ranks.builder()
                .rankName(Greid.valueOf(rankName.name()))
                .salary(salary)
                .build();
        session.persist(rank);
        return rank;
    }

    private static Employees saveEmployee(Session session, String lastName,
                                          String name, String middleName,
                                          LocalDate dateBirth, String phoneNumber,
                                          String address, Ranks rank) {
        Employees employee = Employees.builder()
                .lastName(lastName)
                .name(name)
                .middleName(middleName)
                .dateBirth(dateBirth)
                .phoneNumber(phoneNumber)
                .address(address)
                .rank(rank)
                .build();
        session.persist(employee);
        return employee;
    }

    private static Products saveProduct(Session session, Suppliers supplier, String name,
                                        Long count, Long priceForOne, Category category) {
        Products product = Products.builder()
                .supplier(supplier)
                .name(name)
                .count(count)
                .priceForOne(priceForOne)
                .category(category)
                .build();

        session.persist(product);
        return product;
    }

    private static Suppliers saveSupplier(Session session, String name, String address,
                                          String email, String phoneNumber) {
        Suppliers supplier = Suppliers.builder()
                .name(name)
                .address(address)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
        session.persist(supplier);
        return supplier;
    }

    private static User saveUser(Session session, String name, LocalDate birthday,
                                 String password, Status status) {
        User user = User.builder()
                .name(name)
                .birthday(birthday)
                .password(password)
                .status(status)
                .build();
        session.persist(user);
        return user;
    }


//        Company microsoft = saveCompany(session, "Microsoft");
//        Company apple = saveCompany(session, "Apple");
//        Company google = saveCompany(session, "Google");
//
//        User billGates = saveUser(session, "Bill", "Gates",
//                LocalDate.of(1955, Month.OCTOBER, 28), microsoft);
//        User steveJobs = saveUser(session, "Steve", "Jobs",
//                LocalDate.of(1955, Month.FEBRUARY, 24), apple);
//        User sergeyBrin = saveUser(session, "Sergey", "Brin",
//                LocalDate.of(1973, Month.AUGUST, 21), google);
//        User timCook = saveUser(session, "Tim", "Cook",
//                LocalDate.of(1960, Month.NOVEMBER, 1), apple);
//        User dianeGreene = saveUser(session, "Diane", "Greene",
//                LocalDate.of(1955, Month.JANUARY, 1), google);
//
//        savePayment(session, billGates, 100);
//        savePayment(session, billGates, 300);
//        savePayment(session, billGates, 500);
//
//        savePayment(session, steveJobs, 250);
//        savePayment(session, steveJobs, 600);
//        savePayment(session, steveJobs, 500);
//
//        savePayment(session, timCook, 400);
//        savePayment(session, timCook, 300);
//
//        savePayment(session, sergeyBrin, 500);
//        savePayment(session, sergeyBrin, 500);
//        savePayment(session, sergeyBrin, 500);
//
//        savePayment(session, dianeGreene, 300);
//        savePayment(session, dianeGreene, 300);
//        savePayment(session, dianeGreene, 300);
//    }
//
//    private Company saveCompany(Session session, String name) {
//        Company company = Company.builder()
//                .name(name)
//                .build();
//        session.save(company);
//
//        return company;
//    }
//
//    private User saveUser(Session session,
//                          String firstName,
//                          String lastName,
//                          LocalDate birthday,
//                          Company company) {
//        User user = User.builder()
//                .username(firstName + lastName)
//                .personalInfo(PersonalInfo.builder()
//                        .firstname(firstName)
//                        .lastname(lastName)
//                        .birthDate(new Birthday(birthday))
//                        .build())
//                .company(company)
//                .build();
//        session.save(user);
//
//        return user;
//    }
//
//    private void savePayment(Session session, User user, Integer amount) {
//        Payment payment = Payment.builder()
//                .receiver(user)
//                .amount(amount)
//                .build();
//        session.save(payment);

}
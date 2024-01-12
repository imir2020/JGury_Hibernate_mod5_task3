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
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        Category category1 = saveCategory(session, "Питьевая вода");
        Category category2 = saveCategory(session, "Конфеты");
        Category category3 = saveCategory(session, "Мясо");
        Category category4 = saveCategory(session, "Unknown");
        Category category5 = saveCategory(session, "categoryName1");

        Ranks rank1 = saveRank(session, Greid.MANAGER, 80_000L);
        Ranks rank2 = saveRank(session, Greid.EMPLOYEE, 50_000L);


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

        Products product1 = saveProduct(session, supplierOne, "Шоколад", 5000L, 150L, category2);
        Products product2 = saveProduct(session, supplierOne, "horns", 700L, 100L, category1);
        Products product3 = saveProduct(session, supplierThree, "Мясо", 125L, 500L, category3);

        Employees employee1 = saveEmployee(session, "Ivanov", "Oleg", "Ivanovich", LocalDate.parse("1992-11-21"),
                "8-911-432-89-61", "Moskow, Novyi Arbat 22, kv.2555", rank1);
        Employees employee2 = saveEmployee(session, "Sergeev", "Kirill", "Antonovich", LocalDate.parse("1994-09-11"),
                "8-925-444-89-17", "Moskow, Komissarov 125, kv.800", rank2);
        Employees employee3 = saveEmployee(session, "Иванов", "Сергей", "Вадимович", LocalDate.parse("1990-05-17"),
                "8-933-555-55-55", "Владивосток, Красная 54, кв.3421", rank1);
        saveEmployee(session, "TTT", "VVV", "FFF", LocalDate.parse("2011-01-01"),
                "888", "RRR", rank2);
        saveEmployee(session, "Filatov", "Evgeniy", "Vasilievich", LocalDate.parse("1994-12-02"),
                "8(936)-512-38-74", "Ectb, Stroitiley 125, d 14, corp4, kv 98", rank1);
        saveEmployee(session, "Tolstoy", "Lev", "Anatolievich", LocalDate.parse("1905-05-19"),
                "8(977)-55538-55", "SPB", rank1);
        saveEmployee(session, "Filatov", "Ivan", "Vasilievich", LocalDate.parse("1975-03-02"),
                "8(936)-512-38-74", "Ectb, Stroitiley 125, d 14, corp4, kv 98", rank2);
        saveEmployee(session, "Tunov", "Vadim", "Sadyikov", LocalDate.parse("1990-03-29"),
                "8-992-456-91-00", "Vladivostok, veteranov 54,d 6,corp 9, kv 100", rank2);
        saveEmployee(session, "Tunov", "Leonid", "Sadyikov", LocalDate.parse("1990-03-29"),
                "8-992-555-10-00", "Vladivostok, veteranov 54,d 6,corp 9, kv 100", rank2);
        saveEmployee(session, "Pletnev", "Dmitriy", "Olegovich", LocalDate.parse("1974-04-04"),
                "8(936)-512-55-99", "MSO", rank2);
        saveEmployee(session, "Pletnev", "Dmitriy", "Olegovich", LocalDate.parse("1974-05-05"),
                "8(936)-512-55-99", "MSO", rank2);

        saveProduct(session, supplierOne, "Шоколад", 1L, 2L, category2);
        saveProduct(session, supplierOne, "horns", 1L, 2L, category1);
        saveProduct(session, supplierOne, "Мясо", 1L, 2L, category3);

        saveSales(session, product1,24L,employee1,LocalDate.parse("2022-05-22"));
        saveSales(session, product2,125L,employee3,LocalDate.parse("2022-06-10"));
        saveSales(session, product3,77L,employee2,LocalDate.parse("2022-07-11"));

        saveOrder(session,supplierOne,"Шоколад", 200L,150L,LocalDate.parse("2022-04-12"));
        saveOrder(session,supplierOne,"Мясо", 100L,350L,LocalDate.parse("2022-05-11"));
        saveOrder(session,supplierOne,"Бутил. вода", 200L,150L,LocalDate.parse("2022-06-19"));

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

    private static Sales saveSales(Session session, Products product,
                                   Long count, Employees employee,
                                   LocalDate dateSales) {
        Sales sales = Sales.builder()
                .product(product)
                .count(count)
                .employee(employee)
                .dateSales(dateSales)
                .build();
        session.persist(sales);
        return sales;
    }

    private static Orders saveOrder(Session session, Suppliers suppliers,
                                    String nameProduct, Long countProduct,
                                    Long priceProduct, LocalDate dateOrder){
        Orders order = Orders.builder()
                .supplier(suppliers)
                .nameProduct(nameProduct)
                .countProduct(countProduct)
                .priceProduct(priceProduct)
                .dateOrder(dateOrder)
                .build();
        session.persist(order);
        return order;
    }
}
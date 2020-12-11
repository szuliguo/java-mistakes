package org.geekbang.time.commonmistakes.java8.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Legal
 * @date 2020/12/8
 *
 * 1. https://www.cnblogs.com/clover-toeic/p/10906824.html
 * 2. https://www.cnblogs.com/yizhiamumu/p/8999482.html
 */
public class Person {
    private String name;
    private String gender;
    private int age;
    private Location location;

    public Person() {
    }
    public Person(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public void setName(String name) { this.name = name; }
    public void setGender(String gender) { this.gender = gender; }
    public void setAge(int age) { this.age = age; }
    public Person setLocation(String country, String city) {
        this.location = new Location(country, city);
        return this;
    }

    public String getName() { return name; }
    public String getGender() { return gender; }
    public int getAge() { return age; }
    public Location getLocation() { return location; }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + '}';
    }

    public void greeting(Person person) {
        System.out.println("Hello " + person.getName() + "!");
    }

    public static void showIdentity(Person person) {
        System.out.println("Person: {" + "name='" + person.getName() + '\'' + ", gender='"
                + person.getGender() + '\'' + ", age=" + person.getAge() + '}');
    }


    private String fromCountry(final String country) {
        return "from " + country;
    }
    private String fromNowhere() {
        return "from Nowhere";
    }

    private Person getPersionById(Integer id) {
        return null;
    }


    //map + orElse
    public static String inWhichCityLowercase(final Person person) {
        return Optional.ofNullable(person)
                .map(Person::getLocation)
                .map(Location::getCity)
                .map(String::toLowerCase)
                .orElse("nowhere");
    }

    //map + orElseThrow
    public static String inWhichCountryUppercase(final Person person) {
        return Optional.ofNullable(person)
                .map(Person::getLocation)
                .map(Location::getCountry)
                .map(String::toUpperCase)
                .orElseThrow(NoSuchElementException::new);
        // 或orElseThrow(() -> new NoSuchElementException("No country information"))
    }

    //map + orElseGet
    private static String fromWhere(final Person person) {
        return Optional.ofNullable(person)
                .map(Person::getLocation)
                .map(Location::getCountry)
                .map(person::fromCountry)
                .orElseGet(person::fromNowhere);
    }

    //map + filter + ifPresent
    public static void introduceChinese(final Person person) {
        Optional.ofNullable(person)
                .map(Person::getLocation)
                .map(Location::getCountry)
                .filter("China"::equals)
                .ifPresent(Location::introduce);
    }

    //Optional + Stream
    private static void optionalWithStream() {
        Stream<String> names = Stream.of("Zhou Yi", "Wang Er", "Wu San");
        Optional<String> preWithL = names
                .filter(name -> name.startsWith("Wang"))
                .findFirst();
        preWithL.ifPresent(name -> {
            String u = name.toUpperCase();
            System.out.println("Get " + u + " with family name Wang!");
        });
    }

    // ifPresent
    private void testIfPresent() {

        Optional<Person> person = Optional.ofNullable(getPersionById(0));
        person.ifPresent(p -> System.out.println("person name is : " + p.getName()));
    }


    public static void main(String[] args) {
        optionalWithStream();
        // 输出：Get WANG ER with family name Wang!

        Person person = new Person(); //fetchPersonFromSomewhereElse()
        System.out.println(fromWhere(person));
        // 输出：from Nowhere

        List<Person> personList = new ArrayList<>();
        Person mike = new Person("mike", "male", 10).setLocation("China", "Nanjing");
        personList.add(mike);
        System.out.println(inWhichCityLowercase(mike));
        // 输出：nanjing

        Person lucy = new Person("lucy", "female", 4);
        personList.add(lucy);
        personList.forEach(lucy::greeting);
        // 输出：Hello mike!\nHello lucy!
        // 注意，此处仅为展示object::instanceMethod写法

        personList.forEach(Person::showIdentity);
        // 输出：Person: {name='mike', gender='male', age=10}
        //      Person: {name='lucy', gender='female', age=4}
        personList.forEach(Person::introduceChinese);
        // 输出：I'm from China.
        System.out.println(inWhichCountryUppercase(lucy));
        // 输出：Exception in thread "main" java.util.NoSuchElementException
        //          at java.util.Optional.orElseThrow(Optional.java:290)
        //          at com.huawei.vmf.adapter.inventory.OptionalDemo.inWhichCountryUppercase(OptionalDemo.java:47)
        //          at com.huawei.vmf.adapter.inventory.OptionalDemo.main(OptionalDemo.java:108)


    }







}
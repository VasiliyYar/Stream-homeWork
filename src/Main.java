import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main (String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

       Long count = persons.stream()
                .filter(x -> x.getAge()<18)
                .count();
        System.out.println("Количество несовершеннолетних: " + count);

        List<String> conscript = persons.stream()
                .filter(x ->x.getAge()>18)
                .filter(x ->x.getAge()<27)
                .map(x ->x.getFamily())
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Список фамилий призывников:" + conscript);


        List<String> workPeopleWoman = persons.stream()
                .filter(x -> x.getAge() > 18)
                .filter(x -> x.getSex() == Sex.WOMAN && x.getAge()<60 )
                .filter(x-> x.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(x ->x.getFamily())
                .distinct()
                .collect(Collectors.toList());

        List<String> workPeopleMan = persons.stream()
                .filter(x -> x.getAge() > 18)
                .filter(x -> x.getSex() == Sex.MAN && x.getAge()<65 )
                .filter(x-> x.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(x ->x.getFamily())
                .distinct()
                .collect(Collectors.toList());

        List <String> workPeople = Stream.concat(workPeopleWoman.stream(), workPeopleMan.stream())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Список фамилий работоспособных людей: " + workPeople);




    }
}

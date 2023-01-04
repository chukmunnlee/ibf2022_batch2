package sdf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TaskMain {

    public static void main(String[] args) {

        List<Car> garage = new LinkedList<>();
        Set<String> addressbook = new HashSet<>();
        Map<String, Integer> debts = new HashMap<>();

        //Add cars to garage
        garage.add(new Car());
        garage.add(new Porche());

        System.out.println(garage);

        // Add names to my addressbook
        addressbook.add("fred");
        addressbook.add("barney");
        addressbook.add("wilma");
        addressbook.add("fred");
        System.out.println(addressbook);

        // Add my debtors
        debts.put("fred", 10);
        debts.put("barney", 10);
        debts.put("fred", 100);
        System.out.println(debts);

    }
    
}

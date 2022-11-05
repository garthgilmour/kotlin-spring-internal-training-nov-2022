package com.instil.di.groovy.beans;

import com.instil.di.groovy.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleDataSource {
    public static List<String> buildBannedCards() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("RST456GHI78");
        list.add("UVW456GHI78");
        list.add("XYZ456GHI78");
        return list;
    }

    public int getDiscountAmount() {
        return 500;
    }

    public int getPercentageDiscount() {
        return 10;
    }

    public List<Double> getPrices() {
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(1.20);
        list.add(3.40);
        list.add(5.60);
        return list;
    }

    public Map<String, Integer> buildStockData() {
        HashMap<String, Integer> stockData = new HashMap<String, Integer>();
        stockData.put("ABC123", 10);
        stockData.put("DEF456", 20);
        stockData.put("GHI789", 30);
        return stockData;
    }

    public List<Employee> getStaff() {
        ArrayList<Employee> staff = new ArrayList<Employee>();
        staff.add(new Employee("Dave", 10, 30000.0, 500.0));
        staff.add(new Employee("Jane", 11, 35000.0, 550.0));
        staff.add(new Employee("Fred", 12, 40000.0, 600.0));
        staff.add(new Employee("Mary", 13, 45000.0, 650.0));
        staff.add(new Employee("Peter", 14, 50000.0, 700.0));
        staff.add(new Employee("Sarah", 15, 55000.0, 750.0));
        staff.add(new Employee("Colin", 16, 60000.0, 800.0));
        staff.add(new Employee("Trudy", 17, 65000.0, 850.0));
        return staff;
    }

    public String getOpenDays() {
        return "Monday Tuesday Thursday Saturday";
    }

    public String getCEO() {
        return "Tim Cook";
    }

}

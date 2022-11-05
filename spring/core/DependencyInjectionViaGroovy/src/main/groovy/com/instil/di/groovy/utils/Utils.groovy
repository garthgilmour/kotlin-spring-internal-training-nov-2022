package com.instil.di.groovy.utils

import com.instil.di.groovy.beans.SampleShop

class Utils {
    static void demoShop(SampleShop shop) {
        println("----- Testing ${shop.getShopName()} -----");

        if (shop.isOpenDuringWeekends()) {
            println("\tShop open during weekends");
        } else {
            println("\tShop closed at weekends");
        }

        System.out.println("----- List of Junior Staff -----");
        shop.getJuniorStaff().forEach {
            println("\t ${it}");
        }

        System.out.println("----- List of Senior Staff -----");
        shop.getSeniorStaff().forEach {
            println("\t ${it}");
        }

        println("----- List of Staff Names -----");
        shop.getStaffNames().forEach {
            println("\t ${it}");
        }

        println("----- Trying to make purchase -----");
        if (shop.makePurchase("ABC123", 9, "DEF456GHI78")) {
            println("\tSample purchase succeeded");
        } else {
            println("\tSample purchase failed");
        }
    }
}

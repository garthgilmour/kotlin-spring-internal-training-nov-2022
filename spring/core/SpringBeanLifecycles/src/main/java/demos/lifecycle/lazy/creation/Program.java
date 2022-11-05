package demos.lifecycle.lazy.creation;

import demos.lifecycle.common.Shop;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Program {
    public static void main(String[] args) {
        try (AbstractApplicationContext context = buildContext()) {
            System.out.println("----- Application Context Created -----");
            Shop shop = context.getBean("shop", Shop.class);
            useShop(shop);
        }
    }

    private static void useShop(Shop shop) {
        if (shop.makePurchase("AB123", 20, "DEF456GHI78")) {
            System.out.println("Purchase Succeeded");
        } else {
            System.out.println("Purchase Failed");
        }
    }

    private static AbstractApplicationContext buildContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SampleConfig.class);
        context.refresh();
        return context;
    }
}

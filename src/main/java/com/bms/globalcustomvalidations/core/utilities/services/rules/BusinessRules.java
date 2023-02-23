package com.bms.globalcustomvalidations.core.utilities.services.rules;

import java.util.function.Supplier;

public class BusinessRules {
    public static void run(Runnable... logics) {
        for (var logic : logics) {
            logic.run();
        }
    }

    public static <T> T run(Supplier<T>... logics) {
        for (var logic : logics) {
            return logic.get();
        }
        return null;
    }
}

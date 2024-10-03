package com.example.sonar.rules;

import org.sonar.plugins.java.api.CheckRegistrar;
import org.sonar.plugins.java.api.JavaCheck;

import java.util.Collections;
import java.util.List;

public class NoClickCheckRegistrar implements CheckRegistrar {

    @Override
    public void register(RegistrarContext registrarContext) {
        registrarContext.registerClassesForRepository("com.example.sonar.rules", checkClasses(), Collections.emptyList());
    }

    public static List<Class<? extends JavaCheck>> checkClasses() {
        return List.of(NoClickRule.class);
    }
}

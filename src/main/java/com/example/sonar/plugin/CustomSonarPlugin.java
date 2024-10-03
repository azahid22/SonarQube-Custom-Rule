package com.example.sonar.plugin;

import org.sonar.api.Plugin;
import com.example.sonar.rules.NoClickCheckRegistrar;

public class CustomSonarPlugin implements Plugin {
    @Override
    public void define(Context context) {
        context.addExtension(NoClickCheckRegistrar.class);
    }
}

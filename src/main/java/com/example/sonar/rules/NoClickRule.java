package com.example.sonar.rules;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.Arrays;
import java.util.List;

@Rule(key = "NoClickRule")
public class NoClickRule extends IssuableSubscriptionVisitor {

    // List of prohibited classes
    private static final List<String> PROHIBITED_TYPES = Arrays.asList(
            "org.openqa.selenium.WebDriver",
            "org.openqa.selenium.JavascriptExecutor"
    );

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return List.of(Tree.Kind.METHOD_INVOCATION);
    }

    @Override
    public void visitNode(Tree tree) {
        MethodInvocationTree methodInvocationTree = (MethodInvocationTree) tree;

        // Check if the invoked method is "click"
        if ("click".equals(methodInvocationTree.symbol().name())) {
            String declaringClassName = methodInvocationTree.symbol().owner().type().fullyQualifiedName();

            // Check if the method's owner type is one of the prohibited classes
            if (PROHIBITED_TYPES.contains(declaringClassName)) {
                reportIssue(methodInvocationTree, "Avoid using .click() method on instances of " + declaringClassName);
            }
        }
    }
}

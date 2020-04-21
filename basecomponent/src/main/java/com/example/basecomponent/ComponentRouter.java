package com.example.basecomponent;

public class ComponentRouter {

    public static final String ComponentParams = "ComponentParams";
    public static class ComponentA {

        public static String name = "componenta";

        public static class Action {

            public static String play = "play";
        }
    }

    public static class ComponentB {

        public static String name = "componentb";

        public static class Action {

            public static String basketball = "basketball";
        }
    }
}

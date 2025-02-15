package com.example.myapplication;

public class QuizQuestions {
    public static String questions[] = {
            "Which of the following is the correct way to output 'Hello World' in C++?",
            "What is the correct file extension for a C++ source file?",
            "Which of the following is not a valid access specifier in C++?"
    };

    public static String choices[][] = {
            {
                    "System.out.println(\"Hello World\");",
                    "printf(\"Hello World\");",
                    "cout << \"Hello World\";",
                    "echo \"Hello World\";"
            },
            {
                    ".c",
                    ".cpp",
                    ".cs",
                    ".java"
            },
            {
                    "public",
                    "private",
                    "protected",
                    "package"
            }
    };

    public static String correctAnswers[] = {
            "cout << \"Hello World\";",
            ".cpp",
            "package"
    };
}
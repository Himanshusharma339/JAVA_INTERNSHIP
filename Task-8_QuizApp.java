import java.util.*;

class Question {
    String question;
    String[] options;
    int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class QuizApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Question> quizQuestions = new ArrayList<>();

        // Step 1: Add questions
        quizQuestions.add(new Question("What is the capital of India?",
                new String[]{"Mumbai", "Delhi", "Kolkata", "Chennai"}, 2));

        quizQuestions.add(new Question("Which language is used for Android development?",
                new String[]{"Python", "Swift", "Java", "Ruby"}, 3));

        quizQuestions.add(new Question("Which of these is not a programming language?",
                new String[]{"HTML", "Java", "C++", "Python"}, 1));

        quizQuestions.add(new Question("What does OOP stand for?",
                new String[]{"Object Oriented Programming", "Only Open Projects", "Online Operating Protocol", "Overloaded Operations"}, 1));

        quizQuestions.add(new Question("Which company developed Java?",
                new String[]{"Microsoft", "Apple", "Sun Microsystems", "IBM"}, 3));

        int score = 0;

        // Step 2: Loop through questions
        for (int i = 0; i < quizQuestions.size(); i++) {
            Question q = quizQuestions.get(i);

            System.out.println("\nQ" + (i + 1) + ": " + q.question);
            for (int j = 0; j < q.options.length; j++) {
                System.out.println((j + 1) + ". " + q.options[j]);
            }

            // Step 3: Get user answer
            System.out.print("Enter your answer (1-4): ");
            int userAnswer = sc.nextInt();

            // Step 4: Check answer
            if (userAnswer == q.correctAnswer) {
                System.out.println(" Correct!");
                score++;
            } else {
                System.out.println(" Wrong! Correct answer: " + q.options[q.correctAnswer - 1]);
            }
        }

        // Step 5: Show result
        System.out.println("\n🏁 Quiz Over!");
        System.out.println(" Your Final Score: " + score + " out of " + quizQuestions.size());

        sc.close();
    }
}

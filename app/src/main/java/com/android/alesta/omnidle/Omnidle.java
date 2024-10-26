import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Omnidle {
    public CircularLinkedList<ArrayList<String>> List;
    public Timer time;

    public Omnidle() {
        this.List = new CircularLinkedList<>();
        this.time = new Timer();
    }
    public void initializeTheGame(CircularLinkedList<ArrayList<String>> list) {
        int nodeCount = list.countNodes();
        if (nodeCount == 0) {
            System.out.println("No nodes in the list.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Node<ArrayList<String>> iterator = list.head;

        ArrayList<Boolean> answeredCorrectly = new ArrayList<>(nodeCount);
        ArrayList<Boolean> skippedQuestions = new ArrayList<>(nodeCount);
        for (int i = 0; i < nodeCount; i++) {
            answeredCorrectly.add(false);
            skippedQuestions.add(false);
        }

        int timeLimit = 30;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up! Moving to the next question.");
                scanner.close();
            }
        };
        time.schedule(task, timeLimit * 1000);

        for (int i = 0; i < nodeCount; i++) {
            ArrayList<String> currentQuestion = iterator.data;
            boolean answered = false;

            while (!answered) {
                System.out.println("Letter: " + currentQuestion.get(0));
                System.out.println("Question: " + currentQuestion.get(2));

                System.out.print("Your answer (or type 'pass' to skip): ");
                String userAnswer = scanner.nextLine();

                if (userAnswer.equalsIgnoreCase("pass")) {
                    System.out.println("Skipped this question.");
                    skippedQuestions.set(i, true);
                    answered = true;
                } else if (userAnswer.equals(currentQuestion.get(1))) {
                    System.out.println("Correct!");
                    answeredCorrectly.set(i, true);
                    answered = true;
                } else {
                    System.out.println("Wrong! The correct answer was: " + currentQuestion.get(1));
                    answered = true;
                }
            }

            iterator = iterator.next;
        }

        for (int i = 0; i < nodeCount; i++) {
            if (skippedQuestions.get(i)) {
                ArrayList<String> skippedQuestion = list.getNode(i).data;
                boolean answered = false;

                while (!answered) {
                    System.out.println("Letter: " + skippedQuestion.get(0));
                    System.out.println("Question: " + skippedQuestion.get(2));

                    System.out.print("Your answer (or type 'pass' to skip): ");
                    String userAnswer = scanner.nextLine();

                    if (userAnswer.equalsIgnoreCase("pass")) {
                        System.out.println("Skipped this question again.");
                        answered = true;
                    } else if (userAnswer.equals(skippedQuestion.get(1))) {
                        System.out.println("Correct!");
                        answeredCorrectly.set(i, true);
                        answered = true;
                    } else {
                        System.out.println("Wrong! The correct answer was: " + skippedQuestion.get(1));
                        answered = true;
                    }
                }
            }
        }

        System.out.println("\nSummary of your answers:");
        for (int i = 0; i < nodeCount; i++) {
            if (answeredCorrectly.get(i)) {
                System.out.println("Question " + (i + 1) + ": Correct");
            } else {
                System.out.println("Question " + (i + 1) + ": Incorrect");
            }
        }
        scanner.close();
    }
}

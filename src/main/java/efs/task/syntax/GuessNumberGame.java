package efs.task.syntax;
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
public class GuessNumberGame {

    private final int M;
    private final int toGuess;
    private final int L;
    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        try {
            this.M = Integer.parseInt(argument);
        }catch(NumberFormatException e){
            System.out.println("'" + argument + "' to " + UsefulConstants.WRONG_ARGUMENT + " - to nie liczba");
            throw new IllegalArgumentException();
        }

        if(M < 1 || M > UsefulConstants.MAX_UPPER_BOUND){
            System.out.println(argument + " to " + UsefulConstants.WRONG_ARGUMENT + " - jest spoza zakresu <1, 400>");
            throw new IllegalArgumentException();
        }

        Random rand = new Random();
        this.toGuess = rand.nextInt(M) + 1;
        this.L = (int) (Math.floor(Math.log(M) / Math.log(2)) + 1);
    }

    public void play() {
        //TODO: Implement the method that executes the game session
        Scanner scanner = new Scanner(System.in);
        int trial = 0;
        int userInput;
        String userGuess;
        String[] tab = new String[L];
        Arrays.fill(tab, ".");
        System.out.println("Zgadujesz liczbę z zakresu <1," + M + ">");

        while(true){
            if(trial == L){
                System.out.println(UsefulConstants.UNFORTUNATELY + " wyczerpales " +
                        "liczbe prob " + L + " do odgadniecia liczby"+ toGuess);
                break;
            }
            tab[trial] = "*";
            printAttemptsTable(tab);
            System.out.println(UsefulConstants.GIVE_ME + " liczbę: ");
            userGuess = scanner.next();
            try {
                userInput = Integer.parseInt(userGuess);
            } catch (NumberFormatException e) {
                System.out.println(UsefulConstants.NOT_A_NUMBER);
                scanner.nextLine();
                trial++;
                continue;
            }

            if (userInput > toGuess) {
                System.out.println("to " + UsefulConstants.TO_MUCH);
            } else if (userInput < toGuess) {
                System.out.println("to " + UsefulConstants.TO_LESS);
            } else {
                System.out.println(UsefulConstants.YES + "!");
                System.out.println(UsefulConstants.CONGRATULATIONS + ", " + (trial + 1) +
                        " - tyle prób zajęło Ci odgadnięcie liczby " + " " + toGuess);
                break;
            }
            trial++;
        }
    }

    public void printAttemptsTable(String[] tab){
        System.out.print("Twoje proby: [");
        for(int i = 0; i < tab.length; i++) System.out.print(tab[i]);
        System.out.println("] ");
    }
}

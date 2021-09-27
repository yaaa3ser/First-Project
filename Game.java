
package guess.the.film;
import java.util.*;
import java.io.*;

public class Game {
    public String [] listOfFilms;
    public int guess;
    private String movieGuessed;
    private int pointsLost;
    private String rightLetters;
    private String wrongLetters;
    private boolean gameWon;
    
    //constructor
    public Game() throws Exception{
        File file = new File("films.txt");
        Scanner in = new Scanner (file);
        String films[] = new String[25]; 
        int f=0;
        while(in.hasNextLine()){
            String line = in.nextLine();
            films[f] = line;
            f++;
        }
        
        listOfFilms=films;
    	guess=(int) (Math.random()*(listOfFilms.length));
        movieGuessed = listOfFilms[guess];
        pointsLost=0;
        rightLetters="";
        wrongLetters="";
        gameWon=false;
    }
    //methods
    public String getMovieTitle() {
        return movieGuessed;
    }
    
    public String getWrongLetters() {
        return wrongLetters;
    }
    
    public boolean WonGame() {
        return gameWon;
    }
    
    public String getHiddenMovieTitle() {
        if(rightLetters.equals("")){
            return movieGuessed.replaceAll("[a-zA-Z]", "-");
        }
        else{
            return movieGuessed.replaceAll("[a-zA-Z&&[^" + rightLetters +"]]", "-");
        }
    }
    
    public boolean gameEnded() {
        if (pointsLost >= 10) {
            return true;
        }

        if(!getHiddenMovieTitle().contains("-")) {
            gameWon = true;
            return true;
        }
        return false;
    }
    
    private String inputLetter(){
        System.out.println("Guess a letter:");
        Scanner scanner = new Scanner(System.in);
        String letter = scanner.nextLine().toLowerCase();

        if(!letter.matches("[a-z]")){
            System.out.println("That is not a letter.");
            return inputLetter();
        }
        else if(wrongLetters.contains(letter) || rightLetters.contains(letter)){
            System.out.println("You already guessed that letter.");
            return inputLetter();
        }
        else
            return letter;
    }
    
    public void guessLetter() {
        String guessedLetter = inputLetter();
        if (movieGuessed.toLowerCase().contains(guessedLetter)) 
            rightLetters += guessedLetter ;
        
        else {
            pointsLost++;
            wrongLetters += " " + guessedLetter;
        }
    }
}

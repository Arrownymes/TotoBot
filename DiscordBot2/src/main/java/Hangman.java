import java.util.ArrayList;
import java.lang.*;

public class Hangman {

    private boolean gameFinished;
    private String printable;
    private String linePrint;
    boolean guessed = false;

    public boolean gameOver(){
        if(gameFinished){
            return true;
        }else {
            return false;
        }
    }

    public String hangMan(String received) {


        int d;
        int i;
        int g;
        int score = 0;
        int letter;
        int fails =  0;
        boolean dupe = false;
        ArrayList<Character> guesses;
        int size;

        String secret = new WordGen().getWord();
        String[] blank = new String[secret.length()];
        char[] word = new char[secret.length()];
        StringBuilder sb = new StringBuilder("Current: ");

        //prints #underscores equal to length of secret word and creates array with correct letters.


        for (i = 0; i < secret.length(); i++) {
            blank[i] = ("_");
            sb.append(blank);
            word[i] = secret.charAt(i);
        }


        //Does the following until word is guessed or 10 fails have been counted.
        guesses = new ArrayList<Character>();

            //Creates this iteration's guess (a character).
            String guess = received;

            for (d = 0; d < guesses.size(); d++) {
                if (guess.charAt(0) == guesses.get(d)) {
                    dupe = true;
                    guessed = true;
                }

            }
            if (dupe) {
                fails++;
                dupe = false;
            } else {
                guesses.add(guess.charAt(0));
                //repeats for every letter N in the secret word:
                for (letter = 0; letter < secret.length(); letter++) {
                    //checks the input letter for equality with N
                    if (guess.charAt(0) == word[letter]) {
                        //overwrites the blank template at this position to the guessed letter
                        blank[letter] = String.valueOf(guess.charAt(0));
                        //increases score by 1
                        score = score + 1;
                        guessed = true;
                    }

                }

            }


                linePrint = sb.append(blank).toString();

                if (guessed != true) {
                    fails++;
                    guessed = true;
            }




        //checks for win condition (ie score >= amount of letters

        if (score < secret.length()){
            return toPrint(fails, linePrint);
        }


        if (score >= secret.length()) {
            gameFinished = true;
            return "Well done, you won!";
        } else {
            gameFinished = true;
            return "Unlucky, you lost! \n The secret word was: " + secret;
        }

    }


    String toPrint(int fails, String blank){

        String gallow;

                switch (fails) {
                    case 0:
                        gallow = "\n";
                        break;
                    case 1:
                        gallow = ".______\n";
                        break;
                    case 2:
                        gallow = "\n                    \n                            |\n                            | \n                            |\n                            |\n                            |______\n";
                        break;
                    case 3:
                        gallow = "\n                    \n                        _______\n                            |/\n                            |\n                            |\n                            |\n                            |______\n";
                        break;
                    case 4:
                        gallow = "\n                    \n                            _____=_\n                            |/   |\n                            |\n                            |\n                            |\n                            |______\n";
                        break;
                    case 5:
                        gallow = "\n                    \n                            _____=_\n                            |/   |\n                            |    O\n                            |\n                            |\n                            |______\n";
                        break;
                    case 6:
                        gallow = "\n                    \n                            _____=_\n                            |/   |\n                            |    O\n                            |    #\n                            |\n                            |______\n";
                        break;
                    case 7:
                        gallow = "\n                    \n                            _____=_\n                            |/   |\n                            |   _O\n                            |    #\n                            |\n                            |______\n";
                        break;
                    case 8:
                        gallow = "\n                    \n                            _____=_\n                            |/   |\n                            |   _O_\n                            |    #\n                            |\n                            |______\n";
                        break;

                    case 9:
                        gallow = "\n                    \n                            _____=_\n                            |/   |\n                            |   _O_\n                            |    #\n                            |     )\n                            |______\n";
                        break;

                    case 10:
                        gallow = "\n                    \n                            _____=_\n                            |/   |\n                            |   _O_\n                            |    #\n                            |   ( )\n                            |______\n";
                        break;

                    default:
                        gallow = "no pattern\n";

                }

                printable = gallow + "\n\n" + blank;

        return printable;
    }

}

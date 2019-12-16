import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.nio.channels.Channel;
import java.util.ArrayList;


public class Main extends ListenerAdapter {

    Exam examfunction = new Exam();
    //Hangman Hangman = new Hangman;
    boolean tractorArray = false;
    boolean chameleonOpen = false;
    boolean inLobby = false;
    boolean inGame = false;
    boolean showTime = true;
    boolean chameleonGuess = false;
    String gameLeader = "";
    ArrayList<User> players = new ArrayList<User>();
    hub Chameleon = new hub();
    String[] gameData;
    int chameleonNr;
    String chameleonChannel = "";


    void sleep(long a) {
        try {
            Thread.sleep(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    void bot() throws LoginException {
        JDABuilder builder = new JDABuilder();
        String token = "NjI2NjkwOTEzMjU4ODMxODcy.XYxxgw.8eov57b5yF47JPm3LVwdyH3J_PA";
        builder.setToken(token);
        builder.addEventListeners(new Main());
        builder.build();

    }

    @Override

    public void onMessageReceived(MessageReceivedEvent event) {

        MessageChannel C = event.getChannel();

        String author = event.getAuthor().toString();
        String channel = C.toString();
        String message = (event.getMessage().getContentRaw());

        System.out.println("We received a message from: " + author + ": " + "'" + message + "'" + " in " + channel);


        if (!event.getAuthor().isBot()) {

            //ping
            if (message.equals("/ping")) {
                C.sendMessage("I'm not into ping pong, thanks...-_-").queue();
            }


            //GAMES
            //Hangman
            /*if (message.equals("/Hangman")) {
                C.sendMessage("Starting Hangman...").queue();
                Hangman.hangMan(event);

                while (!Hangman.gameOver()){
                    C.sendMessage(event.getMessage().getContentRaw());
                }

            }*/


            //Chameleon
            if (message.equals("/chameleon")) {
                chameleonChannel = channel;
                if (inLobby) {
                    C.sendMessage("Currently in a lobby, you can join by using /join, or wait for the next round").queue();
                } else {
                    if (inGame) {
                        C.sendMessage("Already doing a game, please wait for the current round to end before starting a new one. The game organiser can end the game by using /endChameleon").queue();
                    } else {
                        inLobby = true;
                        gameLeader = author;
                        System.out.println("chameleon started");
                        C.sendMessage("Welcome to Chameleon! For this game, you'll need at least two other players, I'll open up a queue for you; anyone that wishes to join the game can do so with /join. If everyone is ready, you can use /beginChameleon to start the game.").queue();
                        chameleonOpen = true;
                    }
                }
            }

            if (channel.equals(chameleonChannel)) {

                //Chameleon join room
                if (message.equals("/join") && chameleonOpen) {
                    boolean inthere = false;
                    for (User player : players) {
                        if (author.equals(player.toString())) {
                            inthere = true;
                            break;
                        }
                    }
                    if (!inthere) {
                        players.add(event.getAuthor());
                        C.sendMessage("Added user!").queue();
                        C.sendMessage("Current amount of players: " + players.size()).queue();
                    } else {
                        C.sendMessage("You already joined! What do you want?! More space for your ego perhaps?...").queue();
                    }
                }

                //Chameleon game
                if (message.equals("/beginChameleon") && author.equals(gameLeader)) {

                    int minPlayers = 3;
                    int guessTime = 20;

                    if (players.size() < minPlayers) {
                        C.sendMessage("Current amount of players: " + players.size()).queue();
                        C.sendMessage("You need at least " + minPlayers + " people to play this game, how about you go find some friends first... -_-").queue();
                    } else {
                        if (!inLobby) {
                            C.sendMessage("Can only start a game whilst a lobby!").queue();
                        } else {
                            if (inGame) {
                                C.sendMessage("Already doing a game, please wait for the current round to end before starting a new one. The game organiser can end the game by using /endChameleon").queue();
                            } else {

                                //The game begins
                                chameleonOpen = false;
                                inGame = true;
                                inLobby = false;
                                C.sendMessage("Beginning Chameleon...").queue();
                                sleep(500);
                                gameData = Chameleon.play();
                                C.sendMessage(gameData[0]).queue();


                                chameleonNr = (int) (Math.random() * players.size());

                                for (int i = 0; i < players.size(); i++) {
                                    if (i == chameleonNr) {
                                        players.get(i).openPrivateChannel().queue(Channel -> {
                                            Channel.sendMessage(":lizard: You're the chameleon! Try to stay under the radar by " +
                                                    "cleverly picking a description that makes it look like you know this round's word. " +
                                                    "Good luck!").queue();
                                        });
                                    } else {
                                        players.get(i).openPrivateChannel().queue(Channel -> {
                                            Channel.sendMessage("This round's word is: " + gameData[1] + ", good luck!").queue();
                                        });

                                    }
                                }

                                C.sendMessage(".\n\nEach one of you has received their roles. Now it's up to " + (players.size() - 1) +
                                        " of you to each find a word fitting of the chosen item, but not so obvious that it will give the item away " +
                                        "to the chameleon. You have " + guessTime + " seconds to think of something, after which you will say the words in descending order" +
                                        " starting at the person on top in the voice channel, good luck!").queue();
                                sleep(guessTime * 1000 - 3000);
                                C.sendMessage("3").queue();
                                sleep(1000);
                                C.sendMessage("2").queue();
                                sleep(1000);
                                C.sendMessage("1").queue();
                                sleep(1000);
                                C.sendMessage("Go!").queue();
                                sleep(8000);
                                C.sendMessage("Now, everybody should be done around now, time for the discussion phase. Who do you think is the chameleon?" +
                                        " once it has been decided, the game leader can type /reveal to show the chameleon.").queue();
                                showTime = true;
                            }
                        }
                    }
                }


                //If the previous message unlocked the Chameleonguess, they can now try
                if (chameleonGuess && author.equals(players.get(chameleonNr).toString())) {
                    chameleonGuess = false;
                    System.out.println(message);
                    System.out.println((gameData[1].trim()));
                    if (message.equals(gameData[1].trim())) {
                        C.sendMessage("Correct!").queue();
                    } else {
                        C.sendMessage("Ding! Dong! Your opinion is wrong!... the word was: " + gameData[1].trim()).queue();
                    }
                    chameleonOpen = false;
                    inGame = false;
                    inLobby = false;
                    gameLeader = "";
                    players.clear();
                }


                //Chameleon end
                if (message.equals("/reveal") && author.equals(gameLeader) && showTime) {
                    showTime = false;
                    C.sendMessage("This rounds chameleon was: ").queue();
                    sleep(1000);
                    C.sendMessage(players.get(chameleonNr).getEffectiveAvatarUrl() + " :lizard:").queue();
                    sleep(1000);
                    C.sendMessage("And chameleon, if you haven't won already, what was the word according to you? (Mind the spelling)").queue();
                    chameleonGuess = true;
                }


                //Chameleon end
                if (message.equals("/endChameleon") && author.equals(gameLeader)) {
                    chameleonOpen = false;
                    inGame = false;
                    inLobby = false;
                    C.sendMessage("Okay then, ciao!").queue();
                    sleep(500);
                    gameLeader = "";
                    players.clear();
                }
            }













            //Obi wan
            if (message.contains("hello there") || message.contains("Hello there")) {
                C.sendMessage("General Kenobi!").queue();
                C.sendMessage("https://lumiere-a.akamaihd.net/v1/images/General-Grievous_c9df9cb5.jpeg?region=0%2C0%2C1200%2C675&width=960").queue();
            }
            //neko judge
            if (message.contains("n!neko")) {
                C.sendMessage("Rlly?...").queue();
                sleep(2000);
                C.sendMessage("f*cking weeb...-_-").queue();
            }
            //tractor
            if (message.contains("/ExamQ4")){
                String link = examfunction.getTractor();
                C.sendMessage(link).queue();
            }
            //100 tractors call //TODO disable timer on the active time
            if (message.contains("/ExamQ4-3")){
                C.sendMessage("https://media.giphy.com/media/IfyjWLQMeF6kbG2r0z/giphy.gif").queue();
                C.sendMessage("this question was kinda overkill; continue?").queue();
                C.sendMessage("send ''SteunOnzeBoeren'' to continue").queue();
                tractorArray = true;
            }
            //100 tractors confirm
            if ((message.contains("SteunOnzeBoeren"))&&(tractorArray)){
                tractorArray = false;
                for(int i=0; i<100;i++) {
                    String link = examfunction.getTractor();
                    C.sendMessage(link).queue();
                }

            }
            //D20
            if (message.contains("/d20")) {
                long roll20 = Math.round(Math.random() * 20);
                System.out.println(roll20);

                if (roll20 == 20) {
                    C.sendMessage("Noice, nat. 20!").queue();
                } else {
                    if (roll20 <= 1) {
                        C.sendMessage("Rip... natural 1...").queue();
                    } else {
                        C.sendMessage(Integer.toString((int)roll20)).queue();
                    }
                }
            }
            //PADORU
            if (message.contains("hashire") || message.contains("Hashire")) {

                C.sendMessage("Hashire sori yo...").queue();
                sleep(2000);
                C.sendMessage("Kaze no you ni...").queue();
                sleep(2000);
                C.sendMessage("Tsukumihara wo...").queue();
                sleep(2000);
                C.sendMessage("PADORU PADORUUUUUU!!! :christmas_tree:").queue();
            }
            //Communist propaganda
            if (message.indexOf("/glorify") == 0) {
                System.out.println("Salut");
                String input = message.substring(9, message.length());
                System.out.println(input);

                String output = input.replace("I ", "We ");
                output = output.replace(" my", " our");
                output = output.replace("My", "Our");
                output = output.replace(" your", "our");
                output = output.replace("Your", "Our");
                output = output.replace(" his", " our");
                output = output.replace("His", "Our");
                output = output.replace(" her", " our");
                output = output.replace("Her", "Our");
                output = output.replace(" friend", " comrade");
                output = output.replace(" hello", " salut!");
                output = output.replace("Hello", "Salut!");
                output = output.replace("mine", "ours");
                output = output.replace("capitalist", "swine");
                output = output.replace("capitalism", "treason");


                System.out.println(output);

                C.sendMessage(output).queue();
            }
            //test command only accessible to me
            if (message.indexOf("/say")==0){
                if (author.equals("U:PbQuinn(223856229766660097)")){
                    C.sendMessage(message.substring(4,message.length())).queue();
                }else{
                    C.sendMessage("The bot has evaluated your life.... and found worth nothing").queue();
                }

            }
            
        }


    }





    public static void main(String[] args) throws LoginException {new Main().bot();}
}

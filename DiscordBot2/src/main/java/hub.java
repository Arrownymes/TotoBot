public class hub {


    String[] play() {
        cardPicker picker = new cardPicker();
        cardPrinter printer = new cardPrinter();
        String[][] card = picker.getCard(Math.random());
        String[] toPrint = new String[2];
        String sendCard;
        String secretWord = card[1][(int) (Math.random() * card[1].length)];
        toPrint[0] = printer.printable(card);
        toPrint[1] = secretWord;
        return (toPrint);
    }

    public static void main(String[] args) {
        hub h = new hub();
        h.play();
    }
}

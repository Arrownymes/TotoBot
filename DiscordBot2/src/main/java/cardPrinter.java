public class cardPrinter {

    private StringBuilder stringBuilder = new StringBuilder();

    String printable(String[][] Card){

        stringBuilder.delete(0,stringBuilder.length());
        stringBuilder.append(Card[0][0]+"\n"); //Adds title to the print job.
        String[] wordList = Card[1];
        int lengthMax = 0;

        //Get spacing nicely even
        for(int i = 0; i < wordList.length; i++){
            if(wordList[i].length() > lengthMax){
                lengthMax = wordList[i].length();
            }
        }

        for(int i = 0; i < wordList.length; i++){
            if (i % 4 == 0){
                stringBuilder.append("\n\n");
            }
            stringBuilder.append(wordList[i]); //adds the actual word
            for (int j = 0; j < lengthMax-wordList[i].length(); j++){
                stringBuilder.append(" "); //fills leftover space in column
            }
            stringBuilder.append("      ");

        }


        String printable = stringBuilder.toString();
        return printable;
    }

}

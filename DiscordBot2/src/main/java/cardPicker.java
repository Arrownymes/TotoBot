public class cardPicker {

    //The various cards; every card is an array consisting of two arrays:
    //First array: Card title
    //Second array: 16 objects of choice

    String[][][] cardList = {
            {{"Movies"},{"Jurassic Park", "Star Wars", "The Godfather", "The Avengers",
                    "Harry Potter", "Pirates of the Caribbean", "Transformers", "Batman",
                    "Fast and the Furious", "Lord of the Rings", "Back to the Future", "Rocky",
                    "Terminator", "Star Trek", "Matrix", "The Hobbit",}},

            {{"Foods"},{"Hamburger", "Sushi", "Lasagna", "Taco",
                    "Curry", "Ramen", "Pancakes", "Pizza",
                    "Tomato Soup", "Barbecue", "Fries", "Fruit Salad",
                    "Meatballs", "Toast", "Sausages", "Ice Cream"}},

            {{"Games"},{"League of Legends", "World of Warships", "Starcraft II", "Overwatch",
                    "Hearthstone", "Final Fantasy", "Slay the Spire", "Enter the Gungeon",
                    "Dark Souls", "Skyrim", "Call of Duty", "Sid Meier's Civilization",
                    "Anno", "ARK Survival Evolved", "Borderlands", "The Sims"}},

            {{"Popular Anime"},{"Sword Art Online", "One Piece", "Bleach", "Naruto",
                    "One Punch Man", "Boku No Hero Academy", "Stein's Gate", "Demon Slayer",
                    "Pokemon", "Jojo's Bizarre Adventures", "Konosuba", "Gundam",
                    "Re:Zero", "No Game No Life", "Overlord", "Code Geass"}},

            {{"Boozes"},{"Mead", "Beer", "Whiskey", "Ouzo",
                    "Wine", "Cider", "Ale", "Wodka",
                    "Bacardi", "Martini", "Rum", "Gin",
                    "Sake", "Liquor", "Tequilla", "Stout"}},

            {{"League of Legends Champions"},{"Ahri", "Teemo", "Yasuo", "Garen",
                    "Malphite", "Ashe", "Blitzcrank", "Zed",
                    "Sejuani", "Graves", "Annie", "Xayah",
                    "Viktor", "Vel'Koz", "Rumble", "Alistar"}},

            {{"Multinational Companies"},{"Nike", "Red Bull", "MacDonald's", "Tesla",
                    "Monster", "Google", "Blizzard Entertainment", "Apple",
                    "Samsung", "Sony", "Walt Disney", "Heineken",
                    "Lamborghini", "PayPal", "Facebook", "BBC"}},

            {{"Family Games"},{"Monopoly", "Chess", "Twister", "Risk",
                    "Jenga", "The Settlers of Catan", "Cluedo", "Checkers",
                    "Mousetrap", "Hungry Hungry Hippos", "Game of Life", "Battleship",
                    "Mahjong", "Stratego", "Scrabble", "UNO"}},

            {{"Deities"},{"Zeus", "Ra", "Osiris", "Odin",
                    "Tyr", "Heimdall", "Apollo", "Allah",
                    "Oghma", "Hermes", "Mars", "Buddha",
                    "Hera", "Jupiter", "Wodan", "Hades"}},

            {{"Countries of the world"},{"Argentina", "Italy", "Russia", "Zimbabwe",
                    "Mexico", "Ireland", "Japan", "Australia",
                    "South Korea", "United States", "Canada", "Poland",
                    "Germany", "Egypt", "United Kingdom", "Sweden"}}
    };

    public String[][] getCard(double random){
        return cardList[(int)((random*cardList.length))];
    }
}

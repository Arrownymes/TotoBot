public class WordGen {

    private String wordChoice;
    private String[] List;

    void Word() {

                List = new String[]{
                        "aatrox", "ahri", "akali", "alistar", "amumu", "anivia", "annie",
                        "ashe", "aurelionsol", "azir", "bard", "blitzcrank", "brand", "braum",
                        "caitlyn", "camille", "cassiopeia", "cho'gath", "corki", "darius", "diana",
                        "drmundo", "draven", "ekko", "elise", "evelynn", "ezreal", "fiddlesticks",
                        "fiora", "fizz", "galio", "gangplank", "garen", "gnar", "gragas", "graves",
                        "hecarim", "heimerdinger", "illaoi", "irelia", "ivern", "janna", "jarvaniv",
                        "jax", "jayce", "jhin", "jinx", "kai'sa", "kalista", "karma", "karthus", "kassadin",
                        "katarina", "kayle", "kayn", "kennen", "kha'zix", "kindred", "kled", "kog'maw", "leblanc",
                        "leesin", "leona", "lissandra", "lucian", "lulu", "lux", "malphite", "malzahar", "maokai",
                        "masteryi", "miss fortune", "mordekaiser", "morgana", "nami", "nasus", "nautilus", "neeko",
                        "nidalee", "nocturne", "nunu&willump", "olaf", "orianna", "ornn", "pantheon", "poppy", "pyke",
                        "qiyana", "quinn", "rakan", "rammus", "rek'sai", "renekton", "rengar", "riven", "rumble", "ryze",
                        "sejuani", "shaco", "shen", "shyvana", "singed", "sion", "sivir", "skarner", "sona", "soraka",
                        "swain", "sylas", "syndra", "tahm kench", "taliyah", "talon", "taric", "teemo", "thresh", "tristana",
                        "trundle", "tryndamere", "twistedfate", "twitch", "udyr", "urgot", "varus", "vayne", "veigar", "vel'koz",
                        "vi", "viktor", "vladimir", "volibear", "warwick", "wukong", "xayah", "xerath", "xin zhao", "yasuo",
                        "yorick", "yuumi", "zac", "zed", "ziggs", "zilean", "zoe", "zyra", "kaas"
        };

        wordChoice = List[(int) (Math.random() * List.length)];
    }

    public String getWord(){
        Word();
        return wordChoice;
    }

    public static void main(String[] args){new WordGen().Word();}
}




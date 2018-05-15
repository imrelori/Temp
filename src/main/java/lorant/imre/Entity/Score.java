package lorant.imre.Entity;

public class Score {

    private int value;

    public Score(){
        this.value = 0;
    }

    public Score(int number){
        this.value = number;
    }

    /***
     * Megváltoztatja a value értékét.
     *
     * @param number ennyivel fog változni az aktuális score.
     */
    public void updateScoreBy(int number){ this.value = value + number < 0 ? 0 : value + number; }

}

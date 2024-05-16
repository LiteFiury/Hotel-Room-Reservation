import java.util.Random;

public class RandomGenerator {
    private int randomNum;

    public void randomIDGen() {
        Random rand = new Random();
        int randomID = rand.nextInt(1000000);

        while (randomID < 99999) {
            randomID = rand.nextInt(1000000);
        }
        setRandomNum(randomID);
    }

    public void setRandomNum(int randomNum) {
        this.randomNum = randomNum;
    }

    public int getRandomNumber() {
        return randomNum;
    }
}

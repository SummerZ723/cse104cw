public class City {

    private final int number;
    private final int x;
    private final int y;

    public City(int number, int x, int y) {
        this.number = number;
        this.x = x;
        this.y = y;
    }

    public int getNumber() {
        //Return the serial number of this city
        return number;
    }

    public double distance(City b) {
        return Math.sqrt((b.x- this.x)*(b.x- this.x)+(b.y- this.y)*(b.y- this.y));  //Calculate Euclidean distance
    }
}

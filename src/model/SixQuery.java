package model;

public class SixQuery {

    private int cruiseID;
    private int numOfSuits;

    public SixQuery(int cruiseID, int numOfSuits) {
        this.cruiseID = cruiseID;
        this.numOfSuits = numOfSuits;
    }

    public int getCruiseID() {
        return cruiseID;
    }

    public void setCruiseID(int cruiseID) {
        this.cruiseID = cruiseID;
    }

    public int getNumOfSuits() {
        return numOfSuits;
    }

    public void setNumOfSuits(int numOfSuits) {
        this.numOfSuits = numOfSuits;
    }
}

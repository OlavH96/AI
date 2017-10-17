package neuralnetworks.picture.text.util;

/**
 * Created by faiter on 10/13/17.
 */
public enum Constants {
    NUMBER_OF_BYTES_IN_PIXEL(4);

    private int number;

    Constants(int number) {

        this.number = number;
    }

    public int getNumber() {

        return number;
    }
}

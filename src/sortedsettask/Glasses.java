package sortedsettask;

public class Glasses implements Comparable<Glasses> {
    private String shape;

    public Glasses(String shape) {
        if (shape == null) {
            throw new IllegalArgumentException();
        }
        this.shape = shape;
    }

    public String getShape() {
        return shape;
    }

    @Override
    public String toString() {
        return "Glasses{" +
                "shape='" + shape + '\'' +
                '}';
    }

    @Override
    public int compareTo(Glasses glasses) {
        return this.shape.compareTo(glasses.shape);
    }
}

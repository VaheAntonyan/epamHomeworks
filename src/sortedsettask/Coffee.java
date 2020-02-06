package sortedsettask;

public class Coffee implements Comparable<Coffee> {
    private String type;

    public Coffee(String type) {
        if (type == null) {
            throw new IllegalArgumentException();
        }
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "type='" + type + '\'' +
                '}';
    }

    @Override
    public int compareTo(Coffee coffee) {
        return this.type.compareTo(coffee.type);
    }
}

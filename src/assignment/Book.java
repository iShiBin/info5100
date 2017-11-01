package assignment;

public class Book {
    int size;
    int price;
    String name;

    Book(int size) {
        this.size = size;
    }

    Book(int size, int price, String name) {
        super();
        this.size = size;
        this.price = price;
        this.name = name;
    }

    Book(int size, int price) {
        this(size);
        this.price = price;
    }

    public void setName(String name){
        this.name = name;
    }
}
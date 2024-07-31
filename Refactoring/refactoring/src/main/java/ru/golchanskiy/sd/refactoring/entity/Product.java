package main.java.ru.golchanskiy.sd.refactoring.entity;

public class Product {
    private String name = "";
    private long price = 0;
    public Product(String name, long price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public long getPrice(){
        return price;
    }
    public void setPrice(long price){
        this.price = price;
    }

}

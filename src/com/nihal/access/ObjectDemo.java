package com.nihal.access;

public class ObjectDemo {


    int num;
    public ObjectDemo (int num) {
        this.num = num;
    }
    // it gives the number representation of the object
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    
    @Override
    public boolean equals(Object obj) {
        return this.num == ((ObjectDemo)obj).num;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
    }
    public static void main(String[] args) {
        ObjectDemo obj1 = new ObjectDemo(34);
        ObjectDemo obj2 = new ObjectDemo(34);
        System.out.println(obj1 == obj2);
        System.out.println(obj1.equals(obj2));
        System.out.println(obj1.hashCode());
        System.out.println(obj1.getClass().getName());  // .getClass() contains various methods..
    }
}

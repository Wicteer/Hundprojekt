//Victor Magnusson, vima1339


public class Dog {


    private String name;
    private String breed;
    private int age;
    private int weight;
    private double tailLength;
    private Owner owner;


    public Dog(String name, String breed, int age, int weight) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.tailLength = calculateTailLength(breed, age, weight);
    }


    public void removeOwner() {
        if (getOwner() == null) {
            return;
        }
        // Owner = null, dog == null wtf
        getOwner().removeDogFromOwner(this);
        owner = null;
    }


    public void setOwner(Owner owner) {


        if (owner == this.owner || owner == null) { // Äger ägaren redan 'mig'?
            return;
        }
        if (this.owner != null) { // Har "jag" redan en ägare?
            return;
        }


        if (this.owner == null) {
            Dog dog = this;
            this.owner = owner;
            dog = this.owner.getDog();
            dog = this; // this deklarerar vilken hund som är aktuell i den här instansen


        }
        if (owner.hasDog(this)) { // Äger ägaren redan "mig"?
            return;
        }
        owner.addDogs(this);
        this.owner.addDogToOwner(this); // Om inte, be ägaren att lägga till "mig"
        return;
    }


    public Owner getOwner() {
        return this.owner;
    }


    public String getName() {
        return this.name;
    }


    public String getBreed() {
        return this.breed;
    }


    public int getAge() {
        return this.age;
    }


    public int getWeight() {
        return this.weight;
    }


    public double getTailLength() {
        return this.tailLength;
    }


    public void ageDog(int years) {
        int newAge;
        if (years < 0) {
            this.age += 0;
        } else {
            newAge = this.age + years;
            this.age = newAge;
            this.tailLength = calculateTailLength(this.breed, newAge, this.weight);
        }
    }


    private double calculateTailLength(String breed, int age, int weight) {
        String[] taxArray = { "Tax", "Dachshund" };
        boolean x = false;
        double result;


        for (int i = 0; i < taxArray.length; i++) {
            if (breed.equalsIgnoreCase(taxArray[i])) {
                x = true;
                break;
            }
        }


        if (x) {
            result = 3.7;
            return result;


        } else {
            result = Math.round(((double) this.age * ((double) this.weight / (double) 10)) * 100.0) / 100.0;
            return result;
        }
    }


    public String toString() {
        String dogInfo = "[Name: " + this.name + ", Breed: " + this.breed + ", Age: " + this.age + ", Weight: " + this.weight
                + ", Taillength: " + getTailLength() + "] ";
        return dogInfo;
    }
}

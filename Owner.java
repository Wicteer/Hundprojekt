//Victor Magnusson, vima1339


public class Owner {


    private String name;
    private DogList dogs = new DogList();
    private Dog dog;
    private Owner owner;


    public Owner(String name) {
        this.name = name;
    }


    public void removeDogFromOwner(Dog dog) {
        if (hasDog(dog)) {
            dogs.removeDog(dog);
            dog.removeOwner();
        }
    }


    public void addDogToOwner(Dog dog) {
        if (dog == null || dog.getOwner() != null)
            return;


        if (dog.getOwner() == this)
            return;


        this.dog = dog;
        owner = this.dog.getOwner();
        owner = this;
        this.dog.setOwner(this);
        dogs.addDog(this.dog);
        return;
    }


    public void addDogs(Dog dog) {
        dogs.addDog(dog);
    }


    public Dog getDog() {
        return dog;
    }


    public boolean hasDog(Dog dog) {
        if (dogs.findDog(dog))
            return true;


        return false;
    }


    public String getName() {
        return this.name;
    }


    public String toString() {
        return this.name;
    }
}


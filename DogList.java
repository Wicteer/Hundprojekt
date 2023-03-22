//Victor Magnusson, vima1339
import java.util.*;


public class DogList {


    private Dog[] dogs = new Dog[1];


    public void addDog(Dog dog) {
        if (dog == null || findDog(dog)) { // om hund är null eller redan finns.
            return;
        }
        if (dogIndex(null) > -1) {
            dogs[dogIndex(null)] = dog; // Letar efter ett index med null.
            return;
        }
        if (dogIndex(null) == -1) { // Om det inte finns en tom plats
            Dog[] copyArray = new Dog[dogs.length + 1]; // Skapa kopia med 1+ plats
            System.arraycopy(dogs, 0, copyArray, 0, dogs.length);
            dogs = copyArray;
            dogs[dogIndex(null)] = dog;
        }
    }


    public void removeDog(Dog dog) {
        if (dog == null || !findDog(dog)) { // kontroll att hund finns.
            return;
        }
        int index = dogIndex(dog); // Indexering av eftersökt hund hämtas av dogIndex.


        Dog[] copyArray = new Dog[dogs.length - 1]; // Ny array skapas genom kopiering.
        System.arraycopy(dogs, 0, copyArray, 0, index);
        System.arraycopy(dogs, index + 1, copyArray, index, dogs.length - index - 1);
        dogs = copyArray;


        return;
    }


    public boolean findDog(Dog dog) { // Hitta hund genom index-sök metoden
        if (dogIndex(dog) > -1) {
            return true;
        }
        return false;
    }


    public boolean findDogName(String name) {
        for (int i = 0; i < dogs.length; i++) {
            if (name == dogs[i].getName()) {
                return true;
            }
        }
        return false;
    }


    public int dogIndex(Dog dog) { // Index-sök metod som returnerar index eller -1 om hunden inte kan hittas
        for (int i = 0; i < dogs.length; i++) {
            if (dogs[i] == dog)
                return i;
        }
        return -1;
    }


    public int doglistSize() {
        return dogs.length;
    }
}


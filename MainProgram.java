//Victor Magnusson, vima1339
import java.util.*;

public class MainProgram {
   
    InputAdapter input = new InputAdapter();
    private DogList doglist = new DogList();
    private Dog dog;
    private Owner owner;
    private final static String EXIT_COMMAND="0";


    private static ArrayList<String> templateNames = new ArrayList<String>();
    private static ArrayList<String> templateBreeds = new ArrayList<String>();
    private ArrayList<Dog> dogs = new ArrayList<>();
    private ArrayList<Dog> dogMatches = new ArrayList<>();
    private ArrayList<Owner> owners = new ArrayList<>();
    private ArrayList<String> ownersDogs = new ArrayList<>();
    private ArrayList<Dog> ownedDogs = new ArrayList<>();

    private void generateDoglist() {
        templateNames.add("Karo");
        templateNames.add("Fido");
        templateNames.add("Max");
        templateNames.add("Jose");
        templateNames.add("Bob");
        templateNames.add("Rose");

        templateBreeds.add("Tax");
        templateBreeds.add("Golden retriever");
        templateBreeds.add("Pitbull");
        templateBreeds.add("Bulldog");
        templateBreeds.add("Terrier");
        templateBreeds.add("Husky");

        Random rnd = new Random();
        for(int i = 0; i < templateNames.size(); i++) {
            Dog dog = new Dog(templateNames.get(i), templateBreeds.get(i), rnd.nextInt(10) + 1, rnd.nextInt(10) + 1);
            dogs.add(dog);
            }
            return;
    }

    private void printCommands() {
        System.out.println("");
        System.out.println("Welcome!\n Please enter one of the following commands:\n 1.register new dog\n 2.list dogs\n 3.increase age\n 4.remove dog\n 5.register new owner\n 6.give dog\n 7.list owners\n 8.remove dog from owner\n 9.remove owner\n 0.exit\n");
    }

    private void commandCentral(String command) {

        switch(command) {

            case "1":
                registerDog();
                break;
            case "2":
                listDogs();
                break;
            case "3": //increase age
                increaseAge();
                break;
            case "4": //remove dog
                removeDog();
                break;
            case "5": //register new owner
                registerOwner();
                break;
            case "6": //give dog
                giveDog();
                break;
            case "7": //list owners
                listOwners();
                break;
            case "8": //remove dog from owner
                removeOwnedDog();
                break;
            case "9": //remove owner
                removeOwner();
                break;

            case "0": //exit
                System.exit(0);
                break;

            default:
                System.out.println("ERROR: no such command!");          
        }
    }

private void registerDog() {
    String dogName = input.readString("Name");


        if (findDog(dogName) != null) {
            System.out.println("ERROR: dog already in list!");
            return;
        }


        if(dogName == null) {
            System.out.println("ERROR: dog cannot be null");
            return;
        }
    String dogBreed = input.readString("Breed");


        if(dogBreed == null) {
            System.out.println("ERROR: dog cannot be null");
            return;
        }


    int dogAge = input.readInt("Age");


        if(dogAge <= 0) {
            System.out.println("ERROR: dog cannot be null");
            return;
        }
   
    int dogWeight = input.readInt("Weight");


        if(dogWeight <= 0) {
            System.out.println("ERROR: dog cannot be null");
            return;
        }


    Dog dog = new Dog(dogName, dogBreed, dogAge, dogWeight);
    doglist.addDog(dog);
    dogs.add(dog);
    System.out.println(dogName + " was added to the register");
    }


    private Dog findDog(String name) {
        for(int i = 0; i < dogs.size(); i++) {
            if(name.equalsIgnoreCase(dogs.get(i).getName())) {
                return dogs.get(i);
            }
        } return null;
    }

    private void listDogs() {
        if(dogs.isEmpty()) {
            System.out.println("Error: no dogs in list");
            return;
        }
        double len = input.readDouble("Smallest tail length to display?");
        prntDogMinTail(len);
    }

    private void prntDogMinTail(double len) {
        for (int i = 0; i < dogs.size(); i++) {
            if(len < dogs.get(i).getTailLength()) {
                dogMatches.add(dogs.get(i));
            }
        }


        if(dogMatches.isEmpty() || dogMatches == null) {
            System.out.println("Error: no dogs matches");
            return;
        } else
            System.out.println("The following dogs have such a large tail");
            System.out.println(dogMatches);
            dogMatches.clear();
           
    }

    private void removeDog() {
        String dogName = input.readString("Enter the name of the dog");
        if(findDog(dogName) == null) {
            System.out.println("ERROR: No such dog");
            return;
        }
        Dog dog = findDog(dogName);
        if(dog.getOwner() != null) {
            this.owner = dog.getOwner();
            owner.removeDogFromOwner(dog);
            dog.removeOwner();
        }
        dogs.remove(dog);
        doglist.removeDog(dog);
        System.out.println(dog.getName() + " has been removed from the register");
    }

    private void removeOwnedDog() { //remove dog from owner
        String dogName = input.readString("Enter the name of the dog");
        if(findDog(dogName) == null) {
            System.out.println("ERROR: no such dog");
            return;
        }
        Dog dog = findDog(dogName);
        if(dog.getOwner() == null) {
            System.out.println("ERROR: dog has no owner");
            return;
        }
        this.owner = dog.getOwner();
        owner.removeDogFromOwner(dog);
        System.out.println(dog.getName() + " is removed from " + owner);
    }

    private void removeDogs(Owner owner) {
        for(int i = 0; i < dogs.size(); i++) {  
            if(dogs.get(i).getOwner() == owner) {  
                ownedDogs.add(dogs.get(i));
            }
        }
        if(ownedDogs.isEmpty())
            return;


        for(int i = 0; i < ownedDogs.size(); i++) {
            dogs.remove(ownedDogs.get(i));  
        }
    }

    private void increaseAge() {
        String name = input.readString("Name");
        Dog foundDog = findDog(name);


            if(foundDog == null) {
                System.out.println("Error: dog not found");


            } else {
                int years = 1;
                foundDog.ageDog(years);
                System.out.println(foundDog);
            }
    }

    private void giveDog() {
        String dogName = input.readString("Enter the name of the dog");
            if(findDog(dogName) == null) {
                System.out.println("ERROR: no such dog");
                return;
            }
                Dog dog = findDog(dogName);
           
            if(dog.getOwner() != null || owner.hasDog(dog)) {
                System.out.println("ERROR: the dog already has an owner");
                return;
            }
       
        String ownerName = input.readString("Enter the name of the owner");
            if(findOwner(ownerName) == null) {
                System.out.println("ERROR: no such owner");
                return;
            }
        Owner owner = findOwner(ownerName);
        owner.addDogToOwner(dog);
        System.out.println(owner + " now owns " + dog + "!");
    }
// ---------------------------------------------------------
    private void registerOwner() {
        String name = input.readString("Name");
        if(findOwner(name) != null) {
            System.out.println("ERROR: Owner already in list!");
            return;
        }
        owners.add(new Owner(name));
        System.out.println(name + " was added to the list!");
    }

    private Owner findOwner(String name) {
        for(int i = 0; i < owners.size(); i++) {
            if(name.equalsIgnoreCase(owners.get(i).getName())) {
                return owners.get(i);
            }
        } return null;
    }
   
    private void listOwners() {
        if(owners.isEmpty()) {
            System.out.println("ERROR: no owner in list");
            return;
        }
        prntOwners();
        ownersDogs.clear();
    }

    private void prntOwners() {
        for(int i = 0; i < owners.size(); i++) {
            owner = owners.get(i);

            if(hasDogs(owner))
                System.out.println(owner + extractDogs(owner));
            else
                System.out.println(owner + ", ");
        }
    }

    private void removeOwner() {
        String ownerName = input.readString("Enter the name of the user");
        if(findOwner(ownerName) == null) {
            System.out.println("ERROR: no such owner");
            return;
        }
        owner = findOwner(ownerName);
        removeDogs(owner);
        owners.remove(owner);
        System.out.println(owner + " is removed from the register");
    }

    private boolean hasDogs(Owner owner) {
        for(int i = 0; i < dogs.size(); i++) {
            if(dogs.get(i).getOwner() == owner) {
                return true;
            }
        } return false;
    }

    private String extractDogs(Owner owner) {
        for(int i = 0; i < dogs.size(); i++) {
            if(dogs.get(i).getOwner() == owner) {
                ownersDogs.add(dogs.get(i).getName());
               // if(i < dogs.size())
               //     ownersDogs.add(" ,");
            }
        } return ownersDogs.toString();
    }

    private void start() {
        startUp();
        commandLoop();
        shutDown();
    }

    private void startUp() {
        generateDoglist();
    }

    private String readCommand() {
        printCommands();
        String command = input.readString("command");
        return command;
    }

    private void commandLoop() {
        String command;
        do{
            command=readCommand();
            commandCentral(command);
        }while(command!=EXIT_COMMAND);
    }

    private void shutDown() {
        System.exit(0);
    }

    public static void main(String[] args) {
        new MainProgram().start();
    }
}


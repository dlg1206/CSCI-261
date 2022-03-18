package truck;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Derek Garcia
 **/

public class sim {

    private static ArrayList<Package> getPackages(String fileName) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        ArrayList<Package> packages = new ArrayList<>();
        int curID = 1;
        String line = br.readLine();

        while(line != null){

            packages.add(new Package(curID++, Integer.parseInt(line)));

            line = br.readLine();
        }
        br.close();
        return packages;
    }

    private static ArrayList<Truck> doGreedy(ArrayList<Package> packages){

        System.out.println("\nDoing Greedy Sim");

        ArrayList<Truck> trucks = new ArrayList<>();

        int curID = 1;
        Truck curTruck = new Truck(curID);

        while (!packages.isEmpty()) {

            if (curTruck.addPackage(packages.get(0))) {
                System.out.println("Added " + packages.get(0) + " to Truck " + curTruck.ID);
                packages.remove(0);
            } else {
                System.out.println("\tCan't add " + packages.get(0) + "to Truck " + curTruck.ID + "; Now leaving the depot");
                trucks.add(curTruck);
                curTruck = new Truck(++curID);
                System.out.println("Truck " + curTruck.ID + " has arrived");
            }
        }
        System.out.println("\tTruck " + curTruck.ID + " is leaving the depot");
        trucks.add(curTruck);

        System.out.println("All trucks have left the depot.");
        return trucks;
    }

    private static ArrayList<Truck> doOptimal(ArrayList<Package> packages){
        System.out.println("\nDoing Optimal Sim");

        ArrayList<Truck> trucks = new ArrayList<>();
        ArrayList<Package> loaded = new ArrayList<>();

        int curID = 1;

        while (!packages.isEmpty()) {

            Truck curTruck = new Truck(curID++);
            System.out.println("Truck " + curTruck.ID + " has arrived");

            while(!curTruck.isFull()){
                for (Package pkg : packages) {

                    if (curTruck.addPackage(pkg)) {
                        System.out.println("Added " + pkg + " to Truck " + curTruck.ID);
                        loaded.add(pkg);
                    }

                    if (curTruck.isFull()) {
                        System.out.println("\tTruck " + curTruck.ID + " is full; Now leaving the depot");
                        trucks.add(curTruck);
                        break;
                    }
                }

                for(Package pkg : loaded){
                    packages.remove(pkg);
                }
                loaded.clear();
            }
        }

        System.out.println("\nAll trucks have left the depot.");
        return trucks;

    }

    private static void printTrucks(ArrayList<Truck> trucks){
        System.out.println("\nThe following trucks arrived in NY:");
        for(Truck truck : trucks){
            System.out.println(truck);
        }
    }

    public static void main(String[] args) throws IOException {


        System.out.println("\nOpening depot...");

        printTrucks(doGreedy(getPackages(args[0])));
        //printTrucks(doOptimal(getPackages(args[0])));

        System.out.println("\nClosing depot...");


    }



}

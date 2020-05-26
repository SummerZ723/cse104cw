import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TSPSolver {
    //Read a txt file and store the numbers and locations of cities into an arraylist of class City.
    public static ArrayList<City> readFile(String filename) {
        ArrayList<City> cities = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            String lineContent;
            while ((lineContent = reader.readLine()) != null) {
                String[] thisLine = lineContent.split("\t");
                City thisCity = new City(Integer.parseInt(thisLine[0]), Integer.parseInt(thisLine[1]), Integer.parseInt(thisLine[2]));
                cities.add(thisCity);
            }
            reader.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException a) {
            a.printStackTrace();
        }
        return cities;
    }

    public static ArrayList<City> solveProblem(ArrayList<City> citiesToVisit) {
        int cityNumber = citiesToVisit.size();                  //Get the total number of cities to visit
        ArrayList<City> routine = new ArrayList<>();            //Store the route of visiting cities
        try {
            City start = citiesToVisit.get(0);                      //Get the city as a starting point
            City current = start;                                   //Set the current city to start city
            City next = null;                                       //Set the next visiting city to null
            routine.add(current);                                   //Add the start city to routine
            citiesToVisit.remove(0);                                //Delete the start city from visited cities
            while (routine.size() < cityNumber) {
                double min = Double.MAX_VALUE;                      //Set the minimum distance to infinite
                for (City thisCity : citiesToVisit) {               //For each city in unvisited cities, find the nearest city to it
                    double distance = current.distance(thisCity);
                    if (distance < min) {
                        min = distance;
                        next = thisCity;
                    }
                }
                current = next;                                     //Set the next city to the current city
                routine.add(next);                                  //Add the next city to the routine list
                for (int i = 0; i < citiesToVisit.size(); i++) {          //Delete the city visited currently from citiesToVisit
                    if (citiesToVisit.get(i).getNumber() == current.getNumber()) {
                        citiesToVisit.remove(i);
                        break;
                    }
                }
            }
            routine.add(start);                                     //Add the start city to the routine
        } catch (IndexOutOfBoundsException | NullPointerException i) {
            i.printStackTrace();
        }
        return routine;

    }

    public static double printSolution(ArrayList<City> routine) {
        double totalDistance = 0.0;
        StringBuilder output = new StringBuilder("0");
        for (int i = 1; i < routine.size(); i++) {
            output.append("->").append(routine.get(i).getNumber());         //Build the output string of travel route
            totalDistance += routine.get(i).distance(routine.get(i - 1));     //Calculate the total distance traveled
        }
        System.out.println(output);
        return totalDistance;
    }

}

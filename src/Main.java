import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();

        ArrayList<City> cities = TSPSolver.readFile("src\\instances\\C11010.TXT");
        cities = TSPSolver.solveProblem(cities);

        Double totalDistance = TSPSolver.printSolution(cities);

        System.out.printf("Distances: %f\n", totalDistance);

        long endTime=System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime - startTime)+"ms");
    }
}

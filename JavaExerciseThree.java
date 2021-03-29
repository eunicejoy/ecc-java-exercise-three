import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.Optional;
import java.util.*;

public class JavaExerciseThree{

  public static void main(String[] args) {

    boolean winner;
    int horseCount = 0;
    long healthyHorseCount = 0;
    ArrayList<Horse> horseList = new ArrayList<Horse>();
    int healthyHorses = 0;
    Random randomizer = new Random();
    int turns = 0;
    
    final List<String> warCries = new ArrayList<String>();
    warCries.add("Yeeehaaa!");
    warCries.add("Heeeeyaaa!");
    warCries.add(null);
    warCries.add("Neigh neigh!");
    warCries.add("Yipyip!");

    System.out.print("Enter race distance: ");
    Scanner scanDistance = new Scanner(System.in);
    final int raceDistance = scanDistance.nextInt();

    turns = raceDistance / 5; //assume turns based on the race distance

    do {
      horseList.clear();

      System.out.print("Enter number of horses: ");
      Scanner scanHorseCount = new Scanner(System.in);
      horseCount = scanHorseCount.nextInt();

      IntStream.range(0, horseCount)
      .forEach(i -> horseList.add(new Horse("horse" + (i+1), randomizer.nextBoolean(), 0, null)));

      healthyHorseCount = horseList.parallelStream()
      .filter(horseHealth -> horseHealth.getHealth() == true)
      .count();

    } while (healthyHorseCount < 2);

    // add all the healthy horses into a list
    List<Horse> healthyHorseList = horseList.parallelStream()
    .filter(horseHealth -> horseHealth.getHealth() == true)
    .collect(Collectors.toList());

    healthyHorses = (int)healthyHorseCount;

    // set the name of horses to uppercase and set warCry
    IntStream.range(0, healthyHorses)
    .forEach(i -> healthyHorseList.set(i, new Horse(healthyHorseList.get(i).getName().toUpperCase(),
    healthyHorseList.get(i).getHealth(),
    healthyHorseList.get(i).getDistanceTraveled(),
    warCries.get(randomizer.nextInt(5)))));

    //race variables
    int[] horseSpeed = new int[healthyHorses];
    int[] distanceTraveled = new int[healthyHorses];
    int[] distanceLeft = new int[healthyHorses];

    System.out.println("");

      for(int a = 1 ; a <= turns; a++){
      System.out.print("Name\t Distance Traveled\tDistance Left\t\n");
      IntStream.range(0, healthyHorseList.size())
      .forEach(x->
      {
            //set random speed per horse
            horseSpeed[x] = randomizer.nextInt(10)+1;
            distanceTraveled[x] += horseSpeed[x];
            distanceLeft[x] = raceDistance - distanceTraveled[x];

            //assuming that the horse has arrived to finish line
            if(distanceLeft[x] <= 0){

              System.out.print(healthyHorseList.get(x).getName() + "\t\t" +  raceDistance + "\t\t    0 \n");
              healthyHorseList.get(x).setDistanceTraveled(raceDistance);
              Optional<String> warCry = Optional.ofNullable(healthyHorseList.get(x).getWarCry());

              System.out.println("Finished " + " " + healthyHorseList.get(x).getName() + " " +warCry.orElse("No war cry"));

            }
            else{
              System.out.print(healthyHorseList.get(x).getName() + "\t\t" +  distanceTraveled[x] + "\t\t    " + distanceLeft[x] + "\n");
              healthyHorseList.get(x).setDistanceTraveled(distanceTraveled[x]);
            }
      });
      System.out.println();
      }

    System.out.println("");
    System.out.print("Name\t Distance Traveled\n");
    for(Horse h: healthyHorseList){
      System.out.println(h.getName() + "\t  "+ h.getDistanceTraveled());
    }
  }

}

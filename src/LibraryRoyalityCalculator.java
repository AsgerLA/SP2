
import java.util.Random;

public class LibraryRoyalityCalculator {

  static void usage(String msg) {
    System.err.println(msg);
    System.err.println("Avalible options are:\n"+
                       "  --seed <seed>   seed for random generator");
    System.exit(1);
  }

  public static void main(String[] args) {

    int i, j;
    Random rand = new Random();
    String[] literatureTypes      = { "BI", "TE", "LYRIK", "SKØN", "FAG" };
    float[] literatureTypesPoints = { 3.0f, 3.0f, 6.0f,    1.7f,   1.0f };
    Author[] authors;
    double expectedPay;
    double total = 0;
    double pay;
    float calcPay;
    int titleCnt;
    int copies, pages, type;
    int availability, reach, use;
    boolean illustrated;
    final double rate = 0.067574;
    int res = 0;
    long seed = 0;
    boolean randomSeed = true;

    for (i = 0; i < args.length; i++) {
      if (args[i].equals("--seed")) {
        if (i+1 >= args.length)
          usage("'--seed' needs argument");
        try {
          seed = Long.decode(args[++i]);
        } catch (Exception e) {
          usage("Exception: "+e.getMessage());
        }
        randomSeed = false;
      } else {
        usage("");
      }
    }

    if (randomSeed) {
      seed = rand.nextLong();
    }
    rand.setSeed(seed);

    System.out.printf("Seed: %d\n", seed);
    authors = new Author[rand.nextInt(1, 16)];

    for (i = 0; i < authors.length; i++) {
      authors[i] = new Author("Author"+(i+1));
      titleCnt = 1+rand.nextInt(5);

      System.out.println(authors[i].getName()+"["+titleCnt+"] {");

      expectedPay = 0;
      for (j = 0; j < titleCnt; j++) {
        type = rand.nextInt(1024);
        if (type < 256) {
          copies = rand.nextInt(140);
          pages = rand.nextInt(166);
          type = rand.nextInt(literatureTypes.length);
          authors[i].addTitle(
              new PrintedBook("PrintedBook"+(j+1),
                literatureTypes[type],
                copies, pages));
          pay = pages * literatureTypesPoints[type] * copies * rate;
          expectedPay += pay;
          System.out.printf("  PrintedBook%d type:%s copies:%d pages:%d pay:%.2f\n",
                            (j+1), literatureTypes[type], copies, pages, pay);
        } else if (type >= 256 && type < 512){
          copies = rand.nextInt(140);
          pages = rand.nextInt(192); // durationInMinutes
          type = rand.nextInt(literatureTypes.length);
          authors[i].addTitle(
              new AudioBook("AudioBook"+(j+1),
                literatureTypes[type],
                copies, pages));
          pay = pages*0.5 * literatureTypesPoints[type] * copies * rate;
          System.out.printf("  AudioBook%d type:%s copies:%d min:%d pay:%.2f\n",
                            (j+1), literatureTypes[type], copies, pages, pay);
          expectedPay += pay;
        } else if (type >= 512 && type < 768) {
          pages = rand.nextInt(360000); // characters
          availability = rand.nextInt(97);
          reach = rand.nextInt(50);
          use = rand.nextInt(205);
          illustrated = (rand.nextInt(1024) < 512);
          type = rand.nextInt(literatureTypes.length);
          authors[i].addTitle(
              new EBook("EBook"+(j+1),
                literatureTypes[type],
                availability, reach, use, pages, illustrated));
          pay = (pages/1800+20) * literatureTypesPoints[type] * ((reach*5) + (availability*0.5) + use);
          if (illustrated)
            pay += pay * 0.10;
          pay *= rate;
          System.out.printf("  EBook%d type:%s availability:%d reach:%d use:%d chars:%d illustrated:%b pay:%.2f\n",
                            (j+1), literatureTypes[type], availability, reach, use, pages, illustrated, pay);
          expectedPay += pay;
        } else {
          pages = rand.nextInt(192); // durationInMinutes
          availability = rand.nextInt(97);
          reach = rand.nextInt(50);
          use = rand.nextInt(205);
          type = rand.nextInt(literatureTypes.length);
          authors[i].addTitle(
              new EAudioBook("EAudioBook"+(j+1),
                literatureTypes[type],
                availability, reach, use, pages));
          pay = (pages*0.5) * literatureTypesPoints[type] * ((reach*5) + (availability*0.5) + use);
          pay *= rate;
          System.out.printf("  EAudioBook%d type:%s availability:%d reach:%d use:%d min:%d pay:%.2f\n",
                            (j+1), literatureTypes[type], availability, reach, use, pages, pay);
          expectedPay += pay;
        }
      }

      expectedPay = (float)Math.round(expectedPay*100.0)/100.0;
      total += expectedPay;
      calcPay = authors[i].calculateTotalPay();
      System.out.println("} "+calcPay+"kr"+" ("+expectedPay+")");
      if ((int)expectedPay != (int)calcPay) {
        System.out.println("ERROR: unexpected pay "+calcPay+"kr"+" ("+expectedPay+")");
        res = 1;
      }
    }

    System.out.printf("Total: %.2fkr\n", total);

    System.out.printf("Test %s\n", ((res == 0) ? "PASSED" : "FAILED"));
    System.out.printf("Seed: %d\n", seed);

    //Author a = new Author("Olga Ravn");
    //a.addTitle(new PrintedBook("Celestine", "SKØN", 140, 166));
    //a.addTitle(new AudioBook("Celestine", "SKØN", 140, 192));
    //a.addTitle(new EBook("test", "FAG", 97, 50, 150, 360000, false));
    //System.out.println(a.getName()+": "+a.calculateTotalPay()+"kr");

  }
}

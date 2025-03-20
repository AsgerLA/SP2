import java.util.ArrayList;

public class Author {

  private String name;
  private ArrayList<Title> titles;

  public Author(String name) {
    this.name = name;
    this.titles = new ArrayList<>();
  }

  public void addTitle(Title t) {
    this.titles.add(t);
  }

  public float calculateTotalPay() {
    float total = 0.0f;
    for (Title t : titles)
      total += t.calculateRoyalty();

    return Math.round(total*100.0f)/100.0f;
  }

  String getName() {
    return this.name;
  }

}

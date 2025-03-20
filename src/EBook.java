
public class EBook extends NetBook {

  private int characters;
  private boolean illustrated;

  public EBook(String title, String literatureType, int availability, int reach, int use,
               int characters, boolean illustrated) {
    super(title, literatureType, availability, reach, use);
    this.characters = characters;
    this.illustrated = illustrated;
  }

  protected double calculatePoints() {
    double points = (this.characters/1800+20) * this.literatureTypePoints * ((this.reach*5) + (this.availability*0.5) + this.use);
    if (illustrated)
      points += points * 0.10;
    return points;
  }

}

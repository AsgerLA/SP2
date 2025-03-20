
public abstract class NetBook extends Title {

  protected int availability;
  protected int reach;
  protected int use;

  public NetBook(String title, String literatureType, int availability, int reach, int use) {
    super(title, literatureType, 0);
    this.availability = availability;
    this.reach = reach;
    this.use = use;
  }

}

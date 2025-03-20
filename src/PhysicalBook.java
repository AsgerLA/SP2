
public abstract class PhysicalBook extends Title {
  protected int copies;

  public PhysicalBook(String title, String literatureType, int copies) {
    super(title, literatureType, copies);
    this.copies = copies;
  }
}


public class AudioBook extends PhysicalBook {

  private int durationInMinutes;

  public AudioBook(String title, String literatureType, int copies, int durationInMinutes) {
    super(title, literatureType, copies);
    this.durationInMinutes = durationInMinutes;
  }

  protected double calculatePoints() {
    return ((this.durationInMinutes*0.5) * this.literatureTypePoints * this.copies);
  }
}

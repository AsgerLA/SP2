
public class EAudioBook extends NetBook {

  private int durationInMinutes;

  public EAudioBook(String title, String literatureType, int availability, int reach, int use,
                    int durationInMinutes) {
    super(title, literatureType, availability, reach, use);
    this.durationInMinutes = durationInMinutes;
  }

  protected double calculatePoints() {
    return (this.durationInMinutes*0.5) * this.literatureTypePoints * ((this.reach*5) + (this.availability*0.5) + use);
  }

}

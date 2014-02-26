package travel;

public interface Observable{

  /**
   * When subscribing, you will be noticed when the object is updated, that is it will call the update method.
   * 
   * @param o The observer to notice.
   */
  public void subscribe(Observer o);
}

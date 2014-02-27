package travel;

import java.util.List;
import travel.Observer;
import travel.Observable;
import java.util.LinkedList;


/**
 * A wrapper to have an observable Travel.
 * @author Bromind
 */
public class ObservableTravel implements Observable{
  Travel travel;
  List<Observer> observers = new LinkedList<Observer>();

  /**
   * @param empty True to construct an empty Travel
   * @param o Initial Observers
   */
  public ObservableTravel(boolean empty, Observer... o){
    travel = new Travel(empty);

    for(int i = 0 ; i < o.length ; i ++){
      subscribe(o[i]);
    }
  }

  /**
   * @param s Initial step
   * @param o Initial Observers
   */
  public ObservableTravel(Step s, Observer... o){
    travel = new Travel(s);
    
    for(int i = 0 ; i < o.length ; i ++){
      subscribe(o[i]);
    }
  }

  /**
   * @param stepList Initial list of steps
   * @param o Initial Observers
   */
  public ObservableTravel(List<Step> stepList, Observer... o){
    travel = new Travel(stepList);

    for(int i = 0 ; i < o.length ; i ++){
      subscribe(o[i]);
    }
  }

  /**
   * @param file To import a ObservableTravel from an exported file.
   * @param o Initial Observers
   */
  public ObservableTravel(String file, Observer... o){
    travel = new Travel(file);

    for(int i = 0 ; i < o.length ; i ++){
      subscribe(o[i]);
    }
  }

  /**
   * To be notified when the ObservableTravel is updated
   *
   * @param o The Observer to notify
   */
  public void subscribe(Observer o){
    observers.add(o);
  }

  /**
   * Return the i-th Step of the travel
   * @param i The index of the step.
   */
  public Step getStep(int i){
    return travel.getStep(i);
  }

  /**
   * Edit the travel and update Observers
   */
  public ObservableTravel editTravel(){
    travel = travel.editTravel();
    update();
    return this;
  }

  /**
   * Edit a step
   */
  public ObservableTravel editTravelStep(){
    travel = travel.editTravelStep();
    update();
    return this;
  }

  /**
   * Remove a step
   *
   * @param index The step to remove.
   */
  public ObservableTravel removeStep(int index){
    travel.removeStep(index);
    update();
    return this;
  }

  /**
   * Add a step
   *
   * @param s The step to add.
   */
  public ObservableTravel addStep(Step s){
    travel.addStep(s);
    update();
    return this;
  }

  /**
   * Write a LaTeX file
   */
  public void writeLaTeXFile(){
    travel.writeLaTeXFile();
  }

  /**
   * Write a LaTeX file
   *
   * @param fileDir The file name. 
   */
  public void writeLaTeXFile(String fileDir){
    travel.writeLaTeXFile(fileDir);
  }
      
  /**
   * Write a LaTeX file
   *
   * @param fileDir The file name.
   * @param title The file title.
   */
  public void writeLaTeXFile(String fileDir, String title){
    travel.writeLaTeXFile(fileDir, title);
  }

  /**
   * Write a LaTeX file.
   *
   * @param fileDir The file name.
   * @param title The file title.
   * @param authorFamilyName The family name of the author.
   */
  public void writeLaTeXFile(String fileDir, String title, String authorFamilyName){
	travel.writeLaTeXFile(fileDir, title, authorFamilyName);
  }

  /**
   * Write a LaTeX file.
   *
   * @param fileDir The file name.
   * @param title The file title.
   * @param authorFamilyName The family name of the author.
   * @param authorFirstName The first name of the author.
   */
  public void writeLaTeXFile(String fileDir, String title, String authorFamilyName, String authorFirstName) {
	travel.writeLaTeXFile(fileDir, title, authorFamilyName, authorFirstName);
  }

  /**
   * Get the number of steps.
   */
  public int stepNumber(){
    return travel.stepNumber();
  }

  /**
   * @return A short string explaining the travel
   */
  public String shortName(){
    return travel.shortName();
  }

  /**
   * @return A string explaining the travel in details.
   */
  public String toString(){
    return travel.toString();
  }

  /**
   * Call update on each Observer
   */
  private void update(){
    for(int i = 0 ; i < observers.size() ; i++){
      observers.get(i).update();
    }
  }
}

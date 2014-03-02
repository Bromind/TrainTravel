package gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.AbstractListModel;
import javax.swing.ListModel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JComponent;


import trainTravel.TrainTravel;
import travel.Step;
import travel.ObservableTravel;

class TrainTravelGUI
{
    private final int TAB_POSITION = JTabbedPane.RIGHT;
    private static final Scanner scanner = new Scanner(System.in);
    private final List<ObservableTravel> travelList = new LinkedList<ObservableTravel>();

    public void start()
    {
      travelList.add(TrainTravel.importFile("../TrainSave.tex"));
        JFrame mainFrame = new JFrame("TrainTravel");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	mainFrame.setLayout(new BorderLayout());

        JPanel displayPanel = manageDisplayPanel();
	JTabbedPane travelPanel = manageStepPanel(travelList.get(0));

        mainFrame.getContentPane().add(displayPanel, BorderLayout.WEST);
	mainFrame.getContentPane().add(travelPanel, BorderLayout.CENTER);
	mainFrame.setPreferredSize(new Dimension(600, 300));
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private JPanel manageDisplayPanel(){
      final TravelListModel listModel = new TravelListModel();
      JList travelNamedList = new JList(listModel);
      JPanel buttonPanel = new JPanel();
      JPanel displayPanel = new JPanel(new BorderLayout());
      
      JButton addTravel = new JButton("Add Travel");
      addTravel.addActionListener(
	  new ActionListener() {
	    public void actionPerformed(ActionEvent e){
	      listModel.addTravel();
	    }
	  }
      );
      
      JButton removeTravel = new JButton("Remove Travel");
      addTravel.addActionListener(
	  new ActionListener() {
	    public void actionPerformed(ActionEvent e){
	      listModel.removeTravel(0);
	    }
	  }
      );

      buttonPanel.add(addTravel);
      buttonPanel.add(removeTravel);

      displayPanel.add(travelNamedList, BorderLayout.CENTER);
      displayPanel.add(buttonPanel, BorderLayout.SOUTH);

      return displayPanel;
    }

    private JPanel manageTravelPanel()
    {
        JPanel panel = new JPanel();
        JTabbedPane pane = new JTabbedPane(TAB_POSITION);
        for(int i = 0; i < travelList.size(); i++)
            pane.addTab((i + 1)+"", manageStepPanel(travelList.get(i)));

        panel.add(pane);
        return panel;
    }

    private JTabbedPane manageStepPanel(ObservableTravel travel)
    {
        int i = travel.stepNumber();
        JTabbedPane pane = new JTabbedPane(TAB_POSITION);
        for(int j = 0; j < i; j++)
            pane.addTab((j + 1)+"-th step", manageJourneyPanel(travel.getStep(j)));

        return pane;
    }

    private JComponent manageJourneyPanel(Step step)
    {
      int i = step.journeyNumber();
      JPanel panel = new JPanel(new GridLayout(i, 1, 10, 10));
      for(int j = 0 ; j < i ; j++){
	JButton button = new JButton(step.getJourney(j).toShortString());
	button.addActionListener(
	      new ActionListener(){
		public void actionPerformed(ActionEvent e){
		  editJourneyFrame();
		}
	      });
	panel.add(button);
      }
      return panel;
    }

    private void editJourneyFrame(){
      //TODO
    }

    private List<ObservableTravel> test()
    {
        ObservableTravel travel = TrainTravel.importFile("TrainSave.tex");
        LinkedList linkedlist = new LinkedList();
        linkedlist.add(travel);
        return linkedlist;
    }

    private class TravelListModel extends AbstractListModel implements ListModel {

      public int getSize(){
	return travelList.size();
      }

      public String getElementAt(int i){
	// TODO
	// return travelList.get(i).shortName();
	return "Travel" + (i + 1);
      }

      public void addTravel(){
	travelList.add(new ObservableTravel(true));
	fireIntervalAdded(this, 0, travelList.size() - 1);
      }

      public void removeTravel(int i){
	travelList.remove(i);
	fireIntervalRemoved(this, 0, travelList.size() - 1);
      }
    }
}

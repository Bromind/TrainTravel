package gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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

import trainTravel.TrainTravel;
import travel.Step;
import travel.Travel;

class TrainTravelGUI
{
    private final int TAB_POSITION = JTabbedPane.LEFT;
    private static final Scanner scanner = new Scanner(System.in);
    private final List<Travel> travelList = test();

    public void start()
    {
        JFrame mainFrame = new JFrame("TrainTravel");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel displayPanel = manageDisplayPanel();

        mainFrame.getContentPane().add(displayPanel);
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

    private JPanel manageStepPanel(Travel travel)
    {
        int i = travel.stepNumber();
        JPanel panel = new JPanel(new GridLayout(i, 1));
        for(int j = 0; j < i; j++)
            panel.add(new JLabel(travel.getStep(j).toString()));

        return panel;
    }

    private List<Travel> test()
    {
        Travel travel = TrainTravel.importFile("TrainSave.tex");
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
	travelList.add(new Travel(true));
	fireIntervalAdded(this, 0, travelList.size() - 1);
      }

      public void removeTravel(int i){
	travelList.remove(i);
	fireIntervalRemoved(this, 0, travelList.size() - 1);
      }
    }
}

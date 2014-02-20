package gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
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

        JPanel travelPanel = manageTravelPanel();

        mainFrame.getContentPane().add(travelPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
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

    private java.util.List test()
    {
        Travel travel = TrainTravel.importFile("TrainSave.tex");
        LinkedList linkedlist = new LinkedList();
        linkedlist.add(travel);
        return linkedlist;
    }

}
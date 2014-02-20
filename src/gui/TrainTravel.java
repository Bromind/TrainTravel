package gui;

import javax.swing.*;


class TrainTravel
{

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable() {

            public void run()
            {
                (new TrainTravelGUI()).start();
            }

        });
    }
}

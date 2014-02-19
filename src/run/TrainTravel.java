package run;

import travel.Travel;
import userInterface.*;

public class TrainTravel {

	public static void main(String[] args) {
		final Travel travel = new Travel();

		final UIMenu<UIWidget> mainMenu = new UITitleWidgetMenu<>("Main Menu");


		final UIPanel panel = new UIPanel("===============\n| TrainTravel |\n===============") {

			@Override
			public Object run() {
				//try{
					mainMenu.addWidget(new NewTravelWidget());
					mainMenu.addWidget(new ExportTravelWidget("--- Export du voyage ---", travel));
					UIWidget next = mainMenu.nextWidget();
					next.run();
					return 1;
				//} catch (Exception e) {
				//	return -1;
				//}

			}
		};

		System.out.println("\n\nProgram return " + panel.run());
	}

}

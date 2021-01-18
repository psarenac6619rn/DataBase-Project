package app;

public class Main {

	public static void main(String[] args) {
		
		AppCore app= new AppCore();
		MainFrame mf = MainFrame.getInstance();
		
		mf.setAppCore(app);
		mf.getAppCore().loadResource();
		
		mf.initElements();
		mf.addElements();
		
		app.uradi();
		mf.setVisible(true);
		
		
	}

}

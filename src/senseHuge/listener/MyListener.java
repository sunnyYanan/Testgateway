package senseHuge.listener;

public class MyListener implements   Listenable{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

/*		MySource   ms=new   MySource(); 
		MyListener   ml=new   MyListener(); 
		ms.addListener(ml); 
		ms.setValue(10); 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ms.setValue(100);
*/

	}

	@Override
	public void eventChanged(MyEvent e) {
		// TODO Auto-generated method stub
		 System.out.println("value   changed   to:   "+e.getValue()); 
		
	}

}

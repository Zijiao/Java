class ThreadTest1 { 

    public static class RunnableThreadExample implements Runnable {
	    public int count = 0;

	    public void run() {
		    System.out.println("RunnableThread starting.");
		    try {
			    while (count < 5) {
				    Thread.sleep(200);
				    System.out.println("In Thread, count is " + count);
				    count ++;
			    }
		    } catch (InterruptedException exc) {
			    System.out.println("RunnableThread interrupted.");
		    }
		    System.out.println("RunnableThread terminating.");
	    }
    }

    public static void main(String[] args) {
    	RunnableThreadExample instance = new RunnableThreadExample();
    	Thread thread = new Thread(instance);
    	thread.start();

    	// wait until above thread counts to 5 (slowly)
    	while (instance.count != 5) {
    		try {
    			Thread.sleep(250);
    		} catch (InterruptedException exc) {
    			exc.printStackTrace();
    		}
    	}
    }
}
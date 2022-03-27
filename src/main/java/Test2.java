class Q {            // Q is a class containing two parallel processes
    int n;
    boolean flag = false;

    //PRODUCER
    synchronized void put(int n) {    // Produce a value
        if (flag) {                            // Entry
            try {
                wait();
            } catch (InterruptedException e){} ;        // to the
        }                            // critical section

        this.n = n;
        System.out.println("Produce :" + n);            // Critical Section

        flag = true;                        // Exit from the
        notify();                            // critical section
    }

    //CONSUMER
    synchronized int get() {        // Consume a value
        if (!flag) {                        // Entry
            try {
                wait();
            } catch (InterruptedException e) {};        // to the
        }                            // critical section

        System.out.println("Consume :" + n);            // Critical Section

        flag = false;                        // Exit from the
        notify();                            // critical	// section
        return (n);
    }

}
    class Producer implements Runnable{	// Thread for Producer process
        Q  q;
        Producer ( Q q ) 	{  	// constructor
            this.q =q;
            new Thread (this).start ( ) ;		// Producer process is started
        }

        public void run( ) { 		// infinite running  thread for Producer
            int i = 0;
            while (i!=5)
                q.put ( i++ );
        }
    }

     class Consumer implements Runnable { 	// Thread for consumer process
        Q q;
        Consumer (Q q )	{ 	          // Constructor
            this.q  = q;
            new Thread (this).start ( );
        }

        public void run( ) {		// infinite running thread for Consumer
            while (q.get()!=5)
                q.get ( );
        }
    }
    class PandC  {
        public static void main( String args[ ] ) {
            Q q = new Q( );		// an instance of parallel processes  is created
            new Producer(q) ;			// Run the thread for producer
            new Consumer (q);			// Run consumer thread
        }
    }


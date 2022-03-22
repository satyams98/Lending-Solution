class MovieApp {
    int total_seats = 10;

    synchronized void bookSeats(int seats){

        if(total_seats>=seats){
            System.out.println(seats+" Seats Booked");
            total_seats-=seats;
            System.out.println(total_seats+" seats left");
        }
        else {
            System.out.println("Seats not booked");
            System.out.println("Only "+total_seats+" left");
        }

    }

}

class MovieAppThread extends Thread {
    static MovieApp m;
    int seats;


    public void run() {
        m.bookSeats(seats);
    }

    public static void main(String[] args) {
        m = new MovieApp();
        MovieAppThread satyam = new MovieAppThread();
        satyam.seats = 7;
        satyam.start();
        MovieAppThread ayushman = new MovieAppThread();
        ayushman.seats = 4;
        ayushman.start();

    }
}


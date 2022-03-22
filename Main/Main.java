package Main;
import Bank.*;
import Customer.*;
import Random.RandomClass;
import LoanCalculations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<LoanAgreement> randomLoanAgreements = Collections.synchronizedList( new ArrayList <>());

        ThreadGroup generateThreads = new ThreadGroup("Generating Threads");
        Thread t1 = new Thread(generateThreads, ()->{
           RandomClass.generateLoanAgreement(5000)
                   .forEach(la->{
                       randomLoanAgreements.add(la);
                   });
        });

        Thread t2 = new Thread(generateThreads, ()->{
            RandomClass.generateLoanAgreement(5000)
                    .forEach(la->{
                        randomLoanAgreements.add(la);
                    });
        });


        Thread t3 = new Thread(()->{
            randomLoanAgreements.parallelStream()
                    .forEach(la->{
                        la.emiPerMonth = la.calculateEMI(la.loanAmount,
                                la.roi, la.tenure);
                    });
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t3.start();

        //printing some loanAgreements

        randomLoanAgreements.stream()
                .limit(10)
                .forEach(la->{
                    System.out.println("ID: "+la.loanAgreementId+" Date: "+
                            la.loanDisbursalDate+" Amount: "+
                            +la.loanAmount+
                            "Tenure: "+la.tenure+" EMI Calculated: "+la.emiPerMonth);
                });
    }

}

package Bank;

import Customer.*;
import LoanCalculations.PIComponent;
import Random.RandomClass;

import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class Bank implements Maker, Operator, Checker {

    public static void main(String[] args) {
        Customer c=new Customer();
        Bank bank = new Bank();
        bank.registerCustomer(c);
        HomeLoan l = new HomeLoan();
        LoanAgreement la = new LoanAgreement();
        la.loanProduct=l;
        c.loanAgreements.add(la);

    }


    class IterateCustomerObjects implements MyIterator { //Member Inner Class
        Iterator<Customer> it = customers.iterator();

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }
        @Override
        public Object next() {
            return it.next();

        }
        @Override
        public boolean remove() {
            it.remove();
            return true;
        }
    }

    //static nested class
    static class StaticIterateCustomerObjects implements MyIterator {
        Iterator<Customer> it = customers.iterator();
        public boolean hasNext() {
            return it.hasNext();
        }

        public Object next() {
            return it.next();
        }

        public boolean remove() {
            it.remove();
            return true;
        }
    }
//Anonymous Inner Class

     MyIterator customerIterator = new MyIterator (){
        Iterator<Customer> it = customers.iterator();
        @Override
        public boolean hasNext() {
            return it.hasNext();

        }
        @Override
        public Object next() {
           return it.next();
        }
        @Override
        public boolean remove() {
            it.remove();
            return true;
        }
    };

    static List<LoanProduct> loanProducts = new ArrayList<LoanProduct>();
    static List<Customer> customers = new ArrayList<Customer>();
    static List<LoanAgreement> loanAgreements  = new ArrayList<LoanAgreement>();
    static HashMap<LoanProduct, Customer> customerLoan = new HashMap<LoanProduct, Customer>();

    public boolean registerCustomer(Customer customer) {
        customers.add(customer);
        return true;
    }

    public Customer findCustomer(String customerId) {
            for (Customer c:customers){
                if(c.customerId==customerId){
                    return c;
                }
            }
        return null;
    }

    public void printAllCustomers() {

        customers.forEach(c-> System.out.println(c.customerName));
    }

    public boolean findCustomer(Customer customer) {

        return customers.contains(customer);
    }


    public boolean deleteCustomer(String customerId) {
            for (Customer c: customers){
                if(c.customerId==customerId){
                    return customers.remove(c);
                }
            }
        return false;
    }

    public boolean addNewLoanProduct(LoanProduct loanProduct) {
        return loanProducts.add(loanProduct);

    }
    public boolean addNewLoanProduct(HomeLoan loanProduct){
        return loanProducts.add(loanProduct);
    }
    public boolean addNewLoanProduct(EducationLoan loanProduct){
        return loanProducts.add(loanProduct);
    }
    public boolean addNewLoanProduct(ConsumerVehicleLoan loanProduct){
        return loanProducts.add(loanProduct);
    }

    public boolean removeLoanProduct(String loanProductCode) {
        for (LoanProduct l : loanProducts){
            if(l.loanProductCode==loanProductCode){
                return loanProducts.remove(l);
            }

        }
        return false;
    }

    public String applyLoan(String customerId, double loanAmount, double roi,
                            double tenure, TypeOfLoan typeOfLoan, RepayFrequency repaymentFrequency){

        if (findCustomer(customerId)!=null){
            LoanAgreement l = new LoanAgreement();
            l.loanAmount = loanAmount;
            l.roi = roi;
            l.tenure = tenure;
            l.repaymentFrequency = repaymentFrequency;
            l.typeOfLoan = typeOfLoan;
            l.loanAgreementId = RandomClass.generateRandomAlphaNumeric(6);
            loanAgreements.add(l);
            findCustomer(customerId).loanAgreements.add(l);
            return l.loanAgreementId;
        }
        System.out.println("Customer do not exists!");
        return null;
    }

    public String trackLoanStatus(String loanAgreementId){
        for(LoanAgreement la: loanAgreements){
            if(la.loanAgreementId.equals(loanAgreementId)){
                return la.loanStatus.toString();
            }
        }
        return "Invalid";
    }

    @Override
    public void loanDisbursal(String loanAgreementId) {

        for(LoanAgreement la: loanAgreements){
            if(la.loanAgreementId.equals(loanAgreementId)){
                la.loanStatus = LoanStatus.ACTIVE;
                la.loanDisbursalDate = LocalDate.now();
                la.emiPerMonth = la.calculateEMI(la.loanAmount, la.roi, la.tenure);
                RepaymentSchedule r1 = new RepaymentSchedule();
                r1.emiAmount = la.emiPerMonth;
                r1.emiDueDate = LocalDate.now().plusDays(30);
                r1.balancePrincipalComponent = la.loanAmount;
                r1.status = EMIPaidStatus.OVERDUE;
                r1.penaltyCharges = 0;
                PIComponent pic = new PIComponent();
                r1.principalComponent = pic.getPrincipalComponent(la.loanAmount,
                        la.roi, la.tenure);
                r1.interestAmount = pic.getInterestComponent(la.loanAmount, la.roi);
                la.repaymentSchedules.put(LocalDate.now().getMonthValue(), r1);
            }
            else {
                System.out.println("Loan Agreement id does not exists!");
            }
        }
    }

    public double calculateLatePenalty(String loanAgreementId){
            int totalPenalty = 0;
            double penaltyInterest=7;
            for(LoanAgreement la:loanAgreements){
                if(la.loanAgreementId.equals(loanAgreementId)){
                    for(Map.Entry e: la.repaymentSchedules.entrySet()){
                        RepaymentSchedule r = (RepaymentSchedule) e.getValue();
                        if(r.status.equals(EMIPaidStatus.OVERDUE)){
                            long noOfDays = DAYS.between(r.emiDueDate, LocalDate.now());
                            totalPenalty+=noOfDays * r.emiAmount * penaltyInterest *0.01;
                        }
                    }
                }
                return totalPenalty;
            }
        System.out.println("Invalid LoanAgreementId!");
            return -1;
    }

    public String getLoanDetails(String loanAgreementId){
        String details="";
        for(LoanAgreement la: loanAgreements){
            if(la.loanAgreementId.equals(loanAgreementId)){
                details+=la.loanAgreementId+" "+la.loanStatus+" "
                        +la.typeOfLoan+" "+la.roi+" "+la.tenure+" ";
            }
            return details;
        }
        System.out.println("Invalid LoanAgreementId!");
        return details;

    }

    public List<String> getAllActiveLoanDetails(){
        List<String> details = new ArrayList<>();
            for (LoanAgreement la:loanAgreements){
                if(la.loanStatus.equals(LoanStatus.ACTIVE)){
                   String temp=la.loanAgreementId+" "+la.loanStatus+" "
                            +la.typeOfLoan+" "+la.roi+" "+la.tenure+" ";
                   details.add(temp);
                }
            }
            return details;
    }

    public void printAllLoanProducts() {
        loanProducts.forEach(l-> System.out.println(l.loanProductCode+" "+l.loanProductName));

    }

    public void printLoanProductDetails(String loanProductId) {
        loanProducts.forEach(l-> {if (l.loanProductId==loanProductId) {
            System.out.println(l.loanProductCode+" "+l.loanProductId+" "+l.loanProductName);
        }});
    }

    public void calculateLTVForLoanProducts(LoanProduct loanProduct, double propertyValue) {
        loanProduct.LTVCalculationAsPerCollateralType(propertyValue);
    }
}

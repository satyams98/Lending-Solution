package Random;

import Bank.LoanProduct;
import Customer.Customer;
import Customer.LoanAgreement;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomClass {


    static public synchronized String generateRandomString(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }
    static public synchronized String generateRandomAlphaNumeric(int length){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static int generateRandomInt(int rangeMin, int rangeMax) {
        return ThreadLocalRandom.current().nextInt(rangeMin, rangeMax);
    }

    public static double generateRandomDouble(int rangeMin, int rangeMax) {
        return ThreadLocalRandom.current().nextDouble(rangeMin, rangeMax);
    }

    public static String generateStatus(){
        String[] arr = {"Active", "Closed"};
        return arr[generateRandomInt(0,1)];
    }

    public static LocalDate generateRandomDate() {
        long minDay = LocalDate.of(1998, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2022, 2, 28).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate;
    }

    public static List<Customer> generateCustomer(int num) {

        return Stream.iterate(0, n -> n + 1)
                .limit(num)
                .map(x -> {
                    Customer c = new Customer();
                    c.customerId = String.valueOf(c.hashCode());
                    c.monthlyIncome = generateRandomDouble(30000, 1000000);
                    c.customerName = generateRandomString(8);
                    c.dateOfBirth = generateRandomDate();
                    c.contactNumber = String.valueOf(generateRandomInt(9000, 900000000));
                    c.companyNames = generateRandomString(5);
                    c.totalMonthlyExpenses = generateRandomDouble(10000, (int) c.monthlyIncome-5000);
                    c.loanAgreements.add(generateLoanAgreement(1).get(0));
                    return c;
                }).collect(Collectors.toList());

    }
    public static List<LoanProduct> generateLoanProduct(int num){

        return Stream.iterate(0, n->n+1)
                .limit(num)
                .map(x -> {
                    LoanProduct lp =new LoanProduct();
                    lp.maxLoanAmount = 10000000;
                    lp.loanProductCode = generateRandomAlphaNumeric(8);
                    lp.minLoanAmount = 100000;
                    lp.emiStatus = generateStatus();
                    lp.roi = 8;
                    lp.maxTenure = 10;
                    lp.minTenure=1;
                    return lp;
                })
                .collect(Collectors.toList());
    }

    public static synchronized List <LoanAgreement> generateLoanAgreement(int num){

        return Stream.iterate(0, n->n+1)
                .limit(num)
                .map(x->{
                    LoanAgreement l = new LoanAgreement();
//                    l.loanAgreementId = String.valueOf(l.hashCode());
                    l.tenure = generateRandomInt(1, 15);
                    l.loanAmount = generateRandomDouble(10000, 10000000);
                    l.loanProduct = generateLoanProduct(1).get(0);
                    l.roi = generateRandomDouble(8,13);
                    l.loanDisbursalDate = generateRandomDate();
                    return l;
                })
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        int random = generateRandomInt(200,300);
        System.out.println(random);
    }
}

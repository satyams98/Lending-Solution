package Bank;

import Customer.Customer;

public interface Maker {
    boolean registerCustomer(Customer customer);

    boolean deleteCustomer(int customerId);

    boolean addNewLoanProduct(LoanProduct loanProduct);

    boolean removeLoanProduct(String loanProductCode);
}

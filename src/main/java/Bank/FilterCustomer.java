package Bank;

import Customer.Customer;

@FunctionalInterface
public interface FilterCustomer  {
    public boolean test(Customer c);

}

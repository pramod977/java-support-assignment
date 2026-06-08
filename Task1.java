import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public List<LoanAccount> getOverdueLoans(List<LoanAccount> accounts) {

    // FIX: Initialize result list to avoid NullPointerException
    List<LoanAccount> result = new ArrayList<>();

    // FIX: Handle null input list safely
    if (accounts == null) {
        return result;
    }

    Date today = new Date();

    for (LoanAccount account : accounts) {

        // FIX: Skip null account objects in the list
        if (account == null) {
            continue;
        }

        // FIX: dueDate may be null for restructured accounts
        Date dueDate = account.getDueDate();

        if (dueDate != null && dueDate.before(today)) {

            // FIX: Exclude accounts with zero or negative outstanding balance
            if (account.getOutstandingBalance() > 0) {
                result.add(account);
            }
        }
    }

    return result;
}

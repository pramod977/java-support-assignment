A PSU bank client reported the following error intermittently at peak load. Answer the three
questions below the trace in ANALYSIS.md or as comments in Task2Analysis.java.

ERROR 2024-04-10 09:14:33 [http-nio-8080-exec-23]
c.s.dlp.service.StatementProcessorService - Processing failed
java.util.ConcurrentModificationException
at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:911)
at java.util.ArrayList$Itr.next(ArrayList.java:861)
at c.s.dlp.service.StatementProcessorService
.filterTransactions(StatementProcessorService.java:142)
at c.s.dlp.service.StatementProcessorService
.processStatement(StatementProcessorService.java:98)
at c.s.dlp.controller.StatementController
.upload(StatementController.java:67)

Q1.  What is the exact cause of ConcurrentModificationException in Java?

ConcurrentModificationException occurs when a collection (such as an ArrayList) 
is structurally modified while it is being iterated using an iterator or enhanced for-loop. 
The iterator detects that the collection has changed unexpectedly and throws the exception.



Q2.What code pattern at line 142 most likely triggered this error?
The stack trace shows ArrayList$Itr.next() and ConcurrentModificationException, 
which indicates that the code was iterating over an ArrayList and modifying the same list at the same time.

Most likely pattern at line 142:
for (Transaction tx : transactions) {
    if (shouldRemove(tx)) {
        transactions.remove(tx);
    }
}


Q3.Provide the minimal code change (one or two lines) that resolves this safely.

Use the iterator’s remove() method instead of modifying the ArrayList directly while iterating.
Iterator<Transaction> it = transactions.iterator();
...
it.remove();

Explanation:
The exception occurs because the list is being modified during iteration. 
Using Iterator.remove() updates the iterator correctly and avoids ConcurrentModificationException.

package org.transactions.persistence.factories;

import org.model.transactions.Transaction;
import org.model.transactions.TransactionDetails;
import org.springframework.stereotype.Component;
import org.transactions.persistence.model.TransactionES;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransactionESFactory {

    /**
     * @return a list of transactions build for ES platform
     */
    public List<TransactionES> buildTransactionESList(List<Transaction> transactions) {
       return transactions.stream()
               .map(transaction -> buildTransactionES(transaction))
               .flatMap(List::stream)
               .collect(Collectors.toList());
    }

    /**
     * Build a list of TransactionES object from a Transaction object
     * This list can contain 1 or several TransactionsES object
     *
     * @param transaction : original transaction to convert
     * @return a list of TransactionES object for ES platform
     */
    private List<TransactionES> buildTransactionES(Transaction transaction) {

        return transaction.getTransactions().stream()
                .map(detail -> buildTransactionES(detail, transaction))
                .collect(Collectors.toList());

    }

    /**
     * Build a TransactionES object from TransactionDetails object
     * @param detail : original detail data
     * @param transaction : original transaction data
     * @return a transaction for ES platform
     */
    private TransactionES buildTransactionES(TransactionDetails detail, Transaction transaction) {
        TransactionES result = new TransactionES();

        result.setId(transaction.getId() + "-" + UUID.randomUUID());
        result.setDateTime(transaction.getDate());

        if (transaction.getDate() != null) {
            result.setDateTimeFormatted(transaction.getDate().format(DateTimeFormatter.ISO_DATE_TIME));
        }

        result.setCategory(detail.getCategory());
        result.setIncome(detail.getIncome());
        result.setOutcome(detail.getOutcome());
        result.setFrom(detail.getBankAccount());

        result.setCost((long) (Math.round(detail.getIncome() * 100.0) - Math.round(detail.getOutcome() * 100.0)));
        result.setCostAbs(Math.abs(result.getCost()));
        result.setCostDecimal(detail.getIncome() - detail.getOutcome());
        result.setCostAbsDecimal(Math.abs(result.getCostDecimal()));
        result.setDescription(transaction.getDescription());

        return result;
    }


}

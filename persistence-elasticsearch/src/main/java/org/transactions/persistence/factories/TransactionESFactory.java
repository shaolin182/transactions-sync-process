package org.transactions.persistence.factories;

import org.model.transactions.Transaction;
import org.model.transactions.TransactionDetails;
import org.springframework.stereotype.Component;
import org.transactions.persistence.model.TransactionES;

import java.util.List;
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

        result.setId(transaction.getId());
        result.setDateTime(transaction.getDate());

        result.setCategory(detail.getCategory());
        result.setIncome(detail.getIncome());
        result.setOutcome(detail.getOutcome());
        result.setFrom(detail.getBankAccount());

        result.setCost((long) (detail.getIncome() * 100 - detail.getOutcome() * 100));
        result.setCostAbs(Math.abs(result.getCost()));
        result.setCostDecimal(detail.getIncome() - detail.getOutcome());
        result.setCostAbsDecimal(Math.abs(result.getCostDecimal()));
        result.setDescription(transaction.getDescription());

        return result;
    }


}

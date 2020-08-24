package org.transactions.sync.connector;

import org.model.transactions.Transaction;

import java.util.List;

public interface ITransactionsAggregateDatasource {

    void publishData(List<Transaction> transactions);

    void resetData();
}

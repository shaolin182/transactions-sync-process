package org.transactions.persistence;

import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.model.transactions.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.transactions.persistence.config.ElasticSearchDatabaseConfig;
import org.transactions.persistence.factories.TransactionESFactory;
import org.transactions.persistence.model.TransactionES;
import org.transactions.persistence.repositories.TransactionAggregateRepository;
import org.transactions.sync.connector.ITransactionsAggregateDatasource;

import java.io.IOException;
import java.util.List;

@Component
public class TransactionAggregateDatasource implements ITransactionsAggregateDatasource {

    private TransactionAggregateRepository repository;

    private RestHighLevelClient clientES;

    private TransactionESFactory factory;

    private ElasticSearchDatabaseConfig esConfig;

    @Autowired
    public TransactionAggregateDatasource(TransactionESFactory factory, TransactionAggregateRepository repository, RestHighLevelClient clientES, ElasticSearchDatabaseConfig esConfig){
        this.factory = factory;
        this.repository = repository;
        this.esConfig = esConfig;
        this.clientES = clientES;

    }

    @Override
    public void publishData(List<Transaction> transactions) {

        // Transform data from mongodb datasource into elastic search datasource format
        List<TransactionES> transactionsToPublish = factory.buildTransactionESList(transactions);

        // publish them to ES
        repository.saveAll(transactionsToPublish);
    }

    @Override
    public void resetData() {

        // delete indices in ES
        DeleteIndexRequest deleteIndexRequest = Requests.deleteIndexRequest(esConfig.getIndex());

        try {
            clientES.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        } catch (ElasticsearchStatusException | IOException e) {
            // If an error occurred while deleting index, do nothing
//            throw new ESException("An error occurred while deleting index in Elastic search instance", e);
        }
    }
}

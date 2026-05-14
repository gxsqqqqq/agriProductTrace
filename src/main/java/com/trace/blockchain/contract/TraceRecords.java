package com.trace.blockchain.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.codec.datatypes.Event;
import org.fisco.bcos.sdk.v3.codec.datatypes.Function;
import org.fisco.bcos.sdk.v3.codec.datatypes.Type;
import org.fisco.bcos.sdk.v3.codec.datatypes.TypeReference;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Int256;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.v3.contract.Contract;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.eventsub.EventSubCallback;
import org.fisco.bcos.sdk.v3.model.CryptoType;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class TraceRecords extends Contract {
    public static final String[] BINARY_ARRAY = {""};
    public static final String BINARY = "";
    public static final String[] SM_BINARY_ARRAY = {""};
    public static final String SM_BINARY = "";
    public static final String[] ABI_ARRAY = {"[]"};
    public static final String ABI = "[]";

    public static final String FUNC_INSERT = "insert";
    public static final String FUNC_REMOVE = "remove";
    public static final String FUNC_SELECT = "select";
    public static final String FUNC_UPDATE = "update";

    public static final Event CREATERESULT_EVENT = new Event("CreateResult",
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    public static final Event INSERTRESULT_EVENT = new Event("InsertResult",
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    public static final Event REMOVERESULT_EVENT = new Event("RemoveResult",
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    public static final Event UPDATERESULT_EVENT = new Event("UpdateResult",
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));

    protected TraceRecords(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static String getABI() {
        return ABI;
    }

    public List<CreateResultEventResponse> getCreateResultEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(CREATERESULT_EVENT, transactionReceipt);
        ArrayList<CreateResultEventResponse> responses = new ArrayList<CreateResultEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            CreateResultEventResponse typedResponse = new CreateResultEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeCreateResultEvent(BigInteger fromBlock, BigInteger toBlock,
            List<String> otherTopics, EventSubCallback callback) {
        String topic0 = eventEncoder.encode(CREATERESULT_EVENT);
    }

    public void subscribeCreateResultEvent(EventSubCallback callback) {
        String topic0 = eventEncoder.encode(CREATERESULT_EVENT);
    }

    public List<InsertResultEventResponse> getInsertResultEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(INSERTRESULT_EVENT, transactionReceipt);
        ArrayList<InsertResultEventResponse> responses = new ArrayList<InsertResultEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            InsertResultEventResponse typedResponse = new InsertResultEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeInsertResultEvent(BigInteger fromBlock, BigInteger toBlock,
            List<String> otherTopics, EventSubCallback callback) {
        String topic0 = eventEncoder.encode(INSERTRESULT_EVENT);
    }

    public void subscribeInsertResultEvent(EventSubCallback callback) {
        String topic0 = eventEncoder.encode(INSERTRESULT_EVENT);
    }

    public List<RemoveResultEventResponse> getRemoveResultEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(REMOVERESULT_EVENT, transactionReceipt);
        ArrayList<RemoveResultEventResponse> responses = new ArrayList<RemoveResultEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RemoveResultEventResponse typedResponse = new RemoveResultEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeRemoveResultEvent(BigInteger fromBlock, BigInteger toBlock,
            List<String> otherTopics, EventSubCallback callback) {
        String topic0 = eventEncoder.encode(REMOVERESULT_EVENT);
    }

    public void subscribeRemoveResultEvent(EventSubCallback callback) {
        String topic0 = eventEncoder.encode(REMOVERESULT_EVENT);
    }

    public List<UpdateResultEventResponse> getUpdateResultEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(UPDATERESULT_EVENT, transactionReceipt);
        ArrayList<UpdateResultEventResponse> responses = new ArrayList<UpdateResultEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            UpdateResultEventResponse typedResponse = new UpdateResultEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeUpdateResultEvent(BigInteger fromBlock, BigInteger toBlock,
            List<String> otherTopics, EventSubCallback callback) {
        String topic0 = eventEncoder.encode(UPDATERESULT_EVENT);
    }

    public void subscribeUpdateResultEvent(EventSubCallback callback) {
        String topic0 = eventEncoder.encode(UPDATERESULT_EVENT);
    }

    public TransactionReceipt insert(String productId, String name, String dataJson) {
        final Function function = new Function(
                FUNC_INSERT,
                Arrays.<Type>asList(new Utf8String(productId),
                new Utf8String(name),
                new Utf8String(dataJson)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public Function getMethodInsertRawFunction(String productId, String name, String dataJson)
            throws ContractException {
        final Function function = new Function(FUNC_INSERT,
                Arrays.<Type>asList(new Utf8String(productId),
                new Utf8String(name),
                new Utf8String(dataJson)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return function;
    }

    public String getSignedTransactionForInsert(String productId, String name, String dataJson) {
        final Function function = new Function(
                FUNC_INSERT,
                Arrays.<Type>asList(new Utf8String(productId),
                new Utf8String(name),
                new Utf8String(dataJson)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String insert(String productId, String name, String dataJson,
            TransactionCallback callback) {
        final Function function = new Function(
                FUNC_INSERT,
                Arrays.<Type>asList(new Utf8String(productId),
                new Utf8String(name),
                new Utf8String(dataJson)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple3<String, String, String> getInsertInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_INSERT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<String, String, String>(
                (String) results.get(0).getValue(),
                (String) results.get(1).getValue(),
                (String) results.get(2).getValue()
        );
    }

    public Tuple1<BigInteger> getInsertOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_INSERT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(
                (BigInteger) results.get(0).getValue()
        );
    }

    public TransactionReceipt remove(String productId) {
        final Function function = new Function(
                FUNC_REMOVE,
                Arrays.<Type>asList(new Utf8String(productId)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public Function getMethodRemoveRawFunction(String productId) throws ContractException {
        final Function function = new Function(FUNC_REMOVE,
                Arrays.<Type>asList(new Utf8String(productId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return function;
    }

    public String getSignedTransactionForRemove(String productId) {
        final Function function = new Function(
                FUNC_REMOVE,
                Arrays.<Type>asList(new Utf8String(productId)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String remove(String productId, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_REMOVE,
                Arrays.<Type>asList(new Utf8String(productId)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple1<String> getRemoveInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_REMOVE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(
                (String) results.get(0).getValue()
        );
    }

    public Tuple1<BigInteger> getRemoveOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_REMOVE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(
                (BigInteger) results.get(0).getValue()
        );
    }

    public Tuple3<String, String, String> select(String productId) throws ContractException {
        final Function function = new Function(FUNC_SELECT,
                Arrays.<Type>asList(new Utf8String(productId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple3<String, String, String>(
                (String) results.get(0).getValue(),
                (String) results.get(1).getValue(),
                (String) results.get(2).getValue());
    }

    public Function getMethodSelectRawFunction(String productId) throws ContractException {
        final Function function = new Function(FUNC_SELECT,
                Arrays.<Type>asList(new Utf8String(productId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return function;
    }

    public TransactionReceipt update(String productId, String name, String dataJson) {
        final Function function = new Function(
                FUNC_UPDATE,
                Arrays.<Type>asList(new Utf8String(productId),
                new Utf8String(name),
                new Utf8String(dataJson)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public Function getMethodUpdateRawFunction(String productId, String name, String dataJson)
            throws ContractException {
        final Function function = new Function(FUNC_UPDATE,
                Arrays.<Type>asList(new Utf8String(productId),
                new Utf8String(name),
                new Utf8String(dataJson)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return function;
    }

    public String getSignedTransactionForUpdate(String productId, String name, String dataJson) {
        final Function function = new Function(
                FUNC_UPDATE,
                Arrays.<Type>asList(new Utf8String(productId),
                new Utf8String(name),
                new Utf8String(dataJson)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String update(String productId, String name, String dataJson,
            TransactionCallback callback) {
        final Function function = new Function(
                FUNC_UPDATE,
                Arrays.<Type>asList(new Utf8String(productId),
                new Utf8String(name),
                new Utf8String(dataJson)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple3<String, String, String> getUpdateInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_UPDATE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<String, String, String>(
                (String) results.get(0).getValue(),
                (String) results.get(1).getValue(),
                (String) results.get(2).getValue()
        );
    }

    public Tuple1<BigInteger> getUpdateOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_UPDATE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(
                (BigInteger) results.get(0).getValue()
        );
    }

    public static TraceRecords load(String contractAddress, Client client,
            CryptoKeyPair credential) {
        return new TraceRecords(contractAddress, client, credential);
    }

    public static TraceRecords deploy(Client client, CryptoKeyPair credential) throws
            ContractException {
        return deploy(TraceRecords.class, client, credential, getBinary(client.getCryptoSuite()), getABI(), null, null);
    }

    public static class CreateResultEventResponse {
        public TransactionReceipt.Logs log;
        public BigInteger count;
    }

    public static class InsertResultEventResponse {
        public TransactionReceipt.Logs log;
        public BigInteger count;
    }

    public static class RemoveResultEventResponse {
        public TransactionReceipt.Logs log;
        public BigInteger count;
    }

    public static class UpdateResultEventResponse {
        public TransactionReceipt.Logs log;
        public BigInteger count;
    }
}

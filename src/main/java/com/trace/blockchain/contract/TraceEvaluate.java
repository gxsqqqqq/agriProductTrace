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
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.v3.contract.Contract;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.eventsub.EventSubCallback;
import org.fisco.bcos.sdk.v3.model.CryptoType;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class TraceEvaluate extends Contract {
    public static final String[] BINARY_ARRAY = {""};
    public static final String BINARY = "";
    public static final String[] SM_BINARY_ARRAY = {""};
    public static final String SM_BINARY = "";
    public static final String[] ABI_ARRAY = {"[{\"inputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"int256\",\"name\":\"count\",\"type\":\"int256\"}],\"name\":\"SetEvent\",\"type\":\"event\"},{\"conflictFields\":[{\"kind\":0}],\"inputs\":[{\"internalType\":\"string\",\"name\":\"nick_name\",\"type\":\"string\"}],\"name\":\"get\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"},{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"}],\"selector\":[1765722206,2065403395],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":0}],\"inputs\":[{\"internalType\":\"string\",\"name\":\"nick_name\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"content\",\"type\":\"string\"}],\"name\":\"set\",\"outputs\":[{\"internalType\":\"int256\",\"name\":\"\",\"type\":\"int256\"}],\"selector\":[3913463062,439950516],\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]"};
    public static final String ABI = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_GET = "get";
    public static final String FUNC_SET = "set";

    public static final Event SETEVENT_EVENT = new Event("SetEvent",
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));

    protected TraceEvaluate(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static String getABI() {
        return ABI;
    }

    public List<SetEventEventResponse> getSetEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(SETEVENT_EVENT, transactionReceipt);
        ArrayList<SetEventEventResponse> responses = new ArrayList<SetEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            SetEventEventResponse typedResponse = new SetEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeSetEventEvent(BigInteger fromBlock, BigInteger toBlock,
            List<String> otherTopics, EventSubCallback callback) {
        String topic0 = eventEncoder.encode(SETEVENT_EVENT);
        subscribeEvent(topic0,otherTopics,fromBlock,toBlock,callback);
    }

    public void subscribeSetEventEvent(EventSubCallback callback) {
        String topic0 = eventEncoder.encode(SETEVENT_EVENT);
        subscribeEvent(topic0,callback);
    }

    public Tuple2<Boolean, String> get(String nick_name) throws ContractException {
        final Function function = new Function(FUNC_GET,
                Arrays.<Type>asList(new Utf8String(nick_name)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple2<Boolean, String>(
                Boolean.valueOf(results.get(0).getValue().toString()),
                (String) results.get(1).getValue());
    }

    public Function getMethodGetRawFunction(String nick_name) throws ContractException {
        final Function function = new Function(FUNC_GET,
                Arrays.<Type>asList(new Utf8String(nick_name)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return function;
    }

    public TransactionReceipt set(String nick_name, String content) {
        final Function function = new Function(
                FUNC_SET,
                Arrays.<Type>asList(new Utf8String(nick_name),
                new Utf8String(content)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public Function getMethodSetRawFunction(String nick_name, String content) throws
            ContractException {
        final Function function = new Function(FUNC_SET,
                Arrays.<Type>asList(new Utf8String(nick_name),
                new Utf8String(content)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return function;
    }

    public String getSignedTransactionForSet(String nick_name, String content) {
        final Function function = new Function(
                FUNC_SET,
                Arrays.<Type>asList(new Utf8String(nick_name),
                new Utf8String(content)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String set(String nick_name, String content, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SET,
                Arrays.<Type>asList(new Utf8String(nick_name),
                new Utf8String(content)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple2<String, String> getSetInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SET,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, String>(
                (String) results.get(0).getValue(),
                (String) results.get(1).getValue()
        );
    }

    public Tuple1<BigInteger> getSetOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SET,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(
                (BigInteger) results.get(0).getValue()
        );
    }

    public static TraceEvaluate load(String contractAddress, Client client,
            CryptoKeyPair credential) {
        return new TraceEvaluate(contractAddress, client, credential);
    }

    public static TraceEvaluate deploy(Client client, CryptoKeyPair credential) throws
            ContractException {
        return deploy(TraceEvaluate.class, client, credential, getBinary(client.getCryptoSuite()), getABI(), null, null);
    }

    public static class SetEventEventResponse {
        public TransactionReceipt.Logs log;
        public BigInteger count;
    }
}

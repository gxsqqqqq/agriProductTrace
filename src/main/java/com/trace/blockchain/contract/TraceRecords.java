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
    public static final String[] BINARY_ARRAY = {"60806040523480156200001157600080fd5b5060408051600280825260608201909252600091816020015b60608152602001906001900390816200002a579050509050604051806040016040528060048152602001636e616d6560e01b815250816000815181106200007557620000756200028a565b6020026020010181905250604051806040016040528060088152602001673230ba30a539b7b760c11b81525081600181518110620000b757620000b76200028a565b602090810291909101810191909152604080516080810182526009818301908152681c1c9bd91d58dd125960ba1b6060830152815280830184905281518083018352600681526574726163657360d01b9381019390935290516318d2d28f60e11b81529091611002916331a5a51e9162000136918590600401620002f0565b6020604051808303816000875af115801562000156573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906200017c91906200038d565b50604080518082018252600681526574726163657360d01b6020820152905163f23f63c960e01b81526000916110029163f23f63c991620001c091600401620003b9565b602060405180830381865afa158015620001de573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190620002049190620003ce565b90506001600160a01b038116620002615760405162461bcd60e51b815260206004820152601360248201527f637265617465207461626c65206661696c656400000000000000000000000000604482015260640160405180910390fd5b600080546001600160a01b0319166001600160a01b039290921691909117905550620003f99050565b634e487b7160e01b600052603260045260246000fd5b6000815180845260005b81811015620002c857602081850181015186830182015201620002aa565b81811115620002db576000602083870101525b50601f01601f19169290920160200192915050565b604081526000620003056040830185620002a0565b602083820381850152845160408352620003236040840182620002a0565b9050818601518382038385015281935080518083528383019450838160051b840101848301925060005b828110156200037f57601f198583030187526200036c828551620002a0565b968601969386019391506001016200034d565b509998505050505050505050565b600060208284031215620003a057600080fd5b81518060030b8114620003b257600080fd5b9392505050565b602081526000620003b26020830184620002a0565b[... 5497 chars omitted ...]

    public static final String BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"60806040523480156200001157600080fd5b5060408051600280825260608201909252600091816020015b60608152602001906001900390816200002a579050509050604051806040016040528060048152602001636e616d6560e01b815250816000815181106200007557620000756200028b565b6020026020010181905250604051806040016040528060088152602001673230ba30a539b7b760c11b81525081600181518110620000b757620000b76200028b565b602090810291909101810191909152604080516080810182526009818301908152681c1c9bd91d58dd125960ba1b6060830152815280830184905281518083018352600681526574726163657360d01b93810193909352905163656db23160e11b815290916110029163cadb64629162000136918590600401620002f1565b6020604051808303816000875af115801562000156573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906200017c91906200038e565b50604080518082018252600681526574726163657360d01b602082015290516359a48b6560e01b8152600091611002916359a48b6591620001c091600401620003ba565b602060405180830381865afa158015620001de573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190620002049190620003cf565b90506001600160a01b0381166200026257604051636381e58960e11b815260206004820152601360248201527f637265617465207461626c65206661696c656400000000000000000000000000604482015260640160405180910390fd5b600080546001600160a01b0319166001600160a01b039290921691909117905550620003fa9050565b63b95aa35560e01b600052603260045260246000fd5b6000815180845260005b81811015620002c957602081850181015186830182015201620002ab565b81811115620002dc576000602083870101525b50601f01601f19169290920160200192915050565b604081526000620003066040830185620002a1565b602083820381850152845160408352620003246040840182620002a1565b9050818601518382038385015281935080518083528383019450838160051b840101848301925060005b828110156200038057601f198583030187526200036d828551620002a1565b968601969386019391506001016200034e565b509998505050505050505050565b600060208284031215620003a157600080fd5b81518060030b8114620003b357600080fd5b9392505050565b602081526000620003b360208301846200002a[... 5500 chars omitted ...]

    public static final String SM_BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"inputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"int256\",\"name\":\"count\",\"type\":\"int256\"}],\"name\":\"CreateResult\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"int256\",\"name\":\"count\",\"type\":\"int256\"}],\"name\":\"InsertResult\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"int256\",\"name\":\"count\",\"type\":\"int256\"}],\"name\":\"RemoveResult\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"int256\",\"name\":\"count\",\"type\":\"int256\"}],\"name\":\"UpdateResult\",\"type\":\"event\"},{\"conflictFields\":[{\"kind\":0}],\"inputs\":[{\"internalType\":\"string\",\"name\":\"productId\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"name\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"dataJson\",\"type\":\"string\"}],\"name\":\"insert\",\"outputs\":[{\"internalType\":\"int256\",\"name\":\"\",\"type\":\"int256\"}],\"selector\":[803838940,388276610],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":0}],\"inputs\":[{\"internalType\":\"string\",\"name\":\"productId\",\"type\":\"string\"}],\"name\":\"remove\",\"outputs\":[{\"internalType\":\"int256\",\"name\":\"\",\"type\":\"int256\"}],\"selector\":[2153356875,2260153337],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":0}],\"inputs\":[{\"internalType\":\"string\",\"name\":\"productId\",\"type\":\"string\"}],\"name\":\"select\",\"outputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"}],\"selector\":[4242006977,1530027384],\"stateMutability\":\"view\",\"type\":\"function\"},{\"con[... 450 chars omitted ...]

    public static final String ABI = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_INSERT = "insert";

    public static final String FUNC_REMOVE = "remove";

    public static final String FUNC_SELECT = "select";

    public static final String FUNC_UPDATE = "update";

    public static final Event CREATERESULT_EVENT = new Event("CreateResult", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    public static final Event INSERTRESULT_EVENT = new Event("InsertResult", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    public static final Event REMOVERESULT_EVENT = new Event("RemoveResult", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    public static final Event UPDATERESULT_EVENT = new Event("UpdateResult", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

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

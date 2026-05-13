package com.trace.blockchain.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.codec.datatypes.Bool;
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
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50600080546001600160a01b031916611002908117909155604080518082018252600e81526d74726163655f6576616c7561746560901b6020820152905163b0e89adb60e01b815263b0e89adb9161006a916004016101b4565b6020604051808303816000875af1158015610089573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906100ad9190610214565b5060008054604080518082018252600e81526d74726163655f6576616c7561746560901b6020820152905163f23f63c960e01b81526001600160a01b039092169163f23f63c9916101009160040161023e565b602060405180830381865afa15801561011d573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906101419190610251565b600180546001600160a01b0319166001600160a01b03929092169190911790555061027a565b6000815180845260005b8181101561018d57602081850181015186830182015201610171565b8181111561019f576000602083870101525b50601f01601f19169290920160200192915050565b6060815260006101c76060830184610167565b82810380602085015260098252686e69636b5f6e616d6560b81b602083015260408101604085015250600760408201526618dbdb9d195b9d60ca1b60608201526080810191505092915050565b60006020828403121561022657600080fd5b81518060030b811461023757600080fd5b9392505050565b6020815260006102376020830184610167565b60006020828403121561026357600080fd5b81516001600160a01b038116811461023757600080fd5b6104df806102896000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c8063693ec85e1461003b578063e942b51614610065575b600080fd5b61004e610049366004610294565b610086565b60405161005c92919061032d565b60405180910390f35b610078610073366004610348565b610111565b60405190815260200161005c565b60015460405163349f642f60e11b8152600091606091839183916001600160a01b039091169063693ec85e906100c09088906004016103ac565b600060405180830381865afa1580156100dd573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405261010591908101906103c6565b90969095509350505050565b6001546040516374a15a8b60e11b815260009182916001600160a01b039091169063e942b5169061014890879087906004016104[... 1845 chars omitted ...]

    public static final String BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b50600080546001600160a01b031916611002908117909155604080518082018252600e81526d74726163655f6576616c7561746560901b602082015290516394558dab60e01b81526394558dab9161006a916004016101b4565b6020604051808303816000875af1158015610089573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906100ad9190610214565b5060008054604080518082018252600e81526d74726163655f6576616c7561746560901b602082015290516359a48b6560e01b81526001600160a01b03909216916359a48b65916101009160040161023e565b602060405180830381865afa15801561011d573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906101419190610251565b600180546001600160a01b0319166001600160a01b03929092169190911790555061027a565b6000815180845260005b8181101561018d57602081850181015186830182015201610171565b8181111561019f576000602083870101525b50601f01601f19169290920160200192915050565b6060815260006101c76060830184610167565b82810380602085015260098252686e69636b5f6e616d6560b81b602083015260408101604085015250600760408201526618dbdb9d195b9d60ca1b60608201526080810191505092915050565b60006020828403121561022657600080fd5b81518060030b811461023757600080fd5b9392505050565b6020815260006102376020830184610167565b60006020828403121561026357600080fd5b81516001600160a01b038116811461023757600080fd5b6104db806102896000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c80631a391cb41461003b5780637b1b8e0314610061575b600080fd5b61004e610049366004610290565b610082565b6040519081526020015b60405180910390f35b61007461006f3660046102f4565b610140565b60405161005892919061038d565b60015460405163068e472d60e21b815260009182916001600160a01b0390911690631a391cb4906100b990879087906004016103a8565b6020604051808303816000875af11580156100d8573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906100fc91906103d6565b604051600382900b81529091507fc044934b9c0dc8f2e96c0797246bde787cfafd3e8474b4ac3271c1d3ac7b819a9060200160405180910390a160030b9392505050565b6001546[... 1840 chars omitted ...]

    public static final String SM_BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"inputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"int256\",\"name\":\"count\",\"type\":\"int256\"}],\"name\":\"SetEvent\",\"type\":\"event\"},{\"conflictFields\":[{\"kind\":0}],\"inputs\":[{\"internalType\":\"string\",\"name\":\"nick_name\",\"type\":\"string\"}],\"name\":\"get\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"},{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"}],\"selector\":[1765722206,2065403395],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":0}],\"inputs\":[{\"internalType\":\"string\",\"name\":\"nick_name\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"content\",\"type\":\"string\"}],\"name\":\"set\",\"outputs\":[{\"internalType\":\"int256\",\"name\":\"\",\"type\":\"int256\"}],\"selector\":[3913463062,439950516],\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_GET = "get";

    public static final String FUNC_SET = "set";

    public static final Event SETEVENT_EVENT = new Event("SetEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

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
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple2<Boolean, String>(
                (Boolean) results.get(0).getValue(), 
                (String) results.get(1).getValue());
    }

    public Function getMethodGetRawFunction(String nick_name) throws ContractException {
        final Function function = new Function(FUNC_GET, 
                Arrays.<Type>asList(new Utf8String(nick_name)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
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

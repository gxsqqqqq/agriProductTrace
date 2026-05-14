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
public class UserAccount extends Contract {

    public static final String[] BINARY_ARRAY = {""};
    public static final String BINARY = "";
    public static final String[] SM_BINARY_ARRAY = {""};
    public static final String SM_BINARY = "";
    public static final String[] ABI_ARRAY = {"[]"};
    public static final String ABI = "[]";

    public static final String FUNC_GET = "get";
    public static final String FUNC_SET = "set";

    protected UserAccount(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static String getABI() {
        return ABI;
    }

    public Tuple2<Boolean, String> get(String tel) throws ContractException {
        final Function function = new Function(FUNC_GET,
                Arrays.<Type>asList(new Utf8String(tel)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple2<Boolean, String>(
                Boolean.valueOf(results.get(0).getValue().toString()),
                (String) results.get(1).getValue());
    }

    public TransactionReceipt set(String tel, String account) {
        final Function function = new Function(
                FUNC_SET,
                Arrays.<Type>asList(new Utf8String(tel), new Utf8String(account)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public static UserAccount load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new UserAccount(contractAddress, client, credential);
    }

    public static UserAccount deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(UserAccount.class, client, credential, getBinary(client.getCryptoSuite()), getABI(), null, null);
    }
}

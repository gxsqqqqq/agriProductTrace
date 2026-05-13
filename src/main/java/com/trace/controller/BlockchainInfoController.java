package com.trace.controller;

import com.trace.common.annotation.Anonymous;
import com.trace.common.core.domain.R;
import com.trace.common.utils.StringUtils;
import com.trace.common.utils.spring.SpringUtils;
import com.trace.blockchain.client.TraceEvaluateClient;
import com.trace.blockchain.client.UserAccountClient;
import com.trace.entity.*;
import com.trace.dto.RankDTO;
import com.trace.service.*;
import org.fisco.bcos.sdk.v3.BcosSDK;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.client.protocol.model.JsonTransactionResponse;
import org.fisco.bcos.sdk.v3.client.protocol.response.BcosBlock;
import org.fisco.bcos.sdk.v3.client.protocol.response.BcosGroupNodeInfo;
import org.fisco.bcos.sdk.v3.client.protocol.response.BcosTransaction;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blockchain")
public class BlockchainInfoController {

    @Autowired
    UserAccountClient userAccountClient;
    @Autowired
    TraceEvaluateClient traceEvaluateClient;
    @Autowired
    ITraceActivityRecordsService traceActivityRecordsService;
    @Autowired
    ITraceActivityService traceActivityService;
    @Autowired
    ITraceBatchService traceBatchService;
    @Autowired
    ITraceActivityService activityService;
    @Autowired
    ITraceWelfareService traceWelfareService;
    @Autowired
    ITraceEvaluateService traceEvaluateService;
    @Autowired
    ISysDictDataService dictDataService;
    @Autowired
    ITraceAlaramrecordsService traceAlaramrecordsService;
    @Autowired
    ITraceDataStructureService traceDataStructureService;
    @Autowired
    private ITraceProductListService traceProductListService;
    @Autowired
    ITraceTransService traceTransService;

    @Anonymous
    @GetMapping("/getIntegralBalance")
    public R getIntegralBalance(String tel) throws ContractException {
        Integer balance = userAccountClient.get(tel);
        if (balance == null) {
            return R.fail("账户不存在");
        }
        return R.ok(balance);
    }

    @Anonymous
    @GetMapping("/getTransList")
    public R getTransList() {
        List<TraceTrans> traceTrans = traceTransService.selectTraceTransList(new TraceTrans());
        return R.ok(traceTrans);
    }

    @Transactional(rollbackFor = Exception.class)
    @Anonymous
    @GetMapping("/integralTrans")
    public R integralTrans(String fromUser, String toUser, Integer amount) throws ContractException {
        if (amount <= 0) {
            return R.fail("转让积分不能小于0");
        }
        if (Objects.equals(fromUser, toUser)) {
            return R.fail("转让方和接收方不能一样");
        }
        Integer from = userAccountClient.get(fromUser);
        if (from == null) {
            return R.fail("转让方不存在");
        }
        Integer to = userAccountClient.get(toUser);
        if (to == null) {
            return R.fail("接收方不存在");
        }
        if (from < amount) {
            return R.fail("转让积分数量大于余额，不能转让");
        }

        // 添加接收方余额
        Integer toUserBalance = to + amount;
        userAccountClient.set(toUser, String.valueOf(toUserBalance));

        // 减少转让方余额
        Integer fromUserBalance = from - amount;
        TransactionReceipt receipt = userAccountClient.set(fromUser, String.valueOf(fromUserBalance));

        // 添加流水
        TraceTrans traceTrans = new TraceTrans();
        traceTrans.setAmount(Long.valueOf(amount));
        traceTrans.setFromUser(fromUser);
        traceTrans.setToUser(toUser);
        traceTrans.setBlockNumber(String.valueOf(receipt.getBlockNumber()));
        traceTrans.setBlockHash(receipt.getTransactionHash());
        traceTrans.setCreateTime(new Date());
        traceTransService.insertTraceTrans(traceTrans);

        return R.ok();
    }

    @Anonymous
    @GetMapping("/getBasicInfo")
    public R getBasicInfo() {
        Map<String, Integer> map = new HashMap<>();
        List<TraceProductList> traceProductLists = traceProductListService.selectTraceProductListList(new TraceProductList());
        map.put("productNum", traceProductLists.size());
        List<TraceBatch> traceBatchList = traceBatchService.selectTraceBatchList(new TraceBatch());
        map.put("batchNum", traceBatchList.size());
        List<TraceActivity> traceActivityList = traceActivityService.selectTraceActivityList(new TraceActivity());
        map.put("activityNum", traceActivityList.size());
        List<TraceWelfare> traceWelfareList = traceWelfareService.selectTraceWelfareList(new TraceWelfare());
        map.put("welfareNum", traceWelfareList.size());
        List<TraceEvaluate> traceEvaluateList = traceEvaluateService.selectTraceEvaluateList(new TraceEvaluate());
        map.put("evaluateNum", traceEvaluateList.size());
        return R.ok(map);
    }

    @Anonymous
    @GetMapping("/getDataStructure")
    public R getDataStructure() {
        List<TraceDataStructure> traceDataStructures = traceDataStructureService.selectTraceDataStructureList(new TraceDataStructure());
        Map<String, Long> collect = traceDataStructures.stream().collect(Collectors.groupingBy(TraceDataStructure::getNode, Collectors.counting()));
        return R.ok(collect);
    }

    @Anonymous
    @GetMapping("/getWelfareSts")
    public R getWelfareSts() {
        List<TraceWelfare> traceWelfares = traceWelfareService.selectTraceWelfareList(new TraceWelfare());
        List<String> namaList = traceWelfares.stream().map(TraceWelfare::getName).collect(Collectors.toList());
        List<Map> list = new ArrayList<>();
        traceWelfares.forEach(traceWelfare -> {
            Map<Object, Object> map = new HashMap<>();
            map.put("value", traceWelfare.getNum());
            map.put("name", traceWelfare.getName());
            list.add(map);
        });
        Map<String, Object> map = new HashMap<>();
        map.put("name", namaList);
        map.put("list", list);
        return R.ok(map);
    }

    @Anonymous
    @GetMapping("/getAlarmSts")
    public R getAlarmSts() {
        List<TraceAlaramrecords> traceAlaramrecords = traceAlaramrecordsService.selectTraceAlaramrecordsList(new TraceAlaramrecords());
        Map<String, Long> collect = traceAlaramrecords.stream().collect(Collectors.groupingBy(TraceAlaramrecords::getExt1, Collectors.counting()));
        List<String> nameList = new ArrayList<>();
        List<Long> numList = new ArrayList<>();
        collect.forEach((k, v) -> {
            nameList.add(k);
            numList.add(v);
        });
        Map<Object, Object> map = new HashMap<>();
        map.put("nameList", nameList);
        map.put("numList", numList);
        return R.ok(map);
    }

    @Anonymous
    @GetMapping("/getActivitySts")
    public R getActivitySts() {
        Map<Object, Object> map = new HashMap<>();
        List<TraceActivity> traceActivities = traceActivityService.selectTraceActivityList(new TraceActivity());
        List<String> activityNameList = traceActivities.stream().map(TraceActivity::getActivityName).collect(Collectors.toList());
        List<Long> activitiNumList = traceActivities.stream().map(TraceActivity::getNum).collect(Collectors.toList());
        map.put("activityNameList", activityNameList.subList(0, Math.min(3, activityNameList.size())));
        // 发放数量
        map.put("activityNumList", activitiNumList.subList(0, Math.min(3, activitiNumList.size())));

        List<Long> exchangeList = new ArrayList<>();
        List<TraceActivityRecords> traceActivityRecords = traceActivityRecordsService.selectTraceActivityRecordsList(new TraceActivityRecords());

        Map<Long, Long> activityCountMap = traceActivityRecords.stream()
                .collect(Collectors.groupingBy(TraceActivityRecords::getActivityId, Collectors.counting()));

        traceActivities.forEach(traceActivity -> {
            Long num = activityCountMap.get(traceActivity.getId());
            if (num == null) {
                num = 0L;
            }
            exchangeList.add(num);
        });

        // 领取数量
        map.put("exchangeList", exchangeList.subList(0, Math.min(3, exchangeList.size())));

        return R.ok(map);
    }

    @Anonymous
    @GetMapping("/getRank")
    public R getRank() {
        List<RankDTO> list = traceBatchService.selectTraceBatchListRank();
        return R.ok(list);
    }

    @Anonymous
    @GetMapping("/getEvaluateList")
    public R getEvaluateList() {
        TraceEvaluate traceEvaluate = new TraceEvaluate();
        List<TraceEvaluate> traceEvaluates = traceEvaluateService.selectTraceEvaluateList(traceEvaluate);
        return R.ok(traceEvaluates);
    }

    @Anonymous
    @GetMapping("/submitEvaluate")
    public R submitEvaluate(String content, String tel) throws ContractException {
        TraceEvaluate traceEvaluate = new TraceEvaluate();
        String batchNo = genBatchNo();
        traceEvaluate.setNickName(StringUtils.isNotEmpty(tel) && !StringUtils.equals(tel, "null") ? batchNo : tel);
        traceEvaluate.setContent(content);

        List<TraceEvaluate> traceEvaluates = traceEvaluateService.selectTraceEvaluateList(traceEvaluate);
        if (!traceEvaluates.isEmpty()) {
            return R.fail("很抱歉,你已经评论过!");
        }

        if (StringUtils.isNotEmpty(tel) && !StringUtils.equals(tel, "null")) {
            // 获取评价所获得的积分
            String userIntegral = dictDataService.selectDictLabel("user_integral", "积分");
            traceEvaluate.setExt1(userIntegral);
            Integer balance = userAccountClient.get(tel);
            if (balance == null) {
                return R.fail("账户不存在,不能发放积分");
            }
            // 积分运算后保存
            Integer newBalance = balance + Integer.parseInt(userIntegral);
            userAccountClient.set(tel, newBalance.toString());
        }

        // 数据上链
        TransactionReceipt receipt = traceEvaluateClient.set(batchNo, content);
        traceEvaluate.setBlockNumber(receipt.getBlockNumber().longValue());
        traceEvaluate.setBlockHash(receipt.getTransactionHash());
        traceEvaluateService.insertTraceEvaluate(traceEvaluate);

        return R.ok();
    }

    private String genBatchNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateStr = sdf.format(new Date());
        Random random = new Random();
        int randomNum = random.nextInt(100000);
        return dateStr + String.format("%05d", randomNum);
    }

    @Transactional
    @Anonymous
    @GetMapping("/welfare")
    public R welfare(Long id, String tel) throws ContractException {
        TraceWelfare traceWelfare = traceWelfareService.selectTraceWelfareById(id);
        if (null == traceWelfare) {
            return R.fail("福利不存在");
        }

        if (traceWelfare.getNum() < 1) {
            return R.fail("福利已经发放完");
        }

        Integer account = userAccountClient.get(tel);
        // 账户不存在，则直接添加，并设置积分
        if (account == null) {
            TransactionReceipt receipt = userAccountClient.set(tel, traceWelfare.getExt1());
            // 记录流水
        } else {
            // 账户存在，进行运算
            Integer newBalance = account + Integer.parseInt(traceWelfare.getExt1());
            userAccountClient.set(tel, String.valueOf(newBalance));
        }

        // 扣减福利库存
        Long newNum = traceWelfare.getNum() - 1;
        traceWelfare.setNum(newNum);
        traceWelfareService.updateTraceWelfare(traceWelfare);

        // 查询个人积分
        Integer myBalance = userAccountClient.get(tel);

        return R.ok(myBalance);
    }

    @Anonymous
    @GetMapping("/getWelfareList")
    public R getWelfareList(TraceWelfare traceWelfare) {
        return R.ok(traceWelfareService.selectTraceWelfareList(traceWelfare));
    }

    @Anonymous
    @GetMapping("/getActivityList")
    public R getActivityList(TraceActivity traceActivity) {
        List<TraceActivity> traceActivities = activityService.selectTraceActivityList(traceActivity);
        return R.ok(traceActivities);
    }

    @Anonymous
    @GetMapping("/getExchangeHistory")
    public R getExchangeHistory(String tel) {
        TraceActivityRecords records = new TraceActivityRecords();
        records.setTel(tel);
        List<TraceActivityRecords> traceActivityRecords = traceActivityRecordsService.selectTraceActivityRecordsList(records);
        List<TraceActivityRecords> recordsList = new ArrayList<>();
        traceActivityRecords.forEach(traceActivityRecord -> {
            TraceActivity traceActivity = traceActivityService.selectTraceActivityById(traceActivityRecord.getActivityId());
            if (null != traceActivity) {
                TraceActivityRecords activityRecords = new TraceActivityRecords();
                BeanUtils.copyProperties(traceActivityRecord, activityRecords);
                activityRecords.setActivityName(traceActivity.getActivityName());
                activityRecords.setProductName(traceActivity.getProductName());
                recordsList.add(activityRecords);
            }

        });
        return R.ok(recordsList);
    }

    @Anonymous
    @GetMapping("/getTraceInfo")
    public R getTraceInfo(String batchNo) {
        TraceBatch traceBatch = new TraceBatch();
        traceBatch.setBatchNo(batchNo);
        List<TraceBatch> list = traceBatchService.selectTraceBatchList(traceBatch);
        if (list.size() > 0) {
            return R.ok(list.get(0));
        }
        return R.ok();
    }

    @Anonymous
    @GetMapping("/exchange")
    @Transactional
    public R exchange(String tel, Long activityId) throws ContractException {
        Integer account = userAccountClient.get(tel);
        if (account == null) {
            return R.fail("账户不存在");
        }
        TraceActivity traceActivity = traceActivityService.selectTraceActivityById(activityId);
        if (null == traceActivity) {
            return R.fail("活动不存在");
        }
        Integer integral = Integer.parseInt(traceActivity.getIntegral());
        if (integral > account) {
            return R.fail("账户积分不够兑换");
        }

        if (traceActivity.getNum() < 1) {
            return R.fail("产品已兑换完, 请下次再参与");
        }

        Integer balance = account - integral;
        // 更新余额
        TransactionReceipt receipt = userAccountClient.set(tel, String.valueOf(balance));

        // 保存交易信息
        TraceActivityRecords records = new TraceActivityRecords();
        records.setActivityId(activityId);
        records.setTel(tel);
        records.setBlockHash(receipt.getTransactionHash());
        records.setExt1(receipt.getBlockNumber().toString());
        traceActivityRecordsService.insertTraceActivityRecords(records);

        // 扣减商品库存
        Long num = traceActivity.getNum();
        Long newNum = num - 1;
        traceActivity.setNum(newNum);
        traceActivityService.updateTraceActivity(traceActivity);

        return R.ok();
    }

    @Anonymous
    @GetMapping("/getBlockInfo")
    public R getBlockInfo() {
        BcosSDK bcosSDK = SpringUtils.getBean(BcosSDK.class);
        Client client = bcosSDK.getClient();
        Map<String, Object> blockMap = new HashMap<>();
        BigInteger blockNumber = client.getBlockNumber().getBlockNumber();
        blockMap.put("blockNumber", blockNumber);

        List<BcosGroupNodeInfo.GroupNodeInfo> nodeList = bcosSDK.getClient().getGroupInfo().getResult().getNodeList();
        blockMap.put("nodeList", nodeList);

        //列出十个最新区块
        List<Map<Object, Object>> blockList = new ArrayList<>();
        for (long i = 0; i < 10; i++) {
            BigInteger index = BigInteger.valueOf(blockNumber.intValue() - i);
            BcosBlock.Block block = client.getBlockByNumber(index, false, false).getBlock();
            List<BcosBlock.TransactionResult> transactions = client.getBlockByNumber(index, false, false).getBlock().getTransactions();
            Map<Object, Object> map = new HashMap<>();
            map.put("index", index);
            map.put("hash", block.getHash());
            map.put("transaction", transactions);
            map.put("parentHash", block.getParentInfo().get(0).getBlockHash());
            blockList.add(map);
        }
        Collections.reverse(blockList);
        blockMap.put("blockList", blockList);

        return R.ok(blockMap);
    }

    @Anonymous
    @GetMapping("/getBlockInfoByNumberOrHash")
    public R getBlockInfoByNumberOrHash(String numberOrHash) {
        BcosSDK bcosSDK = SpringUtils.getBean(BcosSDK.class);
        Client client = bcosSDK.getClient();
        BcosTransaction transaction;
        if (checkNumber(numberOrHash)) {
            BcosBlock.Block block = client.getBlockByNumber(BigInteger.valueOf(Long.parseLong(numberOrHash)), false, false).getBlock();
            String transactionsRoot = block.getTransactionsRoot();
            transaction = client.getTransaction(transactionsRoot, false);
        } else {
            transaction = client.getTransaction(numberOrHash, true);
        }

        Map<Object, Object> map = new HashMap<>();
        JsonTransactionResponse response = transaction.getTransaction().get();
        map.put("transactionHash", response.getHash());
        map.put("from", response.getFrom());
        map.put("to", response.getTo());

        return R.ok(map);
    }

    private static boolean checkNumber(String str) {
        return str.matches("\\d+");
    }
}

package com.trace.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSONObject;
import com.trace.common.utils.DateUtils;
import com.trace.common.utils.spring.SpringUtils;
import com.trace.blockchain.client.TraceRecordsClient;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.trace.common.annotation.Log;
import com.trace.common.core.controller.BaseController;
import com.trace.common.core.domain.AjaxResult;
import com.trace.common.enums.BusinessType;
import com.trace.entity.TraceBatch;
import com.trace.service.ITraceBatchService;
import com.trace.common.utils.poi.ExcelUtil;
import com.trace.common.core.page.TableDataInfo;

/**
 * 批次信息Controller
 *
 * 
 * @date 2024-10-13
 */
@RestController
@RequestMapping("/system/batch")
public class TraceBatchController extends BaseController {
    @Autowired
    private ITraceBatchService traceBatchService;

    @Autowired
    private TraceRecordsClient traceRecordsClient;

    @Value("${h5_view}")
    private String h5View;

    /**
     * 查询批次信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:batch:list')")
    @GetMapping("/list")
    public TableDataInfo list(TraceBatch traceBatch) {
        startPage();
        List<TraceBatch> list = traceBatchService.selectTraceBatchList(traceBatch);
        return getDataTable(list);
    }

    /**
     * 导出批次信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:batch:export')")
    @Log(title = "批次信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TraceBatch traceBatch) {
        List<TraceBatch> list = traceBatchService.selectTraceBatchList(traceBatch);
        ExcelUtil<TraceBatch> util = new ExcelUtil<TraceBatch>(TraceBatch.class);
        util.exportExcel(response, list, "批次信息数据");
    }

    /**
     * 获取批次信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:batch:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(traceBatchService.selectTraceBatchById(id));
    }

    /**
     * 新增批次信息
     */
    @PreAuthorize("@ss.hasPermi('system:batch:add')")
    @Log(title = "批次信息", businessType = BusinessType.INSERT)
    @Transactional
    @PostMapping
    public AjaxResult add(@RequestBody TraceBatch traceBatch) {
        Integer batchNum = traceBatch.getBatchNum();
        for (int i = 0; i < batchNum; i++) {
            TraceBatch batch = new TraceBatch();
            BeanUtils.copyProperties(traceBatch, batch);
            String batchNo = traceBatch.getBatchPrefix() + genBatchNo();
            batch.setBatchNo(batchNo);

            // 区块链使用
            TransactionReceipt receipt = traceRecordsClient.insert(batchNo, batch.getProductName(), traceBatch.getDataJson());
            batch.setBlockHash(receipt.getTransactionHash());
            batch.setExt1(receipt.getBlockNumber().toString());
            batch.setExt2(h5View+batchNo);
            //batch.setBlockData(JSONObject.toJSONString(receipt));

            traceBatchService.insertTraceBatch(batch);
        }
        return toAjax(true);
    }

    private String genBatchNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateStr = sdf.format(new Date());
        Random random = new Random();
        int randomNum = random.nextInt(100000);
        return dateStr + String.format("%05d", randomNum);
    }

    /**
     * 修改批次信息
     */
    @PreAuthorize("@ss.hasPermi('system:batch:edit')")
    @Log(title = "批次信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TraceBatch traceBatch) {
        return toAjax(traceBatchService.updateTraceBatch(traceBatch));
    }

    /**
     * 删除批次信息
     */
    @PreAuthorize("@ss.hasPermi('system:batch:remove')")
    @Log(title = "批次信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(traceBatchService.deleteTraceBatchByIds(ids));
    }
}

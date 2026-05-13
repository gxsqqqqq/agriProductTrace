package com.trace.controller;

import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.trace.common.utils.DateUtils;
import com.trace.entity.TraceAlaramrecords;
import com.trace.entity.TraceAlarm;
import com.trace.dto.AlaramRule;
import com.trace.dto.FiledValue;
import com.trace.service.ITraceAlaramrecordsService;
import com.trace.service.ITraceAlarmService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.trace.entity.TraceProducts;
import com.trace.service.ITraceProductsService;
import com.trace.common.utils.poi.ExcelUtil;
import com.trace.common.core.page.TableDataInfo;

/**
 * 农产品Controller
 *
 * 
 * @date 2024-10-09
 */
@RestController
@RequestMapping("/system/products")
public class TraceProductsController extends BaseController {
    @Autowired
    private ITraceProductsService traceProductsService;

    @Autowired
    private ITraceAlarmService traceAlarmService;

    @Autowired
    private ITraceAlaramrecordsService traceAlaramrecordsService;

    /**
     * 查询农产品列表
     */
    @PreAuthorize("@ss.hasPermi('system:products:list')")
    @GetMapping("/list")
    public TableDataInfo list(TraceProducts traceProducts) {
        startPage();
        List<TraceProducts> list = traceProductsService.selectTraceProductsList(traceProducts);
        return getDataTable(list);
    }

    /**
     * 导出农产品列表
     */
    @PreAuthorize("@ss.hasPermi('system:products:export')")
    @Log(title = "农产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TraceProducts traceProducts) {
        List<TraceProducts> list = traceProductsService.selectTraceProductsList(traceProducts);
        ExcelUtil<TraceProducts> util = new ExcelUtil<TraceProducts>(TraceProducts.class);
        util.exportExcel(response, list, "农产品数据");
    }

    /**
     * 获取农产品详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:products:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(traceProductsService.selectTraceProductsById(id));
    }

    /**
     * 新增农产品
     */
    @PreAuthorize("@ss.hasPermi('system:products:add')")
    @Log(title = "农产品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TraceProducts traceProducts) {

        // 告警处理
        Long productId = traceProducts.getProductId();
        String dataJson = traceProducts.getDataJson();
        List<FiledValue> filedValues = JSONArray.parseArray(dataJson, FiledValue.class);
        TraceAlarm traceAlarm = new TraceAlarm();
        traceAlarm.setProductId(productId);
        List<TraceAlarm> traceAlarms = traceAlarmService.selectTraceAlarmList(traceAlarm);
        if (traceAlarms.size() > 0) {
            TraceAlarm alarmRule = traceAlarms.get(0);
            String rules = alarmRule.getRules();
            List<AlaramRule> alaramRules = JSONArray.parseArray(rules, AlaramRule.class);
            alaramRules.forEach(alaramRule -> {
                filedValues.forEach(fieldValue -> {
                    if (fieldValue.getType() != null) {
                        // 非图片类型
                        if (!fieldValue.getType().equals("3")) {
                            if (alaramRule.getCode().equals(fieldValue.getCode())) {
                                String fieldValueValue = fieldValue.getValue();
                                String alaramRuleValue = alaramRule.getValue();
                                if (isNumber(fieldValueValue) && isNumber(alaramRuleValue)) {
                                    Float fileValueNumber = Float.valueOf(fieldValueValue);
                                    Float alaramValueNumber = Float.valueOf(alaramRuleValue);
                                    if (Objects.equals(alaramRule.getOperator(), "1")) {
                                        if (fileValueNumber > alaramValueNumber) {
                                            TraceAlaramrecords traceAlaramrecords = new TraceAlaramrecords();
                                            traceAlaramrecords.setProductId(productId);
                                            traceAlaramrecords.setExt1(traceProducts.getName());
                                            traceAlaramrecords.setExt2(alarmRule.getExt2());
                                            traceAlaramrecords.setRuleId(alarmRule.getId());
                                            traceAlaramrecords.setExt3(DateUtils.dateTimeNow(DateUtils.YYYY_MM_DD_HH_MM_SS));
                                            traceAlaramrecords.setRuleDetail("当前的"+fieldValue.getName()+"为["+fieldValueValue+"]," + "超过[" + alaramRuleValue + "]");
                                            traceAlaramrecordsService.insertTraceAlaramrecords(traceAlaramrecords);
                                        }
                                    }
                                    if (Objects.equals(alaramRule.getOperator(), "2")) {
                                        if (fileValueNumber < alaramValueNumber) {
                                            TraceAlaramrecords traceAlaramrecords = new TraceAlaramrecords();
                                            traceAlaramrecords.setProductId(productId);
                                            traceAlaramrecords.setExt1(traceProducts.getName());
                                            traceAlaramrecords.setExt2(alarmRule.getExt2());
                                            traceAlaramrecords.setRuleId(alarmRule.getId());
                                            traceAlaramrecords.setExt3(DateUtils.dateTimeNow(DateUtils.YYYY_MM_DD_HH_MM_SS));
                                            traceAlaramrecords.setRuleDetail("当前的"+fieldValue.getName()+"为["+fieldValueValue+"]," + "没有达到[" + alaramRuleValue + "]");
                                            traceAlaramrecordsService.insertTraceAlaramrecords(traceAlaramrecords);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            });
        }

        int inserted = traceProductsService.insertTraceProducts(traceProducts);
        return toAjax(inserted);
    }

    private static boolean isNumber(String str) {
        return str.matches("\\d+");
    }

    /**
     * 修改农产品
     */
    @PreAuthorize("@ss.hasPermi('system:products:edit')")
    @Log(title = "农产品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TraceProducts traceProducts) {
        return toAjax(traceProductsService.updateTraceProducts(traceProducts));
    }

    /**
     * 删除农产品
     */
    @PreAuthorize("@ss.hasPermi('system:products:remove')")
    @Log(title = "农产品", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(traceProductsService.deleteTraceProductsByIds(ids));
    }
}

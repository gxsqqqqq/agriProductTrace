package com.trace.controller;

import com.trace.common.annotation.Log;
import com.trace.common.core.controller.BaseController;
import com.trace.common.core.domain.AjaxResult;
import com.trace.common.core.page.TableDataInfo;
import com.trace.common.enums.BusinessType;
import com.trace.common.utils.poi.ExcelUtil;
import com.trace.entity.TraceAlarm;
import com.trace.service.ITraceAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 告警规则Controller
 * 
 * 
 * @date 2024-10-22
 */
@RestController
@RequestMapping("/system/alarm")
public class TraceAlarmController extends BaseController
{
    @Autowired
    private ITraceAlarmService traceAlarmService;

    /**
     * 查询告警规则列表
     */
    @PreAuthorize("@ss.hasPermi('system:alarm:list')")
    @GetMapping("/list")
    public TableDataInfo list(TraceAlarm traceAlarm)
    {
        startPage();
        List<TraceAlarm> list = traceAlarmService.selectTraceAlarmList(traceAlarm);
        return getDataTable(list);
    }

    /**
     * 导出告警规则列表
     */
    @PreAuthorize("@ss.hasPermi('system:alarm:export')")
    @Log(title = "告警规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TraceAlarm traceAlarm)
    {
        List<TraceAlarm> list = traceAlarmService.selectTraceAlarmList(traceAlarm);
        ExcelUtil<TraceAlarm> util = new ExcelUtil<TraceAlarm>(TraceAlarm.class);
        util.exportExcel(response, list, "告警规则数据");
    }

    /**
     * 获取告警规则详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:alarm:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(traceAlarmService.selectTraceAlarmById(id));
    }

    /**
     * 新增告警规则
     */
    @PreAuthorize("@ss.hasPermi('system:alarm:add')")
    @Log(title = "告警规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TraceAlarm traceAlarm)
    {
        return toAjax(traceAlarmService.insertTraceAlarm(traceAlarm));
    }

    /**
     * 修改告警规则
     */
    @PreAuthorize("@ss.hasPermi('system:alarm:edit')")
    @Log(title = "告警规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TraceAlarm traceAlarm)
    {
        return toAjax(traceAlarmService.updateTraceAlarm(traceAlarm));
    }

    /**
     * 删除告警规则
     */
    @PreAuthorize("@ss.hasPermi('system:alarm:remove')")
    @Log(title = "告警规则", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(traceAlarmService.deleteTraceAlarmByIds(ids));
    }
}

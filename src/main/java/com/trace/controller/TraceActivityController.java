package com.trace.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.trace.common.annotation.Anonymous;
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
import com.trace.entity.TraceActivity;
import com.trace.service.ITraceActivityService;
import com.trace.common.utils.poi.ExcelUtil;
import com.trace.common.core.page.TableDataInfo;

/**
 * 营销活动Controller
 * 
 * 
 * @date 2024-10-15
 */
@RestController
@RequestMapping("/system/activity")
public class TraceActivityController extends BaseController
{
    @Autowired
    private ITraceActivityService traceActivityService;

    /**
     * 查询营销活动列表
     */
    @PreAuthorize("@ss.hasPermi('system:activity:list')")
    @GetMapping("/list")
    public TableDataInfo list(TraceActivity traceActivity)
    {
        startPage();
        List<TraceActivity> list = traceActivityService.selectTraceActivityList(traceActivity);
        return getDataTable(list);
    }

    /**
     * 导出营销活动列表
     */
    @PreAuthorize("@ss.hasPermi('system:activity:export')")
    @Log(title = "营销活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TraceActivity traceActivity)
    {
        List<TraceActivity> list = traceActivityService.selectTraceActivityList(traceActivity);
        ExcelUtil<TraceActivity> util = new ExcelUtil<TraceActivity>(TraceActivity.class);
        util.exportExcel(response, list, "营销活动数据");
    }

    /**
     * 获取营销活动详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:activity:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(traceActivityService.selectTraceActivityById(id));
    }

    /**
     * 新增营销活动
     */
    @PreAuthorize("@ss.hasPermi('system:activity:add')")
    @Log(title = "营销活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TraceActivity traceActivity)
    {
        return toAjax(traceActivityService.insertTraceActivity(traceActivity));
    }

    /**
     * 修改营销活动
     */
    @PreAuthorize("@ss.hasPermi('system:activity:edit')")
    @Log(title = "营销活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TraceActivity traceActivity)
    {
        return toAjax(traceActivityService.updateTraceActivity(traceActivity));
    }

    /**
     * 删除营销活动
     */
    @PreAuthorize("@ss.hasPermi('system:activity:remove')")
    @Log(title = "营销活动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(traceActivityService.deleteTraceActivityByIds(ids));
    }
}

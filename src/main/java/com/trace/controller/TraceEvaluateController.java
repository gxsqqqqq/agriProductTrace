package com.trace.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.trace.entity.TraceEvaluate;
import com.trace.service.ITraceEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 消费者评价Controller
 * 
 * @author ruoyi
 * @date 2024-10-16
 */
@RestController
@RequestMapping("/system/evaluate")
public class TraceEvaluateController extends BaseController
{
    @Autowired
    private ITraceEvaluateService traceEvaluateService;

    /**
     * 查询消费者评价列表
     */
    @PreAuthorize("@ss.hasPermi('system:evaluate:list')")
    @GetMapping("/list")
    public TableDataInfo list(TraceEvaluate traceEvaluate)
    {
        startPage();
        List<TraceEvaluate> list = traceEvaluateService.selectTraceEvaluateList(traceEvaluate);
        return getDataTable(list);
    }

    /**
     * 导出消费者评价列表
     */
    @PreAuthorize("@ss.hasPermi('system:evaluate:export')")
    @Log(title = "消费者评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TraceEvaluate traceEvaluate)
    {
        List<TraceEvaluate> list = traceEvaluateService.selectTraceEvaluateList(traceEvaluate);
        ExcelUtil<TraceEvaluate> util = new ExcelUtil<TraceEvaluate>(TraceEvaluate.class);
        util.exportExcel(response, list, "消费者评价数据");
    }

    /**
     * 获取消费者评价详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:evaluate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(traceEvaluateService.selectTraceEvaluateById(id));
    }

    /**
     * 新增消费者评价
     */
    @PreAuthorize("@ss.hasPermi('system:evaluate:add')")
    @Log(title = "消费者评价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TraceEvaluate traceEvaluate)
    {
        return toAjax(traceEvaluateService.insertTraceEvaluate(traceEvaluate));
    }

    /**
     * 修改消费者评价
     */
    @PreAuthorize("@ss.hasPermi('system:evaluate:edit')")
    @Log(title = "消费者评价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TraceEvaluate traceEvaluate)
    {
        return toAjax(traceEvaluateService.updateTraceEvaluate(traceEvaluate));
    }

    /**
     * 删除消费者评价
     */
    @PreAuthorize("@ss.hasPermi('system:evaluate:remove')")
    @Log(title = "消费者评价", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(traceEvaluateService.deleteTraceEvaluateByIds(ids));
    }
}

package com.trace.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.trace.entity.TraceWelfare;
import com.trace.service.ITraceWelfareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 福利中心Controller
 * 
 * @author ruoyi
 * @date 2024-10-16
 */
@RestController
@RequestMapping("/system/welfare")
public class TraceWelfareController extends BaseController
{
    @Autowired
    private ITraceWelfareService traceWelfareService;

    /**
     * 查询福利中心列表
     */
    @PreAuthorize("@ss.hasPermi('system:welfare:list')")
    @GetMapping("/list")
    public TableDataInfo list(TraceWelfare traceWelfare)
    {
        startPage();
        List<TraceWelfare> list = traceWelfareService.selectTraceWelfareList(traceWelfare);
        return getDataTable(list);
    }

    /**
     * 导出福利中心列表
     */
    @PreAuthorize("@ss.hasPermi('system:welfare:export')")
    @Log(title = "福利中心", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TraceWelfare traceWelfare)
    {
        List<TraceWelfare> list = traceWelfareService.selectTraceWelfareList(traceWelfare);
        ExcelUtil<TraceWelfare> util = new ExcelUtil<TraceWelfare>(TraceWelfare.class);
        util.exportExcel(response, list, "福利中心数据");
    }

    /**
     * 获取福利中心详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:welfare:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(traceWelfareService.selectTraceWelfareById(id));
    }

    /**
     * 新增福利中心
     */
    @PreAuthorize("@ss.hasPermi('system:welfare:add')")
    @Log(title = "福利中心", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TraceWelfare traceWelfare)
    {
        return toAjax(traceWelfareService.insertTraceWelfare(traceWelfare));
    }

    /**
     * 修改福利中心
     */
    @PreAuthorize("@ss.hasPermi('system:welfare:edit')")
    @Log(title = "福利中心", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TraceWelfare traceWelfare)
    {
        return toAjax(traceWelfareService.updateTraceWelfare(traceWelfare));
    }

    /**
     * 删除福利中心
     */
    @PreAuthorize("@ss.hasPermi('system:welfare:remove')")
    @Log(title = "福利中心", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(traceWelfareService.deleteTraceWelfareByIds(ids));
    }
}

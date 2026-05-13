package com.trace.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.trace.entity.TraceTrans;
import com.trace.service.ITraceTransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 积分转让统计Controller
 * 
 * @author ruoyi
 * @date 2024-10-24
 */
@RestController
@RequestMapping("/system/trans")
public class TraceTransController extends BaseController
{
    @Autowired
    private ITraceTransService traceTransService;

    /**
     * 查询积分转让统计列表
     */
    @PreAuthorize("@ss.hasPermi('system:trans:list')")
    @GetMapping("/list")
    public TableDataInfo list(TraceTrans traceTrans)
    {
        startPage();
        List<TraceTrans> list = traceTransService.selectTraceTransList(traceTrans);
        return getDataTable(list);
    }

    /**
     * 导出积分转让统计列表
     */
    @PreAuthorize("@ss.hasPermi('system:trans:export')")
    @Log(title = "积分转让统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TraceTrans traceTrans)
    {
        List<TraceTrans> list = traceTransService.selectTraceTransList(traceTrans);
        ExcelUtil<TraceTrans> util = new ExcelUtil<TraceTrans>(TraceTrans.class);
        util.exportExcel(response, list, "积分转让统计数据");
    }

    /**
     * 获取积分转让统计详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:trans:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(traceTransService.selectTraceTransById(id));
    }

    /**
     * 新增积分转让统计
     */
    @PreAuthorize("@ss.hasPermi('system:trans:add')")
    @Log(title = "积分转让统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TraceTrans traceTrans)
    {
        return toAjax(traceTransService.insertTraceTrans(traceTrans));
    }

    /**
     * 修改积分转让统计
     */
    @PreAuthorize("@ss.hasPermi('system:trans:edit')")
    @Log(title = "积分转让统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TraceTrans traceTrans)
    {
        return toAjax(traceTransService.updateTraceTrans(traceTrans));
    }

    /**
     * 删除积分转让统计
     */
    @PreAuthorize("@ss.hasPermi('system:trans:remove')")
    @Log(title = "积分转让统计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(traceTransService.deleteTraceTransByIds(ids));
    }
}

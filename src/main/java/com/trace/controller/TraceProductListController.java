package com.trace.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.trace.entity.TraceProductList;
import com.trace.service.ITraceProductListService;
import com.trace.common.utils.poi.ExcelUtil;
import com.trace.common.core.page.TableDataInfo;

/**
 * 农产品管理Controller
 * 
 * 
 * @date 2024-10-13
 */
@RestController
@RequestMapping("/system/list")
public class TraceProductListController extends BaseController
{
    @Autowired
    private ITraceProductListService traceProductListService;

    /**
     * 查询农产品管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:list:list')")
    @GetMapping("/list")
    public TableDataInfo list(TraceProductList traceProductList)
    {
        startPage();
        List<TraceProductList> list = traceProductListService.selectTraceProductListList(traceProductList);
        return getDataTable(list);
    }

    /**
     * 导出农产品管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:list:export')")
    @Log(title = "农产品管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TraceProductList traceProductList)
    {
        List<TraceProductList> list = traceProductListService.selectTraceProductListList(traceProductList);
        ExcelUtil<TraceProductList> util = new ExcelUtil<TraceProductList>(TraceProductList.class);
        util.exportExcel(response, list, "农产品管理数据");
    }

    /**
     * 获取农产品管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:list:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(traceProductListService.selectTraceProductListById(id));
    }

    /**
     * 新增农产品管理
     */
    @PreAuthorize("@ss.hasPermi('system:list:add')")
    @Log(title = "农产品管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TraceProductList traceProductList)
    {
        return toAjax(traceProductListService.insertTraceProductList(traceProductList));
    }

    /**
     * 修改农产品管理
     */
    @PreAuthorize("@ss.hasPermi('system:list:edit')")
    @Log(title = "农产品管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TraceProductList traceProductList)
    {
        return toAjax(traceProductListService.updateTraceProductList(traceProductList));
    }

    /**
     * 删除农产品管理
     */
    @PreAuthorize("@ss.hasPermi('system:list:remove')")
    @Log(title = "农产品管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(traceProductListService.deleteTraceProductListByIds(ids));
    }
}

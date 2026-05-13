package com.trace.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSONArray;
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
import com.trace.entity.TraceDataStructure;
import com.trace.service.ITraceDataStructureService;
import com.trace.common.utils.poi.ExcelUtil;
import com.trace.common.core.page.TableDataInfo;

/**
 * 数据结构Controller
 *
 * 
 * @date 2024-10-07
 */
@RestController
@RequestMapping("/system/structure")
public class TraceDataStructureController extends BaseController
{
    @Autowired
    private ITraceDataStructureService traceDataStructureService;

    /**
     * 查询数据结构列表
     */
    @PreAuthorize("@ss.hasPermi('system:structure:list')")
    @GetMapping("/list")
    public TableDataInfo list(TraceDataStructure traceDataStructure)
    {
        startPage();
        List<TraceDataStructure> list = traceDataStructureService.selectTraceDataStructureList(traceDataStructure);
        return getDataTable(list);
    }

    /**
     * 导出数据结构列表
     */
    @PreAuthorize("@ss.hasPermi('system:structure:export')")
    @Log(title = "数据结构", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TraceDataStructure traceDataStructure)
    {
        List<TraceDataStructure> list = traceDataStructureService.selectTraceDataStructureList(traceDataStructure);
        ExcelUtil<TraceDataStructure> util = new ExcelUtil<TraceDataStructure>(TraceDataStructure.class);
        util.exportExcel(response, list, "数据结构数据");
    }

    /**
     * 获取数据结构详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:structure:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        TraceDataStructure traceDataStructure = traceDataStructureService.selectTraceDataStructureById(id);
        JSONArray jsonArray = JSONArray.parse(traceDataStructure.getDataJson());
        traceDataStructure.setDataJsonArray(jsonArray);
        return success(traceDataStructure);
    }

    /**
     * 新增数据结构
     */
    @PreAuthorize("@ss.hasPermi('system:structure:add')")
    @Log(title = "数据结构", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TraceDataStructure traceDataStructure)
    {
        return toAjax(traceDataStructureService.insertTraceDataStructure(traceDataStructure));
    }

    /**
     * 修改数据结构
     */
    @PreAuthorize("@ss.hasPermi('system:structure:edit')")
    @Log(title = "数据结构", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TraceDataStructure traceDataStructure)
    {
        return toAjax(traceDataStructureService.updateTraceDataStructure(traceDataStructure));
    }

    /**
     * 删除数据结构
     */
    @PreAuthorize("@ss.hasPermi('system:structure:remove')")
    @Log(title = "数据结构", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(traceDataStructureService.deleteTraceDataStructureByIds(ids));
    }
}
